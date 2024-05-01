package com.yrl;

import java.util.Comparator;
import java.util.Iterator;

public class SortedList<T> implements Iterable<T>{

	private Node<T> head;
	private int size;
	private Comparator<T> com;
	
	public SortedList(Comparator<T> com) {
		this.head = null;
		this.size = 0;
		this.com = com;
	}

	public void add(T element) {
		Node<T> newNode = new Node<>(element);
		if (this.isEmpty()) {
			this.head = newNode;
			this.size++;
			return;
		}
		
		Node<T> curr = this.head;
		Node<T> prev = null;
		while (this.com.compare(newNode.getElement(), curr.getElement()) > 0) {
			prev = curr;
			curr = curr.getNext();
		}
		if (prev == null) {
			newNode.setNext(this.head);
			this.head = newNode;
		} else {
			prev.setNext(newNode);

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
	
	public boolean boundsCheck(int index) {
		if (index >= this.size | index < 0) {
			return false;
		}
		return true;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public Node<T> getHead() {
		return this.head;
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

		return new ListIterator<T>(this);
	}
}

