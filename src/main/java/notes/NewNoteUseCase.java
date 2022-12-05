package notes;

import gateway.DBConnection;
import gateway.NoteDataAccess;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewNoteUseCase implements ActionListener {

    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static NoteDataAccess noteDataAccess;
    private final ListNotesScreen listNotesScreen;
    private final JLayeredPane layeredPane;
    private final ListNotesController listNotesController;

    public NewNoteUseCase(JLayeredPane layeredPane, ListNotesScreen listNotesScreen) {
        this.layeredPane = layeredPane;
        this.listNotesScreen = listNotesScreen;
        //this.editCreateNoteScreen = editCreateNoteScreen;
        noteDataAccess = new NoteDataAccess();
        database=new DBConnection();
        listNotesController = new ListNotesController(noteDataAccess,listNotesScreen);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        listNotesScreen.hideScreen();
        new EditCreateNoteScreen(true, listNotesController, layeredPane);
    }
}
