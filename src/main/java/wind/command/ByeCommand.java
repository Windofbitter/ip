package wind.command;

import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    private String message;

    /**
     * Executes the ByeCommand, which prints a goodbye message to the user.
     *
     * @param taskList The list of tasks.
     * @param storage The storage handler.
     * @param ui The user interface handler.
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
//        ui.printGoodbye();
        message = ui.getGoodbyeMessage();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return true, as this command exits the application.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String getResponse() {
        return message;
    }
}