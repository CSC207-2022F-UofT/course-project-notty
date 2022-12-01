package UI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;


public class WelcomeScreen extends JFrame{
    private JButton SignUpBtn;
    private JButton LoginBtn;
    private JPanel panel;
    private JLabel logo;
    private JLabel title;


    public WelcomeScreen() {
        super("Notty");
        init();

        SignUpBtn = new JButton("Sign Up");

        SignUpBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                SignUpScreen frame = new SignUpScreen();
                frame.setVisible(true);

            }
        });

        LoginBtn = new JButton("Log In");

        LoginBtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                //this method is run whenever the button is clicked
                LogInScreen frame = new LogInScreen();
                frame.setVisible(true);


            }
        });

        buttonDesign(SignUpBtn);
        buttonDesign(LoginBtn);

        panel.add(Box.createVerticalGlue());
        panel.add(SignUpBtn);
        panel.add(Box.createVerticalGlue());
        panel.add(LoginBtn);
        panel.add(Box.createVerticalGlue());
    }

    public void init(){
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setLocationRelativeTo(null);
        setSize(340, 500);
        setResizable(false);
        setVisible(true);

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,  BoxLayout.Y_AXIS));
        panel.setBackground(new Color(160, 205, 250));
        panel.setSize(340, 480);
        panel.setBorder(new CompoundBorder(
                new EmptyBorder(20,20, 30, 30),
                new LineBorder(new Color(157, 92, 242), 7)
        ));
        panel.setVisible(true);
        add(panel);

        String path = "/UI/note.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        logo = new JLabel(icon);
        logo.setAlignmentX(CENTER_ALIGNMENT);
        logo.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel.add(Box.createVerticalGlue());
        panel.add(logo);
        panel.add(Box.createVerticalGlue());

        title = new JLabel("Notty", JLabel.CENTER);
        title.setFont(new Font("Ravie", Font.BOLD, 48));
        title.setForeground(Color.white);
        title.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(title);

    }

    public void buttonDesign(JButton button){
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(100, 50));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
    }
}