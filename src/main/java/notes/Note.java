package notes;

import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Note {
    private String title;
    private String description;
    private boolean isPinned;

    private String item;
    private LocalDateTime date;
    public Note(String title, String description)
    {
        this.title = title;
        this.description = description;
        this.isPinned = false;
        this.date = LocalDateTime.now();
    }

    public Note(String title, String description, boolean isPinned, String dateTime) {
        this.title = title;
        this.description = description;
        this.isPinned = isPinned;
        this.date = LocalDateTime.parse(dateTime);
    }
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return note.title.equals(title) && note.description.equals(description);
    }
    public boolean isPinned() {
        return this.isPinned;
    }

    public String getDateTime() {return this.date.toString();}

}