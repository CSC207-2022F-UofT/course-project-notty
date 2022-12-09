package notes.UseCases;

import tasks.TasksUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToTasksUseCase implements ActionListener {
    // this class redirects the user to the tasks part of the program
    private final JFrame frame;

    public GoToTasksUseCase(JFrame frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        SwingUtilities.invokeLater(TasksUI::new);       // goes to the tasks screen
        frame.dispose();    // removes the notes screen
    }
}
