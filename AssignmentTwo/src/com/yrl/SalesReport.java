package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
	public static void getTotalReport() {
	}

	public static void getStoreReport() {

	}

	public static void getSalesReport() {

	}

	public static void main(String[] args) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Item> itemList = DataLoader.loadItems();
		Map<String, List<String>> itemInfoMap = Item.createItemInfoMap(itemList);
		ArrayList<Store> storeList = DataLoader.loadStores(personList);
		ArrayList<Item> saleItemList = DataLoader.loadSaleItems(personList, itemInfoMap);
		ArrayList<Sale> saleList = DataLoader.loadSales(personList, storeList);
		Map<Store, List<Sale>> storeMap = Store.createStoreMap(saleList);
	}
}
