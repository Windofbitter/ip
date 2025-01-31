package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.task.Todo;
import wind.ui.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class TodoCommand implements Command {
    private final String description;
    private String message;

    /**
     * Constructs a TodoCommand with the specified description.
     *
     * @param description The description of the todo task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Returns the description of the todo task.
     *
     * @return The description of the todo task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Executes the TodoCommand, which adds a todo task to the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task newTask = new Todo(this.description);
        taskList.addTask(newTask);
//        ui.printAddTaskSuccess(newTask, taskList.getSize());
        storage.save(taskList);
        message = ui.getAddTaskSuccessMessage(newTask, taskList.getSize());
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

    @Override
    public String getResponse() {
        return message;
    }
}