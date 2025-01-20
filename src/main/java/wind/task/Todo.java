package wind.task;

public class Todo implements Task {
    private final String description;
    private boolean isDone;

    public Todo(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.isDone ? "X" : " ") + "] " + this.description;
    }
}
