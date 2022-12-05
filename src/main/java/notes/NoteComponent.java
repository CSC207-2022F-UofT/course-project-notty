package notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NoteComponent extends JComponent {
    private final String title;
    private final String desc;

    private Item item;
    private final boolean isPinned;
    private final JPanel listNotes;
    private final ListNotesPanel listNotesPanel;
    public JPanel panel = new JPanel();
    Random rand = new Random();
    float r = rand.nextFloat();
    float g = rand.nextFloat();
    float b = rand.nextFloat();

    public void init() {
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(
                BorderFactory.createLineBorder(Color.black, 3)
        );
        panel.setBackground(new Color(r, g, b));

    }

    public NoteComponent(ListNotesPanel listNotesPanel, JPanel listNotes, String title, String desc, boolean isPinned) {
        this.listNotesPanel = listNotesPanel;
        this.title = title;
        this.desc = desc;
        this.isPinned = isPinned;
        this.listNotes = listNotes;
        this.item = item;
        init();
    }

    public void createNotePanel() {
        JTextField field = new JTextField();
        field.setText(title);
        field.setEditable(false);
        field.setVisible(true);
        ;
        field.setHorizontalAlignment(0);
        panel.add(field);

        JTextField field1 = new JTextField();
        field1.setText(desc);
        field1.setEditable(false);
        field1.setVisible(true);
        panel.add(field1);

        JTextField field2 = new JTextField();
        field2.setText("Add your tag here: " + String.valueOf(item));
        field2.setEditable(true);
        field2.setVisible(true);
        panel.add(field2);

        JButton button = new JButton("Edit");
        button.setVisible(true);
        panel.add(button);

        JButton button1 = new JButton("Delete");
        button1.setVisible(true);
        panel.add(button1);

        String pinUnpinText;
        //create pin/unpin button
        if (isPinned) {
            pinUnpinText = "Unpin";
        } else {
            pinUnpinText = "Pin";
        }

        JButton button2 = new JButton(pinUnpinText);
        button2.setVisible(true);
        panel.add(button2);

        listNotesPanel.getNoteBlockPanel().add(panel);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(title);
                listNotesPanel.getNoteBlockPanel().remove(panel);
            }
        });
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeNoteFromView(title);
                listNotesPanel.getNoteBlockPanel().remove(panel);
                Main.nNotePanel.setFilledDes(desc);
                Main.nNotePanel.setFilledTitle(title);
                listNotesPanel.getPanel().setVisible(false);
                Main.nNotePanel.getPanel().setVisible(true);
            }
        });
        button2.addActionListener(new PinUnpinUseCase(listNotesPanel, listNotes, panel, button2, title));
    }

    public void removeNoteFromView(String title) {
        this.listNotesPanel.noteDataAccess.delete(title);
        ListNotesPanel.blocks.removeIf(note -> note.getTitle().equals(title));
        listNotesPanel.getNoteBlockPanel().revalidate();
    }
}

