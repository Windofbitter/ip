package main.java;

public interface Command {
    public void execute (TaskList taskList, Storage storage, Ui ui);
    public boolean isExit();
}
