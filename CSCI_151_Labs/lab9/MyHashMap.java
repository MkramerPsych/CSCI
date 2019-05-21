import java.util.*;
import java.util.Map.Entry;


public class MyHashMap<K,V> implements MapADT<K,V>{

	//Instance Variables
	LinkedList<Map.Entry<K,V>>[] table; //array of buckets to store K,V pairs
	int size; //current number of items in the hash table (NOT #of buckets)
	double maxLoadFactor; //maximum permitted load factor
	
	//Constants
	public static final double DEFAULTMAXLOAD = 0.75;
	public static final int DEFAULTCAPACITY = 11;
	public static final int[] PRIMES = {11,23,47,97,197,397,797,1597,3203,6241,12853,25717,51437,102877,205759,411527,823117,1646237,3292489,6584983,13169977,26339969,52679969,105359939,210719881,421439783,842879579,1685759167};
	
	//Constructors
	@SuppressWarnings("unchecked") //handle raw typing of LinkedList[capacity]
	MyHashMap(int capacity, double maxLoadFactor){
		table = (LinkedList<Map.Entry<K,V>> []) new LinkedList[capacity];
        for(int i = 0; i < table.length; i++) { //for every bucket in table
        	table[i] = new LinkedList<Map.Entry<K,V>>(); //add in new LL
        }
        this.maxLoadFactor = maxLoadFactor; //init maxloadfactor
        size = 0; //init size
	}
	
	MyHashMap(){
		this(DEFAULTCAPACITY, DEFAULTMAXLOAD);
	}
	
	//Methods
	private int hash(K key) { //HASHING FUNCTION
		int hashCode = Math.abs(key.hashCode()); //convert key to pos int
		int tableSize = table.length;
		return hashCode % tableSize;
	
	}
	
	@Override
	public V put(K key, V value) { //INSERTION FUNCTION
		if(key.equals(null) || value.equals(null)) {
			throw new NullPointerException("Cannot add null to map");
		}
		int hashOfKey = hash(key); //generate hash value for key
		V previous = null; //init previous for return
		LinkedList<Map.Entry<K,V>> bucket = table[hashOfKey]; 
		for(Map.Entry<K, V> entry : bucket) { //search bucket at hashOfKey
			if(entry.getKey().equals(key)) { //if found
				previous = entry.getValue();
				entry.setValue(value);
				System.out.println("replaced value");
				return previous;
			}
		}
		Map.Entry<K, V> newEntry = new AbstractMap.SimpleEntry<K,V>(key, value);
		bucket.add(newEntry);
		if(((double)(size)/table.length) >= maxLoadFactor) {
			//System.out.println("max load factor exceeded, resizing");
			resize();
		}
		//System.out.println("added new entry to bucket: " + newEntry.getValue());
		size++;
		return null;
	}
	
	@Override
	public V get(K key) { //RETRIEVAL FUNCTION
		if(key.equals(null)) {
			throw new NullPointerException("Cannot add null to map");
		}
		int hashOfKey = hash(key); //generate hash value for key
		V ret = null; //init ret value
		LinkedList<Map.Entry<K,V>> bucket = table[hashOfKey]; 
		for(Map.Entry<K, V> entry : bucket) { //search bucket at hashOfKey
			if(entry.getKey().equals(key)) { //if found
				ret = entry.getValue();
				return ret;
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private void resize() {
		//System.out.println("Old length: " + table.length);
		int newSize = primeTester(2 * table.length);
		//System.out.println("New array size generated: " + newSize);
		LinkedList<Map.Entry<K,V>>[] table2 = (LinkedList<Map.Entry<K,V>> []) new LinkedList[newSize];
		LinkedList<Map.Entry<K,V>>[] oldTable = table;
		table = table2;
		for(int i = 0; i < table.length; i++) { //for every bucket in table
	        table[i] = new LinkedList<Map.Entry<K,V>>(); //add in new LL
	    }
		for(int i = 0; i < oldTable.length; i++) {
			if(!oldTable[i].isEmpty()) {
				for(Map.Entry<K, V> entry : oldTable[i]) {
					put(entry.getKey(), entry.getValue());
				}
			}
		}
	}
		
	private int primeTester(int i) { //Helper method for resize
		for(int j = 0; j < PRIMES.length; j++) {
			if(PRIMES[j] > i) {
				return PRIMES[j];
			}
		}
		return 0;
	}
	
	@Override
	public Iterator<K> keys() {
		ArrayList<K> keysList = new ArrayList<K>();
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				for(Map.Entry<K, V> entry : table[i]) {
					keysList.add(entry.getKey());
				}
			}
		}
		return keysList.iterator();
	}

	@Override
	public Iterator<Entry<K, V>> entries() {
		ArrayList<Map.Entry<K, V>> entryList = new ArrayList<Map.Entry<K, V>>();
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				for(Map.Entry<K, V> entry : table[i]) {
					entryList.add(entry);
				}
			}
		}
		return entryList.iterator();
	}
	
	@Override
	public boolean isEmpty() {
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void clear() {
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				table[i].clear();
			}
		}
		size = 0;
	}

	@Override
	public int size() {
		int size = 0;
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				for(Map.Entry<K, V> entry : table[i]) {
					size++;
				}
			}
		}
		return size;
	}

	public String toString() {
		for(int i = 0; i < table.length; i++) {
			if(!table[i].isEmpty()) {
				for(Map.Entry<K, V> entry : table[i]) {
					K key = entry.getKey();
					V value = entry.getValue();
					System.out.println("(Bucket #): " + i + " " + "(Key): " + key + " " + "(Value): " + value);
				}
			}
		}
		return "Done";
	}

}
