package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteNoteUseCase implements ActionListener {
    private final ListNotesController listNotesController;
    private final String title;
    private final JPanel panel;

    public DeleteNoteUseCase(ListNotesController listNotesController, String title, JPanel panel){
        this.listNotesController = listNotesController;
        this.title = title;
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        listNotesController.removeNoteFromView(title);
        listNotesController.getPanel().deleteNoteBlock(panel);
        listNotesController.getPanel().revalidate();
        listNotesController.getPanel().repaint();
    }
}
