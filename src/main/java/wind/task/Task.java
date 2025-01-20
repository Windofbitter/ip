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
    public String getDescription();

    /**
     * Sets the task as done or not done.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setIsDone(boolean isDone);

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone();
}