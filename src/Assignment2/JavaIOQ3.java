package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
		
		getFiles();
		getExtList();
		// getFileList();
		// setupControls();
	}
	
	private void mainDisplay() {

		topPanel = new JPanel();
		topPanel.setSize(TOPPANELWIDTH, TOPPANELHEIGHT);
		frame.add(topPanel);

		tmpFileName = new ArrayList<String>();
		fileExtArrList = new ArrayList<String>();
		fileListArrList = new ArrayList<String>();
		extFilesMap = new HashMap<String, ArrayList<String>>();

		extListModel = new DefaultListModel<String>();
		listExt = new JList<String>(extListModel);
		listExt.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listExt.setSelectedIndex(0);
		listExt.setVisibleRowCount(10);
		listExt.addListSelectionListener(this);
		listExtPane = new JScrollPane(listExt);
		listExtPane.setSize(EXTPANEWIDTH, EXTPANEHEIGHT);
		frame.add(listExtPane);
		
		fileListModel = new DefaultListModel<String>();
		listFile = new JList<String>(fileListModel);
		listFile.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		listFile.setSelectedIndex(0);
		listFile.setVisibleRowCount(10);
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
	
	@Override // selection listener for JList
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == listExt){
				selectedExt = listExt.getSelectedValue();
				getFileList(selectedExt);
			} else if (e.getSource() == listFile) {
				selectedFile = listFile.getSelectedValue();
				// readSelectedFile();
			}
		}
	}
	
	
	private void getExtList() {
//		String path = setPath();
		extListModel.clear();
		for (String key:extFilesMap.keySet()) {
			// System.out.println(key); // test display
			extListModel.addElement(key);
		}
	}
	

	private void getFileList(String key) {
		fileListModel.clear();
		for (String value:extFilesMap.get(key)){
			fileListModel.addElement(value);
		}
	}
	
	/* get all files from current path, map the extension with its files */
	private void getFiles() {
		// tmpFileName.clear();
		try {
			File folder = new File(FILEPATH);
			FilenameFilter fileNameFilter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (name.lastIndexOf('.') > 0){
						int ind = name.lastIndexOf('.'); // index of . in the string
						String str = name.substring(ind); // save the extension in str
						if (!extFilesMap.containsKey(str)) {
							ArrayList<String> tmpList = new ArrayList<String>();
							tmpList.add(name);
							extFilesMap.put(str, tmpList);							
						} else {
							extFilesMap.get(str).add(name);
						}
						return true;
					}
					return false;
				}
			};
			folder.listFiles(fileNameFilter);
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(extFilesMap.toString()); // test display
	}
	
	public static void main(String[] args) {
		new JavaIOQ3();
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
	
	private static HashMap<String, ArrayList<String>> extFilesMap;
	private static ArrayList<String> tmpFileName;
	private static JavaIOQ1 fileFilter;


}
