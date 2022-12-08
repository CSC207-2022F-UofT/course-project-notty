package notes.UseCases;

import notes.ListNotesController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNoteUseCase implements ActionListener {
    // this clss is responsible for removing a note to be deleted from the view and redirecting the user to the edit screen
    private final ListNotesController listNotesController;
    private final String title;
    private final JPanel panel;
    private final String desc;

    public EditNoteUseCase(ListNotesController listNotesController, JPanel panel, String title, String desc){
        this.listNotesController = listNotesController;
        this.title = title;
        this.desc = desc;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listNotesController.removeNoteFromView(title);
        listNotesController.getPanel().deleteNoteBlock(panel);
        listNotesController.getPanel().getLayeredPane().getComponent(0).setVisible(false);

        listNotesController.getPanel().getEditCreateScreen().showScreen(title, desc);
        listNotesController.getPanel().getLayeredPane().getComponent(1).setVisible(true);
    }
}
