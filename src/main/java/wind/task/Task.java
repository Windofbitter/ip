package wind.task;

/**
 * Represents a task with a description and completion status.
 */
public interface Task {

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    String getDescription();

    /**
     * Sets the task as done or not done.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    void setIsDone(boolean isDone);

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    boolean getIsDone();
}
