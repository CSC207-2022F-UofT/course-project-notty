package notes;

import tasks.TasksUI;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class ListNotesScreen extends JPanel {
    private final JLayeredPane layeredPane;
    private final JPanel noteBlockPanel;
    private final JPanel pinnedBlocks;
    private static JFrame frame;

    public ListNotesScreen(boolean visibility, JFrame frame){
        this.frame = frame;
        this.layeredPane = frame.getLayeredPane();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(280, 400);
        setVisible(visibility);
;
        JPanel buttonPanel = new JPanel(new FlowLayout());

//        this.add(Box.createVerticalGlue());
        JButton newNoteButton = new JButton("New Note");
        buttonDesign(newNoteButton, 100, 200);
        newNoteButton.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 7),
                new EmptyBorder(10, 10, 10, 10)

        ));
        newNoteButton.addActionListener(new NewNoteUseCase(this.layeredPane, this));
        buttonPanel.add(newNoteButton);

        JButton tasksButton = new JButton("Tasks");
        buttonDesign(tasksButton, 100, 200);
        tasksButton.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 7),
                new EmptyBorder(10, 10, 10, 10)

        ));
        tasksButton.addActionListener(new ListenForTaskButton());
        buttonPanel.add(tasksButton);
        this.add(buttonPanel);

        noteBlockPanel = new JPanel();
        noteBlockPanel.setLayout(new BoxLayout(noteBlockPanel, BoxLayout.Y_AXIS));
        noteBlockPanel.setAlignmentX(CENTER_ALIGNMENT);
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);

        JScrollPane scrollPane = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200,280));
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
    private static class ListenForTaskButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            SwingUtilities.invokeLater(TasksUI::new);
            frame.dispose();
        }
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
            noteBlockPanel.add(note);
            noteBlockPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        } else{
            pinnedBlocks.add(note, index);
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
