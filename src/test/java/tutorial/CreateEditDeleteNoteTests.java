package tutorial;

import gateway.NoteDataAccess;
import notes.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class CreateEditDeleteNoteTests {

    @Test
    public void CreateNote() {
        NoteDataAccess noteDataAccess = new NoteDataAccess();
        Note note = new Note("title", "description");
        noteDataAccess.insert(note);
        ArrayList<Note> notes = noteDataAccess.getAll();
        Assertions.assertTrue(notes.contains(note));
    }

    @Test
    public void DeleteNote() {
        NoteDataAccess noteDataAccess = new NoteDataAccess();
        Note note = new Note("title", "description");
        noteDataAccess.delete("title");
        ArrayList<Note> notes = noteDataAccess.getAll();
        Assertions.assertFalse(notes.contains(note));
    }
}