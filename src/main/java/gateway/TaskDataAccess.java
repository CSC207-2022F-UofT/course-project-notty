package gateway;

import tasks.Task;

import java.sql.*;
import java.util.ArrayList;

public class TaskDataAccess implements ITaskDataAccess {
    public void create(Task task)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO tasks(title,dates,category) VALUES(?,?,?)";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDate());
            pstmt.setString(3, task.getCategory());


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
    public void delete(String task)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "DELETE FROM tasks WHERE title=?"; //need to be unique

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setString(1, task);
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


    public void updateTaskName(Task task, String oldTitle) //this will remove the task, and add the 'edited' task with a new id
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "UPDATE tasks SET " +
                "title = ?," +
                "date= ?" +
                " WHERE title = ?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDate());
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

    public void updateCategory(Task task, String oldTitle) //this will remove the task, and add the 'edited' task with a new id
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "UPDATE tasks SET " +
                "title = ?," +
                "date= ?" +
                " WHERE title = ?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDate());
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
    public ArrayList<Task> getAll()
    {
        ArrayList<Task> blocks=new ArrayList<Task>();
        ArrayList<Task> notes=null;
        Connection conn=null;
        Statement st=null;
        ResultSet rs=null;
        String sql= "SELECT id, title, dates, category FROM tasks";
        try {
            conn=DBConnection.connect();
            st=conn.createStatement();
            rs=st.executeQuery(sql);

            while(rs.next()) {
                blocks.add(new Task(rs.getString("title"), rs.getString("date"),
                        rs.getString("category")));
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