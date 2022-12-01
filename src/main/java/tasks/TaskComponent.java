package tasks;

import tasks.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TaskComponent  extends  JComponent{
    private final String title;
    private final String category;
    private final String dates;
    private final JPanel listTasks;
    private final ListTasksPanel listTasksPanel;
    public JPanel panel = new JPanel();


    public void init() {
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
                BorderFactory.createLineBorder(Color.black, 3)
        );
    }

    public TaskComponent(ListTasksPanel listTasksPanel, JPanel listTasks, String title, String category, String dates) {
        this.listTasksPanel = listTasksPanel;
        this.title = title;
        this.category = category;
        this.dates = dates;
        this.listTasks = listTasks;
        init();
    }

    public void createTaskPanel() {
        JTextField field = new JTextField();
        field.setText(title);
        field.setEditable(false);
        field.setVisible(true);
        field.setHorizontalAlignment(0);
        panel.add(field);

        JTextField field1 = new JTextField();
        field1.setText(category);
        field1.setEditable(false);
        field1.setVisible(true);
        panel.add(field1);

        JTextField field2 = new JTextField();
        field1.setText(dates);
        field1.setEditable(false);
        field1.setVisible(true);
        panel.add(field1);

        JButton button = new JButton("Edit");
        button.setVisible(true);
        panel.add(button);

        JButton button1 = new JButton("Delete");
        button1.setVisible(true);
        panel.add(button1);

        listTasksPanel.getTaskBlockPanel().add(panel);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTaskFromView(title);
                listTasksPanel.getTaskBlockPanel().remove(panel);
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeTaskFromView(title);
                listTasksPanel.getTaskBlockPanel().remove(panel);
                Main.nTaskPanel.setFilledCat(category);
                Main.nTaskPanel.setFilledTitle(title);
                listTasksPanel.getPanel().setVisible(false);
                Main.nTaskPanel.getPanel().setVisible(true);
            }
        });
    }

    public void removeTaskFromView(String title) {
        this.listTasksPanel.taskDataAccess.delete(title);
        listTasksPanel.blocks.removeIf(task -> task.getTitle().equals(title));
        listTasksPanel.getTaskBlockPanel().revalidate();
    }
}