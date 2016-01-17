package Assignment1;

import java.util.Scanner;

/*
 *	Print a factorial of a number
 */

public class Q4 {	
	public static void main(String[] args) {
		int inputNum = readInput();
		int factorialResult = factorialOfInput(inputNum);
		outputResults(inputNum, factorialResult);
	}

	private static int readInput() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter an integer to calculate it's factorial:");
		int num = scan.nextInt();
		scan.close();
		return num;
	}

	private static int factorialOfInput(int num) {
		int output = 1;
		if (num > 0) {
			output = num * factorialOfInput(num - 1); 
		}
		return output;
	}

	private static void outputResults(int inputNum, int outputResults) {
		if (inputNum == 0) {
			System.out.println("Factorial of 0 is 1.");
		} else if (inputNum > 0) {
			printFactorialCalculation(inputNum, outputResults);
		} else {
			System.out.println("Please enter a non-negative number!");
		}
	}

	private static void printFactorialCalculation(int inputNum, int outputResults) {
		String factorialCalculation = "Factorial of " + inputNum + " is: " + inputNum;
		for (int i = inputNum - 1; i > 0; i--) {
			factorialCalculation += (" x " + i);
		}
		factorialCalculation += " = " + outputResults;
		System.out.println(factorialCalculation);
	}
}
