package wind;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wind.command.ListCommand;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

import static org.mockito.Mockito.*;

public class ListCommandTest {

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
        ListCommand listCommand = new ListCommand();

        // Act
        listCommand.execute(taskList, storage, ui);

        // Assert
        verify(ui).printList(taskList);
    }
}