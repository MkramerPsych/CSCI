import static org.junit.Assert.*;

import org.junit.Test;

public class MyStackTest {

	@Test
	public void testMyStack() {
		MyStack<String> testStack = new MyStack<String>();
	}

	@Test
	public void testPush() {
		MyStack<String> testStack = new MyStack<String>();
		testStack.push("Hello");
	}

	@Test
	public void testPop() {
		MyStack<String> testStack = new MyStack<String>();
		testStack.push("Hello");
		assertTrue("Test", testStack.pop().equals("Hello"));
		
	}

	@Test
	public void testPeek() {
		MyStack<String> testStack = new MyStack<String>();
		testStack.push("Hello");
		assertTrue("Test", testStack.peek().equals("Hello"));
	}

	@Test
	public void testIsEmpty() {
		MyStack<String> testStack = new MyStack<String>();
		assertTrue("Empty test", testStack.isEmpty());
	}

	@Test
	public void testClear() {
		MyStack<String> testStack = new MyStack<String>();
		testStack.push("Hello");
		testStack.clear();
		assertTrue("null test", testStack.isEmpty());

	}

}
