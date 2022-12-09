package notes.UseCases;

import UI.WelcomeScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutUseCase implements ActionListener {
    // this class deals with what happens when a logout button is pressed on the notes screen
    private final JFrame notesFrame;

    public LogOutUseCase(JFrame notesFrame){
        this.notesFrame = notesFrame;       // this use case takes in the JFrame for the notes part
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.notesFrame.dispose();                  // removes the notes screen
        WelcomeScreen frame = new WelcomeScreen();  // makes a new welcome screen
        frame.setVisible(true);                     // redirects user to the welcome screen upon log out
    }
}
