package wind.command;


import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Event;
import wind.ui.Ui;

public class EventCommand implements Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Event event = new Event(description, from, to);
        taskList.addTask(event);
        ui.printAddTaskSuccess(event, taskList.getSize());
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
