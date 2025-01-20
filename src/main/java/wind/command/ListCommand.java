package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the ListCommand, which prints all tasks in the task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printList(taskList);
    }

    /**
     * Indicates that this command will not exit the application.
     *
     * @return false, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}