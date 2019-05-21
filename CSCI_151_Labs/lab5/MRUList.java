import java.util.Iterator;

class MRUList<T> extends DoublyLinkedList<T> {
    public boolean add(T data) {
    	super.add(0, data);
    	return true;
    }

    public void add(int index, T data) {
    	throw new UnsupportedOperationException("Unsupported Operation");
    }

    public boolean contains(Object obj) {
    	if (obj == null) {
    	    throw new NullPointerException("Cannot index null");
    	}
    	if (!isEmpty()) { // if list is not empty
    	    Iterator<T> itTest = super.iterator();
    	    while(itTest.hasNext()) {
    	    	T temp = itTest.next();
    	    	if (temp.equals(obj)) { // if data matches obj
    	    		itTest.remove();
    	    		add(temp);
    	    		return true;
    	    	}
    	    }
    	}
    	return false;
    }

    public int indexOf(Object obj) {
    	if (obj == null) {
    	    throw new NullPointerException("Cannot index null");
    	}
    	if (!isEmpty()) { // if list is not empty
    	    ListNode p = header.next; // load node from header.next
    	    for (int i = 0; i < size; i++) { // iterate over links to each node index
    		if (p.datum.equals(obj)) { // if data matches obj
    			int temp = i;
    			add(p.datum);
    		    return temp; // return index of matched value
    		}
    		p = p.next;
    	    }
    	}
    	return -1; // else return -1 if node is not contained
        }
}