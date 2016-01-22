package Assignment1;

/*
 * create a Java pgm to handler two exceptions using Nested try blocks
 */

import java.io.*;
import java.util.*;

public class Q15 {
	public static void main(String[] args) throws Exception{
		String str = "I";
		Q15 instance = new Q15();
		instance.nestedTryBlock(str);
	}
	
	private /*static*/ void nestedTryBlock(String str) throws Exception {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new FileReader("example.txt"));
			try {
				while (rd.readLine() != null) {
					System.out.println(rd.readLine());
//					System.out.println(rd.readLine().charAt(10));// error test						
				}
			} catch (IndexOutOfBoundsException ex) {
				System.err.println("Caught IndexOutOfBoundsException: " + ex.getMessage());
			}
			rd.close();
		} catch (FileNotFoundException ex) {
//			ex.printStackTrace();
			System.err.println("Caught IOException: " + ex.getMessage());
			throw new Exception(ex);
		}
	}
}
