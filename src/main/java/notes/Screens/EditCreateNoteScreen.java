package notes.Screens;

import notes.ListNotesController;
import notes.UseCases.EditCreateUseCase;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class EditCreateNoteScreen extends JPanel {
    // this class is responsible for the screen that appears when a user wants to create a new note or edit an existing one
    public static JButton[] buttons;
    public static int buttonSize = 2;
    public static JTextField[] fields;
    public static int fieldSize=2;
    String filledTitle = "";
    String filledDes = "";
    private final ActionListener actionListener;

    public EditCreateNoteScreen( ListNotesController listNotesController){
        // this class has the EditCreateUseCase as its action listener and this observes the buttons being clicked on this screen
        this.actionListener = new EditCreateUseCase(listNotesController, this);

        // setting the layout of the screen
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBounds(0, 0, 270, 375);

        // multiple buttons and field are easier to navigate with Arrays
        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];

        // adds the title label
        this.add(Box.createVerticalGlue());
        addLabel("Title");

        // adds the field for the title
        this.add(Box.createVerticalGlue());
        addTextField(filledTitle, fields, 0);

        // adds a description for the label
        this.add(Box.createVerticalGlue());
        addLabel("Description");

        // adds the field for the description
        this.add(Box.createVerticalGlue());
        addTextField(filledDes, fields, 1);

        this.revalidate();
        this.add(Box.createVerticalGlue());

        // changes the appearance of the button depending on if a note is being created or edited
        if(filledTitle.equals("")) {
            addTextButton("Create", buttons, 0, 100, 50);
        } else {
            addTextButton("Save", buttons, 0, 100, 50);
        }

        // add the back button
        this.add(Box.createVerticalGlue());
        addTextButton("Back", buttons,  1, 100, 50);
        this.add(Box.createVerticalGlue());

        filledDes = "";
        filledTitle = "";
    }
    public void addLabel(String text){
        // creates labels for the screens
        JLabel label = new JLabel(text);
        label.setAlignmentX(CENTER_ALIGNMENT);
        label.setForeground(new Color(157, 92, 242));
        label.setFont(new Font("Calibri", Font.BOLD, 20));
        label.setVisible(true);
        this.add(label);
    }
    public void addTextField(String prefilled, JTextField[] fields, int index){
        // creates the text fields for this screen

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
        fields[index].setVisible(true);

        this.add(fields[index]);
    }

    public void addTextButton(String text, JButton[] button, int index, int width, int height){
        // creates the buttons for this screen

        button[index]=new JButton(text);
        button[index].addActionListener(this.actionListener);
        button[index].setBackground(Color.PINK);
        button[index].setAlignmentX(CENTER_ALIGNMENT);
        button[index].setMaximumSize(new Dimension(width, height));
        button[index].setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button[index].setForeground(Color.white);
        button[index].setVisible(true);
        this.add(button[index]);
    }

    public void showScreen(String title, String desc) {
        // this method makes sure that the screen displays create when a note is being created and edit if
        // it is being edited. It also makes sure that the fields are filled when a note is being edited

        fields[1].setText(desc);
        fields[0].setText(title);

        if(fields[0].getText().equals("")) {
            buttons[0].setText("Create");
            buttons[1].setText("Back");
        } else {
            buttons[0].setText("Save");
            buttons[1].setText("Back");
        }

        filledDes = "";
        filledTitle = "";
        revalidate();
        repaint();
        setVisible(true);
    }
    public void hideScreen(){setVisible(false);}        // hides the screen
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
