package wind.parser;


import wind.command.*;
import wind.exception.InvalidCommandException;
import wind.storage.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    public static Command parse(String input, TaskList taskList) throws IllegalArgumentException, InvalidCommandException {
        String[] words = input.split(" ");
        CommandEnum commandEnum = getCommandEnum(words[0]);
        return switch (commandEnum) {
            case BYE -> new ByeCommand();
            case LIST -> new ListCommand();
            case DELETE -> {
                // try and parse the integer and see if it is valid
                if (words.length < 2) {
                    String errorMessage = "Please provide a wind.task number for the delete wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: delete <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                // check whether the wind.task number is valid
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > taskList.getSize()) {
                    String errorMessage = "Please provide a valid wind.task number for the delete wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: delete <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new DeleteCommand(Integer.parseInt(words[1]));
            }
            case MARK -> {
                // try and parse the integer and see if it is valid
                if (words.length < 2) {
                    String errorMessage = "Please provide a wind.task number for the mark wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: mark <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                // check whether the wind.task number is valid
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > taskList.getSize()) {
                    String errorMessage = "Please provide a valid wind.task number for the mark wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: mark <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new MarkCommand(Integer.parseInt(words[1]));
            }
            case UNMARK -> {
                if (words.length < 2) {
                    String errorMessage = "Please provide a wind.task number for the unmark wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: unmark <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > taskList.getSize()) {
                    String errorMessage = "Please provide a valid wind.task number for the unmark wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: unmark <wind.task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new UnmarkCommand(Integer.parseInt(words[1]));
            }
            case TODO -> {
                if (input.length() < 6) {
                    String errorMessage = "Please provide a description for the todo wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: todo <description>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new TodoCommand(input.substring(5));
            }
            case DEADLINE -> {
                if (!input.contains(" /by ")) {
                    String errorMessage = "Please provide a valid deadline wind.command.";
                    errorMessage += "\nCorrect format: deadline <description> /by <deadline>";
                    throw new IllegalArgumentException(errorMessage);
                }
                if (input.substring(9).split(" /by ").length < 2) {
                    String errorMessage = "Please provide a deadline for the deadline wind.command.";
                    errorMessage += "\nCorrect format: deadline <description> /by <deadline>";
                    throw new IllegalArgumentException(errorMessage);
                }
                try {
                    String[] deadlineWords = input.substring(9).split(" /by ");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate deadline = LocalDate.parse(deadlineWords[1], formatter);
                    yield new DeadlineCommand(deadlineWords[0], deadline);
                } catch (Exception e) {
                    String errorMessage = "Please provide a valid deadline for the deadline wind.command.";
                    errorMessage += "\nCorrect format: deadline <description> /by <deadline> (yyyy-MM-dd)";
                    throw new IllegalArgumentException(errorMessage);
                }
            }
            case EVENT -> {
                // check the format
                if (!input.contains(" /from ") || !input.contains(" /to ") || input.indexOf(" /from ") > input.indexOf(" /to ")) {
                    String errorMessage = "Please provide a valid event wind.command.";
                    errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
                    throw new IllegalArgumentException(errorMessage);
                }
                // check the description
                if (input.substring(6).split(" /from | /to ").length < 3) {
                    String errorMessage = "Please provide a description for the event wind.command.";
                    errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
                    throw new IllegalArgumentException(errorMessage);
                }
                String[] eventParts = input.substring(6).split(" /from | /to ");
                yield new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
            }
            case FIND ->  {
                if (words.length < 2) {
                    String errorMessage = "Please provide a keyword for the find wind.command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: find <keyword>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new FindCommand(input.substring(5));
            }
            default -> {
                String errorMessage = getInvalidCommandMessage();
                throw new InvalidCommandException(errorMessage);
            }
        };
    }

    private static String getInvalidCommandMessage() {
        String errorMessage = "I do not understand this wind.command, please try again.";
        errorMessage += "\nHere are the possible commands:";
        errorMessage += "\n1. bye";
        errorMessage += "\n2. list";
        errorMessage += "\n3. mark <wind.task number>";
        errorMessage += "\n4. unmark <wind.task number>";
        errorMessage += "\n5. todo <description>";
        errorMessage += "\n6. deadline <description> /by <deadline> (yyyy-MM-dd)";
        errorMessage += "\n7. event <description> /from <start time> /to <end time>";
        return errorMessage;
    }

    private static CommandEnum getCommandEnum(String command) {
        return switch (command) {
            case "bye" -> CommandEnum.BYE;
            case "list" -> CommandEnum.LIST;
            case "delete" -> CommandEnum.DELETE;
            case "mark" -> CommandEnum.MARK;
            case "unmark" -> CommandEnum.UNMARK;
            case "todo" -> CommandEnum.TODO;
            case "deadline" -> CommandEnum.DEADLINE;
            case "event" -> CommandEnum.EVENT;
            case "find" -> CommandEnum.FIND;
            default -> CommandEnum.INVALID;
        };
    }
}
