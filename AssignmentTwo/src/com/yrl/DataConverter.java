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
		JSONOutput.toJSON(storeList, "data/Stores.csv");
		ArrayList<Item> itemList = DataLoader.loadItems();
		JSONOutput.toJSON(itemList, "data/Items.csv");
		ArrayList<Person> personList = DataLoader.loadPersons();
		JSONOutput.toJSON(personList, "data/Persons.csv");

	}
}