package UI;
import Controller.CreateAccountController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class WelcomeScreen extends JFrame{


    private JButton SignUpBtn;
    private JButton LoginBtn;






    public WelcomeScreen() {
        super("Welcome to Notty");

        setLayout(null);


        SignUpBtn = new JButton("Sign Up");
        SignUpBtn.setBounds(215, 19, 200, 60);

        LoginBtn = new JButton("Log In");
        LoginBtn.setBounds(215, 90, 200, 60);

        SignUpBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                SignUpScreen frame = new SignUpScreen();
                frame.setVisible(true);

            }
        });



        LoginBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                LogInScreen frame = new LogInScreen();
                frame.setVisible(true);


            }
        });


        add(SignUpBtn);
        add(LoginBtn);
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}