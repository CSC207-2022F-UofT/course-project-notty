package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCreateLabelUseCase implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("Edit")  ){
            if(!(EditCreateLabelPanel.fields[0].getText().isEmpty()) && !(EditCreateLabelPanel.fields[1].getText().isEmpty())){
                EditCreateNotePanel.block=new Note(EditCreateNotePanel.fields[0].getText(), EditCreateNotePanel.fields[1].getText());
                ListNotesPanel.blocks.add(EditCreateLabelPanel.block);
                ListNotesPanel.noteDataAccess.insert(EditCreateLabelPanel.block);
                EditCreateLabelPanel.fields[0].setText("");
                EditCreateLabelPanel.panel.setVisible(false);
                ListNotesPanel.addNote(EditCreateNotePanel.block.getTitle(), EditCreateNotePanel.block.getDescription());
                Main.listNotes.getPanel().setVisible(true);
            }
        }
        if(e.getSource().equals(EditCreateLabelPanel.buttons[1])){
            EditCreateLabelPanel.fields[0].setText("");
            EditCreateLabelPanel.panel.setVisible(false);
            Main.listNotes.getPanel().setVisible(true);
        }
    }
}
