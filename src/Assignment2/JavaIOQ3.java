package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/* 
 * create a java GUI screen where, load all the file types in a list box like 
 * .txt, .java, .json, .xml, etc.
 */

public class JavaIOQ3 {
	/** set size for the frame */
	private static final int HEIGHT = 500;
	private static final int WIDTH = 500;
	
	/** file path */ 
	private static final String FILEPATH = "/Users/Yuecheng/Desktop/";
	
	JavaIOQ3() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);

		setupExtList();
		setupFileList();
		mainDisplay();
		// setupControls();
		
	}
	
	private void mainDisplay() {
		// JLabel test = new JLabel("Display Extensions:");
//		panel.setSize(500, 300);
//		panel.setBackground(Color.WHITE);
		
		topPanel = new JPanel();
//		extPanel = new JPanel();
//		filePanel = new JPanel();
//		displayPanel = new JPanel();
		
		
		listFilePane = new JScrollPane(listFile);
		displayPane = new JScrollPane(content);
		content = new JTextArea();
		
		topPanel.setSize(WIDTH, HEIGHT / 10);
//		extPanel.setSize(WIDTH / 20 * 5, HEIGHT / 10 * 9);
//		filePanel.setSize(WIDTH / 20 * 5, HEIGHT / 10 * 9);
//		displayPanel.setSize(WIDTH / 20 * 10, HEIGHT / 10 * 9);
		
		listExtPane.setSize(WIDTH / 20 * 3, HEIGHT / 10 * 6);
		listFilePane.setSize(WIDTH / 20 * 3, HEIGHT / 10 * 6);
		content.setSize(WIDTH/ 20 * 8, HEIGHT / 10 * 6);
		
		// area test starts
		topPanel.setBackground(Color.WHITE);
		listExtPane.setBackground(Color.YELLOW);
		listFilePane.setBackground(Color.RED);
		displayPane.setBackground(Color.GREEN);
		content.setBackground(Color.WHITE);
		// area test done
		
//		extPanel.add(listExtPane);
//		filePanel.add(listFilePane);
//		displayPanel.add(displayPane);
		
		frame.add(topPanel);
		frame.add(listExtPane);
		frame.add(listFilePane);
		frame.add(displayPane);
		
		customizeLayout();
		
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	};
	
	private void customizeLayout() {
		Insets insets = frame.getInsets();
		
		topPanel.setBounds(0 + insets.left, 0 + insets.top, topPanel.getWidth(), topPanel.getHeight());
//		extPanel.setBounds(0 + insets.left, 0 + insets.top, WIDTH / 20 * 5, HEIGHT / 10 * 9);
//		filePanel.setBounds(WIDTH / 20 * 5 + insets.left, 0 + insets.top, WIDTH / 20 * 5, HEIGHT / 10 * 9);
//		displayPanel.setBounds(WIDTH / 20 * 10 + insets.left, 0 + insets.top, WIDTH / 20 * 10, HEIGHT / 10 * 9);
		
		// WIDTH / 20 + insets.left, HEIGHT / 10 * 2 + insets.top, WIDTH / 20 * 3, HEIGHT / 10 * 5
		listExtPane.setBounds(WIDTH / 20 + insets.left, HEIGHT / 10 * 2 + insets.top, WIDTH / 20 * 3, HEIGHT / 10 * 6);
		listFilePane.setBounds(WIDTH / 20 * 6+ insets.left, HEIGHT / 10 * 2 + insets.top, WIDTH / 20 * 3, HEIGHT / 10 * 6);
		displayPane.setBounds(WIDTH / 20 * 11 + insets.left, HEIGHT / 10 * 2 + insets.top, WIDTH/ 20 * 8, HEIGHT / 10 * 6);
	}
	
	private void setupExtList() {
//		String path = setPath();
		String path = FILEPATH;
		readFilesExt(path);
		addExtList();
	}
	
	private void readFilesExt(String path) {
		File folder = new File(path);
		fileExtArrList = new ArrayList<String>();
		for (File file:folder.listFiles()){
			String fileName = file.getName();
			int index = indexOfDot(fileName);
			if (index > 0) {
				String extOfFile = fileName.substring(index);	
				if (!fileExtArrList.contains(extOfFile) && fileExtArrList != null) {
					fileExtArrList.add(extOfFile);
				}
			}
		}
		System.out.println(fileExtArrList); // test Display
	}
	
	private int indexOfDot(String str) {
		if (str.contains(".")) {
			return str.lastIndexOf(".");
			}
		return -1;
	}
	
	private void addExtList() {
		DefaultListModel<String> extListModel = new DefaultListModel<String>();
		for (int i = 0; i < fileExtArrList.size(); i++) {
			extListModel.addElement(fileExtArrList.get(i));
		}
		listExt = new JList<String>(extListModel);
		listExt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listExt.setSelectedIndex(0);
		listExt.setVisibleRowCount(10);
		selectedExt = fileExtArrList.get(0);
		listExt.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					selectedExt = listExt.getSelectedValue();
					setupFileList();
				}
			}
		});
		listExtPane = new JScrollPane(listExt);
	}
	
	private void setupFileList() {
//		String path = setPath();
		String path = FILEPATH;
		loadAllFiles(path);
		addFileList();
	}
	
	/*
	 * list all files with selected extension
	 */
	private void loadAllFiles(String path) {
		if (selectedExt != null) {
			File folder = new File(path);
			fileListArrList = new ArrayList<String>();
			for (File file:folder.listFiles()) {
				String fileName = file.getName();
				if (fileName.endsWith(selectedExt)) {
					fileListArrList.add(fileName);
				}
			}
			System.out.println(fileListArrList);
		}
	}
	
	private void addFileList() {
		DefaultListModel<String> fileListModel = new DefaultListModel<String>();
		for (int i = 0; i < fileListArrList.size(); i++) {
			fileListModel.addElement(fileListArrList.get(i));
		}
		
		listFile = new JList<String>(fileListModel);
		listFile.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listFile.setSelectedIndex(0);
		listFile.setVisibleRowCount(10);
		selectedFile = fileListArrList.get(0);
		listFile.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					selectedFile = new String((String) (listFile.getSelectedValue()));
				}
			}
		});
		listFilePane = new JScrollPane(listFile);
	}
	
	/*
	private void setupListbox() {
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
				int len = fileFilter.displayFiles(FILEPATH, str).length;
				String[] strList = fileFilter.displayFiles(FILEPATH, str);
				panel.removeAll();
				
				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < len; i++) {
					JLabel label = new JLabel("\n" + strList[i]);
					sb.append(strList[i] + "\n");
					panel.add(label);
				}
				
				content.setText(sb.toString());
			}
		});
		JLabel listLabel = new JLabel("Display Extensions:");
		topPanel.add(listLabel);
		topPanel.add(cb);
	};*/
	
	/*
	private void comboBoxListener(String ext) {
		System.out.println(fileFilter.displayFiles(FILEPATH, ext)[0]);
		int len = fileFilter.displayFiles(FILEPATH, ext).length;
		String[] str = fileFilter.displayFiles(FILEPATH, ext);
		panel.removeAll();
		for (int i = 0; i < len; i++) {
			JLabel label = new JLabel("\n" + str[i]);
			panel.add(label);
		}
		
	} */
	
	public static void main(String[] args) {
		JavaIOQ3 instance = new JavaIOQ3();
		// fileFilter = new JavaIOQ1();
	}
	
	private static JFrame frame;
	/* declare all panels */
	private static JPanel panel;
	private static JPanel topPanel;
//	private static JPanel extPanel;
//	private static JPanel filePanel;
//	private static JPanel displayPanel;
	private static JScrollPane listExtPane; // list all types of extensions
	private static JScrollPane listFilePane; // list all the files with certain extension
	private static JScrollPane displayPane; // display file contents
	
	private static ArrayList<String> fileExtArrList; // saves all types of extension included in current path
	private static ArrayList<String> fileListArrList; // saves all files with selected extension in current path
	
	private static JList<String> listExt;
	private static JList<String> listFile;
	private static JTextArea content;
	
	private static String selectedExt;
	private static String selectedFile;
	
	private static JavaIOQ1 fileFilter;
}
