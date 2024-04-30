package com.yrl;
/**
 * A node used in the {@link SortedList} class to hold an object in a linked list.
 * 
 * @param <T>
 */
public class Node<T> {
	
	private T element;
	private Node<T> next;
	
	public Node(T element) {
		this.element = element;
		this.next = null;
	}
	
	public T getElement() {
		return this.element;
	}
	
	public Node<T> getNext() {
		return this.next;
	}
	
	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public String toString() {
		return this.element.toString();
	}
}
