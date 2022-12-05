package notes;

import javax.swing.*;
import java.awt.*;
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
            panel.add(pinIcon(), 0);
            listNotesController.getPanel().deleteNoteBlock(panel);
            listNotesController.getPanel().addNoteBlock(panel, 0, true);

        } else if (this.button.getText().equals("Unpin")) {
            button.setText("Pin");
            unpin(title);
            panel.remove(0);
            listNotesController.getPanel().removeFromPinned(panel);
            listNotesController.getPanel().addNoteBlock(panel, listNotesController.blocks.size() - 1, false);
        }
        listNotesController.getPanel().setPinnedBlocks();
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

    private JLabel pinIcon(){
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
