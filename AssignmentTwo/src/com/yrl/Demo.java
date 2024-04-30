package com.yrl;

public class Demo {

	public static void main(String[] args) {

		SortedList<Integer> myList = new SortedList<>();
		myList.add(1);
		myList.add(2);
		myList.add(3);
		myList.add(4);
		myList.add(5);
		
		for (Integer x : myList) {
			System.out.println(x);
		}
	}
}
