package Assignment1;

/* 
 * Sort the command line arguments (asc/desc) (ignoring dash "-" )
 * priority: length --> ASCII (ignoring dash)
 */
import java.util.ArrayList;

public class Q9 {
	public static void main(String[] args) {
		String str = "git fb -c -db a ddfsf 00 312";// inputCmd(); require including the name/title of the commands at beginning
		System.out.println("Entered commands: " + str);
		SortCmdLnArgs(str, true);
	}
	
	private static void SortCmdLnArgs(String str, boolean flag) {
		separateCmd(str);
	    sortCmd();
		printResult(str, flag);
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
			newStrList.add(strList.get(index)); // add the least cmd from left to right
			trueValue[index] = -1; // erase the value 
		}
		
	}
	
	private static void printResult(String str, boolean flag) {
		int index = 0; // index of the first "space"
		for (int i = 0; i<str.length(); i++) {
			if (str.charAt(i) == ' ') break;
			index++;
		}
		String cmdName = "";
		cmdName += str.substring(0, index);
		String cmd = "";
		for (String strs:newStrList) {
			if (flag) {
				cmd += " " + strs;
			} else {
				cmd = " " + strs + cmd;
			}
		}
		if (flag) {
			System.out.println("Ascend sorting:");
		} else {
			System.out.println("Descend sorting:");
		}
		System.out.println(cmdName + cmd);
	}
	private static ArrayList<String> strList = new ArrayList<String>();
	private static ArrayList<String> newStrList = new ArrayList<String>();
	private static int count; // count the number of commands from input
}
