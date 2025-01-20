package wind.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline implements Task {
    private final String description;
    private LocalDate deadline;
    private boolean isDone;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description The description of the task.
     * @param deadline The deadline of the task.
     */
    public Deadline(String description, LocalDate deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
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
     * Returns the deadline of the task.
     *
     * @return The deadline of the task.
     */
    public LocalDate getDeadline() {
        return this.deadline;
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.deadline.format(formatter);
        return "[D]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description + " (by: " + formattedDate + ")";
    }
}