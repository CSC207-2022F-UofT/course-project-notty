package notes;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Note {
    private String title;
    private String description;
    private boolean isPinned;
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

    public String getDescription() {
        return description;
    }

    public boolean isPinned() {
        return this.isPinned;
    }

    public String getDateTime() {return this.date.toString();}

}