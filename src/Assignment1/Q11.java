package Assignment1;

/*
 *  Search for all appearances of string in a paragraph and display the count
 */

public class Q11 {
	public static void main(String[] args) {
		String str1 = "I have a dream.";
		String str2 = "a";
		if (str2 == "")
			System.out.println("Please assign a string to str2 and run the pgm again.");
		else
			searchString(str1, str2);
	}

	public static void searchString(String para, String str) {
		int count = 0;
		for (int i = 0; i <= para.length() - str.length(); i++) {
			if ( str.equals(para.substring(i, i + str.length())) ) {
				count++;
			}

		}
		if (count == 0) 
			System.out.println("There's no \"" + str + "\" in the paragraph: \"" + para + "\"");
		else if (count == 1) 
			System.out.println("We found only one \"" + str + "\" in the paragraph: \"" + para + "\"");
		else
			System.out.println("We found " + count + " \"" + str + "\" in the paragraph: \"" + para + "\"");
	}
}
