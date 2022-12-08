package notes.UseCases;

import gateway.DBConnection;
import gateway.NoteDataAccess;
import notes.Screens.EditCreateNoteScreen;
import notes.ListNotesController;
import notes.Screens.ListNotesScreen;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteUseCase implements ActionListener {

    static DBConnection database;                //data access level (connection)
    static NoteDataAccess noteDataAccess;        //data access level (note operations)
    private final ListNotesScreen listNotesScreen;
    private final JLayeredPane layeredPane;

    public NewNoteUseCase(JLayeredPane layeredPane, ListNotesScreen listNotesScreen) {
        this.layeredPane = layeredPane;
        this.listNotesScreen = listNotesScreen;
        //this.editCreateNoteScreen = editCreateNoteScreen;
        noteDataAccess = new NoteDataAccess();
        database = new DBConnection();
         new ListNotesController(noteDataAccess, listNotesScreen);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        listNotesScreen.hideScreen();
        layeredPane.getComponent(1).setVisible(true);
    }
}
