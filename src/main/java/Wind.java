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
        List<String> todos = new LinkedList<>();
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < todos.size(); i++) {
                    System.out.println(i + 1 + ". " + todos.get(i));
                }
            } else {
                System.out.println("added: " + input);
                todos.add(input);
            }
        }
    }
}
