package com.yrl;

import java.util.ArrayList;

/**
 * @author Noah Bustard
 * @author Caden France Date: 2024-02-22
 * 
 */

public class DataConverter {

	public static void main(String args[]) {

		ArrayList<Store> storeList = Store.loadData();
		JSONOutputter.toJSON(storeList, "data/Stores.csv");
		ArrayList<Item> itemList = Item.loadData();
		JSONOutputter.toJSON(itemList, "data/Items.csv");
		ArrayList<Person> personList = Person.loadData();
		JSONOutputter.toJSON(personList, "data/Persons.csv");


	}
}