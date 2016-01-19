package Assignment1;

/*
 * Convert a string into character array without using toCharArray()
 */

public class Q13 {
	public static void main(String[] args) {
		String str = "I have a dream again.";
		convertToCharArray(str);
	}

	public static void convertToCharArray(String str) {
		char [] ch = new char [str.length()];
		for (int i = 0; i < str.length(); i++) {
			ch[i] = str.charAt(i);
		}
	}
	

}
