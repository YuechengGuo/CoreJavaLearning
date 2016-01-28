package Assignment2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;


class InsufficientBalanceException extends Exception {
	InsufficientBalanceException() {
		System.out.println("Insufficient fund!");
	}
}

public class CheckBalance {
	
	/** file path */ 
	private static final String DEFAULTPATH = "/Users/Yuecheng/Desktop/";
	
	private static final String FILENAME = "login.properties";
	
	public CheckBalance() throws FileNotFoundException, IOException, InterruptedException {
		initialization();
		createPropertiesFile();
		getPropertiesFile();
		setupConsoleLogin();
	}
	
	private void initialization() throws FileNotFoundException {
		balance = 2000;
		prop = new Properties();
		input = new FileInputStream(DEFAULTPATH + FILENAME);
		scan = new Scanner(System.in);
		access = false;
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}
	
	private void createPropertiesFile() throws FileNotFoundException, IOException {
		Properties props = new Properties();
		FileOutputStream fileOutput = new FileOutputStream(DEFAULTPATH + FILENAME);
//		UserInfo user1 = new UserInfo("Yuecheng", "passwordYG", 2000);
//		UserInfo user2 = new UserInfo("Jie", "passwordJL", 2000);
//		props.setProperty(user1.getUserName(), user1.getPassword());
//		props.setProperty(user2.getUserName(), user2.getPassword());
		props.setProperty("321", "123");
		props.setProperty("Jie", "passwordJL");
		// writing properties into properties file from java:
		props.store(fileOutput, "Properties file created from Java");
	}
	
	private void getPropertiesFile() throws IOException {
		if (input != null) {
			prop.load(input);
		} else
			System.out.println("File not found");
	}
	
	private void setupConsoleLogin() throws InterruptedException, IOException {
		while (!access) {
			System.out.println("Enter your username: ");
			String inputUserName = br.readLine();  //scan.nextLine();
			if (prop.containsKey(inputUserName)) {
				System.out.println("Password: ");
				String inputPassword = br.readLine();//scan.nextLine();
				if (inputPassword.equals(prop.getProperty(inputUserName))) {
					successLogin();
					access = true;
				} else {
					System.out.println("Invalid password! \n");
				}
			} else {
				System.out.println("User Name doesn't exist!");
			}
		}
	}
	
	private void successLogin() throws InterruptedException, IOException {
		while (true) {
			System.out.println("\n1. View Balance \n2. Fund Transfer \n3. Quit");
			
			
			String chooseOption = br.readLine();
			if (chooseBalance(chooseOption)) {
				System.out.println("Balance: " + balance);
			} else if (chooseFundTransfer(chooseOption)) {
				transferFund();
			} else if (chooseQuit(chooseOption)){
				System.out.println("You're logged out!");
				break;
			} else {
				System.out.println("Unrecognized selection! Please choose following options: ");
			}
		}
	}
	
	private boolean chooseBalance(String str) {
		if (str.equals("1") || str.equals("1.") || str.toUpperCase().equals("VIEW BALANCE") || str.toUpperCase().equals("1. VIEW BALANCE"))
			return true;
		return false;
	}
	
	private boolean chooseFundTransfer(String str) {
		if (str.equals("2") || str.equals("2.") || str.toUpperCase().equals("FUND TRANSFER") || str.toUpperCase().equals("2. FUND TRANSFER"))
			return true;
		return false;
	}
	
	private boolean chooseQuit(String str) {
		if (str.equals("3") || str.equals("3.") || str.toUpperCase().equals("QUIT") || str.toUpperCase().equals("3. QUIT"))
			return true;
		return false;
	}
	
	private void transferFund() throws InterruptedException {
		System.out.println("Fund Transfer: ");
		double transFund = scan.nextDouble();
		if (transFund >= 0 && transFund <= balance) {
			balance -= transFund;
			System.out.printf("Transfer completed! You balance is: %.2f \n", balance);
		} else if (transFund > balance) {
			try {
				InsufficientBalanceException fundExp = new InsufficientBalanceException();
				throw fundExp;
			} catch (InsufficientBalanceException e) {
				e.printStackTrace();
			}
//			System.out.println("Insufficient balance left. \n");
		} 			
		else {
			System.out.println("Please enter a positive value \n");
		}
		Thread.sleep(500);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
		new CheckBalance();
		
	}
	

	private static double balance;
	/* use prop to save the data from properties file */
	private static BufferedReader br;
	private static Scanner scan;
	private static boolean access;
	private static Properties prop;
	private static InputStream input;
}
