package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.util.ArrayList;

public class ListNotesController {
    public INoteDataAccess noteDataAccess;
    private final ListNotesScreen listNotesScreen;

    public ArrayList<Note> blocks;
    public ListNotesController(INoteDataAccess noteDataAccess, ListNotesScreen listNotesScreen){
        this.blocks = new ArrayList<Note>();
        this.noteDataAccess = noteDataAccess;
        this.listNotesScreen = listNotesScreen;
    }
    public void setBlocks(ArrayList<Note> blocks) {
        this.blocks = blocks;
        for (Note block : blocks) {
            addNote(block.getTitle(), block.getDescription(), block.isPinned());
        }
    }

    public void addNote(String title, String desc, boolean isPinned) {
        NoteComponent newNote = new NoteComponent(this, title, desc, isPinned);
        JPanel panel = newNote.createNotePanel();
        listNotesScreen.addNoteBlock(panel, blocks.size() - 1);
    }
    public ListNotesScreen getPanel(){
        return listNotesScreen;
    }

    public void removeNoteFromView(String title) {
        noteDataAccess.delete(title);
        blocks.removeIf(note -> note.getTitle().equals(title));
        getPanel().revalidate();
    }

}
