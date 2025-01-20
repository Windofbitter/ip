package main.java.command;

import main.java.storage.Storage;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
