package Assignment1;

import java.util.Scanner;
/* 
 * Count the number of upper case and lower case letters in a given string
 */

public class Q6 {
	public static void main(String[] args) {
		String str = inputString();
		countLetters(str);
		printResults(str);
	}

	private static String inputString() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter a string here:");
		String str = scan.next();
		scan.close();
		return str;
	}

	private static void countLetters(String str) {
		nLowerCase = 0;
		nUpperCase = 0;
		for (int i = 0; i<str.length(); i++) {
			if (str.charAt(i) >= (int) 'a' && str.charAt(i) <= (int) 'z') {
				nLowerCase++;
			} else if (str.charAt(i) >= (int) 'A' && str.charAt(i) <= (int) 'Z') {
				nUpperCase++;
			}
		}
	}

	private static void printResults(String str) {
		System.out.println(str + " containts " + nLowerCase + " lower case letters and "
		 + nUpperCase + " upper case letters.");
	}

	/* declare variables */
	private static int nLowerCase;
	private static int nUpperCase;
}
