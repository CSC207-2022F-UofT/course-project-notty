package gateway;

import notes.Note;

import java.util.ArrayList;

public interface INoteDataAccess {
    void insert(Note note);
    void delete(String note);
    void edit(Note note, String oldTitle);
    ArrayList<Note> getAll();

    void pin(Note note);
}
