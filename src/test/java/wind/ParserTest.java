package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.*;
import wind.exception.InvalidCommandException;
import wind.parser.Parser;
import wind.storage.TaskList;
import wind.task.Todo;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }

    @Test
    public void testParseByeCommand() throws InvalidCommandException {
        Command command = Parser.parse("bye", taskList);
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void testParseListCommand() throws InvalidCommandException {
        Command command = Parser.parse("list", taskList);
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParseMarkCommand() throws InvalidCommandException {
        taskList.addTask(new Todo("Test task"));
        Command command = Parser.parse("mark 1", taskList);
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParseUnmarkCommand() throws InvalidCommandException {
        taskList.addTask(new Todo("Test task"));
        Command command = Parser.parse("unmark 1", taskList);
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParseTodoCommand() throws InvalidCommandException {
        Command command = Parser.parse("todo Test task", taskList);
        assertInstanceOf(TodoCommand.class, command);
    }

    @Test
    public void testParseDeadlineCommand() throws InvalidCommandException {
        Command command = Parser.parse("deadline Test task /by 2023-12-31", taskList);
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParseEventCommand() throws InvalidCommandException {
        Command command = Parser.parse("event Test event /from 2023-12-31T10:00 /to 2023-12-31T12:00", taskList);
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParseInvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> Parser.parse("invalid", taskList));
    }
}