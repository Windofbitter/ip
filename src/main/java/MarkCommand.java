package main.java;

import java.util.List;

public class MarkCommand implements Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(List<Task> tasks, Storage storage) {
        Task task = tasks.get(index - 1);
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:");
        storage.save(tasks);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
