package tasks;

import gateway.ITaskDataAccess;
import tasks.Main;
import tasks.Task;
import tasks.TaskComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListTasksPanel {
    public static JPanel panel;
    private static JPanel taskBlockPanel;
    private JScrollPane sp;
    private JButton[] buttons;
    private int buttonsSize=1;
    private JLabel[] labels;
    private int labelSize=1;
    public static ITaskDataAccess taskDataAccess;
    public ActionListener actionListener;
    public static ArrayList<Task> blocks;

    public ListTasksPanel(boolean visibility, ITaskDataAccess taskDataAccess, ActionListener actionListener){
        this.actionListener = actionListener;
        init();
        panel.setVisible(visibility);
        this.taskDataAccess = taskDataAccess;
    }
    public void setBlocks(ArrayList<Task> blocks) {
        this.blocks =blocks;
        for (Task block : blocks) {
            addTask( block.getTitle(), block.getCategory(), block.getDate());
        }
    }
    public void addTask(String title, String category, String dates)
    {
        TaskComponent newTask = new TaskComponent(this, taskBlockPanel, title, category, dates);
        newTask.createTaskPanel();
    }

    public void init(){
        blocks=new ArrayList<Task>();
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        taskBlockPanel=new JPanel();
        taskBlockPanel.setLayout(new BoxLayout(taskBlockPanel, BoxLayout.Y_AXIS));
        taskBlockPanel.setVisible(true);
        taskBlockPanel.setOpaque(false);
        sp = new JScrollPane(taskBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(15, 74, 288, 350);
        panel.add(sp);
        buttons=new JButton[buttonsSize];
        labels=new JLabel[labelSize];
        buttons[0]=new JButton("New task");
        panel.add(buttons[0]);
        buttons[0].setSize(400,400);
        buttons[0].setLayout(null);
        buttons[0].setVisible(true);
        buttons[0].setBounds(19, 19, 117, 42);
        buttons[0].addActionListener(this.actionListener);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public JPanel getTaskBlockPanel(){
        return this.taskBlockPanel;
    }
}
