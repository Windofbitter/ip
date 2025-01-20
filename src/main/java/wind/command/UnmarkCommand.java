package wind.command;


import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

public class UnmarkCommand implements Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(taskNumber - 1);
        task.setIsDone(false);
        ui.printUnmarkTaskSuccess(task);
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
