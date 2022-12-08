package notes.UseCases;

import notes.ListNotesController;
import notes.Pinnable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PinUnpinUseCase implements ActionListener, Pinnable {
    // this class is responsible for the pinning and unpinning of notes
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
            // if the pin button of a note is clicked,
            button.setText("Unpin");        // the text will change to unpin
            pin(title);                     // database will change it to being pinned
            panel.add(pinIcon(), 0);  // pin icon is added
            listNotesController.getPanel().deleteNoteBlock(panel);  // removes old note

            //adds note back again with added features of a pinned note
            listNotesController.getPanel().addNoteBlock(panel, 0, true);

        } else if (this.button.getText().equals("Unpin")) {
            // if the unpin button of a note is clicked,
            button.setText("Pin");      // the text will change to pin
            unpin(title);               // database will change it to being unpinned
            panel.remove(0);      // pin icon is removed
            listNotesController.getPanel().removeFromPinned(panel);     // removes old note from pinned panel

            //adds note at the bottom of the list without the features of a pinned note
            listNotesController.getPanel().addNoteBlock(panel, listNotesController.blocks.size() - 1, false);
        }
        // sets the notes up properly on the screen so that if notes are pinned, they are displayed to do so
        listNotesController.getPanel().setPinnedBlocks();
        listNotesController.getPanel().revalidate();
        listNotesController.getPanel().repaint();
    }


    @Override
    public void pin(String title) {
        // changes the isPinned attribute of a note to true in the database, so we know that is pinned
        listNotesController.noteDataAccess.pinUnpin(title, true);
    }

    @Override
    public void unpin(String title){
        // changes the isPinned attribute of a note to false in the database, so we know that is not pinned
        listNotesController.noteDataAccess.pinUnpin(title, false);
    }

    private JLabel pinIcon(){
        // creates a pin icon to be displayed on the screen
        String path = "/UI/pin.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage(); // transform it
        Image newimg = image.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING); // scale it the smooth way
        icon = new ImageIcon(newimg);

        JLabel pinLabel = new JLabel(icon, SwingConstants.RIGHT);
        pinLabel.setPreferredSize(new Dimension(300,30));
        pinLabel.setMaximumSize(new Dimension(300, pinLabel.getPreferredSize().height));
        pinLabel.setBackground(Color.black);
        return pinLabel;
    }
}
