package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditCreateUseCase implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().toString().contains("Create") || e.getSource().toString().contains("Edit")  ){
            if(!(EditCreateNotePanel.fields[0].getText().isEmpty()) && !(EditCreateNotePanel.fields[1].getText().isEmpty())){
                for(int i=0;i<ListNotesPanel.blocks.size();i++) {
                    if(ListNotesPanel.blocks.get(i).getTitle().equals(EditCreateNotePanel.fields[0].getText())) {
                        JOptionPane.showMessageDialog(EditCreateNotePanel.panel, "Please enter a different title!","Warning", JOptionPane.WARNING_MESSAGE);
                        EditCreateNotePanel.fields[0].setText("");
                        return;
                    }
                }
                EditCreateNotePanel.block=new Note(EditCreateNotePanel.fields[0].getText(), EditCreateNotePanel.fields[1].getText());
                ListNotesPanel.blocks.add(EditCreateNotePanel.block);
                ListNotesPanel.noteDataAccess.insert(EditCreateNotePanel.block);
                EditCreateNotePanel.fields[0].setText("");
                EditCreateNotePanel.fields[1].setText("");
                EditCreateNotePanel.panel.setVisible(false);
                ListNotesPanel.addNote(EditCreateNotePanel.block.getTitle(), EditCreateNotePanel.block.getDescription());
                Main.listNotes.getPanel().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(EditCreateNotePanel.panel, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(!EditCreateNotePanel.filledTitle.equals("")&&e.getSource().toString().contains("Back"))
        {
            EditCreateNotePanel.block=new Note(EditCreateNotePanel.filledTitle, EditCreateNotePanel.filledDes);
            ListNotesPanel.blocks.add(EditCreateNotePanel.block);
            ListNotesPanel.noteDataAccess.insert(EditCreateNotePanel.block);
            ListNotesPanel.addNote(EditCreateNotePanel.block.getTitle(), EditCreateNotePanel.block.getDescription());
        }
        EditCreateNotePanel.filledTitle="";
        EditCreateNotePanel.filledDes="";
        if(e.getSource().equals(EditCreateNotePanel.buttons[1])){
            EditCreateNotePanel.fields[0].setText("");
            EditCreateNotePanel.fields[1].setText("");
            EditCreateNotePanel.panel.setVisible(false);
            Main.listNotes.getPanel().setVisible(true);
        }
    }
}
