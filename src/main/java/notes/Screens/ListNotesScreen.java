package notes.Screens;

import notes.UseCases.GoToTasksUseCase;
import notes.UseCases.NewNoteUseCase;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Arrays;

public class ListNotesScreen extends JPanel {
    // this class is responsible for displaying all the notes created and transitioning to the taks part of the app
    private final JLayeredPane layeredPane;
    private final JPanel noteBlockPanel;
    private final JPanel pinnedBlocks;
    private EditCreateNoteScreen editCreateScreen;

    public ListNotesScreen(boolean visibility, JFrame frame){
        this.layeredPane = frame.getLayeredPane();

        //sets up the layout of the screen
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setSize(280, 400);

        JPanel buttonPanel = new JPanel(new FlowLayout());      // creates a panel for buttons, so they are side by side

        // create a new note button
        JButton newNoteButton = new JButton("New Note");
        buttonDesign(newNoteButton, 100, 200);
        newNoteButton.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 3),
                new EmptyBorder(10, 10, 10, 10)

        ));
        // when new note button is clicked, the new note use case is called
        newNoteButton.addActionListener(new NewNoteUseCase(layeredPane, this));
        buttonPanel.add(newNoteButton);

        // create a button to transition into the tasks part of the app
        JButton tasksButton = new JButton("Tasks");
        buttonDesign(tasksButton, 100, 200);
        tasksButton.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 3),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // this action listener is responsible for taking the user to the tasks part of the program
        tasksButton.addActionListener(new GoToTasksUseCase(frame));
        buttonPanel.setBackground(Color.white);
        buttonPanel.add(tasksButton);

        this.add(buttonPanel);      // adds the buttons to this screen

        // makes a panel for storing all the notes
        noteBlockPanel = new JPanel();
        noteBlockPanel.setLayout(new BoxLayout(noteBlockPanel, BoxLayout.Y_AXIS));
        noteBlockPanel.setAlignmentX(CENTER_ALIGNMENT);
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);

        // initialize the panel where all notes are put to be a scroll pane
        JScrollPane scrollPane = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(200,280));
        scrollPane.setMaximumSize(new Dimension(250, scrollPane.getPreferredSize().height));
        scrollPane.setOpaque(false);
        this.add(scrollPane);
        this.add(Box.createVerticalGlue());

        // this is the panel created whenever the program has pinned notes
        pinnedBlocks = new JPanel();
        pinnedBlocks.setLayout(new BoxLayout(pinnedBlocks, BoxLayout.Y_AXIS));
        pinnedBlocks.setPreferredSize(new Dimension(200,200 * pinnedBlocks.getComponents().length));
        pinnedBlocks.setMaximumSize(new Dimension(250, pinnedBlocks.getPreferredSize().height));
        pinnedBlocks.setBorder(new TitledBorder("Pinned"));
        pinnedBlocks.setAlignmentX(CENTER_ALIGNMENT);
        pinnedBlocks.setVisible(false);
        pinnedBlocks.setOpaque(false);

        setVisible(visibility);
    }

    public void buttonDesign(JButton button, int width, int height) {
        // creates buttons and sets their design
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(width, height));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 1),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
    }

    public void hideScreen(){setVisible(false);}

    public void addNoteBlock(JPanel note, int index, boolean isPinned){
        // adds a note to the list of notes. if it is pinned, it will go the pinned panel
        if (!isPinned){
            noteBlockPanel.add(note);
        } else{
            pinnedBlocks.add(note, index);
            pinnedBlocks.setPreferredSize(new Dimension(200,220 * pinnedBlocks.getComponents().length));
            pinnedBlocks.setMaximumSize(new Dimension(250, pinnedBlocks.getPreferredSize().height));
        }
    }

    public void setPinnedBlocks(){
        // sets the notes properly so that they are put in the pinned panel if they are pinned
        if (!(Arrays.equals(pinnedBlocks.getComponents(), new Component[]{} ))) {
            pinnedBlocks.setVisible(true);
            noteBlockPanel.add(pinnedBlocks, 0);
        } else {
            pinnedBlocks.setVisible(false);
            noteBlockPanel.remove(pinnedBlocks);
        }
    }

    public void deleteNoteBlock(JPanel note){
        // deletes the note blocks from pinned panel if they are pinned and in noteBlock panel if they are regular ones
        pinnedBlocks.remove(note);
        pinnedBlocks.setPreferredSize(new Dimension(200,225 * pinnedBlocks.getComponents().length));
        pinnedBlocks.setMaximumSize(new Dimension(250, pinnedBlocks.getPreferredSize().height));
        noteBlockPanel.remove(note);
        setPinnedBlocks();
    }
    public void removeFromPinned(JPanel note){
        // removes the note blocks from the pinned panel
        pinnedBlocks.remove(note);
        pinnedBlocks.revalidate();
        pinnedBlocks.repaint();
    }

    public JLayeredPane getLayeredPane(){ return layeredPane;}

    public void setEditCreateScreen(EditCreateNoteScreen e){
        this.editCreateScreen = e;
    }

    public EditCreateNoteScreen getEditCreateScreen() {
        return editCreateScreen;
    }
}
