package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WelcomeScreen extends UIScreen{
    private JButton SignUpBtn;
    private JButton LoginBtn;
    private JPanel panel;
    private JLabel logo;
    private JLabel title;


    public WelcomeScreen() {
        setTitle("Notty");
        init();
        this.panel = super.getPanel();

        String path = "/UI/note.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        logo = new JLabel(icon);
        logo.setAlignmentX(CENTER_ALIGNMENT);
        logo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(logo);
        this.panel.add(Box.createVerticalGlue());

        title = new JLabel("Notty", JLabel.CENTER);
        title.setFont(new Font("Ravie", Font.BOLD, 48));
        title.setForeground(Color.white);
        title.setAlignmentX(CENTER_ALIGNMENT);

        this.panel.add(title);

        SignUpBtn = new JButton("Sign Up");

        SignUpBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                dispose();
                SignUpScreen frame = new SignUpScreen();
                frame.setVisible(true);

            }
        });

        LoginBtn = new JButton("Log In");

        LoginBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                dispose();
                LogInScreen frame = new LogInScreen();
                frame.setVisible(true);
            }
        });

        buttonDesign(SignUpBtn, 100, 150);
        buttonDesign(LoginBtn, 100, 150);

        this.panel.add(Box.createVerticalGlue());
        this.panel.add(SignUpBtn);
        this.panel.add(Box.createVerticalGlue());
        this.panel.add(LoginBtn);
        this.panel.add(Box.createVerticalGlue());
    }

}