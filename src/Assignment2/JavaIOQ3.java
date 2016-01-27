package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/* 
 * create a java GUI screen where, load all the file types in a list box like 
 * .txt, .java, .json, .xml, etc.
 */

public class JavaIOQ3 implements ListSelectionListener {
	/** set size for the frame */
	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;
	
	private static final int TOPPANELWIDTH = WIDTH;
	private static final int TOPPANELHEIGHT = HEIGHT / 10 * 1;
	
	private static final int EXTPANEWIDTH = WIDTH / 20 * 3;
	private static final int EXTPANEHEIGHT = HEIGHT / 10 * 6;
	
	private static final int FILEPANEWIDTH = WIDTH / 20 * 5;
	private static final int FILEPANEHEIGHT = HEIGHT / 10 * 6;
	
	private static final int FILEDISPLAYWIDTH = WIDTH / 20 * 8;
	private static final int FILEDISPLAYHEIGHT = HEIGHT / 10 * 6;
	
	private static final int XOFFSET = WIDTH / 20 * 1;
	private static final int YOFFSET = HEIGHT / 20 * 0;
	
	private static final int XPADDING = WIDTH / 20 * 1;
	private static final int YPADDING = HEIGHT / 10 * 1;
	/** file path */ 
	private static final String FILEPATH = "/Users/Yuecheng/Desktop/";
	
	JavaIOQ3() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		mainDisplay();
		getExtList();
		getFileList();
		// setupControls();
	}
	
	private void mainDisplay() {

		topPanel = new JPanel();
		topPanel.setSize(TOPPANELWIDTH, TOPPANELHEIGHT);
		frame.add(topPanel);
		
		fileExtArrList = new ArrayList<String>();
		fileListArrList = new ArrayList<String>();

		extListModel = new DefaultListModel<String>();
		listExt = new JList<String>(extListModel);
		listExt.addListSelectionListener(this);
		listExtPane = new JScrollPane(listExt);
		listExtPane.setSize(EXTPANEWIDTH, EXTPANEHEIGHT);
		frame.add(listExtPane);
		
		fileListModel = new DefaultListModel<String>();
		listFile = new JList<String>(fileListModel);
		listFile.addListSelectionListener(this);
		listFilePane = new JScrollPane(listFile);
		listFilePane.setSize(FILEPANEWIDTH, FILEPANEHEIGHT);
		frame.add(listFilePane);
		
		displayPane = new JScrollPane(content);
		displayPane.setSize(FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
		content = new JTextArea();
		content.setSize(FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
		frame.add(displayPane);
		
		// area test starts
		topPanel.setBackground(Color.WHITE);
		listExtPane.setBackground(Color.YELLOW);
		listFilePane.setBackground(Color.RED);
		displayPane.setBackground(Color.GREEN);
		content.setBackground(Color.WHITE);
		// area test done
		
		customizeLayout();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	};
	
	
	private void customizeLayout() {
		Insets insets = frame.getInsets();
		topPanel.setBounds(0 + insets.left, YOFFSET + insets.top, TOPPANELWIDTH, TOPPANELHEIGHT);
		listExtPane.setBounds(XOFFSET + insets.left, YOFFSET + TOPPANELHEIGHT + YPADDING + insets.top, EXTPANEWIDTH, EXTPANEHEIGHT);
		listFilePane.setBounds(XOFFSET + EXTPANEWIDTH + XPADDING + insets.left, YOFFSET + TOPPANELHEIGHT + YPADDING + insets.top, FILEPANEWIDTH, FILEPANEHEIGHT);
		displayPane.setBounds(XOFFSET + EXTPANEWIDTH + XPADDING * 2 + FILEPANEWIDTH + insets.left, YOFFSET + TOPPANELHEIGHT + YPADDING + insets.top, FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
	}
	
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == listExt){
				selectedExt = listExt.getSelectedValue();
				getFileList();
			} else if (e.getSource() == listFile) {
				selectedFile = listFile.getSelectedValue();
			}
		}
	}
	
	
	private void getExtList() {
//		String path = setPath();
		String path = FILEPATH;
		readFilesExt(path);
		if (fileExtArrList != null) 
			addExtList();
		else
			System.out.print("empty folder");
	}
	
	private void readFilesExt(String path) {
		fileExtArrList.clear();
		File folder = new File(path);
		for (File file:folder.listFiles()){
			String fileName = file.getName();
			int index = indexOfDot(fileName);
			if (index > 0) {
				String extOfFile = fileName.substring(index);	
				if (!fileExtArrList.contains(extOfFile)) { // removed && fileExtArrList != null
					fileExtArrList.add(extOfFile);
				}
			}
		}
//		System.out.println(fileExtArrList); // test Display
	}
	
	private int indexOfDot(String str) {
		if (str.contains(".")) {
			return str.lastIndexOf(".");
			}
		return -1;
	}
	
	private void addExtList() {
		for (int i = 0; i < fileExtArrList.size(); i++) {
			extListModel.addElement(fileExtArrList.get(i));
		}
		listExt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listExt.setSelectedIndex(0);
		listExt.setVisibleRowCount(10);
		selectedExt = fileExtArrList.get(0);
	}
	
	private void getFileList() {
//		String path = setPath();
		String path = FILEPATH;
		loadAllFiles(path);
		addFileList();
	}
	
	/*
	 * list all files with selected extension
	 */
	private void loadAllFiles(String path) {
		fileListArrList.clear();
		if (selectedExt != null) {
			File folder = new File(path);
			
			for (File file:folder.listFiles()) {
				String fileName = file.getName();
				if (fileName.endsWith(selectedExt)) {
					fileListArrList.add(fileName);
				}
			}
//			System.out.println(fileListArrList); // test display
		}
	}
	
	private void addFileList() {
		fileListModel.clear();
		for (int i = 0; i < fileListArrList.size(); i++) {
			fileListModel.addElement(fileListArrList.get(i));
		}
		
		
		System.out.println(fileListModel); // test display
		
		
		listFile.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listFile.setSelectedIndex(0);
		listFile.setVisibleRowCount(10);
		
	}
	
//	private void readSelectedFile(File f) {
//		if (f != null) {
//			BufferedReader br = new BufferedReader(new FileReader(f));
//			while (br.readLine() != null ){
//				
//			}
//		}
//	}
	
	
	public static void main(String[] args) {
		JavaIOQ3 instance = new JavaIOQ3();
		// fileFilter = new JavaIOQ1();
	}
	
	private static JFrame frame;
	/* declare all panels */
	private static JPanel topPanel;
	
	/* declare all JScrollPane */
	private static JScrollPane listExtPane; // list all types of extensions
	private static JScrollPane listFilePane; // list all the files with certain extension
	private static JScrollPane displayPane; // display file contents
	
	/* declare all ArrayList */
	private static ArrayList<String> fileExtArrList; // saves all types of extension included in current path
	private static ArrayList<String> fileListArrList; // saves all files with selected extension in current path
	
	/* declare all listModel */
	private static DefaultListModel<String> extListModel;
	private static DefaultListModel<String> fileListModel;
	
	/* declare all JList */
	private static JList<String> listExt;
	private static JList<String> listFile;
	private static JTextArea content;
	
	private static String selectedExt;
	private static String selectedFile;
	
	private static JavaIOQ1 fileFilter;


}
