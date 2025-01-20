package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

/**
 * Represents a command that can be executed in the application.
 */
public interface Command {

    /**
     * Executes the command with the given task list, storage, and user interface.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui);

    /**
     * Indicates whether this command will exit the application.
     *
     * @return true if this command exits the application, false otherwise.
     */
    public boolean isExit();
}