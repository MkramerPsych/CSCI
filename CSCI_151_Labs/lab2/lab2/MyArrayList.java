package lab2;

//Import java utility package
import java.util.AbstractList;
import java.util.Arrays;

/* This is an implementation of the arrayList class extending the abstractList class.
 * I affirm that I have adhered to the honor code on this assignment
 * Author: Max Kramer
 */

public class MyArrayList<E> extends AbstractList<E> { // declare generic class MyArrayList

    // INSTANCE VARIABLES
    int size; // create variable size
    E[] data; // create array data

    // CONSTRUCTORS
    @SuppressWarnings("unchecked")
    MyArrayList(int startLength) { // constructor with initial capacity
	data = (E[]) new Object[startLength]; // initialize data with start cap startLength
    }

    MyArrayList() { // empty constructor using default capacity
	this(5); // set initial capacity default to 5
    }

    // METHODS
    @SuppressWarnings("unchecked")
    private void resize() { // doubles length of the array
	System.out.println("Old length: " + data.length); // print old array length
	E[] newArray = (E[]) new Object[2 * data.length]; // create new array of double length
	for (int i = 0; i < data.length; i++) { // data.length repeats
	    newArray[i] = data[i]; // copy from data to newArray
	}
	this.data = newArray; // sets data equal to newArray
	System.out.println("New length: " + data.length); // print new array length
    }

    public int size() { // return # of items in array
	return size;
    }

    // Move bottom element down then move next element down
    public void add(int index, E element) { // add element E at index int

	if (index > size || index < 0) { // throw exception if index is out of range
	    throw new IndexOutOfBoundsException(
		    "Index Out of Bounds! You tried to get " + index + " but the size is " + size);
	}

	if (index <= size) { // if index forces array out of bounds, resize array
	    if (data.length <= size) { // if data.length is less than size
		resize(); // call resize function
	    }
	}

	for (int i = size - 1; i >= index; i--) { // go from size - 1 to index
	    data[i + 1] = data[i]; // shift values up one
	}
	data[index] = element; // assign element to data index index
	size++;
    }

    public boolean add(E element) { // add element to the end of the list, return t
	this.add(size, element); // call add method with index size and pass element
	return true; // return true if element is added
    }

    public E get(int index) { // returns value at index index
	if (index > size - 1 || index < 0) { // boundary error handling
	    throw new IndexOutOfBoundsException(
		    "Index Out of Bounds! You tried to get " + index + " but the size is " + size);
	}
	return data[index];
    }

    public E set(int index, E element) {
	if (index > size - 1 || index < 0) { // boundary error handling
	    throw new IndexOutOfBoundsException(
		    "Index Out of Bounds! You tried to get " + index + " but the size is " + size);
	}
	E temp = data[index]; // store value of data at index to temp variable
	data[index] = element; // replace element at index with element
	return temp; // return replaced value
    }

    public E remove(int index) { // add element E at index int
	if (index > size || index < 0) { // throw exception if index is out of range
	    throw new IndexOutOfBoundsException(
		    "Index Out of Bounds! You tried to get " + index + " but the size is " + size);
	}

	E temp = data[index]; // store removed element in variable temp

	for (int i = index; i <= size - 1; i++) { // go from index to size - 1
	    data[i] = data[i + 1]; // moves all elements up one index
	}
	data[size] = null; // set index at size = null
	size--; // decrement size by 1
	return temp; // return removed value

    }

    public boolean isEmpty() { // return true if list is empty
	boolean result = false; // initialize result
	if (size == 0) { // if list is empty
	    result = true;
	}
	return result;
    }

    public void clear() { // clear array of all data
	if (size > 0) { // if !isEmpty.()
	    Arrays.fill(data, null); // populate array with null
	}
    }
}
