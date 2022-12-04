package notes;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

public class ListNotesScreen extends JPanel {
    private final JLayeredPane layeredPane;
    private final JPanel noteBlockPanel;
    private final JPanel pinnedBlocks;

    public ListNotesScreen(boolean visibility, JLayeredPane layeredPane){
        this.layeredPane = layeredPane;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(280,410);
        setVisible(visibility);

        this.add(Box.createVerticalGlue());
        JButton newNoteButton = new JButton("New Note");
        buttonDesign(newNoteButton, 200, 200);
        newNoteButton.addActionListener(new NewNoteUseCase(layeredPane, this));
        this.add(newNoteButton);
        this.add(Box.createVerticalGlue());

        noteBlockPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        //noteBlockPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        noteBlockPanel.setAlignmentX(CENTER_ALIGNMENT);
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200,300));
        scrollPane.setMaximumSize(new Dimension(250, scrollPane.getPreferredSize().height));
        scrollPane.setOpaque(false);
        this.add(scrollPane);
        this.add(Box.createVerticalGlue());

        pinnedBlocks = new JPanel(new GridLayout(0, 1, 100, 20));
        pinnedBlocks.setPreferredSize(new Dimension(200,200 * pinnedBlocks.getComponents().length));
        pinnedBlocks.setMaximumSize(new Dimension(250, pinnedBlocks.getPreferredSize().height));
        pinnedBlocks.setBorder(new TitledBorder("Pinned"));
        pinnedBlocks.setAlignmentX(CENTER_ALIGNMENT);
        pinnedBlocks.setVisible(false);
        pinnedBlocks.setOpaque(false);
    }
    public void buttonDesign(JButton button, int width, int height) {
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(width, height));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
    }

    public void hideScreen(){
        setVisible(false);
    }

    public void addNoteBlock(JPanel note, int index, boolean isPinned){

        if (!isPinned){
            noteBlockPanel.add(note, index);
        } else{
            pinnedBlocks.add(note, 0);
            pinnedBlocks.setPreferredSize(new Dimension(200,220 * pinnedBlocks.getComponents().length));
            pinnedBlocks.setMaximumSize(new Dimension(250, pinnedBlocks.getPreferredSize().height));
        }
    }

    public void setPinnedBlocks(){
        if (!(Arrays.equals(pinnedBlocks.getComponents(), new Component[]{} ))) {
            pinnedBlocks.setVisible(true);
            noteBlockPanel.add(pinnedBlocks, 0);
        } else {
            pinnedBlocks.setVisible(false);
            noteBlockPanel.remove(pinnedBlocks);
        }
    }

    public void deleteNoteBlock(JPanel note){
        pinnedBlocks.remove(note);
        noteBlockPanel.remove(note);
        setPinnedBlocks();
    }
    public void removeFromPinned(JPanel note){
        pinnedBlocks.remove(note);
        pinnedBlocks.revalidate();
        pinnedBlocks.repaint();
    }

    public JLayeredPane getLayeredPane(){ return layeredPane;}

}
