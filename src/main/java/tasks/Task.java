package tasks;
import gateway.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Task {
    private String title;
    private String date;
    public Task(String title, String date)
    {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

}
