package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditNoteUseCase implements ActionListener {
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
        listNotesController.getPanel().setVisible(false);

        EditCreateNoteScreen editCreateNoteScreen = new EditCreateNoteScreen(true, listNotesController,
                listNotesController.getPanel().getLayeredPane());
        editCreateNoteScreen.showScreen(title, desc);
    }
}
