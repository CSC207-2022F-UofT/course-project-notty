package UI;

import Controller.LogInController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import notes.App;
import notes.NotesScreen;


public class LogInScreen extends JFrame{

    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton LogInButton;

    JButton GoBackToWelcome;
    JPasswordField Password;


    public LogInScreen() {
        super("Log In");



        setLayout(null);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        username1 = new JLabel("Username");
        username1.setBounds(200, 8, 70, 20);
        add(username1);

        username = new JTextField();
        username.setBounds(200, 27, 210, 40);
        add(username);

        password1 = new JLabel("Password");
        password1.setBounds(200, 71, 70, 20);
        add(password1);

        Password = new JPasswordField();
        Password.setBounds(200, 90, 210, 40);
        add(Password);

        LogInButton = new JButton("Login");
        LogInButton.setBounds(197, 150, 200, 60);
        add(LogInButton);

        GoBackToWelcome = new JButton("Back to Welcome");
        GoBackToWelcome.setBounds(197, 220, 200, 60);
        add(GoBackToWelcome);







       // GoBackToWelcome = new JButton("Back to Welcome Screen");
        //add(GoBackToWelcome,  BorderLayout.WEST);

        LogInButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                LogInController logInController = new LogInController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = logInController.create();

                if (result) {
                    // If user was successful in logging in, then the screen should change to the note screen instead.
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
