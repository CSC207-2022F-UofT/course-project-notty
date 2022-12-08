package notes.UseCases;

import notes.ListNotesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteNoteUseCase implements ActionListener {
    // this use case deals with notes that are deleted by the user
    private final ListNotesController listNotesController;
    private final String title;
    private final JPanel panel;

    public DeleteNoteUseCase(ListNotesController listNotesController, String title, JPanel panel){
        this.listNotesController = listNotesController;     // takes in the controller of notes
        this.title = title;     // title of the note to be deleted
        this.panel = panel;     // the JPanel of the note to be deleted
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // this action is triggered when the delete button of a note is clicked
        // all of these methods are from listNotesController as this is the class that controls all the notes

        listNotesController.removeNoteFromView(title);              // removes note from database
        listNotesController.getPanel().deleteNoteBlock(panel);      // deletes the actual panel that contains this note
        listNotesController.getPanel().revalidate();
        listNotesController.getPanel().repaint();
    }
}
