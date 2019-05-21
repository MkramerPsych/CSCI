import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

public class DoublyLinkedListTest {

    @Test
    public void testGetNthNode() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertEquals("nth node test", "world", test.getNthNode(1).datum);

    }

    @Test
    public void testSize() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	ArrayList<String> other = new ArrayList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	other.add("hello");
	other.add("world");
	other.add("life");
	assertEquals("size test", test.size(), other.size());
    }

    @Test
    public void testIsEmpty() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	assertTrue(test.isEmpty());
    }

    @Test
    public void testClear() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	test.clear();
	assertEquals("Clear test", 0, test.size());

    }

    @Test
    public void testGetInt() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertEquals("get test", "world", test.get(1));
    }

    @Test
    public void testSetIntT() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertEquals("set test", "world", test.set(1, "my"));
	assertEquals("set test", "my", test.set(1, "my"));
    }

    @Test
    public void testAddT() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertEquals("add end test", "world", test.getNthNode(1).datum);
    }

    @Test
    public void testAddIntT() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add(0, "life");
	assertEquals("add middle test", "life", test.getNthNode(0).datum);
    }

    @Test
    public void testRemoveInt() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	test.remove(1);
	assertEquals("remove test", "life", test.getNthNode(1).datum);
    }

    @Test
    public void testContainsObject() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertTrue(test.contains("hello"));
    }

    @Test
    public void testIndexOfObject() {
	DoublyLinkedList<String> test = new DoublyLinkedList<String>();
	test.add("hello");
	test.add("world");
	test.add("life");
	assertEquals("index test", 1, test.indexOf("world"));
    }

    @Test
    public void testIterator() {
	DoublyLinkedList<Integer> test = new DoublyLinkedList<Integer>();
	ArrayList<Integer> other = new ArrayList<Integer>();
	for (int i = 0; i < 10; i++) {
	    test.add(i);
	    other.add(i);
	}


	Iterator<Integer> otherTest = other.iterator();
	while (otherTest.hasNext()) {
	    int temp = otherTest.next();
	    if (temp % 2 == 0) {
		otherTest.remove();
	    }
	}
	
	Iterator<Integer> intTest = test.iterator();
	while (intTest.hasNext()) {
		int temp = intTest.next();
	    if (temp % 2 == 0) {
		intTest.remove();
	    }
	}

	

	while (intTest.hasNext() && otherTest.hasNext()) {
	    assertEquals("next test", intTest.next(), otherTest.next());
	}
    }

}