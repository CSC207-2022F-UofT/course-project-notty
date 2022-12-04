package tasks;

import notes.NotesScreen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.*;

class CalendarDataManager{
    static final int CAL_WIDTH = 7;
    final static int CAL_HEIGHT = 6;
    int[][] calDates = new int[CAL_HEIGHT][CAL_WIDTH];
    int calYear;
    int calMonth;
    int calDayOfMon;
    UserCaseReadCategory categoryAccess;
    UserCaseReadTask taskAccess;
    final int[] calLastDateOfMonth ={31,28,31,30,31,30,31,31,30,31,30,31};
    int calLastDate;
    Calendar today = Calendar.getInstance();
    Calendar cal;

    public CalendarDataManager(){
        setToday();
    }
    public void setToday(){
        calYear = today.get(Calendar.YEAR);
        calMonth = today.get(Calendar.MONTH);
        calDayOfMon = today.get(Calendar.DAY_OF_MONTH);
        makeCalData(today);
    }
    private void makeCalData(Calendar cal){
        int calStartingPos = (cal.get(Calendar.DAY_OF_WEEK)+7-(cal.get(Calendar.DAY_OF_MONTH))%7)%7;
        if(calMonth == 1) calLastDate = calLastDateOfMonth[calMonth] + leapCheck(calYear);
        else calLastDate = calLastDateOfMonth[calMonth];
        for(int i = 0 ; i<CAL_HEIGHT ; i++){
            for(int j = 0 ; j<CAL_WIDTH ; j++){
                calDates[i][j] = 0;
            }
        }
        for(int i = 0, num = 1, k; i<CAL_HEIGHT ; i++){
            if(i == 0) k = calStartingPos;
            else k = 0;
            for(int j = k ; j<CAL_WIDTH ; j++){
                if(num <= calLastDate) calDates[i][j]=num++;
            }
        }
    }
    private int leapCheck(int year){
        if(year%4 == 0 && year%100 != 0 || year%400 == 0) return 1;
        else return 0;
    }
    public void moveMonth(int mon){
        calMonth += mon;
        if(calMonth>11) while(calMonth>11){
            calYear++;
            calMonth -= 12;
        } else if (calMonth<0) while(calMonth<0) {
            calYear--;
            calMonth += 12;
        }
        cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
        makeCalData(cal);
    }
}

public class TasksUI extends CalendarDataManager{
    JFrame mainFrame;
    JPanel calOpPanel;
    JButton todayBut;
    JLabel todayLab;
    JButton lYearBut;
    JButton lMonBut;
    JLabel curMMYYYYLab;
    JButton nMonBut;
    JButton nYearBut;
    ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();

    JPanel calPanel;
    JButton[] weekDaysName;
    JButton[][] dateButs = new JButton[6][7];
    listenForDateButs lForDateButs = new listenForDateButs();

    JPanel infoPanel;
    JLabel infoClock;

    JPanel categoriesPanel;
    JLabel selectedDate;
    JPanel toDoArea;
    JScrollPane toDoAreaSP;
    JPanel toDoSubPanel;
    JButton createBut;

    JPanel frameBottomPanel;
    JPanel frameBottomPanel1;
    JPanel frameBottomPanel2;
    JPanel frameBottomPanel3;

    JLabel bottomInfo = new JLabel("Welcome to Task Manager!");
    JButton gotoNotes;
    final String[] WEEK_DAY_NAME = { "SUN", "MN", "TUE", "WD", "THR", "FRI", "SAT" };
    final String title = "Task Manager";

    ///////////// TO DO CATEGORIES /////////////////////////////////////////
    ArrayList<Category> categoryList;
    ArrayList<Task> taskList;
    ArrayList<JPanel> categoryPanel;
    ArrayList<JPanel> tasksPanel;
    ArrayList<JPanel> listPanel;
    ArrayList<JButton> categoryButton;
    ArrayList<JButton> taskAddButton;
    ArrayList<JCheckBox> taskMarkBox;
    ArrayList<JButton> taskButton;

