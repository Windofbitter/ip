package wind.ui;


import wind.storage.TaskList;
import wind.task.Task;

public class Ui {

    public void printAddTaskSuccess(Task task, int taskCount) {
        System.out.println("Got it. I've added this wind.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    public void printMarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this wind.task as done:");
        System.out.println("  " + task);
    }

    public void printUnmarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this wind.task as not done yet:");
        System.out.println("  " + task);
    }

    public void printDeleteTaskSuccess(Task task, int taskCount) {
        System.out.println("Noted. I've removed this wind.task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void printWelcome() {
        System.out.println("Hello! I'm Wind");
        System.out.println("What can I do for you?");
    }

    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }




}
