package wind.parser;

import wind.command.*;
import wind.exception.InvalidCommandException;
import wind.storage.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {
    // Regex patterns for different commands
    private static final Pattern SIMPLE_COMMAND = Pattern.compile("^(bye|list)$");
    private static final Pattern INDEX_COMMAND = Pattern.compile("^(mark|unmark|delete)\\s+(\\d+)$");
    private static final Pattern TODO_COMMAND = Pattern.compile("^todo\\s+(.+)$");
    private static final Pattern DEADLINE_COMMAND = 
            Pattern.compile("^deadline\\s+(.+?)\\s+/by\\s+(\\d{4}-\\d{2}-\\d{2})$");
    private static final Pattern EVENT_COMMAND = 
            Pattern.compile("^event\\s+(.+?)\\s+/from\\s+(.+?)\\s+/to\\s+(.+)$");
    private static final Pattern FIND_COMMAND = Pattern.compile("^find\\s+(.+)$");

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
        
        // Try matching simple commands first
        Matcher simpleMatcher = SIMPLE_COMMAND.matcher(input);
        if (simpleMatcher.matches()) {
            CommandEnum command = getCommandEnum(simpleMatcher.group(1));
            return switch (command) {
                    case BYE -> new ByeCommand();
                    case LIST -> new ListCommand();
                    default -> throw new InvalidCommandException(getInvalidCommandMessage());
            };
        }

        // Try matching index-based commands
        Matcher indexMatcher = INDEX_COMMAND.matcher(input);
        if (indexMatcher.matches()) {
            CommandEnum command = getCommandEnum(indexMatcher.group(1));
            int index = Integer.parseInt(indexMatcher.group(2));
            
            // Validate index is within range
            if (index < 1 || index > taskList.getSize()) {
                throw new IllegalArgumentException(
                    String.format("Please provide a valid task number for the %s command.\n" +
                                "Correct format: %s <task number>", command, command));
            }
            
            return switch (command) {
                    case DELETE -> new DeleteCommand(index);
                    case MARK -> new MarkCommand(index);
                    case UNMARK -> new UnmarkCommand(index);
                    default -> throw new InvalidCommandException(getInvalidCommandMessage());
            };
        }

        // Try matching todo command
        Matcher todoMatcher = TODO_COMMAND.matcher(input);
        if (todoMatcher.matches()) {
            return new TodoCommand(todoMatcher.group(1).trim());
        }

        // Try matching deadline command
        Matcher deadlineMatcher = DEADLINE_COMMAND.matcher(input);
        if (deadlineMatcher.matches()) {
            String description = deadlineMatcher.group(1).trim();
            String dateStr = deadlineMatcher.group(2);
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate deadline = LocalDate.parse(dateStr, formatter);
                return new DeadlineCommand(description, deadline);
            } catch (Exception e) {
                throw new IllegalArgumentException(
                    "Please provide a valid date for the deadline command.\n" +
                    "Date format should be: yyyy-MM-dd");
            }
        }

        // Try matching event command
        Matcher eventMatcher = EVENT_COMMAND.matcher(input);
        if (eventMatcher.matches()) {
            String description = eventMatcher.group(1).trim();
            String from = eventMatcher.group(2).trim();
            String to = eventMatcher.group(3).trim();
            return new EventCommand(description, from, to);
        }

        // Try matching find command
        Matcher findMatcher = FIND_COMMAND.matcher(input);
        if (findMatcher.matches()) {
            return new FindCommand(findMatcher.group(1).trim());
        }

        // If no patterns match, throw invalid command exception
        throw new InvalidCommandException(getInvalidCommandMessage());
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
        errorMessage += "\n6. find <description>";
        errorMessage += "\n7. deadline <description> /by <deadline> (yyyy-MM-dd)";
        errorMessage += "\n8. event <description> /from <start time> /to <end time>";
        return errorMessage;
    }

    /**
     * Returns the command enum corresponding to the command string.
     *
     * @param command The command string.
     * @return The command enum corresponding to the command string.
     */
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
