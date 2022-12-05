package notes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteUseCase implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ListNotesPanel.panel.setVisible(false);
        Main.nNotePanel.getPanel().setVisible(true);
    }
}
