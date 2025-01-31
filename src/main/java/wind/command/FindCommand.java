package wind.command;

import java.util.LinkedList;
import java.util.List;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

public class FindCommand implements Command{
    private final String keyword;
    private String message;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        List<Task> matchingTasks = new LinkedList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
//        ui.printMatchingTasks(matchingTasks);
        message = ui.getMatchingTasksMessage(matchingTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse() {
        return message;
    }
}
