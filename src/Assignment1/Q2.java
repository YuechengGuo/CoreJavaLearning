package Assignment1;

/* description:
 * Print the count of numbers divisible by 7 from 1 to 100 
 */

public class Q2 {
	public static final int divider = 7; //

	public static void main(String[] args) {
		int counter = 0;
		for (int i = 1; i<=100; i++) {
			if ((i % divider) == 0) {
				counter++;
			}
		}
		System.out.println("We have " + counter + " numbers that is divisible by 7.");
		
	}
}
