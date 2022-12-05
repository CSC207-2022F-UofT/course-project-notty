package tasks;

import gateway.TaskDataAccess;

import java.util.ArrayList;

public class UserCaseDeleteTask{
    int id;
    public UserCaseDeleteTask(int id) {
        this.id = id;
    }
    public void action() {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        taskDataAccess.deleteTask(this.id);}

    public void action(int categoryId) {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        ArrayList<Task> taskList = taskDataAccess.getTasks();
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getCategoryId() == categoryId) list.add(task);
        }
        for (Task task : list) taskDataAccess.deleteTask(task.getId());
    }
}