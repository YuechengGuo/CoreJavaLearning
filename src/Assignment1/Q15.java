package Assignment1;

/*
 * create a Java pgm to handler two exceptions using Nested try blocks
 */

import java.io.*;

public class Q15 {
	public static void main(String args[]) {
		String str = "I";
		nestedTryBlock(str);
	}
	
	private static void nestedTryBlock(String str) {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("example.txt"));
			try {
				while (rd.readLine() != null) {
					System.out.println(rd.readLine().charAt(10));						
				}
			} catch (IOException ex) {
				System.err.println("Caught IOException: " + ex.getMessage());
			}
		} catch (IOException ex) {					
			System.err.println("Caught IOException: " + ex.getMessage());
		}
	}
}
