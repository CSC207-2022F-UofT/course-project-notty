package notes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewNotePanel implements ActionListener {
    private JPanel panel;
    private NoteBlock block;
    private ArrayList<NoteBlock> blocks;
    private JLabel[] labels;
    private int labelSize=1;
    private JButton[] buttons;
    private int buttonSize=2;
    private JTextField[] fields;
    private int fieldSize=2;


    private void init(){
        panel=new JPanel();
        panel.setSize(new Dimension(Main.mainWidth, Main.mainHeight));
        panel.setLayout(null);
        labels=new JLabel[labelSize];
        buttons=new JButton[buttonSize];
        fields=new JTextField[fieldSize];
        blocks=new ArrayList<NoteBlock>();
    }
    public static void addTextField(javax.swing.JPanel panel,javax.swing.JTextField[] fields,int index,int x,int y,int width,int height ){
        fields[index]=new javax.swing.JTextField();
        panel.add(fields[index]);
        fields[index].setBounds(x, y, width, height);
        panel.revalidate();
    }
    public static void addImgButton(String text, javax.swing.JPanel panel,javax.swing.JButton[] button,int index,java.awt.event.ActionListener al,int x,int y,int width,int height ){
        button[index]=new javax.swing.JButton(text);
        panel.add(button[index]);
        button[index].setBounds(x, y, width, height);
        button[index].addActionListener(al);
        panel.revalidate();
    }
    public static class FixedSizeDocument extends javax.swing.text.PlainDocument
    {
        private int max = 10;
        public FixedSizeDocument(int max)
        {
            this.max = max;
        }
        @Override
        public void insertString(int offs, String str, javax.swing.text.AttributeSet a)
                throws javax.swing.text.BadLocationException
        {
            if (getLength()+str.length()>max)  str = str.substring(0, max - getLength());
            super.insertString(offs, str, a);
        }
    }
    public NewNotePanel(boolean visibility){
        init();
        panel.setVisible(visibility);
        addTextField(panel, fields, 1, 56, 198, 201, 32);fields[1].setDocument(new FixedSizeDocument(26));//DESCRIPTION TEXT FIELD
        addTextField(panel, fields, 0, 56, 113, 201, 32);fields[0].setDocument(new FixedSizeDocument(11));//TITLE TEXT FIELD
        addImgButton("Create", panel, buttons,0, this, 55, 400, 92, 33);//BACK BUTTON
        addImgButton("Back", panel, buttons,  1, this, 164, 400, 92, 33);//CREATE BUTTO
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(buttons[0])){
            if(!(fields[0].getText().isEmpty()) && !(fields[1].getText().isEmpty())){
                for(int i=0;i<blocks.size();i++) {
                    if(blocks.get(i).getTitle().equals(fields[0].getText())) {
                        JOptionPane.showMessageDialog(panel, "Please enter a different title!","Warning", JOptionPane.WARNING_MESSAGE);
                        fields[0].setText("");
                        return;
                    }
                }
                block=new NoteBlock(fields[0].getText(), fields[1].getText(), Main.menu.getNoteBlockPanel());
                blocks.add(block);
                Note.insert(block.getTitle(), block.getDescription());
                fields[0].setText("");
                fields[1].setText("");
                panel.setVisible(false);
                Main.nNotePanel.setBlocks(Note.loadDatas("notes"));
                Main.menu.getPanel().setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(panel, "Please fill in all fields!","Warning", JOptionPane.WARNING_MESSAGE);
            }
        }
        if(e.getSource().equals(buttons[1])){
            fields[0].setText("");
            fields[1].setText("");
            panel.setVisible(false);
            Main.menu.getPanel().setVisible(true);
        }
    }
    public ArrayList<NoteBlock> getBlocks(){
        return this.blocks;
    }
    public void setBlocks(ArrayList<NoteBlock> blocks) {
        this.blocks=blocks;
        for (int i=0;i<this.blocks.size();++i)
        {
            addNote(32, 100 + 68* i, 200,30, blocks.get(i).getTitle(), blocks.get(i).getDescription() );
        }
    }
    public void addNote(int x, int y, int width, int height, String title, String desc)
    {
        JTextField field = new JTextField();
        field.setBounds(x, y, width, height);
        field.setText(title);
        field.setEditable(false);
        field.setVisible(true);
        Main.menu.getNoteBlockPanel().add(field);
        JTextField field1 = new JTextField();
        field1.setBounds(x, y+32, width, height);
        field1.setText(desc);
        field1.setEditable(false);
        field1.setVisible(true);
        Main.menu.getNoteBlockPanel().add(field1);
        JButton button = new JButton("Edit");
        button.setBounds(x, y+32, width, height);
        button.setVisible(true);
        Main.menu.getNoteBlockPanel().add(button);
        JButton button1 = new JButton("Delete");
        button1.setBounds(x, y+32, width, height);
        button1.setVisible(true);
        Main.menu.getNoteBlockPanel().add(button1);
    }
    public JPanel getPanel() {
        return panel;
    }
}
