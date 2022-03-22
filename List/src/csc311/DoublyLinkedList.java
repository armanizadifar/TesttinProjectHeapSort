package csc311;
import static org.junit.Assume.assumeNoException;

import java.util.Iterator;

//import csc311.CircularArrayQueue.Node;
import net.datastructures.Position;
import net.datastructures.PositionalList;


public class DoublyLinkedList<E> implements PositionalList<E> {
	/**
	 * This instance variable points to the front of the list
	 */
	private Node<E> head;
	
	/**
	 * This instance variable points to the end of the list
	 */
	private Node<E> tail;
	
	/**
	 * This is the constructor, which initializes the instance
	 * variables to be null
	 */
	public DoublyLinkedList() {
		// TODO Auto-generated constructor stub
		head = null;
		tail = null;
	}

	@Override
	/**
	 * This method returns the number of nodes/positions
	 * in the linked list
	 * @return the number of elements in the list
	 */
	public int size() {
		if(head == null) return 0;
		Node<E> current = head.next;
		int size = 1;
		while(current != head) {
			size++;
			current = current.next;
		}
		return size;
	}

	@Override
	/**
	 * This method returns whether the list contains
	 * no elements
	 * @return whether the list is empty or not
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return head == null;
	}

	@Override
	/**
	 * This method returns the head of the list, which
	 * is the first thing in the list
	 * @return the head of the list
	 */
	public Position<E> first() {
		return head;
	}

	@Override
	/**
	 * This method returns the tail of the list, which
	 * is the last thing in the list
	 * @return the tail of the list
	 */
	public Position<E> last() {
		return tail;
	}

