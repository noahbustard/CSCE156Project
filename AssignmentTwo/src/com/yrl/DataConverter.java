package com.yrl;

import java.util.ArrayList;

/**
 * @author Noah Bustard
 * @author Caden France Date: 2024-02-22
 * 
 */

public class DataConverter {

	public static void main(String args[]) {

		ArrayList<Store> storeList = DataLoader.loadStores();
		JSONOutputter.toJSON(storeList, "data/Stores.csv");
		ArrayList<Item> itemList = DataLoader.loadItems();
		JSONOutputter.toJSON(itemList, "data/Items.csv");
		ArrayList<Person> personList = DataLoader.loadPersons();
		JSONOutputter.toJSON(personList, "data/Persons.csv");


	}
}