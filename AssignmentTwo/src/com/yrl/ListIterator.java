package com.yrl;

import java.util.Iterator;

public class ListIterator<T> implements Iterator<T>{
	
	Node<T> curr;
	
	public ListIterator(SortedList<T> list) {
	
		curr = list.getHead();	
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
