package Assignment2;

/*
 * create a GUI program using Java Swing:
 * Display a Text area for input paragraph. 
 * On click of a button, save the file in a .txt file.
 */

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class JavaIOQ2 {
	/** set size for the frame */
	private static final int HEIGHT = 300;
	private static final int WIDTH =300;
	
	JavaIOQ2() {
		Frame frame = new Frame();
		frame.setSize(WIDTH, HEIGHT);
		setupInput();
		createButton();	
		frame.add(panel);
		frame.setVisible(true);
		}
	
	private void setupInput() {
		JTextField textInput = new JTextField("Input text here: ", 10);
		textInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				inputStr += textInput.getText();
				System.out.println(inputStr);
			}
		});
		panel.add(textInput);
	}
	
	private void createButton() {}

	
	public static void main(String[] args) {
		JavaIOQ2 instance = new JavaIOQ2();
		
	}
	
	private static JPanel panel = new JPanel();
	private static String inputStr = "";
}
	
