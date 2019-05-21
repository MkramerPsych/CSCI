import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class MRUListTest {

    @Test
    public void testAddT() {
    	MRUList<String> test = new MRUList<String>();
    	test.add("hello");
    	test.add("world");
    	assertEquals("MRU add test", "world", test.getNthNode(0).datum);
    }

    @Test
    public void testContainsObject() {
    	MRUList<String> test = new MRUList<String>();
    	test.add("hello");
    	test.add("world");
    	assertTrue(test.contains("world"));
    	assertEquals("MRU container test", 0, test.indexOf("world"));
    }

    @Test
    public void testIndexOfObject() {
    	MRUList<String> test = new MRUList<String>();
    	test.add("hello");
    	test.add("world");
    	assertEquals("MRU index test", 1, test.indexOf("hello"));
    }

}