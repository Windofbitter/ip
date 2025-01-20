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
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);
        TaskList todos = new TaskList();
        Storage storage = new Storage();

        storage.loadTask(todos);
        while (true) {
            try {
                String line = scanner.nextLine();
                Command c = Parser.parse(line, todos);
                c.execute(todos, storage, ui);
                if (c.isExit()) {
                    break;
                }
            } catch (WindException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}