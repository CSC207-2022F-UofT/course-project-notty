package UI;
import gateway.DBConnection;

import javax.swing.SwingUtilities;

public class App {

    static DBConnection database;

    public static void main(String[] args) {
        database=new DBConnection();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new WelcomeScreen();
            }
        });
    }
}


