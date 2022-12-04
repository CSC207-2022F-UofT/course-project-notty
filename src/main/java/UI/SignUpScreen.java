package UI;
import Controller.CreateAccountController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class SignUpScreen extends JFrame{

    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton SignUpButton;
    JPasswordField Password;

    JButton GoBackToWelcome;


    public SignUpScreen() {
        super("Sign Up");



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

        SignUpButton = new JButton("Sign Up");
        SignUpButton.setBounds(197, 150, 200, 60);
        add(SignUpButton);

        GoBackToWelcome = new JButton("Back to Welcome");
        GoBackToWelcome.setBounds(197, 220, 200, 60);
        add(GoBackToWelcome);

        SignUpButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                CreateAccountController createAccountController = new CreateAccountController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = createAccountController.create();

                if (result)
                    JOptionPane.showMessageDialog(null, "Sign Up Successful, please login to access your Notty");
                else
                    JOptionPane.showMessageDialog(null, "Username taken, please try again");


            }


        });

        GoBackToWelcome.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                WelcomeScreen frame = new WelcomeScreen();
                frame.setVisible(true);
                dispose();


            }

        });







    }




}
