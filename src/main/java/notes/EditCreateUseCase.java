package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCreateUseCase implements ActionListener {
    private final ListNotesPanel listNotesPanel;
    private final ArrayList<Note> blocks;

    public EditCreateUseCase(ListNotesPanel listNotesPanel){
        this.listNotesPanel = listNotesPanel;
        this.blocks = listNotesPanel.blocks;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("Create") || e.getSource().toString().contains("Edit")  ){
            if(!(EditCreateNotePanel.fields[0].getText().isEmpty()) && !(EditCreateNotePanel.fields[1].getText().isEmpty())){
                for(int i=0;i<this.blocks.size();i++) {
                    if(this.blocks.get(i).getTitle().equals(EditCreateNotePanel.fields[0].getText())) {
                        JOptionPane.showMessageDialog(EditCreateNotePanel.panel, "Please enter a different title!","Warning", JOptionPane.WARNING_MESSAGE);
                        EditCreateNotePanel.fields[0].setText("");
                        return;
                    }
                }
                EditCreateNotePanel.block=new Note(EditCreateNotePanel.fields[0].getText(), EditCreateNotePanel.fields[1].getText());
                this.blocks.add(EditCreateNotePanel.block);
                this.listNotesPanel.noteDataAccess.insert(EditCreateNotePanel.block);
                EditCreateNotePanel.fields[0].setText("");
                EditCreateNotePanel.fields[1].setText("");
                EditCreateNotePanel.panel.setVisible(false);
                this.listNotesPanel.addNote(EditCreateNotePanel.block.getTitle(), EditCreateNotePanel.block.getDescription(),
                        EditCreateNotePanel.block.isPinned());
                Main.listNotes.getPanel().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(EditCreateNotePanel.panel, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource().equals(EditCreateNotePanel.buttons[1])){
            EditCreateNotePanel.fields[0].setText("");
            EditCreateNotePanel.fields[1].setText("");
            EditCreateNotePanel.panel.setVisible(false);
            Main.listNotes.getPanel().setVisible(true);
        }
    }
}
