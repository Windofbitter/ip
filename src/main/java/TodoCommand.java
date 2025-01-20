package main.java;

public class TodoCommand implements Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public void execute(TaskList taskList, Storage storage, Ui ui) {
        Task newTask = new Todo(this.description);
        taskList.addTask(newTask);
        ui.printAddTaskSuccess(newTask, taskList.getSize());
        storage.save(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
