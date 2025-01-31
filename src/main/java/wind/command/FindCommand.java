package wind.command;

import java.util.LinkedList;
import java.util.List;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.task.Task;
import wind.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword.
 */
public class FindCommand implements Command {
    private final String keyword;
    private String message;

    /**
     * Creates a new FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the keyword.
     *
     * @param taskList The list of tasks to search through.
     * @param storage The storage handler (unused).
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        List<Task> matchingTasks = new LinkedList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        //ui.printMatchingTasks(matchingTasks);
        message = ui.getMatchingTasksMessage(matchingTasks);
    }

    /**
     * Indicates whether this command will exit the application.
     *
     * @return false as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Gets the response message containing the matching tasks.
     *
     * @return The message showing tasks that match the search keyword.
     */
    @Override
    public String getResponse() {
        return message;
    }
}
