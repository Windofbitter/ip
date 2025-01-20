package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.TodoCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

import static org.mockito.Mockito.*;

public class TodoCommandTest {

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
        String description = "test";
        TodoCommand todoCommand = new TodoCommand(description);

        // Act
        todoCommand.execute(taskList, storage, ui);

        // Assert
        verify(taskList).addTask(any(Task.class));
        verify(ui).printAddTaskSuccess(any(Task.class), anyInt());
        verify(storage).save(taskList);
    }
}