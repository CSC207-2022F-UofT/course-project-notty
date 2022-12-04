package gateway;

import tasks.Task;

import java.sql.*;
import java.util.ArrayList;

public class TaskDataAccess {
    public void createTask(Task task)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO tasks(title, categoryId, marked) VALUES(?,?,?)";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, task.getTitle());
            pstmt.setInt(2, task.getCategoryId());
            pstmt.setInt(3, 0);
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

    public void deleteTask(int taskId)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "DELETE FROM tasks WHERE id=?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);

            pstmt.setInt(1, taskId);
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

    public void editTask(int taskId, String title, int categoryId)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "UPDATE tasks SET " +
                "title = ?, categoryId = ?" +
                " WHERE id = ?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, title);
            pstmt.setInt(2, categoryId);
            pstmt.setInt(3, taskId);
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
    public ArrayList<Task> getTasks()
    {
        ArrayList<Task> arr =new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT id, title, categoryId, marked FROM tasks";
        try {
            conn = DBConnection.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Task temp = new Task(rs.getInt("id"), rs.getString("title"),
                        rs.getInt("categoryId"), rs.getInt("marked"));
                arr.add(temp);
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
        return arr;
    }

    public void markTask(int taskId, int marked) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE tasks SET " +
                " marked = ?" +
                " WHERE id = ?";

        try {
            conn = DBConnection.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, marked);
            pstmt.setInt(2, taskId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.getMessage();
            }
        }
    }
}

