package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 */
public class UnmarkCommand implements Command {
    private final int taskNumber;

    /**
     * Constructs an UnmarkCommand with the specified task number.
     *
     * @param taskNumber The number of the task to be unmarked as not done.
     */
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the UnmarkCommand, which unmarks a task as not done in the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(taskNumber - 1);
        task.setIsDone(false);
        ui.printUnmarkTaskSuccess(task);
        storage.save(taskList);
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