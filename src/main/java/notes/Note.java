package notes;

import gateway.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Note {
    public void insert(String title, String description) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO notes(title,description) VALUES(?,?)";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.setString(2, description);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null)  conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }

    public void deleteNote(String title) {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "DELETE FROM notes WHERE title=?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, title);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null)  conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
