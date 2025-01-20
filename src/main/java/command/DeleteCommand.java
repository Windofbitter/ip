package main.java.command;

import main.java.storage.Storage;
import main.java.task.Task;
import main.java.storage.TaskList;
import main.java.ui.Ui;

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
