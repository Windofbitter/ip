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
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < todos.size(); i++) {
                    if (todos.get(i).getIsDone()) {
                        System.out.println(i + 1 + ".[X] " + todos.get(i).getDescription());
                    } else {
                        System.out.println(i + 1 + ".[ ] " + todos.get(i).getDescription());
                    }
                }
            } else if (input.startsWith("mark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                todos.get(index).setIsDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[X] " + todos.get(index).getDescription());
            } else if (input.startsWith("unmark")) {
                String[] words = input.split(" ");
                int index = Integer.parseInt(words[1]) - 1;
                todos.get(index).setIsDone(false);
                System.out.println("Nice! I've unmarked this task as done:");
                System.out.println("[ ] " + todos.get(index).getDescription());
            } else {
                System.out.println("added: " + input);
                todos.add(new Task(input));
            }
        }
    }
}
