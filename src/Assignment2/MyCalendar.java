package Assignment2;

import java.awt.BorderLayout;
import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;

import javax.swing.*;

public class MyCalendar {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private static final int DISPLAYWIDTH = WIDTH / 3 * 2;
	private static final int DISPLAYHEIGHT = HEIGHT / 3 * 2;
	
	private static final int NDAYSAWEEK = 7;
	private static final int WEEKSINTABLE = 6;
	
	
	
	MyCalendar() {
		initialization();
		getDate();
	}
	
	private void initialization() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		
		weekStrToNum = new HashMap<String, Integer>();
		
		date = new String[WEEKSINTABLE][NDAYSAWEEK];//{{"1", "2" ,"3" ,"1" ,"2" ,"3" ,"1"}};
		cal = Calendar.getInstance();
		days = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		int count = 0;
		for (String str:days){
			weekStrToNum.put(str, count);
			count++;
		}
		
		calendarTable = new JTable(date, days);
		
		
		calendarDisplay = new JScrollPane(calendarTable);
		calendarDisplay.setSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		
		frame.add(calendarDisplay);
		
		customizeLayout();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void customizeLayout() {
		
	}
	
	private void getDate() {
		year = 2016;
		month = 2;
		cal.setTime(new Date(year - 1900, month - 1, 1)); // set time to the first day of selected month
		String[] str = cal.getTime().toString().split(" ");
		int maxNDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		createMonthTable(str[0], maxNDays); //str[0]weekday; str[1]month; str[2]date; str[3]time; str[4]timeZone; str[5]year 
	}
	
	private void createMonthTable(String weekday, int maxNDays) {
		int firstDayIndex = weekStrToNum.get(weekday); 
		System.out.println(firstDayIndex);
		
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
				int lastMonthMaxNDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
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
	
	private static int year;
	private static int month;
//	private static int lastMonth;
//	private static int nextMonth;
	
	private static HashMap<String, Integer> weekStrToNum;
	
}
