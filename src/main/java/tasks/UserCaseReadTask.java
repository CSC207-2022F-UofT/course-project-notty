package tasks;
import gateway.TaskDataAccess;
import java.util.ArrayList;

public class UserCaseReadTask {
    private final int categoryId;
    public UserCaseReadTask(int categoryId)
    {
        this.categoryId = categoryId;
    }
    public ArrayList<Task> accessData() {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        ArrayList<Task> list = taskDataAccess.getTasks();
        ArrayList<Task> taskList = new ArrayList<>();
        for (Task task : list) {
            if (this.categoryId == task.getCategoryId()) {
                taskList.add(task);
            }
        }
        return taskList;
    }
}