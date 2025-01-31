package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Event;
import wind.ui.Ui;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;
    private String message;

    /**
     * Constructs an EventCommand with the specified description, start time, and end time.
     *
     * @param description The description of the event task.
     * @param from The start time of the event task.
     * @param to The end time of the event task.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the EventCommand, which adds an event task to the task list,
     * prints a success message, and saves the updated task list.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
//        ui.printAddTaskSuccess(event, taskList.getSize());
        storage.save(taskList);
        message = ui.getAddTaskSuccessMessage(event, taskList.getSize());
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