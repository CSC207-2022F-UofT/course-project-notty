package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class ListNotesPanel {
    public static JPanel panel;
    private static JPanel noteBlockPanel;
    private JScrollPane sp;
    private JButton[] buttons;
    private int buttonsSize=2;
    private JLabel[] labels;
    private int labelSize=1;
    public static INoteDataAccess noteDataAccess;

    public ActionListener actionListener;
    public static ArrayList<Note> blocks;
    public ListNotesPanel(boolean visibility, INoteDataAccess noteDataAccess, ActionListener actionListener){
        this.actionListener = actionListener;
        init();
        panel.setVisible(visibility);
        this.noteDataAccess = noteDataAccess;
    }

    public ListNotesPanel() {

    }

    public void setBlocks(ArrayList<Note> blocks) {
        this.blocks = blocks;
        for (Note block : blocks) {
            addNote(block.getTitle(), block.getDescription(), block.isPinned());
        }
    }

    public void addNote(String title, String desc, boolean isPinned) {
        NoteComponent newNote = new NoteComponent(this, noteBlockPanel, title, desc, isPinned);
        newNote.createNotePanel();
    }

    private void init(){
        blocks=new ArrayList<Note>();
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);

        noteBlockPanel=new JPanel(new GridLayout(0, 1, 10, 10));
        noteBlockPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);
        JScrollPane sp = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(15, 74, 288, 350);
        panel.add(sp);
        buttons=new JButton[buttonsSize];
        int labelSize = 1;
        JLabel[] labels = new JLabel[labelSize];
        buttons[0]=new JButton("New note");
        panel.add(buttons[0]);
        buttons[0].setSize(400,400);
        buttons[0].setLayout(null);
        buttons[0].setVisible(true);
        buttons[0].setBounds(19, 19, 117, 42);
        buttons[0].addActionListener(this.actionListener);
        buttons[1]=new JButton("Tasks");
        panel.add(buttons[1]);
        buttons[1].setSize(400,400);
        buttons[1].setLayout(null);
        buttons[1].setVisible(true);
        buttons[1].setBounds(200, 19, 117, 42);
        //buttons[1].addActionListener(this.actionListener);
    }


    public JPanel getPanel(){
        return panel;
    }

    public JPanel getNoteBlockPanel(){
        return noteBlockPanel;
    }
}