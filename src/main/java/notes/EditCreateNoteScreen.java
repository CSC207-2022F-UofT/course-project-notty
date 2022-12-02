package notes;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditCreateNoteScreen extends JPanel {
    public static JButton[] buttons;
    public static int buttonSize = 2;
    public static JTextField[] fields;
    public static int fieldSize=2;
    String filledTitle = "";
    String filledDes = "";
    private ActionListener actionListener;
    public EditCreateNoteScreen(boolean visibility, ListNotesController listNotesController){
        this.actionListener = new EditCreateUseCase(listNotesController, this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(272,410);
        setVisible(visibility);

        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];

        this.add(Box.createVerticalGlue());
        addLabel("Title");

        this.add(Box.createVerticalGlue());
        addTextField(filledTitle, fields, 0);

        this.add(Box.createVerticalGlue());
        addLabel("Description");

        this.add(Box.createVerticalGlue());
        addTextField(filledDes, fields, 1);

        this.revalidate();
        this.add(Box.createVerticalGlue());

        if(filledTitle.equals("")) {
            addTextButton("Create", buttons, 0, 100, 50);
        } else {
            addTextButton("Edit", buttons, 0, 100, 50);
        }

        this.add(Box.createVerticalGlue());
        addTextButton("Back", buttons,  1, 100, 50);
        this.add(Box.createVerticalGlue());

        filledDes = "";
        filledTitle = "";
    }
    public void addLabel(String text){
        JLabel label = new JLabel(text);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(new Color(157, 92, 242));
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        this.add(label);
    }
    public void addTextField(String prefilled, JTextField[] fields, int index){
        fields[index]= new JTextField(prefilled);
        fields[index].setPreferredSize(new Dimension(200,40));
        fields[index].setMaximumSize(new Dimension(400, fields[index].getPreferredSize().height));
        fields[index].setOpaque(false);
        fields[index].setBorder(new CompoundBorder(
                new EmptyBorder(0, 10, 0, 10),
                new LineBorder(Color.pink, 2)
        ));
        fields[index].setForeground(Color.pink);
        fields[index].setAlignmentX(CENTER_ALIGNMENT);

        this.add(fields[index]);
    }
    public void addTextButton(String text, javax.swing.JButton[] button, int index, int width, int height){
        button[index]=new javax.swing.JButton(text);
        button[index].addActionListener(this.actionListener);
        button[index].setBackground(Color.PINK);
        button[index].setAlignmentX(CENTER_ALIGNMENT);
        button[index].setMaximumSize(new Dimension(width, height));
        button[index].setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button[index].setForeground(Color.white);
        this.add(button[index]);
    }

    public void showScreen() {
        setVisible(true);
    }
    public void hideScreen(){setVisible(false);}
    public String getFilledTitle(){
        return fields[0].getText();
    }

    public String getFilledDes(){
        return fields[1].getText();
    }

    public void setFilledTitle(String newTitle){
        fields[0].setText(newTitle);
    }

    public void setFilledDes(String newTitle){
        fields[1].setText(newTitle);
    }

    public JButton getBackButton(){
        return buttons[1];
    }

}
