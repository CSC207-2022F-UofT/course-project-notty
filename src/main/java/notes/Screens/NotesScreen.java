package notes.Screens;

import UI.UIScreen;
import gateway.DBConnection;
import gateway.NoteDataAccess;
import notes.ListNotesController;
import notes.UseCases.LogOutUseCase;

import javax.swing.*;
import java.awt.*;

public class NotesScreen extends UIScreen {
    // this class displays the main note screen for each user
    // it extends UIScreen which is a JFrame also used by the accounts Screen
    static DBConnection database;           //data access level (connection)
    static NoteDataAccess noteDataAccess;   // //data access level (note operations)
    static JButton logOutButton;
    static JLayeredPane layeredPane;

    public NotesScreen() {
        // initialize database connections
        noteDataAccess = new NoteDataAccess();
        database = new DBConnection();

        // initialize the main JFrame for the notes part
        init();
        setTitle("Notty");
        panel = super.getPanel();

        // layeredPane is used to switch between the listNotesScreen and the editCreateNoteScreen
        layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        layeredPane.setPreferredSize(new Dimension(280,375));
        layeredPane.setMaximumSize(new Dimension(280, layeredPane.getPreferredSize().height));

        // listNotesPanel is the initial screen that the user sees when they have successfully logged in
        ListNotesScreen listNotesPanel = new ListNotesScreen(true, this);
        ListNotesController listNotesController = new ListNotesController(noteDataAccess, listNotesPanel);
        listNotesController.setBlocks(noteDataAccess.getAll());
        layeredPane.add(listNotesPanel, 1);

        //create the edit create note screen
        EditCreateNoteScreen editCreateNoteScreen = new EditCreateNoteScreen(listNotesController);
        editCreateNoteScreen.setVisible(false);
        layeredPane.add(editCreateNoteScreen, 2);

        panel.add(layeredPane);

        // creates the logOut button
        logOutButton = new JButton("Log Out");
        buttonDesign(logOutButton, 270, 20);
        panel.add(logOutButton, BorderLayout.SOUTH);

        logOutButton.addActionListener(new LogOutUseCase(this));
    }

    public JLayeredPane getLayeredPane(){return layeredPane;}
}
