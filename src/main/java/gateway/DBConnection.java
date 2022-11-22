package gateway;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private final static String database_url="jdbc:sqlite:notetakingapp.db";

    public DBConnection() {

        this.createTable("notes");
        this.createTable("labels");
    }

    public static Connection connect() {

        Connection conn=null;
        try {
            conn= DriverManager.getConnection(database_url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void createTable(String tableName) {
        Connection conn=null;
        String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
                + "id integer PRIMARY KEY,\n"
                + "title text NOT NULL,\n"
                + "description text NOT NULL\n"
                + ");";
        try {
            conn= connect();
            Statement st=conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createLabelTable(String tableName) {
        Connection conn=null;
        String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
                + "id integer FOREIGN KEY REFERENCES notes(id),\n"
                + "label text NOT NULL,\n"
                + ");";
        try {
            conn= connect();
            Statement st=conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            try {
                if(conn!=null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
