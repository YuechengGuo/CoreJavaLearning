package Assignment2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

class UserInfo {
	UserInfo(String name, String pass, double bal) {
		userName = name;
		password = pass;
		balance = bal;
	}
	
	public String getUserName(){
		return userName;
	}
	public String getPassword(){
		return password;
	}
	public double getBalance(){
		return balance;
	}
	
	private String userName;
	private String password;
	private double balance;
}

public class CheckBalance {
	
	/** file path */ 
	private static final String DEFAULTPATH = "/Users/Yuecheng/Desktop/";
	
	private static final String FILENAME = "login.properties";
	
	public CheckBalance() throws FileNotFoundException, IOException {
		initialization();
		createPropertiesFile();
		getPropertiesFile();
		setupConsoleLogin();
	}
	
	private void initialization() throws FileNotFoundException {
		prop = new Properties();
		input = new FileInputStream(DEFAULTPATH + FILENAME);//getClass().getClassLoader().getResourceAsStream(DEFAULTPATH + FILENAME);
		scan = new Scanner(System.in);
		access = false;
	}
	
	private void createPropertiesFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		FileOutputStream fileOutput = new FileOutputStream(DEFAULTPATH + FILENAME);
		UserInfo user1 = new UserInfo("Yuecheng", "passwordYG", 2000);
		UserInfo user2 = new UserInfo("Jie", "passwordJL", 2000);
		props.setProperty(user1.getUserName(), user1.getPassword());
		props.setProperty(user2.getUserName(), user2.getPassword());
		// writing properties into properties file from java:
		props.store(fileOutput, "Properties file created from Java");
	}
	
	private void getPropertiesFile() throws IOException {
		if (input != null) {
			prop.load(input);
//			System.out.println(prop.getProperty("Yuecheng")); //test display
		} else
			System.out.println("File not found");
	}
	
	private void setupConsoleLogin() {
		while (!access) {
			System.out.println("Enter your username: ");
			String inputUserName = scan.nextLine();
			if (prop.containsKey(inputUserName)) {
				System.out.println("Password: ");
				String inputPassword = scan.nextLine();
				if (inputPassword.equals(prop.getProperty(inputUserName))) {
					System.out.println("Accessed");
					access = true;
				} else {
					System.out.println("Invalid password! \n");
				}
			} else {
				System.out.println("User Name doesn't exist!");
			}
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		new CheckBalance();
	}
	
	
	private static UserInfo user;
	private static HashMap<String, String> map;
	/* use prop to save the data from properties file */
	private static Scanner scan;
	private static boolean access;
	private static Properties prop;
	private static InputStream input;
	
}
