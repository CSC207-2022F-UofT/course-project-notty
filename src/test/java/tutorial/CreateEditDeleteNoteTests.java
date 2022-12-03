package tutorial;

import UI.LogInScreen;
import gateway.NoteDataAccess;
import notes.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CreateEditDeleteNoteTests {

    @Test
    public void CreateNote() {
        LogInScreen.usernameLogged = "kate";
        NoteDataAccess noteDataAccess = new NoteDataAccess();
        Note note = new Note("title", "description");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        noteDataAccess.delete("title");
        Assertions.assertTrue(notes.contains(note));
    }

    @Test
    public void DeleteNote() {
        LogInScreen.usernameLogged = "kate";
        NoteDataAccess noteDataAccess = new NoteDataAccess();
        Note note = new Note("title", "description");
        noteDataAccess.insert(note);
        noteDataAccess.delete("title");
        ArrayList<Note> notes = noteDataAccess.getAll();
        Assertions.assertFalse(notes.contains(note));
    }
}