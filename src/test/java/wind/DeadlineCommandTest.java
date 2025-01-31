package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.DeadlineCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Deadline;
import wind.ui.Ui;

import java.time.LocalDate;

import static org.mockito.Mockito.*;

public class DeadlineCommandTest {

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
        String description = "Submit assignment";
        LocalDate by = LocalDate.of(2023, 12, 31);
        DeadlineCommand deadlineCommand = new DeadlineCommand(description, by);
        Deadline deadline = new Deadline(description, by);

        // Act
        deadlineCommand.execute(taskList, storage, ui);

        // Assert
        verify(taskList).addTask(any(Deadline.class));
        verify(storage).save(taskList);
    }
}