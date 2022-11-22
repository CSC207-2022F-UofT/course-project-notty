package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListNotesPanel {
    public static JPanel panel;
    private JPanel noteBlockPanel;
    private JScrollPane sp;
    private JButton[] buttons;
    private int buttonsSize=1;
    private JLabel[] labels;
    private int labelSize=1;
    public static INoteDataAccess noteDataAccess;
    public ActionListener actionListener;
    public static ArrayList<Note> blocks;
    public ListNotesPanel(boolean visibility, INoteDataAccess noteDataAccess, ActionListener actionListener){
        this.actionListener = actionListener;
        init();
        panel.setVisible(visibility);
        ListNotesPanel.noteDataAccess = noteDataAccess;
    }
    public void setBlocks(ArrayList<Note> blocks) {
        ListNotesPanel.blocks =blocks;
        for (int i = 0; i< ListNotesPanel.blocks.size(); ++i)
        {
            addNote( blocks.get(i).getTitle(), blocks.get(i).getDescription());
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

        JButton button2 = new JButton("Label");
        button2.setVisible(true);
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1,button2,title);
                Main.nNotePanel.setFilledDes(desc);
                Main.nNotePanel.setFilledTitle(title);
                Main.listNotes.getPanel().setVisible(false);
                Main.nNotePanel.getPanel().setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1,button2,title);
            }
        });
        Main.listNotes.getNoteBlockPanel().add(button1);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1,button2,title);
                Main.listNotes.getPanel().setVisible(false);
                Main.lLabelPanel.getPanel().setVisible(true);
            }
        });
        Main.listNotes.getNoteBlockPanel().add(button2);
    }

    public static void addLabel(String label)
    {
        JButton button2 = new JButton("Label");
        button2.setVisible(true);
        button2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Main.lLabelPanel.setFilledLabel(label);
                Main.listNotes.getPanel().setVisible(false);
                Main.lLabelPanel.getPanel().setVisible(true);
            }
        });
        Main.listNotes.getNoteBlockPanel().add(button2);
    }


    public static void removeNoteFromView(JTextField field, JTextField field1, JButton button, JButton button1, JButton button2, String title)
    {
        Main.listNotes.getNoteBlockPanel().remove(field);
        Main.listNotes.getNoteBlockPanel().remove(field1);
        Main.listNotes.getNoteBlockPanel().remove(button);
        Main.listNotes.getNoteBlockPanel().remove(button1);
        Main.listNotes.getNoteBlockPanel().remove(button2);
        noteDataAccess.delete(title);
        blocks.removeIf(note -> note.getTitle().equals(title));
        Main.listNotes.getNoteBlockPanel().revalidate();
        Main.listNotes.getNoteBlockPanel().repaint();
    }
    public void init(){
        blocks=new ArrayList<Note>();
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        noteBlockPanel=new JPanel();
        noteBlockPanel.setLayout(new BoxLayout(noteBlockPanel, BoxLayout.Y_AXIS));
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);
        sp = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(15, 74, 288, 350);
        panel.add(sp);
        buttons=new JButton[buttonsSize];
        labels=new JLabel[labelSize];
        buttons[0]=new JButton("New note");
        panel.add(buttons[0]);
        buttons[0].setSize(400,400);
        buttons[0].setLayout(null);
        buttons[0].setVisible(true);
        buttons[0].setBounds(19, 19, 117, 42);
        buttons[0].addActionListener(this.actionListener);
    }

    public JPanel getPanel(){
        return this.panel;
    }

    public JPanel getNoteBlockPanel(){
        return this.noteBlockPanel;
    }
}
