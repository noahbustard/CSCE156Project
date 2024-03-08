package com.yrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesReport {


	public static void main(String[] args) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Store> storeList = DataLoader.loadStores(personList);
		ArrayList<Sale> saleList = DataLoader.loadSales(personList, storeList);
		ArrayList<Item> itemList = DataLoader.loadItems();
		Map<String, List<String>> itemInfoMap = Item.createItemInfoMap(itemList);
		ArrayList<Item> saleItemList = DataLoader.loadSaleItems(personList, itemInfoMap, saleList);
		Map<Store, List<Sale>> storeMap = Store.createStoreMap(saleList, storeList);
		System.out.println(storeMap);
		
		
		System.out.println("+----------------------------------------------------------------+\n"
				+ "| Store Sales Summary Report                                     |\n"
				+ "+----------------------------------------------------------------+");
		System.out.println("Store      Manager                        # Sales    Grand Total  ");
		for (Store store : storeMap.keySet()) {
            store.generateStoreReport(storeMap.get(store));
		}
	}
}
