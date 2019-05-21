import java.util.Scanner;

/* This program redacts words specified as CL arguments from System.in and prints
 * the redacted document to System.out
 * 
 * Author: Max Kramer
 */

public class Redactor {

    public static void main(String[] args) {

    	Scanner input = new Scanner(System.in); //I'm reading what the user typed
    	boolean bool = false; //Boolean value for matching test
    	
    	if (args.length == 0) { //Case to handle if nothing is to be redacted
    		bool = true;
    	}
    	
    	while (input.hasNextLine()) { //keep looping for each guess, use 'break' to exit
        	String line = input.nextLine(); //Read the next line of input from the user
            Scanner s2 = new Scanner(line); //s2 will let me break 'line' apart  
            
            while (s2.hasNext()) {
            	String word = s2.next(); //stores each word in var word
            	
            	//COMPARE WORD TO EACH STRING IN ARGS
            	for (int i = 0; i < args.length; i++) { //for every entry in args
            		if (!word.equals(args[i])) { //Assign value to bool based on matching to args[i]
            			bool = true;
            		}
            		
            		else {
            			bool = false;
            			break; //to prevent returning to unredaction after matching
            		}
            	}
            		
            	if (bool == true) { //Uses bool to determine whether to redact or not
            		System.out.print(word + " ");
            	}
            	
            	else {
            		System.out.print("XXXXXXX ");
            	}
            			
            	
            	}
            System.out.println(); //Keeps lines consistent from .txt file
            }
            
    
    
    

    }
}

