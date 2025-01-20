package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand implements Command {
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the MarkCommand, which marks a task as done in the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index - 1);
        task.setIsDone(true);
        ui.printMarkTaskSuccess(task);
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