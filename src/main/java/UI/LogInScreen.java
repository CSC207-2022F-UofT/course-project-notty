package UI;

import Controller.LogInController;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import notes.EditCreateNotePanel;
import notes.EditCreateUseCase;
import notes.ListNotesPanel;
import notes.Main;
import notes.NewNoteUseCase;
import notes.Note;


public class LogInScreen extends JFrame{

    JLabel password1;
    JLabel username1;
    JTextField username;
    JButton LogInButton;

    JButton GoBackToWelcome;
    JPasswordField Password;

    private JPanel panel;


    public LogInScreen() {
        super("Log In");
        init();

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
        buttonDesign(LogInButton);
        panel.add(LogInButton);
        panel.add(Box.createVerticalGlue());

        GoBackToWelcome = new JButton("Back to Welcome");
        buttonDesign(GoBackToWelcome);
        panel.add(GoBackToWelcome);
        panel.add(Box.createVerticalGlue());

        LogInButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {

                LogInController logInController = new LogInController(username.getText(), String.valueOf(Password.getPassword()));
                boolean result = logInController.create();

                if (result) {
                    // If user was successful in logging in, then the screen should change to the note screen instead.
                    Main.instanceInit();
                    Main.init();
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

    public void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(340, 500);
        setResizable(false);
        setVisible(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(160, 205, 250));
        panel.setSize(340, 480);
        panel.setBorder(new CompoundBorder(
                new EmptyBorder(20, 20, 30, 30),
                new LineBorder(new Color(157, 92, 242), 7)
        ));
        panel.setVisible(true);
        add(panel);
    }
    public void buttonDesign(JButton button){
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(150, 50));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
    }

    public void uLabelDesign(JLabel userLabel){
        userLabel.setAlignmentX(CENTER_ALIGNMENT);
        userLabel.setForeground(Color.white);
        String path = "/UI/username.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);
        userLabel.setIcon(icon);
    }

    public void pLabelDesign(JLabel pLabel){
        pLabel.setAlignmentX(CENTER_ALIGNMENT);
        pLabel.setForeground(Color.white);
        String path = "/UI/password.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(40, 40,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        icon = new ImageIcon(newimg);
        pLabel.setIcon(icon);
    }

    public void uFieldDesign(JTextField field){
        field.setPreferredSize(new Dimension(200,30));
        field.setMaximumSize(new Dimension(200, username.getPreferredSize().height));
        field.setOpaque(false);
        field.setBorder(new LineBorder(Color.pink, 2));
        field.setForeground(Color.white);
    }

    public void pFieldDesign(JPasswordField field){
        field.setPreferredSize(new Dimension(200,30));
        field.setMaximumSize(new Dimension(200, username.getPreferredSize().height));
        field.setOpaque(false);
        field.setBorder(new LineBorder(Color.pink, 2));
        field.setForeground(Color.white);
    }
}
