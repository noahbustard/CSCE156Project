package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalesReport {
	public static void getTotalReport(ArrayList<Sale> saleList, ArrayList<Store> storeList, ArrayList<Item> itemList,
			ArrayList<Person> personList, ArrayList<SaleItem> saleItemList) {
	}

	public static void getStoreReport() {

	}

	public static void getSalesReport() {

	}

	public static void main(String[] args) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Store> storeList = DataLoader.loadStores(personList);
		ArrayList<Item> itemList = DataLoader.loadItems(personList);
		ArrayList<Sale> saleList = DataLoader.loadSales(personList, storeList);
		
		Map<Store, List<Sale>> storeMap = new HashMap<>();
		for (Sale sale : saleList) {
			Store store = sale.getStore();
			if (!storeMap.containsKey(store)) {
				storeMap.put(store, new ArrayList<Sale>());
			}
			storeMap.get(store).add(sale);
		}
		
	}
}
