package Assignment1;

/*
 * convert a string to upper case without using toUpperCase()
 */

import java.util.Scanner;

public class Q7 {
	public static void main(String[] args) {
		String str = inputString();
		upperCaseStr = "";
		convertToUpperCase(str);
		System.out.println("Convert " + str + " to its upper case: " + upperCaseStr);
	}

	private static String inputString() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a string here:");
		String str = scan.next();
		scan.close();
		return str;
	}

	private static void convertToUpperCase(String str) {
		for (int i = 0; i<str.length(); i++) {
			char ch = str.charAt(i);
			if (ch >= 'a' && ch <= 'z') {
				ch = (char) ((int) ch % (int) 'a' + (int)'A');
			} 
			upperCaseStr += ch;
		}
	}

	private static String upperCaseStr;
}
