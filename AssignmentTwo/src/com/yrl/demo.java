package com.yrl;

import java.util.ArrayList;

public class demo {

	public static void main(String[] args) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		DataLoader.loadItems(personList);
	}

}
