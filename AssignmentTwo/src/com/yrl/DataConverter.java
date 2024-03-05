package com.yrl;

import java.util.ArrayList;

/**
 * @author Noah Bustard
 * @author Caden France Date: 2024-02-22
 * 
 */

public class DataConverter {

	public static void main(String args[]) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Store> storeList = DataLoader.loadStores(personList);
		ArrayList<Item> itemList = DataLoader.loadItems(personList);

	}
}