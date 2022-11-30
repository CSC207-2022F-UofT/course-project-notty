package gateway;

import tasks.Task;

import java.util.ArrayList;

public interface ITaskDataAccess {
    void create(Task task);
    void delete(String task);
    void updateTaskName(Task task, String oldTitle);
    ArrayList<Task> getAll();
}
