package Assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BookManagement {
	public BookManagement() throws IOException {
		initialization();
		readInput();
	}
	
	private void initialization() {
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
	}
	
	private void readInput() throws IOException{
		System.out.println("Choose following options to start: \n");
		while (true) {
			System.out.println( "1. Insert \n2. Update \n3. Delete \n4. Retrieve \n5. Quit");
			String chooseOption = br.readLine();
			if (chooseInsert(chooseOption)) {
				
			} else if (chooseUpdate(chooseOption)) {
				
			} else if (chooseDelete(chooseOption)) {
				
			} else if (chooseRetrieve(chooseOption)) {
				
			} else if (chooseQuit(chooseOption)) {
				break;
			} else {
				System.out.println("Invalid input! Please choose an option to start: ");
			}
			
		}		
		
	}
	
	private boolean chooseInsert(String str) {
		if (str.equals("1") || str.equals("1.") || str.toUpperCase().equals("INSERT") || str.toUpperCase().equals("1. INSERT"))
			return true;
		return false;
	}
	
	private boolean chooseUpdate(String str) {
		if (str.equals("2") || str.equals("2.") || str.toUpperCase().equals("UPDATE") || str.toUpperCase().equals("2. UPDATE"))
			return true;
		return false;
	}
	
	private boolean chooseDelete(String str) {
		if (str.equals("3") || str.equals("3.") || str.toUpperCase().equals("DELETE") || str.toUpperCase().equals("3. DELETE"))
			return true;
		return false;
	}
	
	private boolean chooseRetrieve(String str) {
		if (str.equals("4") || str.equals("4.") || str.toUpperCase().equals("RETRIEVE") || str.toUpperCase().equals("4. RETRIEVE"))
			return true;
		return false;
	}
	
	private boolean chooseQuit(String str) {
		if (str.equals("5") || str.equals("5.") || str.toUpperCase().equals("QUIT") || str.toUpperCase().equals("5. QUIT"))
			return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		new BookManagement();
	}
	
	private static BufferedReader br;
}
