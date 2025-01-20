package wind.command;


import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

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
