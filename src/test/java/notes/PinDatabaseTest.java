package notes;

import UI.LogInScreen;
import notes.Entities.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import gateway.DBConnection;
import gateway.NoteDataAccess;

public class PinDatabaseTest {
    // tests pinUnpin method from the NoteDataAccess Class
    // also tests that a newly created note is not pinned
    private final NoteDataAccess noteDataAccess = new NoteDataAccess();

    @Test
    public void noteUnpinnedAtCreation(){
        // Tests that when a note class is made and added in the database, it is not pinned.
        new DBConnection();
        LogInScreen.usernameLogged = "chloe";

        Note note = new Note("note1", "note1desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(notes.size()-1);

        Assertions.assertFalse(noteFromDatabase.isPinned());
        noteDataAccess.delete(noteFromDatabase.getTitle());
   }

    @Test
    public void notePinnedInDatabase() {
        // Tests that when a note is pinned, the database reflects this change
        new DBConnection();
        LogInScreen.usernameLogged = "chloe";

        Note note = new Note("note2", "note2desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(notes.size()-1);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), true);
        notes = noteDataAccess.getAll();
        noteFromDatabase = notes.get(notes.size()-1);

        Assertions.assertTrue(noteFromDatabase.isPinned());
        noteDataAccess.delete(noteFromDatabase.getTitle());
    }

    @Test
    public void noteUnpinnedInDatabase() {
        // Tests that when a note is unpinned, the database reflects this change
        new DBConnection();
        LogInScreen.usernameLogged = "chloe";

        Note note = new Note("note3", "note3desc");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Note noteFromDatabase = notes.get(notes.size()-1);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), true);
        noteDataAccess.pinUnpin(noteFromDatabase.getTitle(), false);
        notes = noteDataAccess.getAll();
        noteFromDatabase = notes.get(notes.size()-1);

        Assertions.assertFalse(noteFromDatabase.isPinned());
        noteDataAccess.delete(noteFromDatabase.getTitle());
    }
}
