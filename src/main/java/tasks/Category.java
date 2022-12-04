package tasks;

import java.time.LocalDate;

public class Category {
    private int id;
    private String title;
    private LocalDate daily;
    public Category()
    {
        this.id = 0;
        this.title = "";
        this.daily = LocalDate.of(2000, 1, 1);
    }
    public Category(String title, LocalDate date)
    {
        this.title = title;
        this.daily = date;
    }
    public Category(int id, String title, LocalDate date)
    {
        this.id = id;
        this.title = title;
        this.daily = date;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public LocalDate getDaily() { return this.daily; }
    public void setDaily(LocalDate daily) { this.daily = daily; }
}
