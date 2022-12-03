package notes;
import gateway.INoteDataAccess;

import javax.swing.*;
import java.util.ArrayList;

public class ListNotesController {
    public INoteDataAccess noteDataAccess;
    private final ListNotesScreen listNotesScreen;
    public ArrayList<Note> blocks;

    public ListNotesController(INoteDataAccess noteDataAccess, ListNotesScreen listNotesScreen){
        // controls what happens to the list of notes

        this.blocks = new ArrayList<Note>();        // will store all notes in an array
        this.noteDataAccess = noteDataAccess;       // access to database
        this.listNotesScreen = listNotesScreen;     // access to the UI screen
    }
    public void setBlocks(ArrayList<Note> blocks) {
        // takes in a list of notes and transforms them into a note block
//        ArrayList<NoteBlock> noteBlocks = new ArrayList<>();
        this.blocks = blocks;       // sets a variable to hold all the notes

        // each note is made into a note block
        for (Note block : blocks) {
            NoteBlock panel = addNote(block.getTitle(), block.getDescription(), block.isPinned());
//            noteBlocks.add(panel);
        }

//        for (NoteBlock block : noteBlocks){
//            if (block.isPinned()) {
//                new PinUnpinUseCase(this, block.getPanel(), block.getPinButton(), block.getTitle());
//            }
//        }
    }

    public NoteBlock addNote(String title, String desc, boolean isPinned) {
        // a note block panel is created and added to the screen
        NoteBlock newNote = new NoteBlock(this, title, desc, isPinned);
        JPanel panel = newNote.createNotePanel();

        listNotesScreen.addNoteBlock(panel, 0);

        return newNote;
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
