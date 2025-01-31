package wind;

import wind.command.Command;
import wind.exception.WindException;
import wind.parser.Parser;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

/**
 * The main class for the Wind application.
 */
public class Wind {
    private final Ui ui = new Ui();
    private final TaskList todos = new TaskList();
    private final Storage storage = new Storage();
    private boolean isExit = false;

    /**
     * Constructs a new Wind instance and loads tasks from storage.
     */
    public Wind() {
        storage.loadTask(todos);
    }

    /**
     * Gets the welcome message for the application.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    /**
     * Checks if the application should exit.
     *
     * @return true if the application should exit, false otherwise.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Processes user input and returns the appropriate response.
     *
     * @param input The user's input command.
     * @return The response message to the user's command.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input, todos);
            c.execute(todos, storage, ui);
            isExit = c.isExit();
            return c.getResponse();
        } catch (WindException e) {
            return e.getMessage();
        }
    }
}