	@Override
	/**
	 * This method returns the node/position
	 * that comes before a given node
	 * @param p the node which we want to see the previous
	 * node of
	 * @return the node that comes before the parameter node
	 * in the list, or null if none come before
	 * @throws IllegalArgumentException if the node
	 * parameter does not appear in the list
	 */
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		if(first() == p) return null;
		Node<E> current = head;
		while(current.next != p) {
			current = current.next;
			if(current == first()) {
				throw new IllegalArgumentException();
			}
		}
		return current;
	}

	@Override
	/**
	 * This method returns the node/position that comes
	 * after the node given as a parameter
	 * @param p the node/position we want to see the successor to
	 * @return the node that comes after the parameter node
	 * in the list, or null if none come after
	 * @throws IllegalArgumentException if the node does not appear in the list
	 */
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		if(last() == p) return null;
		Node<E> current = head;
		while(current != p) {
			current = current.next;
			if(current == first()) {
				throw new IllegalArgumentException();
			}
		}
		return current.next;
	}

	@Override
	/**
	 * This method adds a new element to the very beginning
	 * of the list
	 * @param e the element we want to add
	 * @return the node/position we add in order to
	 * add the element
	 */
	public Position<E> addFirst(E e) {
		// TODO Auto-generated method stub
		if(head == null) {
			head = new Node<E>(e, null, null);
			head.next = head;
			head.prev = head;
			tail = head;
			return head;
		}
		Node<E> add = new Node<E>(e, tail,head);
		head.prev = add;
		tail.next = add;
		head = add;
		return add;
	}

	@Override
	/**
	 * This method adds an element to the very end of the list
	 * @param e the element we want to add
	 * @return the node/position added to the list
	 */
	public Position<E> addLast(E e) {
		// TODO Auto-generated method stub
		if(head == null) {
			head = new Node<E>(e, null, null);
			head.next = head;
			head.prev = head;
			tail = head;
			return head;
		}
		Node<E> add = new Node<E>(e, tail, head);
		head.prev = add;
		tail.next = add;
		tail = add;
		return add;
	}

	@Override
	/**
	 * This method adds an element before another node,
	 * given as a parameter
	 * @param p the node/position we want to add before, and 
	 * e the element we want to add to the list
	 * @return the node/position added
	 * @throws IllegalArgumentException if the node parameter
	 * does not appear in the list
	 */
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> before = (Node<E>) before(p);
		Node<E> after = (Node<E>) p;
		Node<E> add = new Node<E>(e, before, after);
		before.next = add;
		after.prev =add;
		return add;
	}

	@Override
	/**
	 * This method adds an element after anothe node,
	 * given as a parameter
	 * @param p the node/position we want to add something after, and
	 * e the element we want to add
	 * @return the node/position added
	 * @throws IllegalArgumentException if the node parameter
	 * does not appear in the list
	 */
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(e == null || p == null) throw new IllegalArgumentException();
		Node<E> after = (Node<E>) after(p);
		Node<E> before = (Node<E>)p;
		Node<E> add = new Node<E>(e, before, after);
		before.next = add;
		after.prev = add;
		return add;
	}

	@Override
	/**
	 * This method replaces the element of a certain node/position
	 * with a new element, given as a parameter
	 * @param p the node we want to modify, and 
	 * e the element we put in the old element's place
	 * @return the old element that is replaced
	 * @throws IllegalArgumentException if the given position
	 * is not a node
	 */
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		E store = p.getElement();
		if(!(p instanceof Node)) throw new IllegalArgumentException();
		((Node<E>)p).element = e;
		return store;
	}

	@Override
	/**
	 * This method removes a node from the list and returns
	 * the element that was contained within in
	 * @param p the node/position we want to remove
	 * @return the element of the removed node
	 * @throws IllegalArgumentException if the given node
	 * does not appear in the list
	 */
	public E remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		if(p == head) {
			return removeFirst();
		}
		if(p == tail) {
			return removeLast();
		}
		Node<E> current = head;
		while(!current.getElement().equals(p.getElement())) {
			current = current.next;
			if(current == head || current == null) {
				throw new IllegalArgumentException();
			}
		}
		
		Node<E> mod = current;
		Node<E> before = mod.prev;
		Node<E> after = mod.next;
		before.next = after;
		after.prev = before;
		return mod.element;
	}

	@Override
	/**
	 * This method returns an iterator that iterates
	 * through the elements of the list
	 * @return an iterator that contains the elements
	 * contained within the nodes of the list
	 */
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		java.util.ArrayList<E> list = new java.util.ArrayList<>();
		Node<E> current = head;
		while(current != tail) {
			list.add(current.element);
			current = current.next;
		}
		list.add(tail.element);
		return list.iterator();
	}

	@Override
	/**
	 * This method returns an iterable list of the 
	 * nodes/positions of the list, which it does by
	 * making a list and returning its iterator method
	 * @return an iterable version of the list
	 */
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		java.util.ArrayList<Position<E>> list = new java.util.ArrayList<>();
		Node<E> current = head;
		while(current != tail && current != null) {
			list.add(current);
			current = current.next;
		}
		list.add(tail);
		return list;
	}
	
	/**
	 * This method removes the first node of the list
	 * and returns the element that was contained
	 * within it
	 * @return the element from the removed node/position
	 * @throws IllegalArgumentException if there is 
	 * no first node/position to remove
	 */
	public E removeFirst() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		E store = head.getElement();
		Node<E> next = head.next;
		tail.next = next;
		next.prev = tail;
		head = next;
		return store;
	}
	
	/**
	 * This method removes the last node of the list
	 * and returns the element that used to be contained
	 * within it
	 * @return the element from the removed node/position
	 * @throws IllegalArgumentException if there is no
	 * last node to be removed
	 */
	public E removeLast() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		E store = tail.element;
		Node<E> prev = tail.prev;
		prev.next = head;
		head.prev = prev;
		tail = prev;
		return store;
	}

	/**
	 * This private inner class is a Node made to point forwards
	 * and backwards while containing an element, which is needed
	 * for the doubly linked list
	 *
	 * @param <E> the type of the element the list will contain
	 */
	private class Node<E> implements Position<E> {
		/**
		 * This instance variable is the element contained within the node
		 */
		private E element;
		
		/**
		 * This instance variable points to the previous node in the list,
		 * or null if there isn't one
		 */
		public Node<E> prev;
		
		/**
		 * This instance variable points to the next node in the list,
		 * or null if there isn't one
		 */
		public Node<E> next;
		
		public Node(E element, Node<E> prev, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}
		
		@Override
		/**
		 * This method returns the element inside the node. This method is
		 * required in order to implement the Position interface.
		 * @return the element within the node
		 * @throws IllegalArgumentException if the element is null
		 */
		public E getElement() throws IllegalStateException {
			if(element == null) throw new IllegalStateException();
			return element;
		}
		
	}
}