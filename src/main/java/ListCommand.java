package main.java;

import java.util.List;

public class ListCommand implements Command {
    @Override
    public void execute(List<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
