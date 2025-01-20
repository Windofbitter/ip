package wind.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline implements Task {
    private final String description;
    private LocalDate deadline;
    private boolean isDone;

    public Deadline(String description, LocalDate deadline) {
        this.description = description;
        this.isDone = false;
        this.deadline = deadline;
    }

    public String getDescription() {
        return this.description;
    }

    public LocalDate getDeadline() {
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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDate = this.deadline.format(formatter);
        return "[D]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description + " (by: " + formattedDate + ")";
    }

}
