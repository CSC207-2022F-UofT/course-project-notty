package notes.UseCases;

import gateway.DBConnection;
import gateway.NoteDataAccess;
import notes.ListNotesController;
import notes.Screens.ListNotesScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteUseCase implements ActionListener {
    // this class is responsible for implementing the creation of a new note
    static DBConnection database;                //data access level (connection)
    static NoteDataAccess noteDataAccess;        //data access level (note operations)
    private final ListNotesScreen listNotesScreen;
    private final JLayeredPane layeredPane;

    public NewNoteUseCase(JLayeredPane layeredPane, ListNotesScreen listNotesScreen) {
        this.layeredPane = layeredPane;
        this.listNotesScreen = listNotesScreen;
        noteDataAccess = new NoteDataAccess();
        database = new DBConnection();
        new ListNotesController(noteDataAccess, listNotesScreen);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        listNotesScreen.hideScreen();                       //hides the list of notes from user
        layeredPane.getComponent(1).setVisible(true);    // user sees the screen for creation of notes
    }
}
