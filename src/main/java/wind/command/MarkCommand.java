package wind.command;


import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index - 1);
        task.setIsDone(true);
        ui.printMarkTaskSuccess(task);
        storage.save(taskList);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
