package Assignment1;

/* 
 * Find out the sum of all the numbers of a given integer array
 */

import java.util.Scanner;


public class Q8 {
	public static void main(String[] args) {
		inputNum();
		int sum = sumNum();
		System.out.println("The sum of the input integer array is: " + sum);
	}

	private static void inputNum() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the length of the array:");
		int length = scan.nextInt();
		arr = new int [length];
		scan.nextLine();
		readIntFromUser();
		scan.close();
	}

	private static void readIntFromUser() {
		Scanner scanNum = new Scanner(System.in); 
		for (int i = 0; i<arr.length; i++) {
			System.out.println("Enter an integer to array[" + i + "]");
			arr[i] = scanNum.nextInt();
		}
		scanNum.close();
	}
	
private static int sumNum() {
	int sum = 0;
	for (int i = 0; i<arr.length; i++) {
		sum += arr[i];		
	}
	return sum;
}

	private static int [] arr;
}
