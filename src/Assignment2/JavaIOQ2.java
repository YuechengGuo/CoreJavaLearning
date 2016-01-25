package Assignment2;

/*
 * create a GUI program using Java Swing:
 * Display a Text area for input paragraph. 
 * On click of a button, save the file in a .txt file. 
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class JavaIOQ2 {
	/** set size for the frame */
	private static final int HEIGHT = 300;
	private static final int WIDTH =500;
	
	JavaIOQ2() {
		JFrame frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		setupInput();
		createButton();	
		frame.add(panel);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	
	private void setupInput() {
		JLabel textLabel = new JLabel("Input text here: ");
		JTextField textInput = new JTextField(20);
		JLabel textDisplay = new JLabel("File content: ");
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				inputStr += textInput.getText();
				textDisplay.setText("File content: " + inputStr);
				System.out.println(inputStr);
			}
		});
		panel.add(textLabel);
		panel.add(textInput);
		panel.add(textDisplay);	
	}
	
	private void createButton() {
		JButton button = new JButton("Save the file");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				createFile();
			}
		});
		bottomPanel.add(button);
	}
	
	private void createFile(){
		try {
			PrintWriter writer = new PrintWriter("text01.txt");
			writer.println(inputStr);
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		JavaIOQ2 instance = new JavaIOQ2();
		
	}
	
	private static JPanel panel = new JPanel();
	private static JPanel bottomPanel = new JPanel();
	private static String inputStr = "";
}
	
