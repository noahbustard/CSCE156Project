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
		Map<String, ArrayList<String>> saleCodeItemCodeMap = DataLoader.loadSaleItemsMap();
		Map<String, Sale> saleMap = DataLoader.loadSales(personList, storeList, itemInfoMap);
		Map<Sale, ArrayList<Item>> saleItemsMap = DataLoader.loadSaleItems(personList, itemInfoMap, saleMap, saleCodeItemCodeMap);
		Map<Store, List<Sale>> storeMap = Store.createStoreMap(saleMap, storeList);

		
		//TODO: check s002 tax and total and format tax/total
		//158.33
		//42.91
		
		
		System.out.println("+---------------------------------------------------------------------------------------------------------+\n"
				+ "| Summary Report - By Total                                                                               |\n"
				+ "+---------------------------------------------------------------------------------------------------------+\n"
				+ "");
		System.out.println("Invoice #    Store      Customer                       # Items          Subtotal       Tax       Total\n"
				+ "");
		for (Map.Entry<Sale, ArrayList<Item>>  s: saleItemsMap.entrySet()) {
			Double tax = 0.0;
			Double subtotal = 0.0;
			for (Item item : s.getValue()) {
				subtotal += item.getCost();
				tax += item.getTax();
				
			}
			Double total = subtotal + tax;
			System.out.printf("%-12s %-10s %-30s %-16s %-14.2f %-9.2f %.2f\n",s.getKey().getSaleCode(),
					s.getKey().getStore().getStoreCode(), s.getKey().getCustomer().getFullName(), s.getValue().size(), subtotal, tax, total);
		}
		System.out.println("+---------------------------------------------------------------------------------------------------------+\n");
			
		
		System.out.println("+----------------------------------------------------------------+\n"
		+ "| Store Sales Summary Report                                     |\n"
		+ "+----------------------------------------------------------------+\n");
		System.out.println("Store      Manager                        # Sales    Grand Total\n");
		for (Map.Entry<Store, List<Sale>> entry : storeMap.entrySet()) {
			Double storeTotal = 0.0;
			for (Sale sale : entry.getValue()) {
				ArrayList<Item> items = saleItemsMap.get(sale);
				for (Item item : items) {
					storeTotal += item.getCost() + item.getTax();
				}
				
			}
			System.out.printf("%-10s %-30s %-10s %.2f\n", entry.getKey().getStoreCode(), entry.getKey().getManager().getFullName(), entry.getValue().size(), storeTotal);
		}
		System.out.println("+----------------------------------------------------------------+");
		
	}		
}
