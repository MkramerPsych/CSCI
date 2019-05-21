import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;

public class MyHashMapTest {

	@Test
	public void testMyHashMapIntDouble() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>(12, 0.9);
		assertTrue(test.maxLoadFactor == 0.9);
	}

	@Test
	public void testMyHashMap() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		assertTrue(test.maxLoadFactor == 0.75);
	}

	@Test
	public void testPut() {
		MyHashMap<Integer, Integer> test = new MyHashMap<Integer, Integer>();
		for(int i = 0; i < 20; i++) {
			test.put((i + 1), i);
		}
		test.toString();
	}

	@Test
	public void testGet() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		test.put("hello", 1);
		test.put("world", 2);	
		assertEquals("get test", 2, (int) test.get("world"));
	}
	
	@Test
	public void testKeys() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		test.put("hello", 1);
		test.put("world", 2);
		Iterator<String> it = test.keys();
		String actual = it.next();
		assertEquals("iterator test 1", "world", actual);
		
		
	}

	@Test
	public void testEntries() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		test.put("hello", 1);
		test.put("world", 2);
		Iterator<Map.Entry<String, Integer>> it = test.entries();
		Map.Entry<String, Integer> actual = it.next();
		Map.Entry<String, Integer> expected = new AbstractMap.SimpleEntry<String, Integer>("world", 2);
		assertEquals("iterator test 1", expected, actual);
	}


	@Test
	public void testIsEmpty() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		assertTrue(test.isEmpty());
		test.put("hello", 1);
		test.put("world", 2);
		assertTrue(!test.isEmpty());
	}

	@Test
	public void testClear() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		test.put("hello", 1);
		test.put("world", 2);
		test.clear();
		test.toString();
	}

	@Test
	public void testSize() {
		MyHashMap<String, Integer> test = new MyHashMap<String, Integer>();
		test.put("hello", 1);
		test.put("world", 2);
		assertTrue(test.size() == 2);	
	}
}
