package notes;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCreateUseCase implements ActionListener {
    private final ListNotesController listNotesController;
    private final EditCreateNoteScreen editCreateNoteScreen;
    private final ArrayList<Note> blocks;

    public EditCreateUseCase(ListNotesController listNotesController, EditCreateNoteScreen editCreateNoteScreen){
        this.editCreateNoteScreen = editCreateNoteScreen;
        this.listNotesController = listNotesController;
        this.blocks = listNotesController.blocks;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().toString().contains("Create") || e.getSource().toString().contains("Save")  ){
            if(!(editCreateNoteScreen.getFilledTitle().isEmpty()) && !(editCreateNoteScreen.getFilledDes().isEmpty())){
                for (Note block : this.blocks) {
                    if (block.getTitle().equals(editCreateNoteScreen.getFilledTitle())) {
                        JOptionPane.showMessageDialog(editCreateNoteScreen, "Please enter a different title!", "Warning", JOptionPane.WARNING_MESSAGE);
                        editCreateNoteScreen.setFilledTitle("");
                        return;
                    }
                }
                Note note = new Note(editCreateNoteScreen.getFilledTitle(), editCreateNoteScreen.getFilledDes());
                this.blocks.add(note);
                listNotesController.noteDataAccess.insert(note);

                editCreateNoteScreen.setFilledTitle("");
                editCreateNoteScreen.setFilledDes("");
                editCreateNoteScreen.hideScreen();

                listNotesController.addNote(note.getTitle(), note.getDescription(), note.isPinned());
                listNotesController.getPanel().revalidate();
                listNotesController.getPanel().repaint();
                listNotesController.getPanel().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(editCreateNoteScreen, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }

        if(e.getSource().equals(editCreateNoteScreen.getBackButton())){
            editCreateNoteScreen.setFilledTitle("");
            editCreateNoteScreen.setFilledDes("");
            editCreateNoteScreen.hideScreen();

            listNotesController.getPanel().revalidate();
            listNotesController.getPanel().repaint();
            listNotesController.getPanel().setVisible(true);
        }
    }
}
