package notes;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class NotePanel implements ActionListener {
    private JPanel panel;
    private NoteBlock block;//to detect which note block is clicked
    private JPanel notePanel;
    private JScrollPane sp;
    private JLabel[] labels;private int labelSize=2;//0:Background 1:Header
    private JButton[] buttons;private int buttonSize=2;//0:add button 1:back button
    private JTextField[] fields;private int fieldSize=2;//0:new note field 1:search field
    private JRadioButton[] radioButton;private int radioButtonSize=4;//0:all 1:thick 2:cross 3:exclamation

    private void init(){
        panel=new JPanel();
        panel.setSize(new Dimension(856, 662));
        panel.setLayout(null);
        notePanel=new JPanel();
        notePanel.setLayout(new BoxLayout(notePanel, BoxLayout.Y_AXIS));
        notePanel.setVisible(true);
        notePanel.setOpaque(false);
        sp=new JScrollPane(notePanel,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp.setBounds(50, 211, 791, 397);
        sp.setOpaque(false);
        sp.getViewport().setOpaque(false);
        sp.setBorder(BorderFactory.createEmptyBorder());
        //ScrollBar
        sp.getVerticalScrollBar().setPreferredSize(new Dimension(0, Integer.MAX_VALUE));//invisible scrollbar
        sp.getVerticalScrollBar().setUnitIncrement(5);//scroll speed

        panel.add(sp);
        //--------------------------------------------------------------------------------------------------------------

        labels=new JLabel[labelSize];
        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];
        radioButton=new JRadioButton[radioButtonSize];
    }
    public void addRadioButton(javax.swing.JPanel panel,javax.swing.JRadioButton[] radioButton, int index, java.awt.event.ActionListener al, int x, int y, int width, int height) {
        radioButton[index]=new javax.swing.JRadioButton();
        radioButton[index].setBorder(javax.swing.BorderFactory.createEmptyBorder());
        radioButton[index].setOpaque(false);
        radioButton[index].setBounds(x, y, width, height);
        radioButton[index].addActionListener(al);
        panel.add(radioButton[index]);
    }
    public NotePanel(boolean visibility){
        init();
        //RADIO BUTTONS
        ButtonGroup group=new ButtonGroup();
        addRadioButton(panel, radioButton, 3, this, 795, 180, 20, 20);
        addRadioButton(panel, radioButton, 2, this, 763, 180, 20, 20);
        addRadioButton(panel, radioButton, 1, this, 731, 180, 20, 20);
        addRadioButton(panel, radioButton, 0, this, 699, 180, 20, 20);
        for(int i=0;i<4;i++) {
            group.add(radioButton[i]);
        }
        radioButton[0].setSelected(true);//default value; shows all notes
        //BUTTONS
        NewNotePanel.addImgButton("But", panel, buttons, 1, this, 382, 622, 92, 33);//BACK BUTTON
        //backButton ANIMATION
        buttons[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                //buttons[1].setIcon(ResourceFactory.icons[23]);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                //buttons[1].setIcon(ResourceFactory.icons[13]);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
               // buttons[1].setIcon(ResourceFactory.icons[22]);
            }
        });
        NewNotePanel.addImgButton("But", panel, buttons, 0, this, 789, 114, 34, 32);//ADD BUTTON
        //addButton ANIMATION
        buttons[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                //buttons[0].setIcon(ResourceFactory.icons[31]);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                //buttons[0].setIcon(ResourceFactory.icons[15]);
            }
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
               // buttons[0].setIcon(ResourceFactory.icons[30]);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                //buttons[0].setIcon(ResourceFactory.icons[15]);
            }
        });
        //SEARCH FIELD
        NewNotePanel.addTextField(panel, fields, 1, 640, 16, 200, 32);
        fields[1].setHorizontalAlignment(JTextField.LEFT);
        //fields[1].setDocument(new FixedSizeDocument(23));
        //New Note TextField
        NewNotePanel.addTextField(panel, fields, 0, 88, 122, 686, 15);
        fields[0].setHorizontalAlignment(JTextField.LEFT);
        fields[0].setDocument(new NewNotePanel.FixedSizeDocument(97));
        fields[0].addActionListener(this);//On press the enter key
        //TITLE
        addTextLabel(panel, labels, 1, "TITLE", 275, 22, 300, 33);
        labels[1].setForeground(new Color(255,255,255));
        //labels[1].setFont(ResourceFactory.font);
        labels[1].setHorizontalAlignment(JLabel.CENTER);

        //BACKGROUND IMAGE
        addImgLabel(panel, labels, 0, 0, 0, 856, 662);

        panel.setVisible(visibility);
    }
    public static void addImgLabel(javax.swing.JPanel panel,javax.swing.JLabel[] label,int labelIndex,int x,int y,int width,int height){
        label[labelIndex]=new javax.swing.JLabel("Text");
        panel.add(label[labelIndex]);
        label[labelIndex].setBounds(x, y,width,height);
//        java.awt.Image img=icons[iconIndex].getImage().getScaledInstance(width,height,java.awt.Image.SCALE_SMOOTH);
//        icons[iconIndex]=new javax.swing.ImageIcon(img);
//        label[labelIndex].setIcon(icons[iconIndex]);
        panel.revalidate();
    }
    public static void addTextLabel(javax.swing.JPanel panel,javax.swing.JLabel[] label,int labelIndex,String text,int x,int y,int width,int height){
        label[labelIndex]=new javax.swing.JLabel();
        panel.add(label[labelIndex]);
        label[labelIndex].setText(text);
        label[labelIndex].setBounds(x, y, width, height);
        panel.revalidate();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttons[0])){//ADD BUTTON
            addNewNote();
        }
        if(e.getSource().equals(fields[0])){//NEW NOTE FIELD
            addNewNote();
        }
        if(e.getSource().equals(buttons[1])){//BACK BUTTON
            Main.mainFrame.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
            panel.setVisible(false);
            Main.menu.getPanel().setVisible(true);
            fields[0].setText("");
        }
        if(e.getSource().equals(radioButton[0])) {// SHOW ALL NOTES
            setFilter(0);
        }
        if(e.getSource().equals(radioButton[1])) {// SHOW THICK NOTES
            setFilter(1);
        }
        if(e.getSource().equals(radioButton[2])) {// SHOW CROSS NOTES
            setFilter(2);
        }
        if(e.getSource().equals(radioButton[3])) {// SHOW EXCLAMATION NOTES
            setFilter(3);
        }
    }

    /**
     * This method filters the notes
     * @param type the symbol type
     * 			   <ol>
     * 					<b>TYPES</b>
     * 					<li>Show ALL</li>
     * 					<li>Show THICK</li>
     * 					<li>Show CROSS</li>
     * 					<li>Show EXLAMATION</li>
     * 			   </ol>
     */
    private void setFilter(int type) {
        notePanel.removeAll();
        notePanel.revalidate();
        notePanel.repaint();
//        for(int i=0;i<block.getNotes().size();i++) {
//            if(type!=0) {
//                if(block.getNotes().get(i).getSymbolInfo()==type)
//                    block.getNotes().set(i, new Note(block.getNotes().get(i).getNote(),block.getNotes().get(i).getSymbolInfo(),notePanel));
//            }
//            else block.getNotes().set(i, new Note(block.getNotes().get(i).getNote(),block.getNotes().get(i).getSymbolInfo(),notePanel));
//        }
    }

    private void addNewNote(){
        if(!fields[0].getText().isEmpty()){
            Note note=new Note(fields[0].getText(),0,notePanel);
//            block.getNotes().add(note);
//            String blockNotes="-_-";//database keeps notes in one variable
//            for(int i=0;i<block.getNotes().size();i++) {
//                blockNotes+=block.getNotes().get(i).getSymbolInfo()+"-"+block.getNotes().get(i).getNote()+"-_-";
//            }
            //Main.database.updateNotes(block.getTitle(), blockNotes);
            fields[0].setText("");
        }
    }
    public void openNotePanel(boolean visibility,NoteBlock block){
        Main.mainFrame.setSize(new Dimension(856, 690));
        Main.mainFrame.setLocationRelativeTo(null);
        panel.setVisible(visibility);
        this.block=block;
        labels[1].setText(block.getTitle());
        //NOTES LOAD
        //-------------------------------------------------------------------------------------------------------------
        notePanel.removeAll();
        notePanel.revalidate();
        notePanel.repaint();
//        if(block.getTitle()!=null) {
//            block.getNotes().add(new Note(tempNotes.get(i).getNote(),tempNotes.get(i).getSymbolInfo(),notePanel));
//        }
        //-------------------------------------------------------------------------------------------------------------
        radioButton[0].setSelected(true);//should be selected when the panel open
    }

    public void setTitleText(String text){
        labels[1].setText(text);
    }
    public NoteBlock getCurrentBlock(){
        return this.block;
    }
    public JPanel getNotePanel(){
        return this.notePanel;
    }
    public JPanel getPanel() {
        return panel;
    }
}
