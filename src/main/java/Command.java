package main.java;

import java.util.List;

public interface Command {
    public void execute (List<Task> tasks);
    public boolean isExit();
}
