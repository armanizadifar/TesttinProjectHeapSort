package csc311;

import java.util.Arrays;
import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {
	/**
	 * This instance variable is the basis of the class,
	 * and stores the variables as needed
	 */
	private E[] array;
	
	/**
	 * This instance variable stores the index immediately
	 * after the last element in the array
	 */
	private int index;
	
	/**
	 * The constructor creates a new array, with a default 
	 * length of 16. The instance variable array keeps track
	 * of the elements, while the instance variable index
	 * keeps track of which element is the last in the list
	 */
	public ArrayList() {
		// TODO Auto-generated constructor stub
		array = (E[])new Object[16];
		index = 0;
	}

	@Override
	/**
	 * This method returns the number of elements in 
	 * the arrayList. It does this through the index
	 * instance variable.
	 * @return the number of elements in the array
	 */
	public int size() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	/**
	 * This method returns whether or not the array
	 * has zero elements in it. 
	 * @return whether the array has zero elements
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return index == 0;
	}

	@Override
	/**
	 * This method returns the element found at a particular index
	 * @param  i the index we want to retrieve the element from
	 * @return the element stored at the specified index
	 * @throws IndexOutOfBoundsException if the index is negative
	 * or larger than the number of elements
	 */
	public E get(int i) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(i < 0 || i >= array.length) throw new IndexOutOfBoundsException();
		return array[i];
	}

	@Override
	/**
	 * This method replaces the element at a certain index
	 * with a new element, passed in as an argument
	 * @param i the index we want to replace, and e the element
	 * we want to replace it with
	 * @return the element that used to be at that index
	 * @throws IndexOutOfBoundsException if the index is negative
	 * or larger than the number of elements
	 */
	public E set(int i, E e) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub

		if(i < 0 || i >= array.length || i >= index) throw new IndexOutOfBoundsException();
		E store = array[i];
		array[i] = e;
		return store;
	}

	@Override
	/**
	 * This method adds an element to the array at a
	 * specific index. If needed, we resize the array to
	 * store the new element
	 * @param i the index where we want to add something,
	 *  and e the element we're going to add
	 * @throws IndexOutOfBoundsException if the index is negative
	 * or larger than the number of elements+1
	 */
	public void add(int i, E e) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(i < 0 || i > array.length || i > index) {
			throw new IndexOutOfBoundsException();
		}
		if(i == array.length) {
			addLast(e);
			return;
		}
		if(index == array.length) {
			resize();
		}
		if(i == 0) {
			addFirst(e);
			return;
		}
		if(i == array.length) {
			addLast(e);
			return;
		}
		E[] replace = (E[]) new Object[array.length];
		for(int a = 0; a < i; a++) {
			replace[a] = array[a];
		}
		replace[i] = e;
		for(int a = i; a < array.length-1; a++) {
			replace[a+1] = array[a];
		}
		index++;
		array = replace;
	}
	
	@Override
	/**
	 * This method removes the element from a 
	 * specified index of the array.
	 * @param i the index we want to remove from
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is negative
	 * or larger than the number of elements
	 */
	public E remove(int i) throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(i < 0 || i >= array.length || i >= index) throw new IndexOutOfBoundsException();
		E[] replace = (E[]) new Object[array.length];
		E store = array[i];
		for(int a = 0; a < i; a++) {
			replace[a] = array[a];
		}
		for(int a = i+1; a < array.length; a++) {
			replace[a-1] = array[a];
		}
		array = replace;
		index--;
		return store;
	}

	
	@Override
	/**
	 * This method provides an iterator of the array,
	 * only including valid elements
	 * @return the iterator version of the arraylist
	 */
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		E[] iteratorArray = (E[]) new Object[index];
		for(int a = 0; a < index; a++) {
			iteratorArray[a] = array[a];
		}
		return Arrays.stream(iteratorArray).iterator();
	}

	/**
	 * This method adds an element to the very front
	 * of the list, resizing if necessary
	 * @param e the element we want to add
	 */
	public void addFirst(E e)  {
		// TODO Auto-generated method stub
		if(index == array.length) {
			resize();
		}
		E[] replace = (E[]) new Object[array.length];
		replace[0] = e;
		for(int a = 0; a < array.length-1; a++) {
			replace[a+1] = array[a];
		}
		array = replace;
		index++;
	}
	
	/**
	 * This method adds an element to the very end
	 * of the list, resizing if necessary
	 * @param e the element we want to add
	 */
	public void addLast(E e)  {
		// TODO Auto-generated method stub
		if(index == array.length) {
			resize();
		}
		E[] replace = (E[]) new Object[array.length];
		replace[index] = e;
		for(int a = 0; a < index; a++) {
			replace[a] = array[a];
		}
		array = replace;
		index++;
	}
	
	/**
	 * This method removes the first element of the array
	 * @return the element removed from the front of the list
	 * @throws IndexOutOfBoundsException if the array has no 
	 * elements
	 */
	public E removeFirst() throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(array.length == 0) throw new IndexOutOfBoundsException();
		E[] replace = (E[]) new Object[array.length];
		E store = array[0];
		for(int a = 0; a < array.length-1; a++) {
			replace[a] = array[a+1];
		}
		array = replace;
		index--;
		return store;
	}
	
	/**
	 * This method removes the last element in the array
	 * @return the removed element from the end of the array
	 * @throws IndexOutOfBoundsException if the array has
	 * no elements
	 */
	public E removeLast() throws IndexOutOfBoundsException {
		// TODO Auto-generated method stub
		if(array.length == 0) throw new IndexOutOfBoundsException();
		E[] replace = (E[]) new Object[array.length];
		E store = array[index-1];
		for(int a = 0; a < index+1; a++) {
			replace[a] = array[a];
		}
		array = replace;
		index--;
		return store;
	}
	
	// Return the capacity of array, not the number of elements.
	// Notes: The initial capacity is 16. When the array is full, the array should be doubled. 
	/**
	 * This method returns the total potential capacity
	 * of the array
	 * @return the capacity of the array
	 */
	public int capacity() {
		return array.length;
	}
	
	/**
	 * This method resizes the array to have double
	 * its previous capacity
	 */
	private void resize() {
		E[] replace = (E[]) new Object[array.length*2];
		for(int a = 0; a < array.length; a++) {
			replace[a] = array[a];
		}
		array = replace;
	}
	
}