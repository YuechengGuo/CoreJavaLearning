package Assignment1;

/* 
 * Sort the command line arguments (asc/desc) (ignoring dash "-" )
 */
import java.util.ArrayList;

public class Q9 {
	public static void main(String[] args) {
		String str = "git fb -c -db a ddfsf";// inputCmd();
		separateCmd(str);
		newStrList1 = new String [count];
	    sortCmd();
		printResult();
	    // System.out.println(newStrList);
	}

	private static void separateCmd(String str) {
		char [] ch = str.toCharArray();		
		ArrayList<Integer> cmdtrueValue = new ArrayList<Integer>(); 
		count = 0;
		for (int i = 0; i<ch.length; i++) {
			if (ch[i] == ' ') {
				cmdtrueValue.add(count, i);
				count++;
			}
		}
		for (int i = 0; i<count; i++) {
			if (i != count - 1)
				strList.add(str.substring(cmdtrueValue.get(i), cmdtrueValue.get(i+1)));
			else
				strList.add(str.substring(cmdtrueValue.get(i)));
		}
		System.out.println(strList);
		System.out.println(count);
	}
	
	private static void sortCmd() {
		int[] trueValue = new int [count];
		for (int i = 0; i<count; i++) {
			int nTrue = 0;
			String str1 = strList.get(i);
			for (int j = count - 1; j>=0; j--) {
				String str2 = strList.get(j);
				if (compareElement(str1, str2)) {
					nTrue++;
				}
			}
			trueValue[i] = nTrue;
		}
		addToNewStr(trueValue);
	}
	
	// return true if str1 < str2
	private static boolean compareElement(String str1, String str2) {
		// Arrays.sort(a);
		if (str1.length() < str2.length()) {
			return true;
		} else if (str1.length() > str2.length()) {
			return false;
		} else {
			for (int i = 0; i < str1.length(); i++) {
				if ((int) str1.charAt(i) < (int) str2.charAt(i)) {
					return true;
				} else if ((int) str1.charAt(i) > (int) str2.charAt(i)) {
					return false;
				}
			}
		}
		return false;
	}
	
	private static void addToNewStr(int[] trueValue) {
		for (int i = 0; i<trueValue.length; i++) {
			int maxNum = 0;
			int index = 0;
			for (int j = 0; j<trueValue.length; j++) {
				if (maxNum <= trueValue[j]) {
					maxNum = trueValue[j];
					index = j;	
				}				
			}
			System.out.println(index);
			newStrList.add(strList.get(index)); // add the least cmd from left to right
			trueValue[index] = -1; // erase the value 
		}
		
	}
	
	private static void printResult() {
//		for (int i = 0; i<newStrList1.length; i++) {
//			System.out.println(newStrList1[i]);
//		}
		System.out.println(newStrList);
	}
	private static ArrayList<String> strList = new ArrayList<String>();
	private static ArrayList<String> newStrList = new ArrayList<String>();
	private static String[] newStrList1;
	private static int count; // count the number of commands from input
}
