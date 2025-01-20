package main.java.command;

import main.java.storage.Storage;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class ByeCommand implements Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
