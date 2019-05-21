import java.util.NoSuchElementException;

public class MyQueue<T> implements QueueADT<T>, Worklist<T>
{
    private QNode<T> front;
    private QNode<T> rear;
    
    //nested class QNode:  nodes used in the queue
    @SuppressWarnings("hiding")
	class QNode<T>
    {
	//holds the data value and reference for a node
	private T item;
	private QNode<T> next;
	
	//constructors for node
	QNode(T item){
	    this(item,null);
	}
	
	QNode(T item, QNode<T> next){
	    this.item = item;
	    this.next = next;
	}
    }
    
    //initializes empty queue
    public MyQueue(){
    	front = new QNode<T>(null); //sentinel node front
    	rear = front; //front and rear both point to front
    }
    
    //adds element to the queue
    public void enqueue(T item){
    QNode<T> toBeAdded = new QNode<T>(item); //add node toBeAdded 
    rear.next = toBeAdded; //put toBeAdded after rear
    rear = toBeAdded; //move rear pointer to toBeAdded
    }
    
    public void add(T item){
    this.enqueue(item);	//call enqueue method
    }
    
    // gets and removes the next thing in the queue that is supposed to be executed
    // throw a NoSuchElementException if the queue is empty
    public T dequeue() {
    if(this.isEmpty()) { //throw exception if queue is empty
    	throw new NoSuchElementException("Cannot dequeue from an empty queue.");
    }
    T temp = front.next.item; //get data value from front node
    if (front.next.item == null) {
    	throw new NullPointerException("Cannot dequeue sentinel node");
    }
    front = front.next; //move front to next item in queue
    return temp; // return the value removed
    }
    
    public T remove(){
    T temp = this.dequeue(); //store value from dequeue in temp
    return temp; //return removed value
    }
    
    // gets the first item in the queue without removing it
    // throw a NoSuchElementException if the queue is empty
    public T peek(){
    	if(this.isEmpty()) { //throw exception if queue is empty
        	throw new NoSuchElementException("Cannot dequeue from an empty queue.");
        }
        T temp = front.next.item; //get data value from front node
        return temp; // return data value
    }
    
    // tests to see if the queue is empty
    public boolean isEmpty(){
	//TODO:  write the isEmpty method
    if(front.next == null) {
    	return true;
    } else {
    	return false; // replace this statement
    }
    }
    
    //clears the queue
    public void clear(){
    front.next = null;
    rear= front;
    	
    }
}

