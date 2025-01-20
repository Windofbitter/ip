package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Parser {
    public static Command parse(String input, List<Task> tasks) throws IllegalArgumentException, InvalidCommandException {
        String[] words = input.split(" ");
        CommandEnum commandEnum = getCommandEnum(words[0]);
        return switch (commandEnum) {
            case CommandEnum.BYE -> new ByeCommand();
            case CommandEnum.LIST -> new ListCommand();
            case CommandEnum.DELETE -> {
                // try and parse the integer and see if it is valid
                if (words.length < 2) {
                    String errorMessage = "Please provide a task number for the delete command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: delete <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                // check whether the task number is valid
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > tasks.size()) {
                    String errorMessage = "Please provide a valid task number for the delete command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: delete <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new DeleteCommand(Integer.parseInt(words[1]));
            }
            case CommandEnum.MARK -> {
                // try and parse the integer and see if it is valid
                if (words.length < 2) {
                    String errorMessage = "Please provide a task number for the mark command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: mark <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                // check whether the task number is valid
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > tasks.size()) {
                    String errorMessage = "Please provide a valid task number for the mark command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: mark <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new MarkCommand(Integer.parseInt(words[1]));
            }
            case CommandEnum.UNMARK -> {
                if (words.length < 2) {
                    String errorMessage = "Please provide a task number for the unmark command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: unmark <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 || Integer.parseInt(words[1]) > tasks.size()) {
                    String errorMessage = "Please provide a valid task number for the unmark command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: unmark <task number>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new UnmarkCommand(Integer.parseInt(words[1]));
            }
            case CommandEnum.TODO -> {
                if (input.length() < 6) {
                    String errorMessage = "Please provide a description for the todo command.";
                    // give the correct format
                    errorMessage += "\nCorrect format: todo <description>";
                    throw new IllegalArgumentException(errorMessage);
                }
                yield new TodoCommand(input.substring(5));
            }
            case CommandEnum.DEADLINE -> {
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
                    yield new DeadlineCommand(deadlineWords[0], deadline);
                } catch (Exception e) {
                    String errorMessage = "Please provide a valid deadline for the deadline command.";
                    errorMessage += "\nCorrect format: deadline <description> /by <deadline> (yyyy-MM-dd)";
                    throw new IllegalArgumentException(errorMessage);
                }
            }
            case CommandEnum.EVENT -> {
                // check the format
                if (!input.contains(" /from ") || !input.contains(" /to ") || input.indexOf(" /from ") > input.indexOf(" /to ")) {
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
                yield new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
            }
            default -> {
                String errorMessage = getInvalidCommandMessage();
                throw new InvalidCommandException(errorMessage);
            }
        };
    }

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
            default -> CommandEnum.INVALID;
        };
    }
}
