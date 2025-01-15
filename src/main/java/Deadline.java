package main.java;

public class Deadline implements Task {
    private final String description;
    private String deadline;
    private boolean isDone;

    public Deadline(String description, String deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    public String getDescription() {
        return this.description;
    }

    public String getDeadline() {
        return this.deadline;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description + " (by: " + this.deadline + ")";
    }

}
