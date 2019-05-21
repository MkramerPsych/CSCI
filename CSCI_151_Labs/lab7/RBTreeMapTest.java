import static org.junit.Assert.*;

import org.junit.Test;

public class RBTreeMapTest {

	@Test
	public void testGet() {
		RBTreeMap<Integer, Integer> test = new RBTreeMap<Integer, Integer>();
		test.put(2, 4);
		test.put(7, 14);
		test.put(1, 11);
		assertNull(test.get(3));
	}

	@Test
	public void testPutKV() {
		RBTreeMap<Integer, Integer> test = new RBTreeMap<Integer, Integer>();
		test.put(2, 4);
		assertTrue(test.verify());
	}

	@Test
	public void testPutKVRBTreeMapOfKVRBTreeMapOfKV() {
		RBTreeMap<Integer, Integer> test = new RBTreeMap<Integer, Integer>();
		test.put(2, 4);
		RBTreeMap<Integer, Integer> test2 = new RBTreeMap<Integer, Integer>();
		RBTreeMap<Integer, Integer> test3 =  new RBTreeMap<Integer, Integer>();
		test2.put(3,9);
		test3.put(4,16);
		test.put(6, 36, test2, test3);
		assertTrue(test.verify());
	}
	
	@Test
	public void testRotation() {
		RBTreeMap<String, String> test = new RBTreeMap<String, String>();
		test.put("a", "a");
		test.put("b", "b");
		test.put("c", "c");
		test.put("d", "d");
		RBTreeMap<Integer, Integer> test2 = new RBTreeMap<Integer, Integer>();
		test2.put(3, 1);
		test2.put(1,2);
		test2.put(2,3);
	}

}
