import java.util.Random;
import java.util.Scanner;

/*This program prompts you to guess at a randomly selected number until you guess correctly and prints
 * the number of attempts it took you to get the correct number.
 * 
 *  Author: Max Kramer
 */

public class HiLo {
    public static void main(String[] args) {

    Random rand = new Random(); 
    int target = rand.nextInt(1000) + 1; //Sets computer target for you to guess
    int userGuess = -1; 
    int guessNum = 0; //initialize number of guesses

    System.out.println("Let's play a game!\r\n" + "I'm thinking of a number between 1 and 1000\r\n"
        + "Try to guess what it is!");
    
    System.out.print("Enter a guess: ");
    Scanner input = new Scanner(System.in); // I'm reading what the user typed

    while (input.hasNextLine()) { // keep looping for each guess, use 'break' to exit
    	String line = input.nextLine(); // Read the next line of input from the user
        Scanner s2 = new Scanner(line); // s2 will let me break 'line' apart

        if (s2.hasNextInt()) { // check to see if s2 would next see an integer number

        	userGuess = s2.nextInt(); // read in that number

        	if (userGuess > target) {
        		System.out.println("Sorry, too high! Guess again!");
            	guessNum++; //increment guess number
        	}

        	else if (userGuess < target) {
        		System.out.println("sorry, too low! Guess again!");
            	guessNum++; //increment guess number
        	}

        	else if (userGuess == target) {
        		System.out.println("Correct! You guessed it!");
        		System.out.println("It took you " + guessNum + " guesses.");
        		break; //Terminate program after correct guess
        	}

        }

        else {
        	System.out.println("Please enter a number, nothing else!"); // Error msg: failure to enter a number
        	continue; // jump back to the top of the while loop
        }
        System.out.print("Enter a guess: "); //Print prompt for next guess
    
    }

    }

}
