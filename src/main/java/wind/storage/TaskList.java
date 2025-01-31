package wind.storage;

import java.util.Collections;
import java.util.LinkedList;

import wind.task.Task;
import wind.task.Deadline;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private final LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Retrieves the task at the specified index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        assert index >= 0 && index < tasks.size() : "Task index out of bounds: " + index;
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param index The index of the task to delete.
     */
    public void deleteTask(int index) {
        tasks.remove(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public LinkedList<Task> getTasks() {
        return tasks;
    }

    /**
     * Sorts the tasks alphabetically by description.
     */
    public void sortAlphabetically() {
        Collections.sort(tasks, (a, b) -> 
            a.getDescription().compareToIgnoreCase(b.getDescription()));
    }

    /**
     * Sorts the tasks by deadline, with deadline tasks coming first sorted by date,
     * and non-deadline tasks at the end sorted alphabetically.
     */
    public void sortByDeadline() {
        Collections.sort(tasks, (a, b) -> {
            if (!(a instanceof Deadline) && !(b instanceof Deadline)) {
                return a.getDescription().compareToIgnoreCase(b.getDescription());
            }
            if (!(a instanceof Deadline)) {
                return 1; // Non-deadline tasks go to the end
            }
            if (!(b instanceof Deadline)) {
                return -1; // Deadline tasks come first
            }
            // Both are deadline tasks, compare dates
            return ((Deadline) a).getDeadline().compareTo(((Deadline) b).getDeadline());
        });
    }
}
