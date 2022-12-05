package notes;
import gateway.INoteDataAccess;
import tasks.TasksUI;

import javax.swing.*;
import java.awt.*;
import java.awt.Component;
import java.awt.event.*;
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
    private JTextField search;
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

    public static void addNote(String title, String description) {
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

    private EventTags event;


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
        int JTextFieldSize = 1;
        JTextField search = new JTextField("Search here");
        panel.add(search);
        search.setSize(400,400);
        search.setLayout(null);
        search.setVisible(true);
        search.setBounds(19, 5, 293, 20);
        search.setVisible(true);
        buttons[0]=new JButton("New note");
        panel.add(buttons[0]);
        buttons[0].setSize(400,400);
        buttons[0].setLayout(null);
        buttons[0].setVisible(true);
        buttons[0].setBounds(19, 29, 117, 42);
        buttons[0].addActionListener(this.actionListener);
        buttons[1]=new JButton("Tasks");
        buttons[1].addActionListener(new ListenForTaskButton());
        panel.add(buttons[1]);
        buttons[1].setSize(400,400);
        buttons[1].setLayout(null);
        buttons[1].setVisible(true);
        buttons[1].setBounds(200, 29, 117, 42);
        //buttons[1].addActionListener(this.actionListener);

    }

    public JPanel getPanel(){
        return panel;
    }

    public JPanel getNoteBlockPanel(){
        return noteBlockPanel;
    }

    private class ListenForTaskButton implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(TasksUI::new);
            Main.mainFrame.dispose();
        }
    }

}