    public static void main(String[] args){
        SwingUtilities.invokeLater(TasksUI::new);
    }
    public TasksUI(){
        mainFrame = new JFrame(title);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(700,400);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        try{
            UIManager.setLookAndFeel ("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(mainFrame) ;
        }catch(Exception e){
            bottomInfo.setText("Welcome to Task Manager!");
        }

        calOpPanel = new JPanel();
        todayBut = new JButton("Today");
        todayBut.setToolTipText("Today");
        todayBut.addActionListener(lForCalOpButtons);
        todayLab = new JLabel(today.get(Calendar.MONTH)+1+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR));
        lYearBut = new JButton("<<");
        lYearBut.setToolTipText("Previous Year");
        lYearBut.addActionListener(lForCalOpButtons);
        lMonBut = new JButton("<");
        lMonBut.setToolTipText("Previous Month");
        lMonBut.addActionListener(lForCalOpButtons);
        curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
        nMonBut = new JButton(">");
        nMonBut.setToolTipText("Next Month");
        nMonBut.addActionListener(lForCalOpButtons);
        nYearBut = new JButton(">>");
        nYearBut.setToolTipText("Next Year");
        nYearBut.addActionListener(lForCalOpButtons);
        calOpPanel.setLayout(new GridBagLayout());
        GridBagConstraints calOpGC = new GridBagConstraints();
        calOpGC.gridx = 1;
        calOpGC.gridy = 1;
        calOpGC.gridwidth = 2;
        calOpGC.gridheight = 1;
        calOpGC.weightx = 1;
        calOpGC.weighty = 1;
        calOpGC.insets = new Insets(5,5,0,0);
        calOpGC.anchor = GridBagConstraints.WEST;
        calOpGC.fill = GridBagConstraints.NONE;
        calOpPanel.add(todayBut,calOpGC);
        calOpGC.gridwidth = 3;
        calOpGC.gridx = 2;
        calOpGC.gridy = 1;
        calOpPanel.add(todayLab,calOpGC);
        calOpGC.anchor = GridBagConstraints.CENTER;
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 1;
        calOpGC.gridy = 2;
        calOpPanel.add(lYearBut,calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 2;
        calOpGC.gridy = 2;
        calOpPanel.add(lMonBut,calOpGC);
        calOpGC.gridwidth = 2;
        calOpGC.gridx = 3;
        calOpGC.gridy = 2;
        calOpPanel.add(curMMYYYYLab,calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 5;
        calOpGC.gridy = 2;
        calOpPanel.add(nMonBut,calOpGC);
        calOpGC.gridwidth = 1;
        calOpGC.gridx = 6;
        calOpGC.gridy = 2;
        calOpPanel.add(nYearBut,calOpGC);

        calPanel=new JPanel();
        weekDaysName = new JButton[7];
        for(int i=0 ; i<CAL_WIDTH ; i++){
            weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
            weekDaysName[i].setBorderPainted(false);
            weekDaysName[i].setContentAreaFilled(false);
            weekDaysName[i].setForeground(Color.WHITE);
            if(i == 0) weekDaysName[i].setBackground(new Color(200, 50, 50));
            else if (i == 6) weekDaysName[i].setBackground(new Color(50, 100, 200));
            else weekDaysName[i].setBackground(new Color(150, 150, 150));
            weekDaysName[i].setOpaque(true);
            weekDaysName[i].setFocusPainted(false);
            calPanel.add(weekDaysName[i]);
        }
        for(int i=0 ; i<CAL_HEIGHT ; i++){
            for(int j=0 ; j<CAL_WIDTH ; j++){
                dateButs[i][j]=new JButton();
                dateButs[i][j].setBorderPainted(false);
                dateButs[i][j].setContentAreaFilled(false);
                dateButs[i][j].setBackground(Color.WHITE);
                dateButs[i][j].setOpaque(true);
                dateButs[i][j].addActionListener(lForDateButs);
                calPanel.add(dateButs[i][j]);
            }
        }
        calPanel.setLayout(new GridLayout(0,7,2,2));
        calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
        showCal();

        infoPanel = new JPanel();
        infoPanel.setLayout(new BorderLayout());
        infoClock = new JLabel("", SwingConstants.RIGHT);
        infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoPanel.add(infoClock, BorderLayout.NORTH);
        selectedDate = new JLabel("<Html><font size=3>"+(today.get(Calendar.MONTH)+1)+"/"+today.get(Calendar.DAY_OF_MONTH)+"/"+today.get(Calendar.YEAR)+"&nbsp;(Today)</html>", SwingConstants.LEFT);

        ///////////// TO DO CATEGORIES ////////////////////

        categoriesPanel = new JPanel();
        toDoArea = new JPanel();
        toDoArea.setLayout(new BoxLayout(toDoArea, BoxLayout.PAGE_AXIS));
        toDoAreaSP = new JScrollPane(toDoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        readData();

        toDoSubPanel = new JPanel();
        createBut = new JButton("Create New Category");
        gotoNotes = new JButton("go back to Notes");
        gotoNotes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                mainFrame.dispose();
                new NotesScreen();
            }
        });
        createBut.addActionListener(new ListenForCreateCategoryButtons(calYear, (calMonth+1), calDayOfMon));
        toDoSubPanel.add(createBut);
        categoriesPanel.setLayout(new BorderLayout());
        categoriesPanel.add(selectedDate, BorderLayout.NORTH);
        categoriesPanel.add(toDoAreaSP,BorderLayout.CENTER);
        categoriesPanel.add(toDoSubPanel,BorderLayout.SOUTH);

        JPanel frameSubPanelWest = new JPanel();
        Dimension calOpPanelSize = calOpPanel.getPreferredSize();
        calOpPanelSize.height = 90;
        calOpPanel.setPreferredSize(calOpPanelSize);
        frameSubPanelWest.setLayout(new BorderLayout());
        frameSubPanelWest.add(calOpPanel,BorderLayout.NORTH);
        frameSubPanelWest.add(calPanel,BorderLayout.CENTER);

        JPanel frameSubPanelEast = new JPanel();
        Dimension infoPanelSize=infoPanel.getPreferredSize();
        infoPanelSize.height = 65;
        infoPanel.setPreferredSize(infoPanelSize);
        frameSubPanelEast.setLayout(new BorderLayout());
        frameSubPanelEast.add(infoPanel,BorderLayout.NORTH);
        frameSubPanelEast.add(categoriesPanel,BorderLayout.CENTER);

        Dimension frameSubPanelWestSize = frameSubPanelWest.getPreferredSize();
        frameSubPanelWestSize.width = 410;
        frameSubPanelWest.setPreferredSize(frameSubPanelWestSize);

        frameBottomPanel = new JPanel(new GridLayout(1, 3));
        frameBottomPanel1 = new JPanel(new FlowLayout((FlowLayout.LEFT)));
        frameBottomPanel2 = new JPanel();
        frameBottomPanel3 = new JPanel();
        frameBottomPanel1.add(gotoNotes);
        frameBottomPanel2.add(bottomInfo);
        frameBottomPanel.add(frameBottomPanel1);
        frameBottomPanel.add(frameBottomPanel2);
        frameBottomPanel.add(frameBottomPanel3);

        mainFrame.setLayout(new BorderLayout());
        mainFrame.add(frameSubPanelWest, BorderLayout.WEST);
        mainFrame.add(frameSubPanelEast, BorderLayout.CENTER);
        mainFrame.add(frameBottomPanel, BorderLayout.SOUTH);
        mainFrame.setVisible(true);

        focusToday();
        ThreadControl threadCnl = new ThreadControl();
        threadCnl.start();
    }
    private void focusToday(){
        if(today.get(Calendar.DAY_OF_WEEK) == 1)
            dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
        else
            dateButs[today.get(Calendar.WEEK_OF_MONTH)-1][today.get(Calendar.DAY_OF_WEEK)-1].requestFocusInWindow();
    }

