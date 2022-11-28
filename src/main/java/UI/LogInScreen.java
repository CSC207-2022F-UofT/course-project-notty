package UI;

import Controller.LogInController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class LogInScreen extends JFrame{

    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton LogInButton;
    JPasswordField Password;


    public LogInScreen() {
        super("Log In");



        setLayout(new BorderLayout());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        username1 = new JLabel("Username");
        username1.setBounds(200, 8, 70, 20);
        add(username1);

        username = new JTextField();
        username.setBounds(200, 27, 193, 28);
        add(username);

        password1 = new JLabel("Password");
        password1.setBounds(200, 55, 70, 20);
        add(password1);

        Password = new JPasswordField();
        Password.setBounds(200, 75, 193, 28);
        add(Password);

        LogInButton = new JButton("Login");
        add(LogInButton,  BorderLayout.CENTER);

        LogInButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                LogInController logInController = new LogInController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = logInController.create();

                if (result)
                    JOptionPane.showMessageDialog(null, "Login Successful");
                else
                    JOptionPane.showMessageDialog(null, "Wrong username and/or password combination- try again!");


            }


        });





    }


}
