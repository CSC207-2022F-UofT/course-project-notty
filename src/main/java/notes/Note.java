package notes;

public class Note {
    private final String title;               // title of the note
    private final String description;         // description of the note
    private final boolean isPinned;           // whether the not note is pinned ot not


    public Note(String title, String description) {
        this.title = title;                 // sets given title as this note's title
        this.description = description;     // sets given description as this note's description
        this.isPinned = false;              // newly created notes must not be pinned so initialized to false
    }

    public Note(String title, String description, boolean isPinned) {
        this.title = title;
        this.description = description;

        // this constructor takes in a boolean of whether the note is pinned or not
        // was used mainly if setting up notes stored in the database
        this.isPinned = isPinned;
    }

    public String getTitle() {
        return title;
    }   // returns this note's title

    public String getDescription() {
        return description;
    }   //return this note's description

    public boolean isPinned() {
        return this.isPinned;
    }       // returns if note is pinned or not

}
