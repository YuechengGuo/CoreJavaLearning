package Assignment2;

import java.awt.BorderLayout;

import javax.swing.*;

public class MyCalendar {
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private static final int DISPLAYWIDTH = WIDTH / 3 * 2;
	private static final int DISPLAYHEIGHT = HEIGHT / 3 * 2;
	
	
	
	MyCalendar() {
		initialization();
		
	}
	
	private void initialization() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		
//		date = new String[][];//{{"1", "2" ,"3" ,"1" ,"2" ,"3" ,"1"}};
		days = new String[]{"Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat"};  
		calendarTable = new JTable(date, days);
//		calendarTable.set
		calendarDisplay = new JScrollPane(calendarTable);
		calendarDisplay.setSize(DISPLAYWIDTH, DISPLAYHEIGHT);
		
		
		frame.add(calendarDisplay);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MyCalendar();
	}
	
	private static JFrame frame;
	
	private static Object[][] date;
	private static String[] days; 
	private static JTable calendarTable;
	private static JScrollPane calendarDisplay;
	
	
}
