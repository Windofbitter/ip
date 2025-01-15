package main.java;

import java.util.List;

public class UnmarkCommand implements Command {
    private final int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(List<Task> tasks) {
        Task task = tasks.get(taskNumber - 1);
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
