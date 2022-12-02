package notes;

import UI.UIScreen;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ListNotesScreen extends JPanel {
    private final JLayeredPane layeredPane;
    private JPanel noteBlockPanel;
    private JButton newNoteButton;
    public ListNotesScreen(boolean visibility, JLayeredPane layeredPane){
        this.layeredPane = layeredPane;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(280,410);
        setVisible(visibility);

        this.add(Box.createVerticalGlue());
        newNoteButton = new JButton("New Note");
        buttonDesign(newNoteButton, 200, 200);
        newNoteButton.addActionListener(new NewNoteUseCase(layeredPane, this));
        this.add(newNoteButton);
        this.add(Box.createVerticalGlue());

        noteBlockPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        noteBlockPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        noteBlockPanel.setAlignmentX(CENTER_ALIGNMENT);
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);


        JScrollPane scrollPane = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200,300));
        scrollPane.setMaximumSize(new Dimension(250, scrollPane.getPreferredSize().height));
        scrollPane.setOpaque(false);
        this.add(scrollPane);
        this.add(Box.createVerticalGlue());
    }

    public void buttonDesign(JButton button, int width, int height) {
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(width, height));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
    }
    public void hideScreen(){
        setVisible(false);
    }
    public void addNoteBlock(JPanel note, int index){
        noteBlockPanel.add(note, index);
    }
    public void deleteNoteBlock(JPanel note){
        noteBlockPanel.remove(note);
    }
    public JLayeredPane getLayeredPane(){ return layeredPane;}

}
