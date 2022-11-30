package tasks;
import gateway.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Task {
    private String title;
    private String dates;

    private String category;
    public Task(String title, String category, String dates)
    {
        this.title = title;
        this.dates = dates;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return dates;
    }

    public String getCategory() {
        return category;
    }

}
