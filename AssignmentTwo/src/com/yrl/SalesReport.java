package com.yrl;

import java.util.ArrayList;

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
		
	}
}
