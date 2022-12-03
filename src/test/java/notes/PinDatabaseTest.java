package notes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import gateway.DBConnection;
import gateway.NoteDataAccess;
import notes.Note;

public class PinDatabaseTest {
    // tests pinUnpin method from the NoteDataAccess Class
    // also tests that a newly created note is not pinned

    private DBConnection database = new DBConnection();
    private NoteDataAccess noteDataAccess = new NoteDataAccess();

    @Test
    public void noteUnpinnedAtCreation(){
        // Tests that when a note class is made and added in the database, it is not pinned.

        Note note = new Note("note1", "note1desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(0);
        
        Assertions.assertFalse(noteFromDatabase.isPinned());
    }

    @Test
    public void notePinnedInDatabase() {
        // Tests that when a note is pinned, the database reflects this change

        Note note = new Note("note2", "note2desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(1);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), true);

        Assertions.assertTrue(noteFromDatabase.isPinned());
    }

    @Test
    public void noteUnpinnedInDatabase() {
        // Tests that when a note is unpinned, the database reflects this change

        Note note = new Note("note3", "note3desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(2);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), true);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), false);

        Assertions.assertFalse(noteFromDatabase.isPinned());
    }
}
