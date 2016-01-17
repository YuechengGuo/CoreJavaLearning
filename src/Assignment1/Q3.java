package Assignment1;
/*
 *	create a multiplication table
 */
	
public class Q3 {
	private static final int BASEMULTIPLIER = 5;

	private static final int MULTIPLYTIMES = 10;

	public static void main(String[] args) {
		for (int i=1; i<=MULTIPLYTIMES; i++) {
			calculationAndPrint(i);
		}
	}

	private static void calculationAndPrint(int i) {
		int result = 0;
		result = BASEMULTIPLIER * i;
		System.out.println(" " + BASEMULTIPLIER + 
			" x " + i + " = " + result);	
	}
}
