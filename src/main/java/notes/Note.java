package notes;

import java.util.Objects;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return note.title.equals(title) && note.description.equals(description);
    }

}
