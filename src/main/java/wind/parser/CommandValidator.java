package wind.parser;

import wind.storage.TaskList;
import wind.exception.IllegalArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Provides validation methods for command inputs.
 */
public class CommandValidator {
    /**
     * Validates the sort type parameter.
     *
     * @param words The command input split into words
     * @param commandName The name of the command being validated
     * @throws IllegalArgumentException if the sort type is invalid or missing
     */
    public static void validateSortType(String[] words, String commandName) 
            throws IllegalArgumentException {
        if (words.length < 2) {
            throw new IllegalArgumentException(
                "Please specify sort type: alpha or deadline\n" +
                "Correct format: sort <type>"
            );
        }
        String sortType = words[1].toLowerCase();
        if (!sortType.equals("alpha") && !sortType.equals("deadline")) {
            throw new IllegalArgumentException(
                "Sort type must be either 'alpha' or 'deadline'\n" +
                "Correct format: sort <type>"
            );
        }
    }

    
    /**
     * Validates a task number input for commands like delete, mark, and unmark.
     *
     * @param words The command input split into words
     * @param taskList The current task list
     * @param commandName The name of the command being validated
     * @throws IllegalArgumentException if the task number is invalid or missing
     */
    public static void validateTaskNumber(String[] words, TaskList taskList, String commandName) 
            throws IllegalArgumentException {
        if (words.length < 2) {
            String errorMessage = String.format("Please provide a task number for the %s command.", commandName);
            errorMessage += String.format("\nCorrect format: %s <task number>", commandName);
            throw new IllegalArgumentException(errorMessage);
        }
        
        if (!words[1].matches("\\d+") || Integer.parseInt(words[1]) < 1 
                || Integer.parseInt(words[1]) > taskList.getSize()) {
            String errorMessage = String.format("Please provide a valid task number for the %s command.", commandName);
            errorMessage += String.format("\nCorrect format: %s <task number>", commandName);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Validates a description input for commands like todo, deadline, and event.
     *
     * @param input The full command input
     * @param prefixLength The length of the command prefix to skip
     * @param commandName The name of the command being validated
     * @throws IllegalArgumentException if the description is missing
     */
    public static void validateDescription(String input, int prefixLength, String commandName) 
            throws IllegalArgumentException {
        if (input.length() < prefixLength) {
            String errorMessage = String.format("Please provide a description for the %s command.", commandName);
            errorMessage += String.format("\nCorrect format: %s <description>", commandName);
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Validates a date string format.
     *
     * @param dateStr The date string to validate
     * @param commandName The name of the command being validated
     * @throws IllegalArgumentException if the date format is invalid
     */
    public static void validateDateFormat(String dateStr, String commandName) 
            throws IllegalArgumentException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            String errorMessage = String.format("Please provide a valid date for the %s command.", commandName);
            errorMessage += "\nDate format should be: yyyy-MM-dd";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Validates event time format.
     *
     * @param input The full command input
     * @param commandName The name of the command being validated
     * @throws IllegalArgumentException if the event time format is invalid
     */
    public static void validateEventFormat(String input, String commandName) 
            throws IllegalArgumentException {
        if (!input.contains(" /from ") || !input.contains(" /to ") 
                || input.indexOf(" /from ") > input.indexOf(" /to ")) {
            String errorMessage = String.format("Please provide a valid %s command.", commandName);
            errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
            throw new IllegalArgumentException(errorMessage);
        }

        String[] parts = input.substring(6).split(" /from | /to ");
        if (parts.length < 3) {
            String errorMessage = String.format("Please provide all required parts for the %s command.", commandName);
            errorMessage += "\nCorrect format: event <description> /from <start time> /to <end time>";
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
