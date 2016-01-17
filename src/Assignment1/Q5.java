package Assignment1;

/*
 *  print all the alphabets
 */

public class Q5 {
	private static final int NLETTER = 26;
	
	public static void main (String[] args) {
		printAlphabets();
	}
	
	private static void printAlphabets() {
		int int_a = (int) 'a';
		int int_A = (int) 'A';
		String lowerCase = "";
		String upperCase = "";
		for (int i=0; i<NLETTER; i++) {
			lowerCase += (char) (int_a + i);
			upperCase += (char) (int_A + i);
		}
		System.out.println(lowerCase + "\n" + upperCase);
	}
}
