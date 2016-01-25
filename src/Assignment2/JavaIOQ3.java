package Assignment2;

import javax.swing.*;

/* 
 * create a java GUI screen where, load all the file types in a list box like 
 * .txt, .java, .json, .xml, etc.
 */

public class JavaIOQ3 {
	/** set size for the frame */
	private static final int HEIGHT = 300;
	private static final int WIDTH =500;
	
	JavaIOQ3() {
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		setupListbox();
		displayFiles();
		frame.add(listPanel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setupListbox() {
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		listModel.addElement(".txt");
		listModel.addElement(".json");

		JList<String> list = new JList<String>(listModel);
		listPanel = new JScrollPane(list);
		
	};
	
	private void displayFiles() {};
	
	public static void main(String[] args) {
		JavaIOQ3 instance = new JavaIOQ3();
	}
	
	private static JPanel panel = new JPanel();
	private static JScrollPane listPanel = new JScrollPane();
}