    private void readData() {
        toDoArea.removeAll();
        categoryAccess = new UserCaseReadCategory(calYear, (calMonth + 1), calDayOfMon);
        categoryList = categoryAccess.accessData();
        if (categoryList.size() == 0) {
            toDoArea.revalidate();
            toDoArea.repaint();
            return;
        }
        categoryPanel = new ArrayList<>();
        tasksPanel = new ArrayList<>();
        listPanel = new ArrayList<>();

        categoryButton = new ArrayList<>();
        taskAddButton = new ArrayList<>();
        taskMarkBox = new ArrayList<>();
        taskButton = new ArrayList<>();
        for (int i = 0; i < categoryList.size(); i++) {
            // set panels
            categoryPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
            listPanel.add(new JPanel());
            listPanel.get(i).setLayout(new BoxLayout(listPanel.get(i), BoxLayout.Y_AXIS));

            // set category Button with "title"
            categoryButton.add(new JButton(categoryList.get(i).getTitle()));
            categoryButton.get(i).setContentAreaFilled(false);
            categoryButton.get(i).addActionListener(new ListenForEditCategoryButtons(categoryList.get(i).getId(), categoryList.get(i).getTitle()));
            categoryButton.get(i).setBackground(Color.WHITE);
            categoryButton.get(i).setOpaque(true);

            // set category Button with "+"
            taskAddButton.add(new JButton("+"));
            taskAddButton.get(i).addActionListener(new ListenForCreateTaskButtons(categoryList.get(i).getId()));
            taskAddButton.get(i).setPreferredSize(new Dimension(38, 23));
            taskAddButton.get(i).setContentAreaFilled(false);
            taskAddButton.get(i).setBackground(Color.WHITE);
            taskAddButton.get(i).setOpaque(true);

            // set categoryPanel with "title" "+" buttons
            categoryPanel.get(i).add(categoryButton.get(i));
            categoryPanel.get(i).add(taskAddButton.get(i));
            listPanel.get(i).add(categoryPanel.get(i));

            taskAccess = new UserCaseReadTask(categoryList.get(i).getId());
            taskList = taskAccess.accessData();
            for (Task task : taskList) {
                tasksPanel.add(new JPanel(new FlowLayout(FlowLayout.LEFT)));
                taskMarkBox.add(new JCheckBox(""));
                taskMarkBox.get(tasksPanel.size() - 1).setSelected(task.getMarked() != 0);
                taskMarkBox.get(tasksPanel.size() - 1).addActionListener(new ListenForMarkTaskCheckboxes(task.getId(), task.getMarked()));
                taskButton.add(new JButton(task.getTitle()));
                taskButton.get(tasksPanel.size() - 1).addActionListener(new ListenForEditTaskButtons(
                        task.getId(), task.getTitle(), task.getCategoryId()));
                taskButton.get(tasksPanel.size() - 1).setOpaque(false);
                taskButton.get(tasksPanel.size() - 1).setContentAreaFilled(false);
                taskButton.get(tasksPanel.size() - 1).setBorderPainted(false);
                tasksPanel.get(tasksPanel.size() - 1).add(taskMarkBox.get(tasksPanel.size() - 1));
                tasksPanel.get(tasksPanel.size() - 1).add(taskButton.get(tasksPanel.size() - 1));
                listPanel.get(i).add(tasksPanel.get(tasksPanel.size() - 1));
            }
            toDoArea.add(listPanel.get(i));
        }
    }

