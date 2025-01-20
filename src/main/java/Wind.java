package main.java;

import java.util.Scanner;

public class Wind {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
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
