package Assignment2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class BookManagement {
	public BookManagement() throws IOException, InterruptedException {
		initialization();
		readInput();
	}
	
	private void initialization() {
		InputStreamReader isr = new InputStreamReader(System.in);
		br = new BufferedReader(isr);
		flag = true;
		map = new HashMap<String, ArrayList<String>>();
		description = new ArrayList<String>();
	}
	
	private void readInput() throws IOException, InterruptedException{
		while (flag) {
			System.out.println("\nChoose following options to start: ");
			System.out.println( "1. Insert \n2. Update \n3. Delete \n4. Retrieve \n5. Quit");
			String chooseOption = br.readLine();
			if (chooseInsert(chooseOption)) {
				insert();
			} else if (chooseUpdate(chooseOption)) {
				update();
			} else if (chooseDelete(chooseOption)) {
				delete();
			} else if (chooseRetrieve(chooseOption)) {
				retrieve();
			} else if (chooseQuit(chooseOption)) {
				System.out.println("You've logged out from book management system.");
				flag = false;
				System.exit(1);
			} else {
				System.out.println("Invalid input!");
			}
			Thread.sleep(100);
		}		
	}
	
	private void insert() throws IOException, InterruptedException {
		System.out.println("Name of the book: \n(enter \"q\" to start again)");
		ArrayList<String> authorAndEdition = new ArrayList<String>();
		bookName = br.readLine();
		if (bookName.equals("q"))
			readInput();
		else if (map.containsKey(bookName)) {
			System.out.println("You've already added this book.");
			readInput();
		} else if (bookName.equals("")) {
			System.out.println("Book name can't be omit");
			readInput();
		}
		System.out.println("Author of the book:");
		authorAndEdition.add(br.readLine());
		System.out.println("Edition of the book:");
		authorAndEdition.add(br.readLine());
		
		map.put(bookName, authorAndEdition);
	}
	
	private void update() throws IOException, InterruptedException {
		if (map.size() > 0) {
			retrieve();
			System.out.println("Which book needs update? Enter book name: \n(enter \"q\" to start again)");
			bookName = br.readLine();
			String updateBook = bookName;
			if (bookName.equals("q"))
				readInput();
			else if (!map.containsKey(bookName)) {
				System.out.println("No such book in your inventory!");
				readInput();
			}
			
			System.out.println("Name of the book:");
			bookName = br.readLine();
			if (map.containsKey(bookName)) {
				System.out.println("You've added this book. Do you wannt delete book " + updateBook +"?");
				readInput();
			}
			
			ArrayList<String>authorAndEdition = new ArrayList<String>();
			map.remove(updateBook);
			System.out.println("Author of the book:");
			authorAndEdition.add(br.readLine());
			System.out.println("Edition of the book:");
			authorAndEdition.add(br.readLine());
			
			map.put(bookName, authorAndEdition);
		
		} else 
			System.out.println("Your stock is empty!");
	}

	
	private void delete() throws IOException, InterruptedException {
		if (map.size() > 0) {
			retrieve();
			System.out.println("Delete book: ");
			bookName = br.readLine();
			if (bookName.equals("q"))
				readInput();
			else if (!map.containsKey(bookName)) {
				System.out.println("No such book in your inventory!");
				readInput();
			}
			map.remove(bookName);
			System.out.print("Book -- " + bookName + " is removed.");
		} else 
			System.out.println("Your stock is empty!");
	}
	
	private void retrieve() {
		if (map.size() > 0) {
			System.out.println("You have following books: ");
			for (String str:map.keySet()) {
				if (map.get(str).size() >= 2) {
					System.out.println("Book name: " + str + ". Author: "
						+ map.get(str).get(0) + ". Edition: " + map.get(str).get(1));
				} else if (map.get(str).size() >=1) {
					System.out.println("Book name: " + str + ". Author: "
							+ map.get(str).get(0));
				} else {
					System.out.println("Book: " + str + ".");
				}
			}
		} else 
			System.out.println("Your stock is empty!");
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
	
	public static void main(String[] args) throws IOException, InterruptedException {
		new BookManagement();
	}
	
	private static BufferedReader br;
	
	private boolean flag;
	private static HashMap<String, ArrayList<String>> map;
	private static ArrayList<String> description; // 
	private static String bookName;	
}
