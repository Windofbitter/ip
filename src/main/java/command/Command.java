package main.java.command;

import main.java.storage.Storage;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public interface Command {
    public void execute (TaskList taskList, Storage storage, Ui ui);
    public boolean isExit();
}
