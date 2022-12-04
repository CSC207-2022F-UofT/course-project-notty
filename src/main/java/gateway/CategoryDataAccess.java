package gateway;

import UI.LogInScreen;
import tasks.Category;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class CategoryDataAccess {
    public static void createCategory(Category category)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "INSERT INTO categories(title, daily, userName) VALUES(?,?,?)";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, category.getTitle());
            pstmt.setObject(2, category.getDaily());
            pstmt.setString(3, LogInScreen.usernameLogged);
            // pstmt.setArray(3, (Array) category.getTasks());

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

    public void deleteCategory(int categoryId)
    {
        String sql= "DELETE FROM categories WHERE id=?";
        DeleteDataAccess(categoryId, null, null, sql);
    }

    static void DeleteDataAccess(int categoryId, Connection conn, PreparedStatement pstmt, String sql) {
        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setInt(1, categoryId);
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

    public void editCategory(int categoryId, String newTitle)
    {
        Connection conn=null;
        PreparedStatement pstmt=null;
        String sql= "UPDATE categories SET " +
                "title = ?" +
                " WHERE id = ?";

        try {
            conn= DBConnection.connect();
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1, newTitle);
            pstmt.setInt(2, categoryId);
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
    public ArrayList<Category> getCategories()
    {
        ArrayList<Category> arr =new ArrayList<>();
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        String sql = "SELECT id, title, daily, userName FROM categories";
        try {
            conn = DBConnection.connect();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                Category temp = new Category();
                temp.setId(rs.getInt("id"));
                temp.setTitle(rs.getString("title"));
                String daily = rs.getString("daily");
                temp.setDaily(LocalDate.parse(daily));
                temp.setUserName(rs.getString("userName"));
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
                e.printStackTrace();
            }
        }
        return arr;
    }
}
