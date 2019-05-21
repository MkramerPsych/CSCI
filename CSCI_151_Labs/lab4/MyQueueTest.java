import static org.junit.Assert.*;

import org.junit.Test;

public class MyQueueTest {

	@Test
	public void testMyQueue() {
		MyQueue<String> testQueue = new MyQueue<String>();
	}

	@Test
	public void testEnqueue() {
		MyQueue<String> testQueue = new MyQueue<String>();
		testQueue.enqueue("hello");
		testQueue.enqueue("world");
	}

	@Test
	public void testDequeue() {
		MyQueue<String> testQueue = new MyQueue<String>();
		testQueue.enqueue("hello");
		assertTrue("Test", testQueue.dequeue().equals("hello"));
	}

	@Test
	public void testPeek() {
		MyQueue<String> testQueue = new MyQueue<String>();
		testQueue.enqueue("hello");
		assertTrue("Test", testQueue.peek().equals("hello"));
	}

	@Test
	public void testIsEmpty() {
		MyQueue<String> testQueue = new MyQueue<String>();
		assertTrue("Empty test", testQueue.isEmpty());
	}

	@Test
	public void testClear() {
		MyQueue<String> testQueue = new MyQueue<String>();
		testQueue.enqueue("hello");
		testQueue.clear();
		assertTrue("null test", testQueue.isEmpty());
	}

}
