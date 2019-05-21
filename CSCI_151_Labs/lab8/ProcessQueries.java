import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class ProcessQueries {

	public static void main(String[] args) throws IOException {
		// PART 1: CREATE LIST OF WEBPAGEINDICES FROM A FILE OF URLS
		File file = new File(args[0]); // read in file from command line
		Scanner scan = new Scanner(file);
		ArrayList<WebPageIndex> webList = new ArrayList<WebPageIndex>(); //instantiate empty list of indices
		while(scan.hasNextLine()) { //for every line in scanner
			String line = scan.nextLine(); //store each line in string
			WebPageIndex ind = new WebPageIndex(line); //create WebPageIndex from line
			System.out.println(ind.getUrl());
			webList.add(ind); //add index to list of indices
			System.out.println("url successfully added");
		}
		System.out.println("All urls successfully added");
		
		//PART 2: PROCESS USER QUERIES (LOOP)
		Scanner queries = new Scanner(System.in); //Initialize reading from system.in
		System.out.print("Please Enter Query (or press enter to quit): "); //prompt
		while(queries.hasNextLine()) { //LOOP for query list until end of system.in or blank line entered
			String query = queries.nextLine(); //read in queries
			if(query.length() <= 0) { //Handle blank space entered
				System.out.println("Blank line entered, exiting!");
				break;
			}
			else {
			String[] queryHold = query.split("\\s+"); //split into array
			ArrayList<String> queryList = new ArrayList<String>();
			for(int l = 0; l < queryHold.length; l++) { //dump array to list
				queryList.add(queryHold[l]);
			}
			URLComparator comparator = new URLComparator(queryList);
			MyPriorityQueue<WebPageIndex> webHeap = new MyPriorityQueue<WebPageIndex>(comparator);
			for(WebPageIndex dex : webList) {
				webHeap.add(dex);
			}
			while(!webHeap.isEmpty()) { //for every page in the heap
				WebPageIndex index = webHeap.remove(); //remove top scoring page
				int pageScore = comparator.score(index); //calculate score
				if(pageScore > 0) {
				System.out.println("(Score: " + pageScore + ") " + index.getUrl());
				}
			}
			System.out.print("Please Enter Query (or press enter to quit): "); //prompt
			continue; //next query
			}
		}

	}

}
