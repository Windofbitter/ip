package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

/**
 * Represents a command to sort the task list.
 */
public class SortCommand implements Command {
    private final String sortType;
    private String message;

    /**
     * Constructs a SortCommand with the specified sort type.
     *
     * @param sortType The type of sort to perform ("alpha" or "deadline").
     */
    public SortCommand(String sortType) {
        this.sortType = sortType;
    }

    /**
     * Executes the sort command, sorting the task list based on the specified type.
     *
     * @param taskList The list of tasks to sort.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        if (sortType.equals("alpha")) {
            taskList.sortAlphabetically();
            message = "Tasks sorted alphabetically.";
        } else {
            taskList.sortByDeadline();
            message = "Tasks sorted by deadline.";
        }
        storage.save(taskList);
    }

    /**
     * Indicates that this command will not exit the application.
     *
     * @return false, as this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Gets the response message from executing this command.
     *
     * @return The response message to be displayed to the user.
     */
    @Override
    public String getResponse() {
        return message;
    }
}
