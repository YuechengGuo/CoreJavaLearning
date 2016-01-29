package Assignment2;

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.*;

public class MyCalendar implements ActionListener {
	
	private static final int WIDTH = 280;
	private static final int HEIGHT = 240;
	
	private static final int XOFFSET = WIDTH / 20;
	private static final int YOFFSET = HEIGHT / 20;
	
	private static final int DISPLAYWIDTH = WIDTH - 2 * XOFFSET;
	private static final int DISPLAYHEIGHT = 120;
	
	private static final int YPADDING = 10;
	private static final int XPADDING = WIDTH / 20 * 0;
	
	private static final int NDAYSAWEEK = 7;
	private static final int WEEKSINTABLE = 6;
	
	private static final int DEFAULTYEAR = 2016;
	private static final int DEFAULTMONTH = 1;
	

	MyCalendar() {
		initialization();
		getDate();
	}
	
	private void initialization() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		
		weekStrToNum = new HashMap<String, Integer>();
		
		date = new String[WEEKSINTABLE][NDAYSAWEEK];
		days = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		cal = Calendar.getInstance();
		
		int count = 0;
		for (String str:days){
			weekStrToNum.put(str, count);
			count++;
		}
		
		year = DEFAULTYEAR;
		month = DEFAULTMONTH;
		
		calendarTable = new JTable(date, days);
		calendarTable.setSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		
		cbMonthModel = new DefaultComboBoxModel<Integer>();
		cbMonthModel.setSelectedItem(DEFAULTMONTH);
		cbMonth = new JComboBox<Integer>(cbMonthModel);
		selectMonth();
		cbMonth.addActionListener(this);
		cbMonth.setSize(100, 50);
		 frame.add(cbMonth);
		
		cbYearModel = new DefaultComboBoxModel<Integer>();
		cbYearModel.setSelectedItem(DEFAULTYEAR);
		cbYear = new JComboBox<Integer>(cbYearModel);
		selectYear();
		cbYear.addActionListener(this);
		cbYear.setSize(100, 50);
		frame.add(cbYear);

		
		calendarDisplay = new JScrollPane(calendarTable);
		calendarDisplay.setSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		
		frame.add(calendarDisplay);
		
		customizeLayout();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void customizeLayout() {
		Insets insets = frame.getInsets();
		calendarDisplay.setBounds(XOFFSET + insets.left, YOFFSET + insets.top, calendarTable.getWidth(), calendarTable.getHeight());
		cbYear.setBounds(XOFFSET + insets.left, YOFFSET + calendarTable.getHeight() + YPADDING + insets.top, cbYear.getWidth(), cbYear.getHeight());
		cbMonth.setBounds(XOFFSET + cbYear.getWidth() + XPADDING + insets.left, YOFFSET + calendarTable.getHeight() + YPADDING + insets.top, cbMonth.getWidth(), cbMonth.getHeight());
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cbMonth) {
			month = (int) cbMonth.getSelectedItem();
			getDate();
		} else if (e.getSource() == cbYear) {
			year = (int) cbYear.getSelectedItem();
			getDate();
		}
	}
	
	private void selectMonth() {
		for (int i = 1; i < 13; i++) {
			cbMonthModel.addElement(i);
		}
	}
	
	private static void selectYear() {
		for (int i = 1900; i < 2100; i++) {
			cbYearModel.addElement(i);
		}
	}	
	
	private void getDate() {
		calendarTable.repaint();
		cal.setTime(new Date(year - 1900, month - 1, 1)); // set time to the first day of selected month
		String[] str = cal.getTime().toString().split(" ");
		int maxNDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		createMonthTable(str[0], maxNDays); //str[0]weekday; str[1]month; str[2]date; str[3]time; str[4]timeZone; str[5]year
//		// test display start
//		System.out.println("Weekday:" + str[0] + ". Month: " + str[1] + ". Date: " + str[2] + ". Year: " + str[5]);
//		for (int i = 0; i < date.length; i++) { 
//			String rowElement = "";
//			for (int j = 0; j < date[i].length; j++) {
//				rowElement += date[i][j] + ", ";
//			}
//			System.out.println("row " + i + ": " + rowElement);
//		}
//		// test display done
	}
	

	
	private void createMonthTable(String weekday, int maxNDays) {
		int firstDayIndex = weekStrToNum.get(weekday); 
		for (int i = 0; i < NDAYSAWEEK * WEEKSINTABLE; i ++) {
			int[] LMNMY = new int[3];
			int ind;
			if (i < firstDayIndex) {
				LMNMY = lastMonthNextMonthYear();
				cal.setTime(new Date(LMNMY[2] - 1900, LMNMY[0] - 1, 1)); 
				int lastMonthMaxNDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
				ind = lastMonthMaxNDays - firstDayIndex + i;
				addDayToTable(i, ind, LMNMY[0]);
			} else if (i >= firstDayIndex + maxNDays) {
				cal.setTime(new Date(LMNMY[2] - 1900, LMNMY[1] - 1, 1)); 
				ind = i - firstDayIndex - maxNDays;
				addDayToTable(i, ind, LMNMY[0]);
			} else {
				ind = i - firstDayIndex;
				addDayToTable(i, ind, month);
			}
		}
	}
	
	private int[] lastMonthNextMonthYear() {
		int[] LMNMY = new int[3];
		if (month - 1 < 1) { // current month is Jan
			LMNMY[0] = 12;
			LMNMY[1] = month + 1;
			LMNMY[2] = year - 1;
		} else if (month - 1 > 11) { // current month is Dec
			LMNMY[0] = month - 1;
			LMNMY[1] = 1;
			LMNMY[2] = year + 1;
		} else { // current month is Feb - Nov
			LMNMY[0] = month - 1;
			LMNMY[1] = month + 1;
			LMNMY[2] = year;
		}
		return LMNMY;
	}
	
	private void addDayToTable(int i, int index, int month) {
		int row;
		if (month == 12)
			row = 0;
		else
			row = i / NDAYSAWEEK;
		date[row][i % 7] = "" + (index + 1);
	}
	
	
	public static void main(String[] args) {
		new MyCalendar();
	}
	
	private static JFrame frame;
	
	private static Object[][] date;
	private static String[] days; 
	private static Calendar cal;
	private static JTable calendarTable;
	private static JScrollPane calendarDisplay;
	
	private static DefaultComboBoxModel<Integer> cbMonthModel;
	private static JComboBox<Integer> cbMonth;
	
	private static DefaultComboBoxModel<Integer> cbYearModel;
	private static JComboBox<Integer> cbYear;
	
	private static int year;
	private static int month;
	
	private static HashMap<String, Integer> weekStrToNum;



	
	
}
