import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Test;

public class MyPriorityQueueTest {

	public class MyComparator implements Comparator<String>{
		
		public int compare(String o1, String o2) {
			if(o1 == null || o2 == null) {
				throw new NullPointerException("cannot compare to null");
			}
			return o1.compareToIgnoreCase(o2);
		}
		
	}
	
	@Test
	public void testMyPriorityQueue() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		assertTrue(test.size() == 0);
	}

	@Test
	public void testAdd() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		test.add("hello");	
		test.add("world");
		test.add("everyone");
		test.add("hi");
		System.out.println(test.toString());
		assertTrue(test.size() == 4);
	}

	@Test
	public void testRemove() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		test.add("hello");	
		test.add("world");
		test.add("everyone");
		test.add("hi");
		assertEquals("remove test", "hi", test.remove());
		System.out.println(test.toString());
	}

	@Test
	public void testIsEmpty() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		assertTrue(test.isEmpty());
		test.add("hello");
		assertTrue(!test.isEmpty());
	}

	@Test
	public void testSize() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		test.add("hello");
		assertTrue(test.size() == 1);
	}

	@Test
	public void testClear() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		test.add("hello");
		test.clear();
		assertTrue(test.size() == 0);
	}

	@Test
	public void testToString() {
		MyPriorityQueue<String> test = new MyPriorityQueue<String>(new MyComparator());
		test.add("hello");
		System.out.println(test.toString());
	}

}
