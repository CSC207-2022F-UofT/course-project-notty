package notes;

import gateway.DBConnection;
import gateway.NoteDataAccess;

import javax.swing.*;

public class Main {
    //data access level (connection)
    static DBConnection database;
    //data access level (note operations)
    static NoteDataAccess noteDataAccess;
    //use case interaction
    static EditCreateUseCase editCreateUseCase;
    static NewNoteUseCase newNoteUseCase;
    static EditCreateLabelUseCase editCreateLabelUseCase;
    static JFrame mainFrame;
    private static JLayeredPane lp;
    public final static int mainWidth = 340;
    public final static int mainHeight = 500;
    static ListNotesPanel listNotes;
    static EditCreateNotePanel nNotePanel;
    static EditCreateLabelPanel lLabelPanel;
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
        lp.add(nNotePanel.getPanel(),2);
        lp.add(lLabelPanel.getPanel(), 3);
    }
    public static void instanceInit()
    {
        mainFrame=new JFrame();
        lp=new JLayeredPane();
        noteDataAccess = new NoteDataAccess();
        editCreateUseCase = new EditCreateUseCase();
        newNoteUseCase = new NewNoteUseCase();
        editCreateLabelUseCase = new EditCreateLabelUseCase();
        database=new DBConnection();
        listNotes =new ListNotesPanel(true, noteDataAccess, newNoteUseCase);
        nNotePanel=new EditCreateNotePanel(false, editCreateUseCase);
        lLabelPanel=new EditCreateLabelPanel(false, editCreateLabelUseCase);
    }
    public static void main(String[] args) {
        instanceInit();
        init();
    }
}
