package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

public interface Command {
    public void execute (TaskList taskList, Storage storage, Ui ui);
    public boolean isExit();
}
