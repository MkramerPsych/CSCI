import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class Bacon {
	
	public static int bfs(String line, String center, String startActor, Map<String,ArrayList<String>> graph){
		int n = 0; //n for bacon distance
		System.out.println("Starting From: " + startActor + " To: " + center);
		MyQueue<String> q = new MyQueue<String>(); //queue for BFS
		if(graph.containsKey(startActor)) { //if contained, construct breadth first search
			String vertex = center; //get center
			boolean done = false; //test to end bfs
			Map<String,String> predecessor = new HashMap<String,String>(); //predecessor map for output
			predecessor.put(center, "CENTER IS REACHED");
			//BREADTH FIRST SEARCH
			q.enqueue(vertex);
			try { //TRY FOR SEARCH, CATCH NULL IF CURRENT CENTER IS NOT CONTAINED IN DATABASE
				if(vertex == center) { //if Center is input, bypass bfs
					done = true;
				}
				while(!q.isEmpty() || done == false) {
					vertex = q.dequeue();
					ArrayList<String> adjVertices = graph.get(vertex);
					for(String i : adjVertices) {
						if(!predecessor.containsKey(i)){
							if(i.equals(startActor)) {
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
				
				
				//return path to center 
				System.out.print("INIT: " + startActor + " -> ");
				System.out.print(predecessor.get(startActor) + " -> ");
				String nextValue = predecessor.get(startActor);
				while(!nextValue.equals("CENTER IS REACHED")) {
					System.out.print(predecessor.get(nextValue) + " -> ");
					nextValue = predecessor.get(nextValue);
					n++;
				}
				System.out.print("(" + n/2 + ")");
				System.out.println("");
				return n/2;
			}
			catch (NullPointerException e) { //If there is no path from startActor to Center
				System.out.println(startActor + " is unreachable");
				return -1;
			}
		}
		else { //if name is not in database
			System.out.println(startActor + " Does not appear in the database");
			return -1;
		}
	}

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
		
		//DEFINE GRAPH STRUCTURE
		Map<String,ArrayList<String>> graph = new HashMap<String,ArrayList<String>>();
		
		//POPULATE THE GRAPH
		ArrayList<String> actorOnly = new ArrayList<String>();
		ArrayList<String> movieOnly = new ArrayList<String>();

		
		while(urlReader.hasNextLine()) { //for every line in urlReader
			String line = urlReader.nextLine();
			String[] names = line.split("\\|"); //split entry about "|"
			String actorName = names[0];
			String movieName = names[1];
			if(graph.containsKey(actorName)) { //test actors
				ArrayList<String> temp = graph.get(actorName);
				temp.add(movieName);
				graph.put(actorName, temp);
			}
			else { //add new actor
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(movieName);
				graph.put(actorName, temp);
				actorOnly.add(actorName);
				
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
				movieOnly.add(movieName);
			
			}
		}
		//System.out.println(actorOnly.toString());
		//System.out.println(movieOnly.toString());
		System.out.println("Hash table size: " + graph.size());
		System.out.println("Finished building graph");
		
		//MAIN QUERY LOOP
		Scanner input = new Scanner(System.in);
		System.out.print("Enter a command (or enter 'help' for list of commands): ");
		while(input.hasNextLine()) {
			String line = input.nextLine();
			//String[] query = line.split("\\s+"); //split into array at spaces
		
		//"METHODS"
			if(line.substring(0,4).equals("quit")) { //QUIT
				System.out.println("Quitting");
				break;
			}
			
			else if(line.substring(0,4).equals("help")) { //HELP
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
			
			else if(line.substring(0,4).equals("find")) { //FIND <NAME>
				int bfs = bfs(line,center, line.substring(5), graph);
				System.out.println("Bacon number is: " + bfs);
			}
				
			else if(line.substring(0,7).equals("avgdist")) { //AVGDIST
				int avgBaconDist = 0;
				int reachCounter = 0;
				int unreachCounter = 0;
				System.out.println("num of actors: "  + actorOnly.size());
				for(String actor : actorOnly) {
					int baconDist = bfs(line,center, actor, graph);
					if(baconDist == -1) {
						unreachCounter++;
					}
					else {
						reachCounter++;
					}
					avgBaconDist = avgBaconDist + baconDist;
				}
				double Final =(double) (avgBaconDist) / actorOnly.size()+1; //final avg bacon distance to center
				System.out.println(Final + "\t" + center + " " + "(" + reachCounter + "," + unreachCounter + ")");
			}
			
			else if(line.substring(0,7).equals("circles")) { //CIRCLES
				int baconDist = 0;
				int[] circles = new int[10];
				String[] actorAtCircle = new String[10];
				for(String actor : actorOnly) {
					baconDist = bfs(line,center,actor,graph);
					if(baconDist != -1) { 
						circles[baconDist]++;
						actorAtCircle[baconDist] = actor;
					}
				}
				int i = 0;
				while(circles[i] != 0) {
					System.out.println(i + "\t" + circles[i] + " " + "[" + actorAtCircle[i] + "]");
					i++;
				}
			}
			
			else if(line.substring(0,8).equals("recenter")) { //RECENTER
				String newCenter = line.substring(9);
				if(graph.containsKey(newCenter)) {
					center = newCenter;
					System.out.println("New center is: " + center);
				}
				else {
					System.out.println("Center is not in database, cannot recenter!");
				}
			}
		
			else if(line.substring(0,9).equals("topcenter")) { //TOPCENTER
				
			}
			
			System.out.print("Enter a command (or enter 'help' for list of commands): ");
			continue;
		}
	}

}
