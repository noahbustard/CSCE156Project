package com.yrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SalesReport {


	public static void main(String[] args) {
		ArrayList<Person> personList = DataLoader.loadPersons();
		ArrayList<Store> storeList = DataLoader.loadStores(personList);
		ArrayList<Item> itemList = DataLoader.loadItems();
		Map<String, Item> itemInfoMap = Item.createItemInfoMap(itemList);
		Map<String, String> saleItemMap = DataLoader.loadSaleItemsMap();
		ArrayList<Sale> saleList = DataLoader.loadSales(personList, storeList, itemInfoMap, saleItemMap);
		ArrayList<Item> saleItemList = DataLoader.loadSaleItems(personList, itemInfoMap, saleList);
		//System.out.println(saleItemList);
		Map<Store, List<Sale>> storeMap = Store.createStoreMap(saleList, storeList);
		
		

		System.out.println("+----------------------------------------------------------------------------------------+\n"
				+ "| Summary Report - By Total                                                              |\n"
				+ "+----------------------------------------------------------------------------------------+\n"
				+ "");
		System.out.println("Invoice #  Store      Customer                       Num Items          Tax       Total\n"
				+ "");
		for (Sale s: saleList) {
			System.out.printf("%-10s %-10s %-10s\n",s.toString(),
					s.getStore().getStoreCode(), s.getCustomer().printName());
		}
		System.out.println("+----------------------------------------------------------------------------------------+");
			
		
		System.out.println("+----------------------------------------------------------------+\n"
		+ "| Store Sales Summary Report                                     |\n"
		+ "+----------------------------------------------------------------+");
		System.out.println("Store      Manager                        # Sales    Grand Total  ");
		for (Store store : storeMap.keySet()) {
			System.out.printf("%-10s %-10s\n", store.getStoreCode(), store.getManager().printName());
		}
		System.out.println("+----------------------------------------------------------------+");
					

		for (Sale s: saleList) {
			s.printReport();
		}
		
	}		
}
