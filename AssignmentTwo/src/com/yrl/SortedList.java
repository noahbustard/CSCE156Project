package com.yrl;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Implementation of a sorted linked list. A comparator is required when creating a new instance.
 * When objects are added they are put where they belong based on the comparator used. 
 * The list implements iterable so that it may be used in enhanced for loops.
 * @param <T>
 */
public class SortedList<T> implements Iterable<T> {

	private Node<T> head;
	private int size;
	private Comparator<T> com;

	public SortedList(Comparator<T> com) {
		this.head = null;
		this.size = 0;
		this.com = com;
	}

	public class ListIterator implements Iterator<T>{
		
		Node<T> curr;
		
		public ListIterator(SortedList<T> list) {
		
			curr = list.head;	
		}
		
		public boolean hasNext() {
		
			return curr != null;
		}
		
		public T next() {
		
			T element = curr.getElement();
			curr = curr.getNext();
			return element;
		}	
	}
	
	public void add(T element) {
		Node<T> newNode = new Node<>(element);
		if (this.isEmpty()) {
			this.head = newNode;
			this.size++;
			return;
		}

		 Node<T> prev = null;
		 Node<T> curr = this.head;
		    
		 while (curr != null && com.compare(element, curr.getElement()) > 0) {
			 prev = curr;
			 curr = curr.getNext();
		 }
		    
		 if (prev == null) {
			 newNode.setNext(this.head);
			 this.head = newNode;
		 } else {
			 prev.setNext(newNode);
			 newNode.setNext(curr);
		 }
		    
		    this.size++;
		}

	public Node<T> remove(int index) {
		if (this.boundsCheck(index) == false) {
			return null;
		}
		Node<T> prev = null;
		Node<T> curr = null;
		if (index == 0) {
			curr = this.head;
		} else {
			prev = this.getNode(index - 1);
			curr = prev.getNext();
		}
		if (index == 0) {
			this.head = curr.getNext();
		} else {
			prev.setNext(curr.getNext());
		}
		curr.setNext(null);
		this.size--;
		return curr;
	}

	private Node<T> getNode(int index) {
		if (this.boundsCheck(index) == false) {
			return null;
		}
		Node<T> curr = this.head;
		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}
		return curr;
	}

	public boolean isEmpty() {
		return (this.size == 0);
	}

	private boolean boundsCheck(int index) {
		if (index >= this.size | index < 0) {
			return false;
		}
		return true;
	}

	public int getSize() {
		return this.size;
	}

	public String toString() {
		if (this.isEmpty()) {
			return "[empty]";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("[ ");
		Node<T> curr = this.head;
		while (curr.getNext() != null) {
			sb.append(curr.getElement());
			sb.append(", ");
			curr = curr.getNext();
		}
		sb.append(curr.getElement());
		sb.append(" ]");

		return sb.toString();
	}

	@Override
	public Iterator<T> iterator() {

		return new ListIterator(this);
	}
}
