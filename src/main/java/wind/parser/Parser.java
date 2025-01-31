package wind.parser;

import wind.command.*;
import wind.exception.InvalidCommandException;
import wind.storage.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param input The user input.
     * @param taskList The list of tasks.
     * @return The command corresponding to the user input.
     * @throws IllegalArgumentException If the input is invalid.
     * @throws InvalidCommandException If the command is invalid.
     */
    public static Command parse(String input, TaskList taskList) 
            throws IllegalArgumentException, InvalidCommandException {
        String[] words = input.split(" ");
        CommandEnum commandEnum = getCommandEnum(words[0]);
        
        switch (commandEnum) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return new ListCommand();
        case DELETE:
            CommandValidator.validateTaskNumber(words, taskList, "delete");
            int deleteIndex = Integer.parseInt(words[1]);
            assert deleteIndex >= 1 && deleteIndex <= taskList.getSize() 
                : "Task number must be within valid range: " + deleteIndex;
            return new DeleteCommand(deleteIndex);
        case MARK:
            CommandValidator.validateTaskNumber(words, taskList, "mark");
            int markIndex = Integer.parseInt(words[1]);
            assert markIndex >= 1 && markIndex <= taskList.getSize() 
                : "Task number must be within valid range: " + markIndex;
            return new MarkCommand(markIndex);
        case UNMARK:
            CommandValidator.validateTaskNumber(words, taskList, "unmark");
            int unmarkIndex = Integer.parseInt(words[1]);
            assert unmarkIndex >= 1 && unmarkIndex <= taskList.getSize() 
                : "Task number must be within valid range: " + unmarkIndex;
            return new UnmarkCommand(unmarkIndex);
        case TODO:
            CommandValidator.validateDescription(input, 6, "todo");
            return new TodoCommand(input.substring(5));
        case DEADLINE:
            CommandValidator.validateDescription(input, 9, "deadline");
            if (!input.contains(" /by ")) {
                throw new IllegalArgumentException(
                    "Please provide a valid deadline command.\n" +
                    "Correct format: deadline <description> /by <deadline>"
                );
            }
            String[] deadlineWords = input.substring(9).split(" /by ");
            if (deadlineWords.length < 2) {
                throw new IllegalArgumentException(
                    "Please provide a deadline for the deadline command.\n" +
                    "Correct format: deadline <description> /by <deadline>"
                );
            }
            CommandValidator.validateDateFormat(deadlineWords[1], "deadline");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadline = LocalDate.parse(deadlineWords[1], formatter);
            return new DeadlineCommand(deadlineWords[0], deadline);
        case EVENT:
            CommandValidator.validateDescription(input, 6, "event");
            CommandValidator.validateEventFormat(input, "event");
            String[] eventParts = input.substring(6).split(" /from | /to ");
            return new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
        case FIND:
            CommandValidator.validateDescription(input, 6, "find");
            return new FindCommand(input.substring(5));
        case SORT:
            CommandValidator.validateSortType(words, "sort");
            return new SortCommand(words[1].toLowerCase());
        default:
            String errorMessage = getInvalidCommandMessage();
            throw new InvalidCommandException(errorMessage);
        }
    }

    /**
     * Returns the error message for an invalid command.
     *
     * @return The error message for an invalid command.
     */
    private static String getInvalidCommandMessage() {
        String errorMessage = "I do not understand this command, please try again.";
        errorMessage += "\nHere are the possible commands:";
        errorMessage += "\n1. bye";
        errorMessage += "\n2. list";
        errorMessage += "\n3. mark <task number>";
        errorMessage += "\n4. unmark <task number>";
        errorMessage += "\n5. todo <description>";
        errorMessage += "\n6. deadline <description> /by <deadline> (yyyy-MM-dd)";
        errorMessage += "\n7. event <description> /from <start time> /to <end time>";
        errorMessage += "\n8. sort <type> (alpha or deadline)";
        return errorMessage;
    }

    /**
     * Returns the command enum corresponding to the command string.
     *
     * @param command The command string.
     * @return The command enum corresponding to the command string.
     */
    private static CommandEnum getCommandEnum(String command) {
        switch (command) {
        case "bye":
            return CommandEnum.BYE;
        case "list":
            return CommandEnum.LIST;
        case "delete":
            return CommandEnum.DELETE;
        case "mark":
            return CommandEnum.MARK;
        case "unmark":
            return CommandEnum.UNMARK;
        case "todo":
            return CommandEnum.TODO;
        case "deadline":
            return CommandEnum.DEADLINE;
        case "event":
            return CommandEnum.EVENT;
        case "find":
            return CommandEnum.FIND;
        case "sort":
            return CommandEnum.SORT;
        default:
            return CommandEnum.INVALID;
        }
    }
}
