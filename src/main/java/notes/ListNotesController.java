package notes;
import gateway.INoteDataAccess;
import notes.Entities.Note;
import notes.Entities.NoteBlock;
import notes.Screens.ListNotesScreen;

import javax.swing.*;
import java.util.ArrayList;

public class ListNotesController {
    public INoteDataAccess noteDataAccess;
    private final ListNotesScreen listNotesScreen;
    public ArrayList<Note> blocks;

    public ListNotesController(INoteDataAccess noteDataAccess, ListNotesScreen listNotesScreen){
        // controls what happens to the list of notes

        this.blocks = new ArrayList<>();        // will store all notes in an array
        this.noteDataAccess = noteDataAccess;       // access to database
        this.listNotesScreen = listNotesScreen;     // access to the UI screen
    }
    public void setBlocks(ArrayList<Note> blocks) {
        // takes in a list of notes and transforms them into a note block
        this.blocks = blocks;       // sets a variable to hold all the notes

        // each note is made into a note block
        for (Note block : blocks) {
            addNote(block.getTitle(), block.getDescription(), block.isPinned());
        }

        listNotesScreen.setPinnedBlocks();
    }

    public void addNote(String title, String desc, boolean isPinned) {
        // a note block panel is created and added to the screen
        NoteBlock newNote = new NoteBlock(this, title, desc, isPinned);
        JPanel panel = newNote.createNotePanel();

        listNotesScreen.addNoteBlock(panel,0, isPinned);
    }

    public ListNotesScreen getPanel(){
        return listNotesScreen;
    }

    public void removeNoteFromView(String title) {
        // removes a note block from the list of notes
        noteDataAccess.delete(title);       //removes the note from the database
        blocks.removeIf(note -> note.getTitle().equals(title));     // removes note from the list of notes
        getPanel().revalidate();            // changes what the user sees
    }

}
