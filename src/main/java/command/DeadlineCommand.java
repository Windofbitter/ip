package main.java.command;

import main.java.task.Deadline;
import main.java.storage.Storage;
import main.java.storage.TaskList;
import main.java.ui.Ui;

import java.time.LocalDate;

public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Deadline deadline = new Deadline(this.description, this.by);
        taskList.addTask(deadline);
        ui.printAddTaskSuccess(deadline, taskList.getSize());
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
