package notes;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NoteBlock extends JPanel {
    // this class is the individual note panels created for each note
    private final String title;
    private final String desc;
    private final boolean isPinned;
    private final ListNotesController listNotesController;
    private final JPanel panel = new JPanel();
    private Color bgColor;

    public NoteBlock(ListNotesController listNotesController, String title, String desc, boolean isPinned) {
        this.listNotesController = listNotesController;     // controller for this note
        this.title = title;                                 // title displayed in panel
        this.desc = desc;                                   // description displayed in panel
        this.isPinned = isPinned;                           // if note is pinned or not
        init();
    }

    public void init() {
        // initializes the appearance of individual note block or the main panel for each one
        panel.setSize(200, 225);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));                        // set layout
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));        // add borders

        panel.setBackground(noteColor());       // set background colors
        bgColor = panel.getBackground();        // stores background color for button panels since it is separate
    }

    public JPanel createNotePanel() {
        // adds the buttons and text fields for each note block

        // store buttons in an array since they will be in a different panel with a different layout manager
        ArrayList<JButton> buttons = new ArrayList<>();

        JTextField textField = addTextField(title);     // creates title text field
        titleDesign(textField);                         // different design for title so it pops out
        addTextField(desc);                             // creates description field

        JButton button = new JButton("Edit");       // creates edit button
        buttonDesign(button);
        buttons.add(button);

        JButton button1 = new JButton("Delete");    // creates delete button
        buttonDesign(button1);
        buttons.add(button1);

        String pinUnpinText;    //displays "Pin" or "Unpin" depending on the state of the note

        if (isPinned) {
            pinUnpinText = "Unpin";             // if a note is pinned, change text to unpin
            panel.add(pinIcon(), 0);      // adds a pin icon
        } else {
            pinUnpinText = "Pin";               // if not pinned, displays regular "Pin" text
        }

        JButton pinButton = new JButton(pinUnpinText);        // creates pin button
        buttonDesign(pinButton);
        buttons.add(pinButton);

        panel.add(buttonLayout(buttons));       //adds buttons to the note block panel

        // adds action listeners to the buttons
        button.addActionListener(new EditNoteUseCase(listNotesController, panel, title, desc));
        button1.addActionListener(new DeleteNoteUseCase(listNotesController, title, panel));
        pinButton.addActionListener(new PinUnpinUseCase(listNotesController, panel, pinButton, title));

        return panel;
    }

    private void buttonDesign(JButton button){
        // creates the design for the buttons

        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(80, 40));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)));
        button.setForeground(Color.white);
        button.setVisible(true);
    }

    private JPanel buttonLayout(ArrayList<JButton> buttons){
        // returns a panel for the buttons so they are side by side and not stacked to one another

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(200,75));
        panel.setMaximumSize(new Dimension(400, panel.getPreferredSize().height));
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(bgColor);

        for (JButton b : buttons){
            panel.add(b);
        }
        return panel;
    }

    public JTextField addTextField(String text){
        // creates the text field and adds it to the note block

        JTextField field = new JTextField(text);
        field.setPreferredSize(new Dimension(200,50));
        field.setMaximumSize(new Dimension(400, field.getPreferredSize().height));
        field.setBorder(new EmptyBorder(10, 10, 10, 10));
        field.setOpaque(false);
        field.setEditable(false);
        field.setVisible(true);
        panel.add(field);

        return field;
    }

    private void titleDesign(JTextField title){
        // sets design for the title
        title.setHorizontalAlignment(0);
        title.setFont(new Font("Calibri", Font.BOLD, 18));
        title.setAlignmentX(CENTER_ALIGNMENT);
    }

    private JLabel pinIcon(){
        // creates a pin icon that will be displayed on the right corner when a note is pinned
        String path = "/UI/pin.png";
        ImageIcon icon = new ImageIcon(getClass().getResource(path));
        Image image = icon.getImage();
        Image newimg = image.getScaledInstance(30, 30, Image.SCALE_AREA_AVERAGING);
        icon = new ImageIcon(newimg);

        JLabel pinLabel = new JLabel(icon, SwingConstants.RIGHT);
        pinLabel.setPreferredSize(new Dimension(300,30));
        pinLabel.setMaximumSize(new Dimension(300, pinLabel.getPreferredSize().height));
        pinLabel.setBackground(Color.black);

        return pinLabel;
    }

    public Color noteColor(){
        // picks from 5 colors chosen for the note's background
        ArrayList<Color> colors = new ArrayList<>();
        colors.add(new Color(232, 215, 255));
        colors.add(new Color(255, 211, 232));
        colors.add(new Color(223, 255, 214));
        colors.add(new Color(255, 215, 213));
        colors.add(new Color(243, 255, 225));


        return colors.get(new Random().nextInt(4));
    }

    public String getTitle() {return title;}
}