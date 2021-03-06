package Assignment2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/* 
 * create a java GUI screen where, load all the file types in a list box like 
 * .txt, .java, .json, .xml, etc.
 */

public class JavaIOQ3 implements ListSelectionListener, ActionListener {
	/** set size for the frame */
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	
	private static final int SCROLLPANEYOFFSET = HEIGHT / 10 * 1;
	
	private static final int PATHLABELWIDTH = 120;
	private static final int PATHLABELHEIGHT = HEIGHT / 10 / 2;
	
	private static final int SHOWPATHWIDTH = WIDTH / 20 * 12;
	private static final int SHOWPATHHEIGHT = HEIGHT / 10 / 2;
	
	private static final int BUTTONWIDTH = WIDTH / 20 * 3;
	private static final int BUTTONHEIGHT = HEIGHT / 10 / 2;
	
	private static final int EXTPANEWIDTH = WIDTH / 20 * 3;
	private static final int EXTPANEHEIGHT = HEIGHT / 10 * 6;
	
	private static final int FILEPANEWIDTH = WIDTH / 20 * 4;
	private static final int FILEPANEHEIGHT = HEIGHT / 10 * 6;
	
	private static final int FILEDISPLAYWIDTH = WIDTH / 20 * 9;
	private static final int FILEDISPLAYHEIGHT = HEIGHT / 10 * 6;
	
	private static final int XOFFSET = WIDTH / 20 * 1;
	private static final int YOFFSET = HEIGHT / 20 * 1;
	
	private static final int XPADDING = WIDTH / 20 * 1;
	private static final int YPADDING = HEIGHT / 10 * 1;
	/** file path */ 
	private static final String DEFAULTPATH = "/Users/Yuecheng/Desktop/";
	
	JavaIOQ3() {
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		
		mainDisplay();
		getFiles();
	}
	
	private void mainDisplay() {
		
		filePath = DEFAULTPATH;
		pathLabel = new JLabel("Current Directory: ");
		pathLabel.setSize(PATHLABELWIDTH, PATHLABELHEIGHT);
		frame.add(pathLabel);
		
		showPath = new JTextArea(filePath);
		showPath.setSize(SHOWPATHWIDTH, SHOWPATHHEIGHT);
		showPath.setEditable(false);
		frame.add(showPath);
		
		fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(filePath));
		fileChooser.setDialogTitle("Select Directory");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		pathButton = new JButton("Choose Directory");
		pathButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
		pathButton.addActionListener(this);
		frame.add(pathButton);
		
		openFileButton = new JButton("Open file");
		openFileButton.setSize(BUTTONWIDTH, BUTTONHEIGHT);
		openFileButton.addActionListener(this);
		frame.add(openFileButton);
		
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
		
		content = new JTextArea("");
		content.setSize(FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
		content.setEditable(false);
		displayPane = new JScrollPane(content);
		displayPane.setSize(FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
		displayPane.setPreferredSize(new Dimension(FILEDISPLAYWIDTH / 2, FILEDISPLAYHEIGHT / 2));
		frame.add(displayPane);
		
		// area test starts
//		topPanel.setBackground(Color.WHITE);
//		listExtPane.setBackground(Color.YELLOW);
//		listFilePane.setBackground(Color.RED);
//		displayPane.setBackground(Color.GREEN);
//		content.setBackground(Color.RED);
		// area test done
		
		customizeLayout();
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	};
	
	
	private void customizeLayout() {
		Insets insets = frame.getInsets();
		pathLabel.setBounds(XOFFSET + insets.left, YOFFSET + insets.top, pathLabel.getWidth(), pathLabel.getHeight());
		showPath.setBounds(XOFFSET + insets.left + pathLabel.getWidth(), YOFFSET + insets.top, showPath.getWidth(), showPath.getHeight());
		listExtPane.setBounds(XOFFSET + insets.left, SCROLLPANEYOFFSET + YPADDING + insets.top, EXTPANEWIDTH, EXTPANEHEIGHT);
		listFilePane.setBounds(XOFFSET + EXTPANEWIDTH + XPADDING + insets.left, SCROLLPANEYOFFSET + YPADDING + insets.top, FILEPANEWIDTH, FILEPANEHEIGHT);
		displayPane.setBounds(XOFFSET + EXTPANEWIDTH + XPADDING * 2 + FILEPANEWIDTH + insets.left, SCROLLPANEYOFFSET + YPADDING + insets.top, FILEDISPLAYWIDTH, FILEDISPLAYHEIGHT);
		pathButton.setBounds(XOFFSET + insets.left, SCROLLPANEYOFFSET + EXTPANEHEIGHT + YPADDING + insets.top, BUTTONWIDTH, BUTTONHEIGHT);
		openFileButton.setBounds(XOFFSET + EXTPANEWIDTH + XPADDING + insets.left, SCROLLPANEYOFFSET + EXTPANEHEIGHT + YPADDING + insets.top, BUTTONWIDTH, BUTTONHEIGHT);
	}
	
	@Override // selection listener for JList
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			if (e.getSource() == listExt){
				String selectedExt = listExt.getSelectedValue();
				content.setText("");
				getFileList(selectedExt);
			} else if (e.getSource() == listFile) {
				selectedFile = filePath + listFile.getSelectedValue();
				content.setText("");
				readSelectedFile();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == pathButton) {
			setPath();
		} else if (e.getSource() == openFileButton) {
			openSelectedFile();
		}
		
	}

	
	/* get all files from current path, map the extension with its files */
	private void getFiles() {
		// setPath();
		extFilesMap.clear();
		try {
			File folder = new File(filePath);
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
			getExtList();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(extFilesMap.toString()); // test display
	}
	
	private void setPath() {
		if (fileChooser.showOpenDialog(openFileButton) == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getCurrentDirectory().toString() + "/";
			String subPath = fileChooser.getSelectedFile().getName() + "/";
			filePath = path + subPath;
			showPath.setText(filePath);
			fileListModel.clear();
			content.setText("");
			getFiles();
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
	
	private void readSelectedFile() {
//		System.out.println(fileName); // test display
		content.setText("");
		BufferedReader rd = null;
		try{
			rd = new BufferedReader(new FileReader(selectedFile));
			String str = "";
			while (rd.readLine() != null){
				str += rd.readLine() + "\n";
			}
			if (selectedFile.endsWith(".txt") || selectedFile.endsWith(".doc") || selectedFile.endsWith(".docx")  )
				content.setText(str);
			else
				content.setText(selectedFile);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void openSelectedFile() {
		try {
			Desktop.getDesktop().open(new File(selectedFile));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new JavaIOQ3();
		// fileFilter = new JavaIOQ1();
	}
	
	private static JFrame frame;	
	
	/* declare current directory by JLabel */
	private static JLabel pathLabel;
	private static JTextArea showPath;
	
	/* declare path finder */
	private static JFileChooser fileChooser;
	
	private static String filePath;
	private static String selectedFile;
	
	/* declare all buttons */
	private static JButton pathButton;
	private static JButton openFileButton;
	
	/* declare all JScrollPane */
	private static JScrollPane listExtPane; // list all types of extensions
	private static JScrollPane listFilePane; // list all the files with certain extension
	private static JScrollPane displayPane; // display file contents
		
	/* declare all listModel */
	private static DefaultListModel<String> extListModel;
	private static DefaultListModel<String> fileListModel;
	
	/* declare all JList */
	private static JList<String> listExt;
	private static JList<String> listFile;
	
	/* declare preview file reader */
	private static JTextArea content;
	
	/* declare a HashMap to map extension with its files */
	private static HashMap<String, ArrayList<String>> extFilesMap;

}
