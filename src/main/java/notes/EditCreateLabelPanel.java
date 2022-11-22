package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCreateLabelPanel{

    //data access layer
    private ActionListener actionListener;
    public static JPanel panel;
    public static Note block;
    public static JButton[] buttons;
    public static int buttonSize=2;
    public static JTextField[] fields;
    public static int fieldSize=2;
    String filledLabel = "";
    public void setFilledLabel(String filledLabel) {
        this.filledLabel = filledLabel;
    }

    private void init(){
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];
    }
    public void addTextField(String prefilled, javax.swing.JPanel panel,javax.swing.JTextField[] fields,int index,int x,int y,int width,int height ){
        fields[index]=new javax.swing.JTextField(prefilled);
        panel.add(fields[index]);
        fields[index].setBounds(x, y, width, height);
        panel.revalidate();
    }
    public void addTextButton(String text, javax.swing.JPanel panel, javax.swing.JButton[] button, int index, java.awt.event.ActionListener al, int x, int y, int width, int height ){
        button[index]=new javax.swing.JButton(text);
        panel.add(button[index]);
        button[index].setBounds(x, y, width, height);
        button[index].addActionListener(al);
        panel.revalidate();
    }
    public EditCreateLabelPanel(boolean visibility, ActionListener actionListener){
        init();
        this.actionListener = actionListener;
        panel.setVisible(visibility);
        JLabel label=new JLabel("Label");
        panel.add(label);
        label.setBounds(54, 80, 201, 32);
        addTextField(filledLabel, panel, fields, 0, 56, 113, 201, 32);
        if(filledLabel.equals("")) {
            addTextButton("Create", panel, buttons, 0, this.actionListener, 55, 400, 92, 33);
            addTextButton("Back", panel, buttons,  1, this.actionListener, 164, 400, 92, 33);
        }else
        {
            addTextButton("Edit", panel, buttons, 0, this.actionListener, 55, 400, 92, 33);
            addTextButton("Back", panel, buttons,  1, this.actionListener, 164, 400, 92, 33);
            buttons[1].setVisible(false);
        }
        filledLabel = "";
    }

    public JPanel getPanel() {
        fields[0].setText(filledLabel);
        if(filledLabel.equals("")) {
            buttons[0].setText("Create");
            buttons[1].setText("Back");
        }else
        {
            buttons[0].setText("Edit");
            buttons[1].setVisible(false);
        }
        filledLabel = "";
        return panel;
    }

}
