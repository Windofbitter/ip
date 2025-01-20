package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.EventCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Event;
import wind.ui.Ui;

import static org.mockito.Mockito.*;

public class EventCommandTest {

    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    @BeforeEach
    public void setUp() {
        taskList = mock(TaskList.class);
        storage = mock(Storage.class);
        ui = mock(Ui.class);
    }

    @Test
    public void testExecute() {
        // Arrange
        String description = "Team meeting";
        String from = "2021-08-24T14:00";
        String to = "2021-08-24T16:00";
        EventCommand eventCommand = new EventCommand(description, from, to);
        Event event = new Event(description, from, to);

        // Act
        eventCommand.execute(taskList, storage, ui);

        // Assert
        verify(taskList).addTask(any(Event.class));
        verify(ui).printAddTaskSuccess(any(Event.class), anyInt());
        verify(storage).save(taskList);
    }
}