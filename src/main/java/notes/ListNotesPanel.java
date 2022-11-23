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
    public static JButton[] buttons;
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
        ListNotesPanel.noteDataAccess = noteDataAccess;
    }
    public void setBlocks(ArrayList<Note> blocks) {
        ListNotesPanel.blocks = blocks;
        for (int i = 0; i< ListNotesPanel.blocks.size(); i++)
        {
            addNote( blocks.get(i).getTitle(), blocks.get(i).getDescription(), blocks.get(i).isPinned() );
        }

//        blocks.sort(new Comparator<Note>() {
//            @Override
//            public int compare(Note n1, Note n2) {
//                return n1.getDateTime().compareTo(n2.getDateTime());
//            }
//        });
    }
    public void updatePinnedBlock(NoteComponent n){
//        ArrayList<Component> notePanels = new ArrayList<>(Arrays.asList(panel.getComponents()));
//        int index = notePanels.indexOf(n);
        if (n.isPinned()) {
            panel.remove(n);
            panel.add(n);
        }
    }
    public static void addNote(String title, String desc, boolean  isPinned) {
        NoteComponent newNote = new NoteComponent(title, desc, isPinned);
        newNote.createNotePanel();
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

    public static JPanel getPanel(){
        return panel;
    }

    public static JPanel getNoteBlockPanel(){
        return noteBlockPanel;
    }
}
