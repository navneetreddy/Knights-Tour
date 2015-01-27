///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FindKnightsPath.java
// File:             SimpleStack.java
// Semester:         CS367 Fall 2013
//
// Author:           Navneet Reddy nsreddy@wisc.edu
// CS Login:         navneet
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jason Tiedt tiedt2@wisc.edu
// CS Login:         jtiedt
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Implements the Stack ADT class.
 * 
 * @author NavneetReddy
 * @author Jason Tiedt
 * 
 */
public class SimpleStack<E> implements StackADT<E>{

	private static final int INITSIZE = 10;  // initial array size
    private E[] items; // the items in the stack
    private E[] shadow; // shadow array for the items in the stack
    private int numItems;   // the number of items in the stack
    private int index; // index to copy items into the shadow array
	
    /**
     * Constructor to create a new stack object.
     */
	public SimpleStack() {
		items = (E[])(new Object[INITSIZE]);
		shadow = (E[])(new Object[INITSIZE * 2]);
	    numItems = 0;
	    index = 0;
	}

	/**
     * Checks if the stack is empty.
     * 
     * @return true if stack is empty; otherwise false
     */
	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		else
			return false;
	}

	/**
     * Returns (but does not remove) the top item of the stack.
     *
     * @return the top item of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E peek() throws EmptyStackException {
		if (numItems == 0)
	        throw new EmptyStackException();
	    else
	    	return items[numItems - 1];
	}

	/**
     * Pops the top item off the stack and returns it. 
     *
     * @return the top item of the stack
     * @throws EmptyStackException if the stack is empty
     */
	@Override
	public E pop() throws EmptyStackException {
		if (numItems == 0)
	        throw new EmptyStackException();
	    else {
	        numItems--;
	        return items[numItems];
	    }
	}

	/**
     * Pushes the given item onto the top of the stack.
     *
     * @param item the item to push onto the stack
     */
	@Override
	public void push(E item) {
		if (items.length == numItems) {
			items = shadow;
			shadow = (E[])(new Object[2*items.length]);
			index = 0;
		}
		
		items[numItems] = item;
		shadow[numItems] = item;
		
		items[index] = shadow[index];
		
		index++;
		numItems++;
	}

	/**
     * Returns the size of the stack.
     *
     * @return the size of the stack
     */
	@Override
	public int size() {
		return numItems;
	}
	
	/**
	 * Returns a string representation of the stack.
	 * 
	 * @return the string representation of the stack
	 */
	public String toString() {
		
		String itemsString = "";
		
        for ( int i = items.length - 1; i >= 0; i-- ) 
                itemsString = itemsString + items[i].toString() + "\n";
		
		return itemsString;
	}
}
