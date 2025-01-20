package main.java;

import java.util.List;

public class ByeCommand implements Command{
    @Override
    public void execute(List<Task> tasks, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
