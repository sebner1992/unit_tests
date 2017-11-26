package averkova_ebner;

public class StackArray<E> implements Stack<E>{
    private E[] stack;
    private int size;
    private int top;

    //constructs empty array of length size
    public StackArray(int size) {
        this.size = size;
        top = -1;
        stack = (E[]) new Object[size];
    }

    //inserts item at the top of this stack
    public void push(E item) {
        if (top >= size - 1)
            throw new IndexOutOfBoundsException("ERROR! Push-operation on full stack! Stack size = " + size);
        stack[++top] = item;
    }

    //returns the top item of this stack and removes it
    public E pop() {
        if (top < 0) throw new IndexOutOfBoundsException("ERROR! Pop-operation on empty stack!");
        E item = stack[top--];
        stack[top + 1] = null;
        return item;
    }

    //returns the top item of this stack without removing it
    public E peek() {
        if (top < 0) throw new IndexOutOfBoundsException("ERROR! Peek-operation on empty stack!");
        return stack[top];
    }

    //stack empty?
    public boolean isEmpty() {
        return top == -1;
    }
    
	//get size of stack
	public int getSize() {
		if (top < 0) return 0;
		int counter = 0;
		for(E item : stack) {
			if(item != null) counter++;
		}
		return counter;
	}
}
