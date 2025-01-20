package main.java;

import java.util.List;

public interface Command {
    public void execute (List<Task> tasks, Storage storage);
    public boolean isExit();
}
