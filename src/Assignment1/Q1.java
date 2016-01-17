package Assignment1;

/* description:
 * Print all ASCII codes from 0 to 255
 */

public class Q1 {
	public static void main(String[] args) {
		for (int i = 0; i<256; i++) {
			System.out.println("The ASCII code for " + i + " is " + (char) i );
		}
	}
}
