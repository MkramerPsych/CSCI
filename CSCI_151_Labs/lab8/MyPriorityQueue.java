import java.util.*;

class MyPriorityQueue<E> implements PriorityQueueADT<E> {
   
   //Instance Variables
   ArrayList<E> heap;
   Comparator<E> comparator;
     
   //Constructors
   MyPriorityQueue(Comparator<E> comparator){ //instantiate priorityQueue
      this.comparator = comparator;
      heap = new ArrayList<E>();
   }
   
   //Methods
   public boolean add(E item){
	  if(item == null) {
		  throw new NullPointerException("Cannot add null to Queue");
	  }
	  heap.add(item);
	  siftUp(heap.size()-1);
      return true;
   }
   
   public E remove(){
	  E copyRoot = heap.get(0);
	  E copyBtm = heap.get(heap.size()-1);
	  heap.set(0, copyBtm);
	  heap.remove(heap.size()-1);
	  if(!heap.isEmpty()) {
	  siftDown(0);
	  }
      return copyRoot;
   }
   
   public boolean isEmpty(){ //test if queue is empty
      if(heap.isEmpty()) {
    	  return true;
      }
      return false;
   }
   
   public int size(){ //return priorityQueue Size
       return heap.size();
   }

   public void clear(){ //clear the heap
       heap.clear();
   }

   public String toString(){
      return heap.toString();
   }
   
   private void siftUp(int pos){
	  int i = pos; //POS SHOULD BE HEAP.SIZE()-1
	  E node = heap.get(i);
	  E parent = heap.get((i-1)/2);
	  while(i != 0 && comparator.compare(node, parent) >= 0) {
		heap.set(i, parent);
		heap.set((i-1)/2, node);
		i = (i-1)/2;
		node = heap.get(i);
		parent = heap.get((i-1)/2);
	  }
   }
         
   private void siftDown(int pos){
	  int i = pos; //POS SHOULD BE ROOT
	  int iLarger = 0;
	  E leftChild = null;
	  E rightChild = null;
	  E node = heap.get(i);
	  if((2 * i) + 1 < heap.size()) {
		  leftChild = heap.get((2*i)+1);
	  }
	  if((2 * i) + 2 < heap.size()) {
		  rightChild = heap.get((2*i)+2);
	  }
	  E largerChild = null;
	  if(leftChild == null && rightChild == null) { //if both are null do nothing
		  return;
	  }
	  
	  else if(leftChild != null && rightChild == null) { //if left is not null and right is
		  largerChild = leftChild;
		  iLarger = (2*i)+1;
	  }
	  
	  else if(comparator.compare(node, leftChild) >= 0 && comparator.compare(node, rightChild) >= 0) { //parent is larger than children
		  return;
	  }
	  
	  else if(comparator.compare(leftChild, rightChild) <= 0) { //right child is larger than leftchild
		  largerChild = rightChild;
		  iLarger = (2*i)+2;
	  }
	  
	  else if(comparator.compare(leftChild, rightChild) > 0) { //left child is larger than rightchild
		  largerChild = leftChild;
		  iLarger = (2*i)+1;	
	  }
	  
	  if(leftChild != null || rightChild !=null && comparator.compare(node, largerChild) <= 0) {
		  heap.set(i, largerChild);
		  heap.set(iLarger, node);
		  i = iLarger;
		  siftDown(i);
  }
   }   

   private int parent(int x){
       int parent = (x-1)/2;
	   return parent;
   }
   
   private int leftChild(int x){
	   int leftChild = (2*x)+1;
       return leftChild;
   }
   
   private int rightChild(int x){
	   int rightChild = (2*x)+2;
       return rightChild;
   }
   
}