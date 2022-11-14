package notes;

import gateway.DBConnection;
import notes.Menu;
import javax.swing.*;
import java.awt.*;

public class Main {
    static DBConnection database;
    static JFrame mainFrame;
    private static JLayeredPane lp;
    public static int mainWidth = 700;
    public static int mainHeight = 500;
    static Menu menu;
    static NewNotePanel nNotePanel;
    static NotePanel notePanel;

    public static void main(String[] args) {
        mainFrame=new JFrame();
        lp=new JLayeredPane();
        mainFrame.setTitle("Notty");
        mainFrame.setSize(mainWidth,mainHeight);
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        mainFrame.add(lp);
        lp.setLayout(null);
        menu=new Menu(true);
        nNotePanel=new NewNotePanel(false);
        database=new DBConnection();
        nNotePanel.setBlocks(Note.loadDatas("notes"));
        notePanel=new NotePanel(false);
        lp.add(menu.getPanel(),1);
        lp.add(nNotePanel.getPanel(),2);
        // lp.add(notePanel.getPanel(), 3);
    }
}
