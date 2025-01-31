package wind.ui;


import java.util.List;

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

    public void printMatchingTasks(List<Task> matchingTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println((i + 1) + ". " + matchingTasks.get(i));
        }

    }

    /**
     * Prints a goodbye message.
     */
    public void printGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Returns a message indicating that a task has been successfully added.
     *
     * @param task The task that was added.
     * @param taskCount The current number of tasks in the list.
     * @return The success message.
     */
    public String getAddTaskSuccessMessage(Task task, int taskCount) {
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns the list of tasks as a string.
     *
     * @param taskList The list of tasks to return.
     * @return The list of tasks.
     */
    public String getListMessage(TaskList taskList) {
        StringBuilder message = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.getSize(); i++) {
            message.append((i + 1)).append(". ").append(taskList.getTask(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Returns a message indicating that a task has been successfully marked as done.
     *
     * @param task The task that was marked as done.
     * @return The success message.
     */
    public String getMarkTaskSuccessMessage(Task task) {
        return "OK, I've marked this task as done:\n  " + task;
    }

    /**
     * Returns a message indicating that a task has been successfully marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return The success message.
     */
    public String getUnmarkTaskSuccessMessage(Task task) {
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    /**
     * Returns a message indicating that a task has been successfully deleted.
     *
     * @param task The task that was deleted.
     * @param taskCount The current number of tasks in the list.
     * @return The success message.
     */
    public String getDeleteTaskSuccessMessage(Task task, int taskCount) {
        return "Noted. I've removed this task:\n  " + task + "\nNow you have " + taskCount + " tasks in the list.";
    }

    /**
     * Returns a welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return "Hello! I'm Wind\nWhat can I do for you?";
    }

    /**
     * Returns the list of matching tasks as a string.
     *
     * @param matchingTasks The list of matching tasks to return.
     * @return The list of matching tasks.
     */
    public String getMatchingTasksMessage(List<Task> matchingTasks) {
        StringBuilder message = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            message.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return message.toString();
    }

    /**
     * Returns a goodbye message.
     *
     * @return The goodbye message.
     */
    public String getGoodbyeMessage() {
        return "Bye. Hope to see you again soon!";
    }
}