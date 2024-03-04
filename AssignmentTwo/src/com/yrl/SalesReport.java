package com.yrl;

import java.util.ArrayList;

public class SalesReport {
	public static void getTotalReport() {
		return;
	}

	public static void getStoreReport() {

	}

	public static void getSalesReport() {

	}

	public static void main(String[] args) {
		ArrayList<Store> storeList = DataLoader.loadStores();
		ArrayList<Item> itemList = DataLoader.loadItems();
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Sale> saleList = DataLoader.loadSales();
		ArrayList<SaleItem> saleItemList = DataLoader.loadSaleItems();
	}
}

