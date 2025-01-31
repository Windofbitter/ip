package wind;

import wind.command.Command;
import wind.exception.WindException;
import wind.parser.Parser;
import wind.storage.Storage;
import wind.storage.TaskList;
import wind.ui.Ui;

import java.util.Scanner;

/**
 * The main class for the Wind application.
 */
public class Wind {
    private final Ui ui = new Ui();
    private final TaskList todos = new TaskList();
    private final Storage storage = new Storage();
    private boolean isExit = false;
//    public static void main(String[] args) {
//        Ui ui = new Ui();
//        ui.printWelcome();
//        Scanner scanner = new Scanner(System.in);
//        TaskList todos = new TaskList();
//        Storage storage = new Storage();
//
//        storage.loadTask(todos);
//        while (true) {
//            try {
//                String line = scanner.nextLine();
//                Command c = Parser.parse(line, todos);
//                c.execute(todos, storage, ui);
//                if (c.isExit()) {
//                    break;
//                }
//            } catch (WindException e) {
//                System.out.println(e.getMessage());
//            }
//        }


    public Wind() {
        storage.loadTask(todos);
    }

    public String getWelcomeMessage() {
        return ui.getWelcomeMessage();
    }

    public boolean isExit() {
        return isExit;
    }


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