package tasks;

import gateway.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Category {

    private final int id;
    private static int number = 0;
    private final String title;
    private final Date date;

    public Category(String title, Date date)
    {
        this.title = title;
        this.date = date;
        this.id = number;
        number += 1;
    }

    public String getTitle() {
        return title;
    }
    public Date getDate() { return date; }
    public int getId() { return id; }
}
