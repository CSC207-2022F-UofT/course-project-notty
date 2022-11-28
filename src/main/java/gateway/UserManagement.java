package gateway;

import entities.Credentials;
import entities.User;

import java.sql.*;
import java.util.ArrayList;

public class UserManagement {
    public UserManagement() {

    }

    public boolean createUser(User user)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO users(username,password) VALUES(?,?)";
        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null)  conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
        return true;
    }
    public boolean checkCredentials(Credentials credentials)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "SELECT * FROM users WHERE username=? AND password=?";
        ResultSet rs = null;
        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, credentials.getUsername());
            pstmt.setString(2, credentials.getPassword());

            rs = pstmt.executeQuery();
            while (rs.next())
            {
                return true;
            }
            return  false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null)  conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }

    public boolean checkUsername(Credentials credentials)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "SELECT * FROM users WHERE username=?";
        ResultSet rs = null;
        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, credentials.getUsername());
            rs = pstmt.executeQuery();
            while (rs.next())
            {
                return true;
            }
            return  false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            try {
                if(pstmt!=null) pstmt.close();
                if(conn!=null)  conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }


}

