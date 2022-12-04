package tasks;

public class Task {
    private int id;
    private String title;
    private int categoryId;
    private int marked;
    public Task(String title, int categoryId)
    {
        this.title = title;
        this.categoryId = categoryId;
    }
    public Task(int id, String title, int categoryId, int marked)
    {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.marked = marked;
    }

    public int getId() { return this.id; }
    public void setId(int id) { this.id = id; }
    public String getTitle() { return this.title; }
    public void setTitle(String title) { this.title = title; }
    public int getCategoryId() { return this.categoryId; }
    public int getMarked() { return this.marked; }
}
