package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCreateNotePanel{

    //data access layer
    public static INoteDataAccess noteDataAccess;
    private ActionListener actionListener;
    public static JPanel panel;
    public static Note block;
    public static ArrayList<Note> blocks;
    public static JButton[] buttons;
    public static int buttonSize=2;
    public static JTextField[] fields;
    public static int fieldSize=2;
    String filledTitle = "";
    String filledDes= "";
    public void setFilledTitle(String filledTitle) {
        this.filledTitle = filledTitle;
    }

    public void setFilledDes(String filledDes) {
        this.filledDes = filledDes;
    }
    private void init(){
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];
        blocks=new ArrayList<Note>();
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
    public EditCreateNotePanel(boolean visibility, INoteDataAccess noteDataAccess, ActionListener actionListener){
        init();
        this.actionListener = actionListener;
        EditCreateNotePanel.noteDataAccess = noteDataAccess;
        panel.setVisible(visibility);
        JLabel label=new JLabel("Title");
        panel.add(label);
        label.setBounds(54, 80, 201, 32);
        JLabel label1=new JLabel("Description");
        panel.add(label1);
        label1.setBounds(54, 150, 201, 32);
        addTextField(filledDes,panel, fields, 1, 56, 198, 201, 32);
        addTextField(filledTitle, panel, fields, 0, 56, 113, 201, 32);
        if(filledTitle.equals("")) {
            addTextButton("Create", panel, buttons, 0, this.actionListener, 55, 400, 92, 33);
            addTextButton("Back", panel, buttons,  1, this.actionListener, 164, 400, 92, 33);
        }else
        {
            addTextButton("Edit", panel, buttons, 0, this.actionListener, 55, 400, 92, 33);
            addTextButton("Back", panel, buttons,  1, this.actionListener, 164, 400, 92, 33);
            buttons[1].setVisible(false);
        }
        filledDes = "";
        filledTitle = "";
    }


    public void setBlocks(ArrayList<Note> blocks) {
        EditCreateNotePanel.blocks =blocks;
        for (int i = 0; i< EditCreateNotePanel.blocks.size(); ++i)
        {
            addNote( blocks.get(i).getTitle(), blocks.get(i).getDescription() );
        }
    }
    public static void addNote(String title, String desc)
    {
        JTextField field = new JTextField();
        field.setText(title);
        field.setEditable(false);
        field.setVisible(true);
        Main.listNotes.getNoteBlockPanel().add(field);
        JTextField field1 = new JTextField();
        field1.setText(desc);
        field1.setEditable(false);
        field1.setVisible(true);
        Main.listNotes.getNoteBlockPanel().add(field1);
        JButton button = new JButton("Edit");

        button.setVisible(true);
        Main.listNotes.getNoteBlockPanel().add(button);
        JButton button1 = new JButton("Delete");
        button1.setVisible(true);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1,title);
                Main.nNotePanel.setFilledDes(desc);
                Main.nNotePanel.setFilledTitle(title);
                Main.listNotes.getPanel().setVisible(false);
                Main.nNotePanel.getPanel().setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1,title);
            }
        });
        Main.listNotes.getNoteBlockPanel().add(button1);
    }
    public JPanel getPanel() {
        fields[1].setText(filledDes);
        fields[0].setText(filledTitle);
        if(filledTitle.equals("")) {
            buttons[0].setText("Create");
            buttons[1].setText("Back");
        }else
        {
            buttons[0].setText("Edit");
            buttons[1].setVisible(false);
        }
        filledDes = "";
        filledTitle = "";
        return panel;
    }
    public static void removeNoteFromView(JTextField field, JTextField field1, JButton button, JButton button1, String title)
    {
        Main.listNotes.getNoteBlockPanel().remove(field);
        Main.listNotes.getNoteBlockPanel().remove(field1);
        Main.listNotes.getNoteBlockPanel().remove(button);
        Main.listNotes.getNoteBlockPanel().remove(button1);
        noteDataAccess.delete(title);
        blocks.removeIf(note -> note.getTitle().equals(title));
        Main.listNotes.getNoteBlockPanel().revalidate();
        Main.listNotes.getNoteBlockPanel().repaint();
    }
}
