///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  FindKnightsPath.java
// File:             SimpleQueue.java
// Semester:         CS367 Fall 2013
//
// Author:           Navneet Reddy
// CS Login:         navneet
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   PAIR PROGRAMMERS COMPLETE THIS SECTION
// Pair Partner:     Jason Tiedt
// CS Login:         jtiedt
// Lecturer's Name:  Jim Skrentny
// Lab Section:      Lecture 1
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * Implements the Queue ADT class.
 * 
 * @author NavneetReddy
 * @author Jason Tiedt
 * 
 */
public class SimpleQueue<E> implements QueueADT<E>{

	private static final int INITSIZE = 10;  // initial array size
	private E[] items; // the items in the queue
	private int numItems;   // the number of items in the queue
	private int frontIndex; // front of the queue
	private int rearIndex; // rear of the queue

	/**
	 * Constructor to create a new queue object.
	 */
	public SimpleQueue() {
		items = (E[])(new Object[INITSIZE]);
		numItems = 0;
		frontIndex = 0;
		rearIndex = 0;
	}

	/**
	 * Checks if the queue is empty.
	 * 
	 * @return true if queue is empty; otherwise false
	 */
	@Override
	public boolean isEmpty() {
		if (numItems == 0)
			return true;
		else
			return false;
	}

	/**
	 * Removes and returns the front item of the queue.
	 *
	 * @return the front item of the queue
	 * @throws EmptyQueueException if the queue is empty
	 */
	@Override
	public E dequeue() throws EmptyQueueException {
		if(numItems == 0)
			throw new EmptyQueueException();
		else
		{
			// Find the front item and return it from the queue
			E tempItem = items[frontIndex];
			items[frontIndex] = null;
			frontIndex++;
			numItems--;
			return tempItem;
		}
	}

	/**
	 * Adds an item to the rear of the queue.
	 *
	 * @param item the item to add to the queue
	 */
	@Override
	public void enqueue(E item) {
		// Check for full array and expand if necessary
		if (items.length == rearIndex) 
		{
			E[] temp = (E[])(new Object[items.length*2]);

			for (int i = 0; i < items.length; i++)
				temp[i] = items[i];

			items = temp;
			rearIndex = frontIndex + numItems;
		}

		// Insert new item at rear of queue
		items[rearIndex] = item;
		numItems++;
		rearIndex++;
	}

	/**
	 * Returns the size of the queue.
	 *
	 * @return the size of the queue
	 */
	@Override
	public int size() {
		return numItems;
	}

	/**
	 * Returns a string representation of the queue.
	 * 
	 * @return the string representation of the queue
	 */
	public String toString() {
		String itemsString = "";
		E[] tempItems = items;

		for(int i = frontIndex; i < rearIndex + 1; i++)
			itemsString = itemsString + tempItems[i] + "\n";

		return itemsString;
	}
}
