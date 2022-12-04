package UI;
import Controller.CreateAccountController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpScreen extends UIScreen{
    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton SignUpButton;
    JPasswordField Password;
    JButton GoBackToWelcome;
    JPanel panel;


    public SignUpScreen() {
        setTitle("Sign Up");
        init();
        this.panel = super.getPanel();

        this.panel.add(Box.createVerticalGlue());
        username1 = new JLabel("Username");
        uLabelDesign(username1);;
        this.panel.add(username1);
        this.panel.add(Box.createVerticalGlue());

        username = new JTextField(100);
        uFieldDesign(username);
        this.panel.add(username);
        this.panel.add(Box.createVerticalGlue());

        password1 = new JLabel("Password");
        pLabelDesign(password1);
        this.panel.add(password1);
        this.panel.add(Box.createVerticalGlue());

        Password = new JPasswordField();
        pFieldDesign(Password);
        this.panel.add(Password);
        this.panel.add(Box.createVerticalGlue());

        SignUpButton = new JButton("Sign Up");
        buttonDesign(SignUpButton, 150, 50);
        this.panel.add(SignUpButton);
        this.panel.add(Box.createVerticalGlue());

        GoBackToWelcome = new JButton("Back to Welcome");
        buttonDesign(GoBackToWelcome, 150, 50);
        this.panel.add(GoBackToWelcome);
        this.panel.add(Box.createVerticalGlue());

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
