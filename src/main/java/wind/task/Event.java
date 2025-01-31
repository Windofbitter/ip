package wind.task;

/**
 * Represents an event task with a start and end date.
 */
public class Event implements Task {
    private final String description;
    private String startDate;
    private String endDate;
    private boolean isDone;

    /**
     * Constructs an Event task with the specified description, start date, and end date.
     *
     * @param description The description of the event.
     * @param startDate The start date of the event.
     * @param endDate The end date of the event.
     */
    public Event(String description, String startDate, String endDate) {
        this.description = description;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the description of the event.
     *
     * @return The description of the event.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the start date of the event.
     *
     * @return The start date of the event.
     */
    public String getStartDate() {
        return this.startDate;
    }

    /**
     * Returns the end date of the event.
     *
     * @return The end date of the event.
     */
    public String getEndDate() {
        return this.endDate;
    }

    /**
     * Returns whether the event is done.
     *
     * @return True if the event is done, false otherwise.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Sets the event as done or not done.
     *
     * @param isDone True if the event is done, false otherwise.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of the event.
     *
     * @return The string representation of the event.
     */
    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s to: %s)",
                this.isDone ? "X" : " ",
                this.description,
                this.startDate,
                this.endDate);
    }
}
