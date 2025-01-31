package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.MarkCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

import static org.mockito.Mockito.*;

public class MarkCommandTest {

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
        int index = 1;
        MarkCommand markCommand = new MarkCommand(index);
        Task task = mock(Task.class);
        when(taskList.getTask(index - 1)).thenReturn(task);

        // Act
        markCommand.execute(taskList, storage, ui);

        // Assert
        verify(task).setIsDone(true);
        verify(storage).save(taskList);
    }
}