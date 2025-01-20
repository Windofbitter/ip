package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the DeleteCommand, which deletes a task from the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index - 1);
        taskList.deleteTask(index - 1);
        ui.printDeleteTaskSuccess(task, taskList.getSize());
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