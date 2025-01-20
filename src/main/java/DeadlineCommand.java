package main.java;

import java.time.LocalDate;
import java.util.List;

public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(List<Task> tasks, Storage storage) {
        Deadline deadline = new Deadline(this.description, this.by);
        tasks.add(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
