package notes;

public class Note {
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String title;
    public String description;
    public Note(String tile, String des)
    {
        this.title= tile;
        this.description = des;
    }

}
