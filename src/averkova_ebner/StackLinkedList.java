package averkova_ebner;

public class StackLinkedList<E> implements Stack<E>{
	private int maxSize;
	private Node head;

	//constructor, creates an empty stack
	public StackLinkedList(int maxSize) {
		this.maxSize = maxSize;
		head = null;
	}

	//inserts item at the top of this stack
	public void push(E item){
		if(getSize() + 1 > maxSize) {
			throw new IndexOutOfBoundsException("ERROR! Push-operation on full stack! Stack size = " + maxSize);
		}
		Node p = new Node(item);
		p.next = head;
		head = p;    
	}

	//returns the top item of this stack and removes it
	public E pop(){
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("ERROR! Pop-operation on empty stack!");
		} 
		else { 
			Node p = head;
			head = p.next;
			return p.data;
		}    
	}

	//returns the top item of this stack without removing it
	public E peek(){
		if (isEmpty()){
			throw new IndexOutOfBoundsException("ERROR! Peek-operation on empty stack!");
		} 
		else {
			return head.data;
		}
	}

	//stack empty?
	public boolean isEmpty(){
		return head == null;
	}

	//get size of stack
	public int getSize() {
		if (isEmpty()) return 0;
		Node temp=head;
		int count = 0;
		while(temp!=null) {
			temp=temp.next;
			count++;  
		}
		return count;
	}

	/**
	 * *************************************************
	 * Inner class Node: for creating dynamic lists of elements of Type E
	 * *************************************************
	 */
	private class Node {

		E data;
		Node next;

		Node(E d) {
			data = d;
			next = null;
		}
	}
}

