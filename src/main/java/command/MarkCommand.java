package main.java.command;

import main.java.storage.Storage;
import main.java.task.Task;
import main.java.storage.TaskList;
import main.java.ui.Ui;

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
