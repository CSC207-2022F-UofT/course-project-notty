package tasks;

import java.sql.*;
import java.util.ArrayList;

public class Category {
    private String title;
    private final Date date;
    private ArrayList<Task> tasks;

    public Category(String title, Date date)
    {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }
    public Date getDate() { return date; }
    public ArrayList<Task> getTasks() { return tasks; }
}
