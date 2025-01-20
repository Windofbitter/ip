package main.java;

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
