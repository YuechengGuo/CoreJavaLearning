package Assignment1;
/*
 * implement split()
 */
import java.util.ArrayList;

public class Q10 {
	public static void main(String[] args) {
		String str = " asd  -- fasf f    dsf    ";
		System.out.println("Input string: " + str);
		strSplit(str);
		//display test begin
		System.out.println("Split String: ");
		for (int i = 0; i < strList.length; i++) { 
			System.out.println(strList[i]);
		}
		//display test end
	}

	private static void strSplit(String str) {
		char [] ch = str.toCharArray();	
		setFlag(ch);
		// countSubstrings();
		assignStr(str);
	}
	
	// set flag at each character of the given string, if there's a space, flag = 1, o.w. flag = 0.
	private static void setFlag(char[] ch) {
		int[] spaceFlag = new int [ch.length];
		for (int i = 0; i<ch.length; i++) {
			if (ch[i] == ' ') {
				spaceFlag[i] = 1;
			} else
				spaceFlag[i] = 0;
		}
		countSubstrings(spaceFlag);
	}
	
	// count the number of substrings and record their start&end point
	private static void countSubstrings(int[] spaceFlag) {
		if (spaceFlag[0] == 0) {
			startPoint.add(0);
			count = 1;
			}
		else
			count = 0;
		for (int i = 0; i<spaceFlag.length - 1; i++) {
			if (spaceFlag[i] - spaceFlag[i+1] > 0) {
				startPoint.add(i+1);
				count++;
			} else if (spaceFlag[i] - spaceFlag[i+1] < 0) {
				endPoint.add(i+1);
			}
		}
	}
	
	// assign each substring into a string array
	private static void assignStr(String str) {
		strList = new String[count];
		for (int i = 0; i<strList.length; i++){
			if (i < strList.length - 1)
				strList[i] = str.substring(startPoint.get(i), endPoint.get(i));
			else
				strList[i] = str.substring(startPoint.get(i));				
		}
	}

	private static int count;
	private static String[] strList;
	private static ArrayList<Integer> startPoint = new ArrayList<Integer>();
	private static ArrayList<Integer> endPoint = new ArrayList<Integer>();
}
