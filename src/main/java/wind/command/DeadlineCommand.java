package wind.command;


import java.time.LocalDate;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Deadline;
import wind.ui.Ui;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;
    private String message;

    /**
     * Constructs a DeadlineCommand with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the DeadlineCommand, which adds a deadline task to the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Deadline deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
//        ui.printAddTaskSuccess(deadline, taskList.getSize());
        storage.save(taskList);
        message = ui.getAddTaskSuccessMessage(deadline, taskList.getSize());
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
