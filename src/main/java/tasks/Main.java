package tasks;

import gateway.DBConnection;
import gateway.TaskDataAccess;
import tasks.CreateTaskPanel;
import tasks.CreateTaskUseCase;
import tasks.ListTasksPanel;
import tasks.NewTaskUseCase;

import javax.swing.*;

public class Main {
    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static TaskDataAccess taskDataAccess;
    //use case interaction
    static CreateTaskUseCase createTaskUseCase;
    static NewTaskUseCase newTaskUseCase;
    static JFrame mainFrame;
    private static JLayeredPane lp;
    public final static int mainWidth = 340;
    public final static int mainHeight = 500;
    static ListTasksPanel listTasks;
    static CreateTaskPanel nTaskPanel;
    public static void init()
    {
        mainFrame.setTitle("Notty");
        mainFrame.setSize(mainWidth,mainHeight);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        mainFrame.add(lp);
        lp.setLayout(null);
        listTasks.setBlocks(taskDataAccess.getAll());
        lp.add(listTasks.getPanel(),1);
        lp.add(nTaskPanel.getPanel(), 2);
    }
    public static void instanceInit()
    {
        mainFrame=new JFrame();
        lp=new JLayeredPane();
        taskDataAccess = new TaskDataAccess();
        newTaskUseCase = new NewTaskUseCase();
        database=new DBConnection();
        listTasks =new ListTasksPanel(true, taskDataAccess, newTaskUseCase);
        createTaskUseCase = new CreateTaskUseCase(listTasks);
        nTaskPanel= new CreateTaskPanel(false, createTaskUseCase);
    }
    public static void main(String[] args) {
        instanceInit();
        init();
    }
}



