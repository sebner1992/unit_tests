package averkova_ebner;

public interface Stack<E> {

    //inserts item at the top of this stack
    public void push(E item);

    //returns the top item of this stack and removes it
    public E pop();

    //returns the top item of this stack without removing it
    public E peek();

    //stack empty?
    public boolean isEmpty();

    //get size of stack
	public int getSize();
}   

