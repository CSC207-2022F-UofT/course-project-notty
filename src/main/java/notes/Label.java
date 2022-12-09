package notes;
import gateway.DBConnection;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class Label {
    private String label;

    public Label(String label)
    {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
