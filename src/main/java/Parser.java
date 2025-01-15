package main.java;

public class Parser {
    public static Command parse(String input) {
        String[] words = input.split(" ");
        return switch (words[0]) {
            case "bye" -> new ByeCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(words[1]));
            case "unmark" -> new UnmarkCommand(Integer.parseInt(words[1]));
            case "todo" -> new TodoCommand(input.substring(5));
            case "deadline" -> {
                String[] deadlineWords = input.substring(9).split(" /by ");
                yield new DeadlineCommand(deadlineWords[0], deadlineWords[1]);
            }
            case "event" -> {
                String[] eventParts = input.substring(6).split(" /from | /to ");
                yield new EventCommand(eventParts[0], eventParts[1], eventParts[2]);
            }
            default -> throw new RuntimeException();
        };
    }
}
