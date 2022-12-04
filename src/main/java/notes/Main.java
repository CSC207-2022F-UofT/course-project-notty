package notes;

import UI.WelcomeScreen;
import gateway.DBConnection;
import gateway.NoteDataAccess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static NoteDataAccess noteDataAccess;
    //use case interaction
    static EditCreateUseCase editCreateUseCase;
    static NewNoteUseCase newNoteUseCase;
    static JFrame mainFrame;
    private static JLayeredPane lp;
    public final static int mainWidth = 340;
    public final static int mainHeight = 500;
    static ListNotesPanel listNotes;
    static EditCreateNotePanel nNotePanel;

    static JButton logOutButton;

    public static void init()
    {
        mainFrame.setTitle("Notty");
        mainFrame.setSize(mainWidth,mainHeight);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        mainFrame.add(lp);
        lp.setLayout(null);
        listNotes.setBlocks(noteDataAccess.getAll());
        lp.add(listNotes.getPanel(),1);
        lp.add(nNotePanel.getPanel(), 2);

        logOutButton = new JButton("Log Out");
        mainFrame.add(logOutButton, BorderLayout.SOUTH);

        logOutButton.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                WelcomeScreen frame = new WelcomeScreen();
                frame.setVisible(true);



            }

        });


    }
    public static void instanceInit()
    {
        mainFrame=new JFrame();
        lp=new JLayeredPane();
        noteDataAccess = new NoteDataAccess();
        newNoteUseCase = new NewNoteUseCase();
        database=new DBConnection();
        listNotes =new ListNotesPanel(true, noteDataAccess, newNoteUseCase);
        editCreateUseCase = new EditCreateUseCase(listNotes);
        nNotePanel=new EditCreateNotePanel(false, editCreateUseCase);
    }
    public static void main(String[] args) {
        instanceInit();
        init();
    }
}