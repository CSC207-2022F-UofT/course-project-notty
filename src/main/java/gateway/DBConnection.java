package gateway;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private final static String database_url="jdbc:sqlite:notetakingapp.db";

    public DBConnection() {
        this.createTable("notes");
        this.createUserTable("users");
        this.createCategoryTable("categories");
        this.createTaskTable("tasks");
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
        Connection conn = null;
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id integer PRIMARY KEY,\n"
                + "title text NOT NULL,\n"
                + "description text NOT NULL,\n"
                + "isPinned text NOT NULL,\n"
                + "username text NOT NULL\n"
                + ");";
        try {
            conn = connect();
            Statement st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void createUserTable(String tableName) {
        Connection conn=null;
        String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
                + "id integer PRIMARY KEY,\n"
                + "username text NOT NULL,\n"
                + "password text NOT NULL\n"
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

    public void createCategoryTable(String tableName) {
        Connection conn = null;
        String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
                + "id integer PRIMARY KEY,\n"
                + "title text NOT NULL,\n"
                + "daily time NOT NULL\n"
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

    public void createTaskTable(String tableName) {
        Connection conn=null;
        String sql= "CREATE TABLE IF NOT EXISTS "+tableName+"(\n"
                + "id integer PRIMARY KEY, \n"
                + "title text NOT NULL,\n"
                + "categoryId integer NOT NULL,\n"
                + "marked integer NOT NULL\n"
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
