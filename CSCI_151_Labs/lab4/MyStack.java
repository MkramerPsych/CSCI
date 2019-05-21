import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyStack<T> implements StackADT<T>, Worklist<T> {

    private ArrayList<T> data;
    
    //initializes the stack
    public MyStack(){
    data = new ArrayList<T>(); //initialize empty ArrayList of type T	
    }

    //adds an item to the stack
    public void push(T item){
    data.add(item);//add item to data at the end
    }
   
    public void add(T item){
    this.push(item); //call the push method
    }
    
    //returns and removes the top element of the stack unless the stack is empty
    public T pop() {
    if(data.isEmpty()) {
    	throw new NoSuchElementException("Stack Empty");
    }
    T temp = data.get(data.size() - 1);
    data.remove(data.size() - 1); //remove element at top of stack
	return temp; // return the removed value
    }
    
    public T remove(){
    T temp = this.pop();
    return temp; // return the removed value
    }

    // returns the top of the stack without removing it    
    public T peek(){
    	if(data.isEmpty()) {
        	throw new NoSuchElementException("Stack Empty");
        }
    	T temp = data.get(data.size() - 1);
        data.remove(data.size() - 1); //remove element at top of stack
        data.add(temp); //return element to its place
    	return temp; // return value of top of stack
    }
    
    //determines if the stack is empty or not
    public boolean isEmpty() {
    if(data.isEmpty()) {
    	return true;
    } else {
	return false;
    	}
    }
    
    //clears the stack
    public void clear(){
    	data = new ArrayList<T>();
    }
    
}