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


    public SignUpScreen() {
        super("Sign Up");



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

        SignUpButton = new JButton("Sign Up");
        add(SignUpButton,  BorderLayout.CENTER);

        SignUpButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                CreateAccountController createAccountController = new CreateAccountController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = createAccountController.create();

                if (result)
                    JOptionPane.showMessageDialog(null, "Sign Up Successful");
                else
                    JOptionPane.showMessageDialog(null, "Username taken, please try again");


            }


        });





    }




}
