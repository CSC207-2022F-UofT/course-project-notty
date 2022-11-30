package tasks;

import notes.ListNotesPanel;
import notes.Note;
import tasks.CreateTaskPanel;
import tasks.ListTasksPanel;
import tasks.Main;
import tasks.Task;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CreateTaskUseCase implements ActionListener {

    private ListTasksPanel listTasksPanel = null;
    private final ArrayList<Task> blocks;
    public CreateTaskUseCase(ListTasksPanel listTasksPanel) {
        this.listTasksPanel = listTasksPanel;
        this.blocks = listTasksPanel.blocks;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("Create")){
            if(!(CreateTaskPanel.fields[0].getText().isEmpty()) && !(CreateTaskPanel.fields[1].getText().isEmpty())){
                for(int i = 0; i< this.blocks.size(); i++) {
                    if(this.blocks.get(i).getTitle().equals(CreateTaskPanel.fields[0].getText())) {
                        JOptionPane.showMessageDialog(CreateTaskPanel.panel, "Please enter a different title!","Warning", JOptionPane.WARNING_MESSAGE);
                        CreateTaskPanel.fields[0].setText("");
                        return;
                    }
                }
                CreateTaskPanel.block= new Task(CreateTaskPanel.fields[0].getText(), CreateTaskPanel.fields[1].getText()
                        , CreateTaskPanel.fields[2].getText());
                this.blocks.add(CreateTaskPanel.block);
                this.listTasksPanel.taskDataAccess.create(CreateTaskPanel.block);
                CreateTaskPanel.fields[0].setText("");
                CreateTaskPanel.fields[1].setText("");
                CreateTaskPanel.panel.setVisible(false);
                this.listTasksPanel.addTask(CreateTaskPanel.block.getTitle(), CreateTaskPanel.block.getCategory(),CreateTaskPanel.block.getDate());
                Main.listTasks.getPanel().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(CreateTaskPanel.panel, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource().equals(CreateTaskPanel.buttons[1])){
            CreateTaskPanel.fields[0].setText("");
            CreateTaskPanel.fields[1].setText("");
            CreateTaskPanel.panel.setVisible(false);
            Main.listTasks.getPanel().setVisible(true);
        }
    }

}
