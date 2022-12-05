package UI;

import Controller.LogInController;
import notes.NotesScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogInScreen extends UIScreen{
    public static String usernameLogged;
    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton LogInButton;

    JButton GoBackToWelcome;
    JPasswordField Password;


    public LogInScreen() {
        setTitle("LogIn");
        init();
        JPanel panel = super.getPanel();

        panel.add(Box.createVerticalGlue());
        username1 = new JLabel("Username");
        uLabelDesign(username1);;
        panel.add(username1);
        panel.add(Box.createVerticalGlue());

        username = new JTextField(100);
        uFieldDesign(username);
        panel.add(username);
        panel.add(Box.createVerticalGlue());

        password1 = new JLabel("Password");
        pLabelDesign(password1);
        panel.add(password1);
        panel.add(Box.createVerticalGlue());

        Password = new JPasswordField();
        pFieldDesign(Password);
        panel.add(Password);
        panel.add(Box.createVerticalGlue());

        LogInButton = new JButton("Login");
        buttonDesign(LogInButton, 150, 50);
        panel.add(LogInButton);
        panel.add(Box.createVerticalGlue());

        GoBackToWelcome = new JButton("Back to Welcome");
        buttonDesign(GoBackToWelcome, 150, 50);
        panel.add(GoBackToWelcome);
        panel.add(Box.createVerticalGlue());

        LogInButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                LogInController logInController = new LogInController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = logInController.create();

                if (result) {
                    // If user was successful in logging in, then the screen should change to the note screen instead.
                    usernameLogged = username.getText();
                    dispose();
                    new NotesScreen();
                }
                else
                    JOptionPane.showMessageDialog(null, "Wrong username and/or password combination- try again!");
            }

        });

        GoBackToWelcome.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                WelcomeScreen frame = new WelcomeScreen();
                frame.setVisible(true);
            }

        });
    }

}
