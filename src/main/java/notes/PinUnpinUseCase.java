package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinUnpinUseCase implements ActionListener, Pinnable {
    private final JPanel panel;
    private final JButton button;
    private final String title;

    private final ListNotesController listNotesController;


    public PinUnpinUseCase(ListNotesController listNotesController, JPanel panel, JButton button, String title){
        this.listNotesController = listNotesController;
        this.panel = panel;
        this.button = button;
        this.title = title;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (button.getText().equals("Pin")) {
            button.setText("Unpin");
            pin(title);
            listNotesController.getPanel().deleteNoteBlock(panel);
            listNotesController.getPanel().addNoteBlock(panel, 0);

        } else if (this.button.getText().equals("Unpin")){
            button.setText("Pin");
            unpin(title);
            listNotesController.getPanel().deleteNoteBlock(panel);
            listNotesController.getPanel().addNoteBlock(panel, listNotesController.blocks.size() - 1);
        }

        listNotesController.getPanel().revalidate();
        listNotesController.getPanel().repaint();
    }

    @Override
    public void pin(String title) {
        listNotesController.noteDataAccess.pinUnpin(title, true);
    }

    @Override
    public void unpin(String title){
        listNotesController.noteDataAccess.pinUnpin(title, false);
    }
}
