package wind.storage;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


import wind.task.Deadline;
import wind.task.Event;
import wind.task.Task;
import wind.task.Todo;


/**
 * Handles the storage of tasks to and from a file.
 */
public class Storage {
    private final String filePath = "./data/tasks.txt";

    /**
     * Saves the tasks to the file.
     *
     * @param tasks The list of tasks to be saved.
     */
    public void save(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        for (Task task : tasks.getTasks()) {
            sb.append(getTaskString(task)).append("\n");
        }

        try {
            Path path = Paths.get(filePath);
            if (Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            Files.write(path, sb.toString().getBytes());
        } catch (java.io.IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file.
     *
     * @param tasks The list of tasks to be loaded.
     */
    public void loadTask(TaskList tasks) {
        try {
            java.io.File f = new java.io.File(filePath);
            if (!f.exists()) {
                return;
            }
            java.util.Scanner sc = new java.util.Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                Task task = getTask(line);
                tasks.addTask(task);
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    /**
     * Converts a task to its string representation.
     *
     * @param task The task to be converted.
     * @return The string representation of the task.
     */
    private String getTaskString(Task task) {
        if (task.getClass().equals(Todo.class)) {
            return "T | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task.getClass().equals(Event.class)) {
            return "E | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + ((Event) task).getStartDate() + " | " + ((Event) task).getEndDate();
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = ((Deadline) task).getDeadline().format(formatter);
            return "D | " + (task.getIsDone() ? "1" : "0") + " | " + task.getDescription() + " | " + formattedDate;
        }
    }

    /**
     * Converts a string representation of a task to a Task object.
     *
     * @param string The string representation of the task.
     * @return The Task object.
     */
    private Task getTask(String string) {
        String[] arr = string.split(" \\| ");
        if (arr[0].equals("T")) {
            Todo todo = new Todo(arr[2]);
            if (arr[1].equals("1")) {
                todo.setIsDone(true);
            }
            return todo;
        } else if (arr[0].equals("E")) {
            Event event = new Event(arr[2], arr[3], arr[4]);
            if (arr[1].equals("1")) {
                event.setIsDone(true);
            }
            return event;
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineDate = LocalDate.parse(arr[3], formatter);
            Deadline deadline = new Deadline(arr[2], deadlineDate);
            if (arr[1].equals("1")) {
                deadline.setIsDone(true);
            }
            return deadline;
        }
    }
}