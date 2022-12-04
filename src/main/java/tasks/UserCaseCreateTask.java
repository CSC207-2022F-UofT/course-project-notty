package tasks;

import gateway.TaskDataAccess;

public class UserCaseCreateTask{
    String title;
    public UserCaseCreateTask(String title)
    {
        this.title = title;
    }

    public void action(int categoryId) {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        taskDataAccess.createTask(new Task(this.title, categoryId));
    }
}