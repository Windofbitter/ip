package main.java;

public class ByeCommand implements Command{
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        ui.printGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
