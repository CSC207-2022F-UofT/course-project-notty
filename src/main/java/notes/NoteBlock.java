package notes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class NoteBlock implements ActionListener{
    private String title;
    private String description;

    private JPanel panel;
    private JLabel bg;
    private JLabel[] labels;//0:Title and 1:Description Labels
    private JButton[] buttons;//0:Edit and 1:Trash buttons


    public NoteBlock(String title,String description,JPanel panel){
        this.title=title;
        this.description=description;
        this.panel=panel;

        bg=new JLabel();

        //Buttons
        buttons=new JButton[2];//0:Edit and 1:Trash buttons
        addImgButton(0, 9);
        addImgButton(1, 50);
        //Labels
        labels=new JLabel[2];//0:Title and 1:Description Labels
        addLabel(0, title);
        addLabel(1, description);


        bg.add(labels[0]);
        bg.add(labels[1]);
        bg.add(buttons[0]);
        bg.add(buttons[1]);
        panel.add(bg);

        //editButton-trashButton ANIMATIONS

        labels[0].setBounds(59, 8, 79, 26);//Title
        labels[1].setBounds(22, 37, 190, 41);//Description
        panel.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttons[0])){//Edit button
            Main.menu.getPanel().setVisible(false);
            Main.notePanel.openNotePanel(true,this);
        }
        if(e.getSource().equals(buttons[1])){//Trash button
            int dialogButton = javax.swing.JOptionPane.YES_NO_OPTION;
            int dialogResult = javax.swing.JOptionPane.showConfirmDialog (null, "Are you sure you want to remove "+title+" block?","Warning",dialogButton);
            if(dialogResult == javax.swing.JOptionPane.YES_OPTION){
                Main.nNotePanel.getBlocks().remove(this);
                panel.remove(bg);
                panel.revalidate();
                panel.repaint();
                //Main.database.deleteBlock(this.getTitle());
            }
        }
    }

    private void addImgButton(int index,int y){
        buttons[index]=new JButton("lala");
        buttons[index].setBounds(226, y, 34, 32);
        buttons[index].addActionListener(this);
//        buttons[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
//        buttons[index].setContentAreaFilled(false);
//        buttons[index].setOpaque(false);
       // buttons[index].setFocusPainted(false);
    }
    private void addLabel(int index,String text){
        labels[index]=new JLabel();
        labels[index].setText(text);
        labels[index].setForeground(new Color(255,255,255));
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
}
