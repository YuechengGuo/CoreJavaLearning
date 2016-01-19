package Assignment1;

/*
 *  Find out the vowels in a string, display their positions and the respective vowel
 */

public class Q12 {
	public static void main(String[] args) {
		String str = "I really have a dream";
		findVowels(str);
	}

	public static void findVowels(String str) {
		char [] ch = new char[str.length()];
		ch = str.toCharArray();
		locateVowels(ch);
	}

	private static void locateVowels(char[] ch) {
		for (int i = 0; i < ch.length; i++) {
			if (isVowels(ch[i])) 
				System.out.println("Found a vowel: " + ch[i] + " - " + i );
			}
		}
	private static boolean isVowels(char ch) {
		if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u'
				|| ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' )
			return true;
		return false;
	}
}
