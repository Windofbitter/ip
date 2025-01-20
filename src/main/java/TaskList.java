package main.java;

import java.util.LinkedList;

public class TaskList {
    private final LinkedList<Task> tasks = new LinkedList<>();

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public LinkedList<Task> getTasks() {
        return tasks;
    }
}
