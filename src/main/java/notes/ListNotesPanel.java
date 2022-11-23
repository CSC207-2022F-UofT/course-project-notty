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
    public static void updatePinnedBlocks() {
        ArrayList<Note> blocks = ListNotesPanel.blocks;

        for (Note b : blocks) {
            if (b.isPinned()) {
                blocks.remove(b);
                blocks.add(0, b);
            }
        }
    }
    public static void addNote(String title, String desc, boolean  isPinned)
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
                removeNoteFromView(field,field1,button,button1, buttons[1], title);
                Main.nNotePanel.setFilledDes(desc);
                Main.nNotePanel.setFilledTitle(title);
                Main.listNotes.getPanel().setVisible(false);
                Main.nNotePanel.getPanel().setVisible(true);
            }
        });
        button1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(field,field1,button,button1, buttons[1], title);
            }
        });
        Main.listNotes.getNoteBlockPanel().add(button1);

        String pinUnpinText;
        //create pin/unpin button
            if (isPinned){
                pinUnpinText = "Unpin";
            } else {
                pinUnpinText = "Pin";
            }

        JButton button2 = new JButton(pinUnpinText);
        button2.setVisible(true);
        Main.listNotes.getNoteBlockPanel().add(button2);

        button2.addActionListener(new PinUseCase() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button2.getText().equals("Pin")) {
                    button2.setText("Unpin");
                    pinUnpinNote(title, true);
                } else if (button2.getText().equals("Unpin")){
                    button2.setText("Pin");
                    pinUnpinNote(title, false);
                }
            }
        });

    }

    public static void pinUnpinNote(String title, boolean isPinned){
        noteDataAccess.pinUnpin(title, isPinned);
    }

    public static void removeNoteFromView(JTextField field, JTextField field1, JButton button, JButton button1,
                                          JButton button2, String title)
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
