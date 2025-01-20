package main.java.task;

public class Event implements Task {
    private final String description;
    private String startDate;
    private String endDate;
    private boolean isDone;

    public Event(String description, String startDate, String endDate) {
        this.description = description;
        this.isDone = false;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description + " (from: " + this.startDate + " to: " + this.endDate + ")";
    }

}
