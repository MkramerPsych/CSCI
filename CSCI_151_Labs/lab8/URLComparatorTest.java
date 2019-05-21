import static org.junit.Assert.*;

import java.io.IOException;
import java.util.*;
import org.junit.Test;

public class URLComparatorTest {

	@Test
	public void testURLComparator() {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("programming");
		list.add("debugging");
		list.add("of");
		URLComparator comparator = new URLComparator(list);
	}

	@Test
	public void testScore() throws IOException {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("programming");
		list.add("debugging");
		list.add("of");
		URLComparator comparator = new URLComparator(list);
		WebPageIndex index = new WebPageIndex("test1.txt");
		int finalScore = comparator.score(index);
	}

	@Test
	public void testCompare() throws IOException {
		ArrayList<String> list =  new ArrayList<String>();
		list.add("programming");
		list.add("debugging");
		list.add("of");
		URLComparator comparator = new URLComparator(list);
		WebPageIndex index = new WebPageIndex("test1.txt");
		WebPageIndex index2 = new WebPageIndex("test2.txt");
		int comp = comparator.compare(index, index2);
		System.out.println("comp value: " + comp);
	}

}
