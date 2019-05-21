
/*
 * DoublyLinkedList
 * 
 * This class implements the Java List interface using a doubly-linked list.
 * 
 * A nested ListNode class is included.
 * 
 */
import java.util.AbstractList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> extends AbstractList<T> {
    int size;
    int modCount;
    ListNode header, trailer;

    /*
     * ListNode
     * 
     * This nested class represents one node in a doubly linked list.
     */
    protected class ListNode {
	T datum;
	ListNode prior, next;

	ListNode() {
	    this(null);
	}

	ListNode(T data) {
	    this(data, null, null);
	}

	ListNode(T data, ListNode prior, ListNode next) {
	    this.datum = data;
	    this.prior = prior;
	    this.next = next;
	}
    } // end of class ListNode

    /*
     * Constructs an empty list.
     */
    DoublyLinkedList() {
	header = new ListNode();
	trailer = new ListNode();
	header.prior = null;
	header.next = trailer;
	trailer.prior = header;
	trailer.next = null;
	size = 0;
    }

    /*
     * Returns a reference to the nth node in the list.
     */
    public ListNode getNthNode(int n) {
	if (n < 0 || n > size) {
	    throw new IndexOutOfBoundsException("cannot return node: index out of bounds");
	}
	ListNode p = header.next;
	for (int i = 0; i < n; i++) { // iterate over links to find node index n
	    p = p.next;
	}
	return p; // return node
    }

    /*
     * Returns a count of the number of elements in the list.
     */
    public int size() {
	return size;
    }

    /*
     * Returns the data item at the given position in the list.
     */
    public T get(int position) {
	if (position < 0 || position > size) {
	    throw new IndexOutOfBoundsException("cannot return value: index out of bounds");
	}
	return getNthNode(position).datum; // returns value of node at position position
    }

    /*
     * Replaces the item at the given position with the given data item. The return
     * value is the element that is replaced.
     */
    public T set(int position, T data) {
	if (data == null) {
	    throw new NullPointerException("Cannot add null to list");
	}
	if (position < 0 || position > size) {
	    throw new IndexOutOfBoundsException("Index out of bounds");
	}
	ListNode node = getNthNode(position); // acquire node at position
	T temp = node.datum; // store data in variable temp
	node.datum = data;
	return temp; // return stored data value
    }

    /*
     * Inserts the given data item at the end of the list.
     */
    public boolean add(T data) {
	if (data == null) {
	    throw new NullPointerException("Cannot add null to list");
	}
	ListNode node = new ListNode(data); // create new node with data value data
	node.next = trailer; // set node to point to trailer node
	node.prior = trailer.prior;
	trailer.prior.next = node;
	trailer.prior = node; // set trailers prior to node
	size++;
	modCount++;
	return true;
    }

    /*
     * Inserts the given data item at the given position in the list.
     */
    public void add(int position, T data) {
	if (data == null) {
	    throw new NullPointerException("Cannot add null to list");
	}
	if (position < 0 || position > size) {
	    throw new IndexOutOfBoundsException("Index out of bounds");
	}
	ListNode node = new ListNode(data); // create new node with data value data
	ListNode target = getNthNode(position); // get target node for insertion
	node.next = target;
	node.prior = target.prior;
	target.prior.next = node;
	target.prior = node;
	size++;
	modCount++;
    }

    /*
     * Removes the element at a given index in the list.
     */
    public T remove(int index) {
	if (index < 0 || index > size) {
	    throw new IndexOutOfBoundsException("Index out of bounds");
	}
	ListNode node = getNthNode(index); // get node for removal
	node.next.prior = node.prior; // sever prior pointer to node
	node.prior.next = node.next; // sever forward pointer to node
	size--;
	modCount++;
	return node.datum; // return data value of removed node
    }

    /*
     * Searches the list for the given object
     */
    public boolean contains(Object obj) {
	if (obj == null) {
	    throw new NullPointerException("Cannot index null");
	}
	if (!isEmpty()) { // if list is not empty
	    ListNode p = header.next; // load node from header.next
	    for (int i = 0; i < size; i++) { // iterate over links to each node index
		if (p.datum.equals(obj)) { // if data matches obj
		    return true;
		}
		p = p.next;

	    }
	}
	return false; // return false if not contained
    }

    /*
     * Returns the position of the given object
     */
    public int indexOf(Object obj) {
	if (obj == null) {
	    throw new NullPointerException("Cannot index null");
	}
	if (!isEmpty()) { // if list is not empty
	    ListNode p = header.next; // load node from header.next
	    for (int i = 0; i < size; i++) { // iterate over links to each node index
		if (p.datum.equals(obj)) { // if data matches obj
		    return i; // return index of matched value
		}
		p = p.next;
	    }
	}
	return -1; // else return -1 if node is not contained
    }

    /*
     * Deletes all elements from the list.
     */
    public void clear() {
	header.next = trailer; // set header pointer to trailer
	trailer.prior = header; // set trailer pointer to header
	size = 0;
    }

    /*
     * Determines if the list is empty.
     */
    public boolean isEmpty() {
	return size == 0; // size test for empty list
    }

    /*
     * Returns an iterator for this list
     */
    public Iterator<T> iterator() { // ANONYMOUS CLASS
	return new Iterator<T>() { // RETURN ITERATOR OVERRIDING FOLLOWING VARIABLES/METHODS

	    // INSTANCE VARIABLES
		ListNode CurrentNode = header.next;
	    int IteratorModCount = modCount;
	    boolean ModifiableState = false;

	    // METHOD OVERRIDES
	    @Override
	    public boolean hasNext() {
		if (CurrentNode == trailer) {
		    return false;
		}
		return true;
	    }

	    @Override
	    public T next() {
		if (hasNext() == false) {
		    throw new NoSuchElementException("No such element");
		}
		if (IteratorModCount != modCount) {
		    throw new ConcurrentModificationException("Concurrent Modification");
		}
		T temp = CurrentNode.datum;
		CurrentNode = CurrentNode.next;
		ModifiableState = true;
		return temp;
	    }

	    @Override
	    public void remove() {
		if (ModifiableState == false) {
		    throw new IllegalStateException("Cannot modify");
		}
		ModifiableState = false;
		if (IteratorModCount != modCount) {
		    throw new ConcurrentModificationException("Concurrent Modification");
		}
		CurrentNode.prior = CurrentNode.prior.prior;
		CurrentNode.prior.next = CurrentNode;
		IteratorModCount++;
		modCount++;
	    }
	};
    }
}