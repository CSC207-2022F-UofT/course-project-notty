package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinUnpinUseCase implements ActionListener, Pinnable {
    private final JPanel panel;
    private final JButton button;
    private final String title;
    private final JPanel listPanel;
    private final ListNotesPanel listNotesPanel;


    public PinUnpinUseCase(ListNotesPanel listNotesPanel, JPanel listPanel, JPanel panel, JButton button, String title){
        this.listNotesPanel = listNotesPanel;
        this.listPanel = listPanel;
        this.panel = panel;
        this.button = button;
        this.title = title;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.button.getText().equals("Pin")) {
            this.button.setText("Unpin");
            pin(title);
            this.listPanel.remove(this.panel);
            this.listPanel.add(this.panel, 0);

        } else if (this.button.getText().equals("Unpin")){
            this.button.setText("Pin");
            unpin(title);
            this.listPanel.remove(this.panel);
            this.listPanel.add(this.panel, this.listNotesPanel.blocks.size() - 1);
        }
    }

    @Override
    public void pin(String title) {
        listNotesPanel.noteDataAccess.pinUnpin(title, true);
    }

    @Override
    public void unpin(String title){
        listNotesPanel.noteDataAccess.pinUnpin(title, false);
    }
}
