/**
 * 
 */
package csc311;

import net.datastructures.Position;
import net.datastructures.Queue;

/**
 * @author ruihong-adm
 * @param <E>
 *
 */

public class CircularArrayQueue<E> implements Queue<E> {
	/**
	 * This instance variable is the basis of the class
	 * and stores the elements
	 */
	E[] array;
	
	/**
	 * This instance variable stores the front of the array,
	 * pointing to the index immediately after the last
	 * element
	 */
	int front;
	
	/**
	 * This instance variable points to the last thing added,
	 * which we will be removed by dequeuing.
	 */
	int back;
	
	/**
	 * This constructor creates the circular array and
	 * initializes instance variables
	 * @param queueSize the capacity of the array the
	 * class uses to store elements
	 */
	public CircularArrayQueue(int queueSize) {
		// TODO: create a new queue with the specified size
		array = (E[]) new Object[queueSize];
		front = 0;
		back = 0;
	}
	
	@Override
	/**
	 * This method returns the total number of elements 
	 * (Things that aren't null) in the array
	 * @return the total number of elements in the array
	 */
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		for(int i = 0; i < array.length; i++) {
			if(array[i] != null) size++;
		}
		return size;
	}

	@Override
	/**
	 * This method returns whether the array zero
	 * total elements inside of it
	 * @return whether the array is empty or not
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	/**
	 * This method adds a new element to the queue, 
	 * updating the front variable in the process
	 * @param e the element that is added
	 */
	public void enqueue(E e) {
		// TODO Auto-generated method stub
		if(size() == array.length) return;
		if(size() == 0) {
			array[back] = e;
			front++;
			return;
		}
		array[front] = e;
		if(front + 1 == array.length) {
			front = 0;
		} else {
			front++;
		}
	}

	@Override
	/**
	 * This method returns the oldest element in the queue.
	 * It's very similar to a queue's peek() method
	 * @return this method return the first, oldest
	 * element in the queue
	 */
	public E first() {
		// TODO Auto-generated method stub
		return array[back];
	}

	@Override
	/**
	 * This method removes and returns the oldest element
	 * in the queue
	 * @return the oldest element in the queue
	 */
	public E dequeue() {
		// TODO Auto-generated method stub
		if(size() == 0) return null;
		E store = array[back];
		array[back] = null;
		if(back + 1 == array.length) {
			back = 0;
		} else {
			back++;
		}
		return store;
	}
	

    
}