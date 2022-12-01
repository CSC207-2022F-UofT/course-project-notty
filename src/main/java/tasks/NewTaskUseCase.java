package tasks;

import tasks.ListTasksPanel;
import tasks.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewTaskUseCase implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ListTasksPanel.panel.setVisible(false);
        Main.nTaskPanel.getPanel().setVisible(true);
        }
    }


