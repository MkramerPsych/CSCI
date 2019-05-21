package lab10;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Bacon {
	public static void main(String[] args) throws MalformedURLException, IOException {
		//READ IN DATABASE
		String url = args[0];
		Scanner urlReader = new Scanner(new URL(url).openStream());
		
		//DEFINE INITIAL CENTER
		final String DEFAULTCENTER = "Kevin Bacon (I)"; //assign the default
		String center;
		if (args.length == 1) { //if there is no supplied center
			center = DEFAULTCENTER;
			System.out.println("Center is: " + center);
		}
		else {
			center = args[1];
			System.out.println("Center is: " + center);
		}
		
		//define structures for the graph
		Map<String,ArrayList<String>> graph = new HashMap<String,ArrayList<String>>();
		
		//POPULATE THE GRAPH
		while(urlReader.hasNextLine()) { //for every line in urlReader
			String line = urlReader.nextLine();
			String[] names = line.split("\\|"); //split entry about "|"
			String actorName = names[0];
			String movieName = names[1];
			//System.out.println(actorName + " " + movieName);
			if(graph.containsKey(actorName)) { //test actors
				ArrayList<String> temp = graph.get(actorName);
				temp.add(movieName);
				graph.put(actorName, temp);
			}
			else { //add new actor
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(movieName);
				graph.put(actorName, temp);
			}
			if(graph.containsKey(movieName)) { //test movies
				ArrayList<String> temp = graph.get(movieName);
				temp.add(actorName);
				graph.put(movieName, temp);
			}
			else { //add new movie
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(actorName);
				graph.put(movieName, temp);
			}
		}
		System.out.println("Hash table size: " + graph.size());
		System.out.println("Finished building graph");
		
		//MAIN QUERY LOOP
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a command (or enter 'help' for list of commands): ");
		while(input.hasNextLine()) {
			String line = input.nextLine();
			String[] query = line.split("\\s+"); //split into array at spaces
		
			if(query[0].equals("quit")) { //QUIT
				System.out.println("Quitting");
				break;
			}
			
			else if(query[0].equals("help")) { //HELP
				System.out.println("");
				System.out.println("Welcome to the help guide!");
				System.out.println("You have multiple commands you can input");
				System.out.println("");
				System.out.println("'find <name>': shortest path from center to <name>");
				System.out.println("'quit': exits from program");
				System.out.println("'recenter <name>': recenter at <name>");
				System.out.println("'avgdist': average bacon distance from center");
				System.out.println("'circles': calculate circles of actors at each bacon distance");
				System.out.println("'topcenter <n>': calculate avg bacon distance to all actors connected directly to center, print n best centers");
				System.out.println("");
			}
			
			else if(query[0].equals("find")) { //FIND<NAME>
				//MUST FIX WHITESPACE PROBLEM: TWO NAMES
				MyQueue<String> q = new MyQueue<String>();
				
				if(graph.containsKey(query[1])) {
					String vertex = center;
					boolean done = false;
					int distance = 0;
					Map<String,String> predecessor = new HashMap<String,String>();
					predecessor.put(center, "root");
					
					//perform breadth first search
					q.enqueue(vertex);
					while(!q.isEmpty() || done == false) {
						vertex = q.dequeue();
						ArrayList<String> adjVertices = graph.get(vertex);
						for(String i : adjVertices) {
							if(!predecessor.containsKey(i)){
								if(i.equals(query[1])) {
									predecessor.put(i, vertex);
									done = true;
								}
								else {
									predecessor.put(i, vertex);
									q.enqueue(i);
								}
							}
						}
					}
					System.out.println(predecessor.get(query[1]));
					String nextValue = predecessor.get(query[1]);
					while(!nextValue.equals("root")) {
						System.out.println(predecessor.get(nextValue));
						nextValue = predecessor.get(nextValue);
					}
				}
				else {
					System.out.println(query[1] + " Does not appear in the database");
				}
			}
			
			else if(query[0].equals("recenter")) { //RECENTER
				
			}
			
			else if(query[0].equals("avgdist")) { //AVGDIST
			
			}
			
			else if(query[0].equals("circles")) { //CIRCLES
				
			}
			
			else if(query[0].equals("topcenter")) { //TOPCENTER
				
			}
			
			System.out.print("Enter a command (or enter 'help' for list of commands): ");
			continue;
		}


		
		
		
		
	}

}
