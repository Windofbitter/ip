package wind.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import wind.command.ByeCommand;
import wind.command.CommandEnum;
import wind.command.DeadlineCommand;
import wind.command.DeleteCommand;
import wind.command.EventCommand;
import wind.command.FindCommand;
import wind.command.ListCommand;
import wind.command.MarkCommand;
import wind.command.TodoCommand;
import wind.command.UnmarkCommand;
import wind.command.Command;
import wind.exception.InvalidCommandException;
import wind.storage.TaskList;

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
            // try and parse the integer and see if it is valid
            if (words.length < 2) {
                String errorMessage = "Please provide a task number for the delete command.";
                // give the correct format
                errorMessage += "\nCorrect format: delete <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            // check whether the task number is valid
            if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 
                    || Integer.parseInt(words[1]) > taskList.getSize()) {
                String errorMessage = "Please provide a valid task number for the delete command.";
                // give the correct format
                errorMessage += "\nCorrect format: delete <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            int deleteIndex = Integer.parseInt(words[1]);
            assert deleteIndex >= 1 && deleteIndex <= taskList.getSize() 
                   : "Task number must be within valid range: " + deleteIndex;
            return new DeleteCommand(deleteIndex);
        case MARK:
            // try and parse the integer and see if it is valid
            if (words.length < 2) {
                String errorMessage = "Please provide a task number for the mark command.";
                // give the correct format
                errorMessage += "\nCorrect format: mark <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            // check whether the task number is valid
            if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 
                    || Integer.parseInt(words[1]) > taskList.getSize()) {
                String errorMessage = "Please provide a valid task number for the mark command.";
                // give the correct format
                errorMessage += "\nCorrect format: mark <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            int markIndex = Integer.parseInt(words[1]);
            assert markIndex >= 1 && markIndex <= taskList.getSize() 
                   : "Task number must be within valid range: " + markIndex;
            return new MarkCommand(markIndex);
        case UNMARK:
            if (words.length < 2) {
                String errorMessage = "Please provide a task number for the unmark command.";
                // give the correct format
                errorMessage += "\nCorrect format: unmark <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 
                    || Integer.parseInt(words[1]) > taskList.getSize()) {
                String errorMessage = "Please provide a valid task number for the unmark command.";
                // give the correct format
                errorMessage += "\nCorrect format: unmark <task number>";
                throw new IllegalArgumentException(errorMessage);
            }
            int unmarkIndex = Integer.parseInt(words[1]);
            assert unmarkIndex >= 1 && unmarkIndex <= taskList.getSize() 
                   : "Task number must be within valid range: " + unmarkIndex;
            return new UnmarkCommand(unmarkIndex);
        case TODO:
            if (input.length() < 6) {
                String errorMessage = "Please provide a description for the todo command.";
                // give the correct format
                errorMessage += "\nCorrect format: todo <description>";
                throw new IllegalArgumentException(errorMessage);
            }
            return new TodoCommand(input.substring(5));
        case DEADLINE:
            if (!input.contains(" /by ")) {
                String errorMessage = "Please provide a valid deadline command.";
                errorMessage += "\nCorrect format: deadline <description> /by <deadline>";
                throw new IllegalArgumentException(errorMessage);
            }
            if (input.substring(9).split(" /by ").length < 2) {
                String errorMessage = "Please provide a deadline for the deadline command.";
                errorMessage += "\nCorrect format: deadline <description> /by <deadline>";
                throw new IllegalArgumentException(errorMessage);
            }
            try {
                String[] deadlineWords = input.substring(9).split(" /by ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deadline = LocalDate.parse(deadlineWords[1], formatter);
                return new DeadlineCommand(deadlineWords[0], deadline);
            } catch (Exception e) {
                String errorMessage = "Please provide a valid deadline for the deadline command.";
                errorMessage += "\nCorrect format: deadline <description> /by <deadline> (yyyy-MM-dd)";
                throw new IllegalArgumentException(errorMessage);
            }
        case EVENT:
            // check the format
            if (!input.contains(" /from ") || !input.contains(" /to ") 
                    || input.indexOf(" /from ") > input.indexOf(" /to ")) {
                String errorMessage = "Please provide a valid event command.";
                errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
                throw new IllegalArgumentException(errorMessage);
            }
            // check the description
            if (input.substring(6).split(" /from | /to ").length < 3) {
                String errorMessage = "Please provide a description for the event command.";
                errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
                throw new IllegalArgumentException(errorMessage);
            }
            String[] eventParts = input.substring(6).split(" /from | /to ");
            return new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
        case FIND:
            if (input.length() < 6) {
                String errorMessage = "Please provide a keyword for the find command.";
                // give the correct format
                errorMessage += "\nCorrect format: find <keyword>";
                throw new IllegalArgumentException(errorMessage);
            }
            return new FindCommand(input.substring(5));
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
        default:
            return CommandEnum.INVALID;
        }
    }
}
