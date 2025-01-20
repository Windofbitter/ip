package wind.task;

/**
 * Represents a to-do task.
 */
public class Todo implements Task {
    private final String description;
    private boolean isDone;

    /**
     * Constructs a Todo task with the specified description.
     *
     * @param description The description of the task.
     */
    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the task as done or not done.
     *
     * @param isDone True if the task is done, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the task.
     *
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        return "[T]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}