package main.java;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Wind {
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm Wind\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        List<Task> todos = new LinkedList<>();
        Storage storage = new Storage();
        storage.loadTask(todos);
        while (true) {
            try {
                String line = scanner.nextLine();
                Command c = Parser.parse(line, todos);
                c.execute(todos, storage);
                if (c.isExit()) {
                    break;
                }
            } catch (WindException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
