package notes;

import UI.UIScreen;
import UI.WelcomeScreen;
import gateway.DBConnection;
import gateway.NoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NotesScreen extends UIScreen {

    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static NoteDataAccess noteDataAccess;
    static JButton logOutButton;

    public NotesScreen() {
        init();
        setTitle("Notty");
        panel = super.getPanel();
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setLayout(null);
        panel.add(layeredPane);

        ListNotesScreen listNotesPanel = new ListNotesScreen(true, layeredPane);
        noteDataAccess = new NoteDataAccess();
        database = new DBConnection();
        ListNotesController listNotesController = new ListNotesController(noteDataAccess, listNotesPanel);
        listNotesController.setBlocks(noteDataAccess.getAll());
        layeredPane.add(listNotesPanel, 1);

        logOutButton = new JButton("Log Out");
        panel.add(logOutButton, BorderLayout.SOUTH);

        logOutButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                WelcomeScreen frame = new WelcomeScreen();
                frame.setVisible(true);
            }
        });
    }
}
