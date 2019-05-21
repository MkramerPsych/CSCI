import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class WebPageIndex {
	//Instance Variables
	
	String url = "";
	RBTreeMap<String,List<Integer>> lookup = new RBTreeMap<String,List<Integer>>();
	int wordTotal = 0;
	
	//Constructor
	public WebPageIndex(String url) throws IOException {
		this.url = url;
		HTMLScanner scan = new HTMLScanner(url); //scan through webpage
		int index = 0;
		while(scan.hasNext()) { //while there is additional input
			String next = scan.next().toLowerCase();
			if(lookup.get(next) == null) { //if word is not contained in RBTreeMap already
				LinkedList<Integer> addOn = new LinkedList<Integer>();
				addOn.add(index);
				lookup.put(next, addOn);
			}
			else { //word is already contained
				lookup.get(next).add(index);
			}
			wordTotal++;
			index++;
		}
	}
	
	//Methods
	public String getUrl() {
		return url;
	}
	
	public int getWordCount() {
		return wordTotal;
	}
	
	public boolean contains(String s) {
		if(lookup.get(s) != null) {
			return true;
		}
		return false;
	}
	
	public int getCount(String s) {
		return lookup.get(s).size();
	}
	
	public double getFrequency(String s) {
		return (double) (getCount(s)) / wordTotal;
	}
	
	public List<Integer> getLocations(String s){
		if(contains(s)) {
			return lookup.get(s);
		}
		List<Integer> empty = new ArrayList<Integer>();
		return empty;
	}
	
	public Iterator<String> words(){
		return lookup.keys();
	}
	
	public String toString() {
		return lookup.toString();
	}
	
	public boolean containsPhrase(String s) {
		String[] input = s.split("\\s+"); //split phrase and add to array
		Iterator<Integer> it = lookup.get(input[0]).iterator();
		while(it.hasNext()) {
			int matchCount = 1;
			int temp = it.next();
			for(int i = 1; i < input.length; i++) {
				List<Integer> list = getLocations(input[i]);  
				if(list.contains(temp + i)) {
					matchCount++;
				}
			}
			if(matchCount == input.length) {
				return true;
			}
		}
		return false;
	}
	
	
	public int getPhraseCount(String s) {
		int counter = 0;
		String[] input = s.split("\\s+"); //split phrase and add to array
		Iterator<Integer> it = lookup.get(input[0]).iterator();
		while(it.hasNext()) {
			int matchCount = 1;
			int temp = it.next();
			for(int i = 1; i < input.length; i++) {
				List<Integer> list = getLocations(input[i]);  
				if(list.contains(temp + i)) {
					matchCount++;
				}
			}
			if(matchCount == input.length) {
				counter++;
			}
		}
		return counter;
		}
	
	
	public double getPhraseFrequency(String s) {
		return (double) (getPhraseCount(s)) / wordTotal;
	}
	
	public List<Integer> getPhraseLocations(String s){
		String[] input = s.split("\\s+"); //split phrase and add to array
		Iterator<Integer> it = lookup.get(input[0]).iterator();
		List<Integer> locations = new ArrayList<Integer>();
		while(it.hasNext()) {
			int matchCount = 1;
			int temp = it.next();
			for(int i = 1; i < input.length; i++) {
				List<Integer> list = getLocations(input[i]);  
				if(list.contains(temp + i)) {
					matchCount++;
				}
			}
			if(matchCount == input.length) {
				locations.add(temp);
			}
		}
		return locations;
	}
	
	
	public static void main(String[] args) throws IOException {
		if(args.length > 0) {
			try {
				WebPageIndex test = new WebPageIndex(args[0]);
			
		
			Iterator<String> it = test.words();
			while(it.hasNext()) {
				String word = it.next();
				System.out.printf(word + " % .5f" + " " + test.getLocations(word), test.getFrequency(word));
				System.out.println();
			}
			System.out.println();
			System.out.println("Height: " + test.lookup.getHeight());
			System.out.println("Verify RB Tree: " + test.lookup.verify());
			}
			catch(IndexOutOfBoundsException k) {
				System.out.println("Only one argument allowed");
			}
			catch(Exception t) {
				System.out.println("Invalid argument");
			}
		}
	}
	
	
	
	
	
	
	
}

