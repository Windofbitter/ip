package main.java;

import java.util.List;

public class TodoCommand implements Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void execute(List<Task> tasks) {
        Task newTask = new Todo(this.description);
        tasks.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
