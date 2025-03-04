package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.DeleteCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

import static org.mockito.Mockito.*;

public class DeleteCommandTest {

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
        Task task = mock(Task.class);
        when(taskList.getTask(index - 1)).thenReturn(task);
        when(taskList.getSize()).thenReturn(0);
        DeleteCommand deleteCommand = new DeleteCommand(index);

        // Act
        deleteCommand.execute(taskList, storage, ui);

        // Assert
        verify(taskList).getTask(index - 1);
        verify(taskList).deleteTask(index - 1);
        verify(storage).save(taskList);
    }
}