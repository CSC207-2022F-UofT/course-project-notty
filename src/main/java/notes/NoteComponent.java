package notes;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NoteComponent extends JComponent {
    private final String title;
    private final String desc;
    private final boolean isPinned;
    private final ListNotesController listNotesController;
    private JPanel panel = new JPanel();

    public Color noteColor(){
        ArrayList<Color> colors = new ArrayList<Color>();
        colors.add(new Color(232, 215, 255));
        colors.add(new Color(255, 211, 232));
        colors.add(new Color(223, 255, 214));
        colors.add(new Color(255, 215, 213));
        colors.add(new Color(243, 255, 225));


        return colors.get(new Random().nextInt(4));
    }

    public void init() {
        panel.setPreferredSize(new Dimension(200,150));
        panel.setMinimumSize(new Dimension(200, panel.getPreferredSize().height));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        panel.setBackground(noteColor());
    }

    public NoteComponent(ListNotesController listNotesController, String title, String desc, boolean isPinned) {
        this.listNotesController = listNotesController;
        this.title = title;
        this.desc = desc;
        this.isPinned = isPinned;
        init();
    }

    public JPanel createNotePanel() {
        ArrayList<JButton> buttons = new ArrayList<>();

        JTextField textField = addTextField(title);
        titleDesign(textField);
        addTextField(desc);

        JButton button = new JButton("Edit");
        buttonDesign(button);
        buttons.add(button);

        JButton button1 = new JButton("Delete");
        buttonDesign(button1);
        buttons.add(button1);

        String pinUnpinText;
        //create pin/unpin button
        if (isPinned) {
            pinUnpinText = "Unpin";
        } else {
            pinUnpinText = "Pin";
        }

        JButton button2 = new JButton(pinUnpinText);
        buttonDesign(button2);
        buttons.add(button2);

        panel.add(buttonLayout(buttons));

        button.addActionListener(new EditNoteUseCase(listNotesController, panel, title, desc));
        button1.addActionListener(new DeleteNoteUseCase(listNotesController, title, panel));
        button2.addActionListener(new PinUnpinUseCase(listNotesController, panel, button2, title));

        return panel;
    }

    private void buttonDesign(JButton button){
        button.setBackground(Color.PINK);
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(100, 50));
        button.setBorder(new CompoundBorder(
                new LineBorder(Color.white, 2),
                new EmptyBorder(10, 10, 10, 10)
        ));
        button.setForeground(Color.white);
        button.setVisible(true);
    }

    private JPanel buttonLayout(ArrayList<JButton> buttons){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        for (JButton b : buttons){
            panel.add(b);
        }
        panel.setBackground(Color.LIGHT_GRAY);
        return panel;
    }

    public JTextField addTextField(String text){
        JTextField field = new JTextField(text);
        field.setPreferredSize(new Dimension(200,40));
        field.setMaximumSize(new Dimension(400, field.getPreferredSize().height));
        field.setBorder(new EmptyBorder(10, 10, 10, 10));
        field.setOpaque(false);
        field.setEditable(false);
        field.setVisible(true);
        panel.add(field);

        return field;
    }

    private void titleDesign(JTextField title){
        title.setHorizontalAlignment(0);
        title.setFont(new Font("Calibri", Font.BOLD, 18));
        title.setAlignmentX(CENTER_ALIGNMENT);
    }

}