package tasks;

import gateway.TaskDataAccess;

public class UserCaseMarkTask{
    private final int id;
    private final int marked;
    public UserCaseMarkTask(int id, int marked) {
        this.id = id;
        this.marked = marked;
    }
    public void action() {
        TaskDataAccess taskDataAccess = new TaskDataAccess();
        int mark;
        if (this.marked == 0) mark = 1;
        else mark = 0;
        taskDataAccess.markTask(this.id, mark);
    }
}