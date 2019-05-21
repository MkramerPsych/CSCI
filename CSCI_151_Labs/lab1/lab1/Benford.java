//I have adhered to the Honor Code in this assignment.

import java.util.Scanner;

/* This program analyses a data file and tests the assumptions of benford's law.
 * It then creates a data table and histogram to display the data graphically.
 * 
 * I noticed that the population data from the census files appear to follow
 * the law, while the values of e, pi, perfect squares, and prime numbers do not appear
 * to follow it. 
 * 
 * 
 * Author: Max Kramer
 */

public class Benford {
	public static final int MAXWIDTH = 50; //Sets max width for the histogram
	
	public static void main(String args[]) {
		System.out.println("Welcome to the Benford analysis program!");
		Scanner input = new Scanner(System.in); // I'm reading what the user typed
		int[] counterArray = new int[10]; //creates an array of counters
		
		
		while(input.hasNext()) { //while there is another word in input.
			String testWord = input.next(); //Read the next character from input
			char testChar = testWord.charAt(0); //test first character of System.in
		
			if (Character.isDigit(testChar) == true) { //Test if first character is a digit
				String digitRet = testWord.substring(0, 1); //extract first digit as string.
				int finalDigit = Integer.parseInt(digitRet); //parse string to int.
				counterArray[finalDigit]++; //increment the appropriate counter.
			}
		
			else {
				continue; //begin while loop over if word doesnt start with a digit.
			}
		}
		
		int total = 0;
		for (int i = 0; i < 10; i++) {
			total += counterArray[i];
		}
		
		//Stars
		int maxVal = 0;
		for (int i = 0; i < 10; i++) {
			if (counterArray[i] > maxVal) {
				maxVal = counterArray[i];
			}
		}
		
		int currentVal = 0;
		int numberOfStars = 0;
		
		//print table
		for (int i = 0; i < 10; i++) { // perform 10 times for digits 0-9
			float percent = (float) counterArray[i] / total * 100;
			System.out.printf("%d %8d %4.1f%% : ", i, counterArray[i], percent);
			currentVal = counterArray[i];
			float floatStars = (float) currentVal / maxVal * MAXWIDTH; //setup floating point division.
			numberOfStars = Math.round(floatStars);
			
			for (int j = 0; j < numberOfStars; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		
		
	}
}
