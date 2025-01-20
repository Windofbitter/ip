package wind.command;


import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

public class DeleteCommand implements Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task task = taskList.getTask(index - 1);
        taskList.deleteTask(index - 1);
        ui.printDeleteTaskSuccess(task, taskList.getSize());
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
