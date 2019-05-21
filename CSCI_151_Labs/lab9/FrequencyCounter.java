import java.util.*;

public class FrequencyCounter {

	public static void main(String[] args) {
		int order = Integer.parseInt(args[0]); //order of markov model is first arg
		Scanner inputScan = new Scanner(System.in);
		MyHashMap<String,ArrayList<String>> hashTable = new MyHashMap<String,ArrayList<String>>();
		System.out.print("Enter a line: ");
		while(inputScan.hasNextLine()) {
			String line = inputScan.nextLine();
			if(line.length() <= 0) { //Handle blank space entered
				System.out.println("Blank line entered, exiting!");
				break;
			}
			for(int index = 0; index < line.length()-order; index++) {
				String subString = line.substring(index, index+order);
				if(hashTable.get(subString) != null) {
					hashTable.get(subString).add(line.substring(index+order, index+(order+1)));
				}
				else {
					ArrayList<String> list = new ArrayList<String>();
					list.add(line.substring(index+order, index+(order+1)));
					hashTable.put(subString, list);
				}
			}
			System.out.println(hashTable.size() + "  " + "Distinct Keys"); //get number of distinct keys
			Iterator<Map.Entry<String, ArrayList<String>>> it = hashTable.entries();
			while(it.hasNext()) {
				Map.Entry<String, ArrayList<String>> temp = it.next();
				System.out.println(temp.getValue().size() + " " + temp.getKey());
			}
		System.out.println(hashTable.toString());
		System.out.print("Enter a line: ");
		continue; //next input
		}
		
		
	}
}
