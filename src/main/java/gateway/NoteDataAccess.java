package gateway;

import UI.LogInScreen;
import notes.Note;

import java.sql.*;
import java.util.ArrayList;

public class NoteDataAccess implements INoteDataAccess{
    public void insert(Note note)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO notes(title,description,username) VALUES(?,?,?)";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getDescription());
            pstmt.setString(3, LogInScreen.usernameLogged);

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
    public void delete(String note)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "DELETE FROM notes WHERE title=?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, note);
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
    public void edit(Note note, String oldTitle)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "UPDATE notes SET " +
                "title = ?," +
                "description= ?" +
                " WHERE title = ?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, note.getTitle());
            pstmt.setString(2, note.getDescription());
            pstmt.setString(3, oldTitle);
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
    public ArrayList<Note> getAll()
    {
        ArrayList<Note> blocks=new ArrayList<Note>();
        ArrayList<Note> notes=null;
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        String sql= "SELECT id, title, description FROM notes WHERE username='"+LogInScreen.usernameLogged + "'";
        try {
            conn=DBConnection.connect();
            st=conn.createStatement();
            rs=st.executeQuery(sql);

            while(rs.next()) {
                blocks.add(new Note(rs.getString("title"), rs.getString("description")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(rs!=null) rs.close();
                if(st!=null) st.close();
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return blocks;
    }

}
