package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

/* 
 * create a java GUI screen where, load all the file types in a list box like 
 * .txt, .java, .json, .xml, etc.
 */

public class JavaIOQ3 {
	/** set size for the frame */
	private static final int HEIGHT = 300;
	private static final int WIDTH =500;
	
	/** file path */ 
	private static final String FILEPATH = "/Users/Yuecheng/Desktop/";
	
	JavaIOQ3() {
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		setupListbox();
		mainDisplay();
		frame.add(topPanel, BorderLayout.NORTH);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void setupListbox() {
//		DefaultListModel<String> listModel = new DefaultListModel<String>();
//		listModel.addElement(".txt");
//		listModel.addElement(".json");
//
//		JList<String> list = new JList<String>(listModel);
//		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
//		list.setSelectedIndex(0);
//		list.setVisibleRowCount(1);
//		listPane = new JScrollPane(list);
//		JLabel listLabel = new JLabel("Display Extensions:");
//
//		topPanel.add(listLabel);
//		topPanel.add(listPane);
		
		JComboBox<String> cb = new JComboBox<String>();
		cb.addItem(".txt");
		cb.addItem(".json");
		cb.addItem(".java");
		cb.addItem(".xml");
		cb.addItem(".pdf");
		cb.addItem(".class");
		cb.setSelectedIndex(0);
		cb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				String str = (String) cb.getSelectedItem();
				comboBoxListener(str);
			}
		});
		JLabel listLabel = new JLabel("Display Extensions:");
		topPanel.add(listLabel);
		topPanel.add(cb);
	};
	
	private void mainDisplay() {
		JLabel test = new JLabel("Display Extensions:");
		panel.setSize(WIDTH, 2 / 3 * HEIGHT);
		panel.setBackground(Color.WHITE);
		panel.add(test);
	};
	
	private void comboBoxListener(String ext) {
		ArrayList<String> printPath = new ArrayList<String>(); 
		System.out.println(fileFilter.displayFiles(FILEPATH, ext)[0]);
		int len = fileFilter.displayFiles(FILEPATH, ext).length;
		// fileFilter.displayFiles(FILEPATH, ext).length;
		//System.out.println(paths);
		JLabel[] labels = new JLabel[len];
//		// labels[0].setText(paths[0].getPath());
		for (int i = 0; i < len; i++) {
			//labels[i].setText();
			panel.add(labels[i]);
		}
		
	}
	
	public static void main(String[] args) {
		JavaIOQ3 instance = new JavaIOQ3();
		fileFilter = new JavaIOQ1();
	}
	
	private static JPanel panel = new JPanel();
	private static JScrollPane listPane = new JScrollPane();
	private static JPanel topPanel = new JPanel();
	private static JavaIOQ1 fileFilter;
}
