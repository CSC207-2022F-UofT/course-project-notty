package notes;

import UI.UIScreen;
import gateway.DBConnection;
import gateway.NoteDataAccess;

import javax.swing.*;

public class NotesScreen extends UIScreen {

    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static NoteDataAccess noteDataAccess;

    public NotesScreen(){
        init();
        setTitle("Notty");
        panel = super.getPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        panel.add(layeredPane);

        ListNotesScreen listNotesPanel = new ListNotesScreen(true, layeredPane);
        noteDataAccess = new NoteDataAccess();
        database=new DBConnection();
        ListNotesController listNotesController = new ListNotesController(noteDataAccess, listNotesPanel);
        listNotesController.setBlocks(noteDataAccess.getAll());
        layeredPane.add(listNotesPanel, 1);
    }
}
