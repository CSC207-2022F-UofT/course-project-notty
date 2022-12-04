package UI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class UIScreen extends JFrame {
    protected JPanel panel;

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
                new EmptyBorder(20, 20, 20, 20),
                new LineBorder(new Color(157, 92, 242), 7)
        ));
        panel.setVisible(true);
        add(panel);
    }

    public void buttonDesign(JButton button, int width, int height) {
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(width, height));
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
        field.setMaximumSize(new Dimension(200, field.getPreferredSize().height));
        field.setOpaque(false);
        field.setBorder(new LineBorder(Color.pink, 2));
        field.setForeground(Color.white);
    }

    public void pFieldDesign(JPasswordField field){
        field.setPreferredSize(new Dimension(200,30));
        field.setMaximumSize(new Dimension(200, field.getPreferredSize().height));
        field.setOpaque(false);
        field.setBorder(new LineBorder(Color.pink, 2));
        field.setForeground(Color.white);
    }

    public JPanel getPanel() {
        return panel;
    }
}
