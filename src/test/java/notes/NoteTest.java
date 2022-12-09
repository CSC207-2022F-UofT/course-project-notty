package notes;

import notes.Entities.Note;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class NoteTest {

    @Test
    void getTitle() {
        // Checks that this method returns the title of a note
        Note note = new Note("hello", "world");
        Note note1 = new Note("CSC207", "Software Design");

        Assertions.assertEquals(note.getTitle(), "hello");
        Assertions.assertEquals(note1.getTitle(), "CSC207");
    }

    @Test
    void getDescription() {
        // Checks that this method returns the description of a note
        Note note = new Note("hello", "world");
        Note note1 = new Note("CSC207", "Software Design");

        Assertions.assertEquals(note.getDescription(), "world");
        Assertions.assertEquals(note1.getDescription(), "Software Design");
    }

    @Test
    void isPinned() {
        // Checks that this method returns true when a note is pinned and false when it is unpinned
        Note note = new Note("hello", "world");
        Note note1 = new Note("CSC207", "Software Design", true);


        Assertions.assertFalse(note.isPinned());
        Assertions.assertTrue(note1.isPinned());
    }
}