package wind.ui;

import wind.storage.TaskList;
import wind.task.Task;

/**
 * Handles the user interface for the application.
 */
public class Ui {

    /**
     * Prints a message indicating that a task has been successfully added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     */
    public void printAddTaskSuccess(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints the list of tasks.
     *
     * @param taskList The list of tasks to print.
     */
    public void printList(TaskList taskList) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.getSize(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTask(i));
        }
    }

    /**
     * Prints a message indicating that a task has been successfully marked as done.
     *
     * @param task The task that was marked as done.
     */
    public void printMarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this task as done:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message indicating that a task has been successfully marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public void printUnmarkTaskSuccess(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
    }

    /**
     * Prints a message indicating that a task has been successfully deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     */
    public void printDeleteTaskSuccess(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    /**
     * Prints a welcome message.
     */
    public void printWelcome() {
        System.out.println("Hello! I'm Wind");
        System.out.println("What can I do for you?");
    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}