    private void showCal(){
        for(int i=0;i<CAL_HEIGHT;i++){
            for(int j=0;j<CAL_WIDTH;j++){
                String fontColor="black";
                if(j==0) fontColor="red";
                else if(j==6) fontColor="blue";

                dateButs[i][j].setText("<html><font color="+fontColor+">"+calDates[i][j]+"</font></html>");

                JLabel todayMark = new JLabel("<html><font color=green>*</html>");
                dateButs[i][j].removeAll();
                if(calMonth == today.get(Calendar.MONTH) &&
                        calYear == today.get(Calendar.YEAR) &&
                        calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)){
                    dateButs[i][j].add(todayMark);
                    dateButs[i][j].setToolTipText("Today");
                }

                dateButs[i][j].setVisible(calDates[i][j] != 0);
            }
        }
    }
    private class ListenForCalOpButtons implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == todayBut){
                setToday();
                lForDateButs.actionPerformed(e);
                focusToday();
            }
            else if(e.getSource() == lYearBut) moveMonth(-12);
            else if(e.getSource() == lMonBut) moveMonth(-1);
            else if(e.getSource() == nMonBut) moveMonth(1);
            else if(e.getSource() == nYearBut) moveMonth(12);

            curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>"+((calMonth+1)<10?"&nbsp;":"")+(calMonth+1)+" / "+calYear+"</th></tr></table></html>");
            showCal();
        }
    }

    private class ListenForCreateCategoryButtons implements ActionListener{
        LocalDate daily;
        public ListenForCreateCategoryButtons(int year, int month, int date)
        { this.daily = LocalDate.of(year, month, date); }
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            final JFrame parent = new JFrame("New Category");
            String title = JOptionPane.showInputDialog(parent,
                    "New Category Title:",null);
            if(title != null) {
                 UserCaseCreateCategory userCaseCreateCategory = new UserCaseCreateCategory(title);
                 userCaseCreateCategory.action(this.daily);
            }
            readData();
        }
    }

    private class ListenForEditCategoryButtons implements ActionListener{
        int id;
        private final String title;
        public ListenForEditCategoryButtons(int id, String title) {
            this.id = id;
            this.title = title;
        }
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            int n = JOptionPane.showOptionDialog(new JFrame(), "Edit or Delete?",
                    "Modify Category", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new Object[] {"Edit", "Delete"}, JOptionPane.YES_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                final JFrame parent = new JFrame("New Category");
                String title = JOptionPane.showInputDialog(parent,
                        "Edit Category Title to:",  this.title);
                if(title != null) {
                    UserCaseEditCategory userCaseEditCategory = new UserCaseEditCategory(this.id, title);
                    userCaseEditCategory.action();
                }
                readData();
            } else if (n == JOptionPane.NO_OPTION) {
                UserCaseDeleteCategory userCaseDeleteCategory = new UserCaseDeleteCategory(this.id);
                userCaseDeleteCategory.action();
                UserCaseDeleteTask userCaseDeleteTask = new UserCaseDeleteTask(this.id);
                userCaseDeleteTask.action(this.id);
                readData();
            }
        }
    }

    private class ListenForEditTaskButtons implements ActionListener{
        int id;
        private final String title;
        private final int categoryId;
        public ListenForEditTaskButtons(int id, String title, int categoryId) {
            this.id = id;
            this.title = title;
            this.categoryId = categoryId;
        }
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {

            int n = JOptionPane.showOptionDialog(new JFrame(), "Edit or Delete?",
                    "Modify Task", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, new Object[] {"Edit", "Delete"}, JOptionPane.YES_OPTION);

            if (n == JOptionPane.YES_OPTION) {
                JLabel titleLabel = new JLabel("Edit Task name to:");
                Font titleFont = titleLabel.getFont();
                titleLabel.setFont(titleFont.deriveFont(titleFont.getStyle() & ~Font.BOLD));
                JLabel categoryLabel = new JLabel("Categorize Task to:");
                Font categoryFont = categoryLabel.getFont();
                categoryLabel.setFont(categoryFont.deriveFont(categoryFont.getStyle() & ~Font.BOLD));
                JTextField title = new JTextField(this.title);

                categoryAccess = new UserCaseReadCategory(calYear, (calMonth + 1), calDayOfMon);
                categoryList = categoryAccess.accessData();
                String[] categoryNameList = new String[categoryList.size()];
                int categoryComboBoxId = 0;
                for (int j = 0; j < categoryList.size(); j++){
                    categoryNameList[j] = categoryList.get(j).getTitle();
                    if (this.categoryId == categoryList.get(j).getId()) { categoryComboBoxId = j; }
                }
                JComboBox<String> category = new JComboBox<>(categoryNameList);
                category.setSelectedItem(categoryList.get(categoryComboBoxId).getTitle());
                Font checkBoxFont = category.getFont();
                category.setFont(checkBoxFont.deriveFont(checkBoxFont.getStyle() & ~Font.BOLD));
                final JComponent[] inputs = new JComponent[] {
                        titleLabel, title,
                        categoryLabel, category};
                int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION) {
                    UserCaseEditTask userCaseEditTask = new UserCaseEditTask(this.id, title.getText(), (String) category.getSelectedItem());
                    userCaseEditTask.action();}
                readData();
            } else if (n == JOptionPane.NO_OPTION) {
                UserCaseDeleteTask userCaseDeleteTask = new UserCaseDeleteTask(this.id);
                userCaseDeleteTask.action();
                readData();
            }
        }
    }

    private class ListenForCreateTaskButtons implements ActionListener{
        int categoryId;
        public ListenForCreateTaskButtons(int categoryId)
        { this.categoryId = categoryId; }
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            final JFrame parent = new JFrame("New Task");
            String name = JOptionPane.showInputDialog(parent,
                    "New Task Name:",null);
            if(name != null) {
                UserCaseCreateTask userCaseCreateTask = new UserCaseCreateTask(name);
                userCaseCreateTask.action(this.categoryId);
            }
            readData();
        }
    }

    private class ListenForMarkTaskCheckboxes implements ActionListener{
        int id;
        int marked;
        public ListenForMarkTaskCheckboxes(int id, int marked){
            this.id = id;
            this.marked = marked;}
        @Override
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            UserCaseMarkTask userCaseMarkTask = new UserCaseMarkTask(this.id, this.marked);
            userCaseMarkTask.action();
            readData();
        }
    }

    private class listenForDateButs implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            int k=0,l=0;
            for(int i=0 ; i<CAL_HEIGHT ; i++){
                for(int j=0 ; j<CAL_WIDTH ; j++){
                    if(e.getSource() == dateButs[i][j]){
                        k=i;
                        l=j;}}}

            if(!(k ==0 && l == 0)) calDayOfMon = calDates[k][l];
            cal = new GregorianCalendar(calYear,calMonth,calDayOfMon);
            String dDayString;
            int dDay=((int)((cal.getTimeInMillis() - today.getTimeInMillis())/1000/60/60/24));
            if(dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR))
                    && (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
                    && (cal.get(Calendar.DAY_OF_MONTH) == today.get(Calendar.DAY_OF_MONTH))) dDayString = "Today";
            else if(dDay >=0) dDayString = "D-"+(dDay+1);
            else dDayString = "D+"+(dDay)*(-1);

            selectedDate.setText("<Html><font size=3>"+(calMonth+1)+"/"+calDayOfMon+"/"+calYear+"&nbsp;("+dDayString+")</html>");
            createBut.removeActionListener(createBut.getActionListeners()[0]);
            createBut.addActionListener(new ListenForCreateCategoryButtons(calYear, (calMonth+1), calDayOfMon));
            readData();
        }
    }
    private class ThreadControl extends Thread{
        public void run(){
            while(true){
                try{
                    today = Calendar.getInstance();
                    String amPm = (today.get(Calendar.AM_PM)==0?"AM":"PM");
                    String hour;
                    if(today.get(Calendar.HOUR) == 0) hour = "12";
                    else if(today.get(Calendar.HOUR) == 12) hour = " 0";
                    else hour = (today.get(Calendar.HOUR)<10?" ":"")+today.get(Calendar.HOUR);
                    String min = (today.get(Calendar.MINUTE)<10?"0":"")+today.get(Calendar.MINUTE);
                    String sec = (today.get(Calendar.SECOND)<10?"0":"")+today.get(Calendar.SECOND);
                    infoClock.setText(amPm+" "+hour+":"+min+":"+sec);

                    sleep(1000);
                }
                catch(InterruptedException e){
                    System.out.println("Thread:Error");
                }
            }
        }
    }
}