package notes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ListNotesPanel implements ActionListener{
    private JPanel panel;
    private JPanel noteBlockPanel;
    private JScrollPane sp;
    private JButton[] buttons;
    private int buttonsSize=1;
    private JLabel[] labels;
    private int labelSize=1;

    public void init(){
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        //NOTE PANEL
        noteBlockPanel=new JPanel();
        noteBlockPanel.setLayout(new BoxLayout(noteBlockPanel, BoxLayout.Y_AXIS));
        noteBlockPanel.setVisible(true);
        noteBlockPanel.setOpaque(false);
        sp = new JScrollPane(noteBlockPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(15, 74, 288, 350);
        panel.add(sp);
        buttons=new JButton[buttonsSize];
        labels=new JLabel[labelSize];
        buttons[0]=new JButton("New note");
        panel.add(buttons[0]);
        buttons[0].setSize(400,400);
        buttons[0].setLayout(null);
        buttons[0].setVisible(true);
        buttons[0].setBounds(19, 19, 117, 42);
        buttons[0].addActionListener(this);
    }

    public ListNotesPanel(boolean visibility){
        init();
        panel.setVisible(visibility);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        panel.setVisible(false);
        Main.nNotePanel.getPanel().setVisible(true);
    }
    public JPanel getPanel(){
        return this.panel;
    }

    public JPanel getNoteBlockPanel(){
        return this.noteBlockPanel;
    }
}
