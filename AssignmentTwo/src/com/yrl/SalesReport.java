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

		
		
		System.out.println("+---------------------------------------------------------------------------------------------------------+\n"
				+ "| Summary Report - By Total                                                                               |\n"
				+ "+---------------------------------------------------------------------------------------------------------+\n"
				+ "");
		System.out.println("Invoice #  Store      Customer                       Num Items          Tax       Total");
		int numOfItems = 0;
		Double totalTaxGrandTotal = 0.0;
		Double totalGrandTotal = 0.0;
		for (Map.Entry<Sale, ArrayList<Item>>  s: saleItemsMap.entrySet()) {
			Double tax = 0.0;
			Double subtotal = 0.0;
			for (Item item : s.getValue()) {
				subtotal += Math.round(item.getCost()*100.0) / 100.0;
				tax += Math.round(item.getTax()*100.0) / 100.0;
				numOfItems++;
			}
			Double total = subtotal + tax;
			totalGrandTotal += total;
			totalTaxGrandTotal += tax;
			System.out.printf("%-10s %-10s %-30s %-10d$ %10.2f $%10.2f\n",s.getKey().getSaleCode(),
					s.getKey().getStore().getStoreCode(), s.getKey().getCustomer().getFullName(), s.getValue().size(), tax, total);
		}
		System.out.println("+---------------------------------------------------------------------------------------------------------+");
		System.out.printf("%54d         $%11.2f $%10.2f\n\n", numOfItems,totalTaxGrandTotal,totalGrandTotal);
			
		
		System.out.println("+----------------------------------------------------------------+\n"
		+ "| Store Sales Summary Report                                     |\n"
		+ "+----------------------------------------------------------------+\n");
		System.out.println("Store      Manager                        # Sales    Grand Total\n");
		int numOfSales = 0;
		double storeGrandTotal = 0;
		for (Map.Entry<Store, List<Sale>> entry : storeMap.entrySet()) {
			Double storeTotal = 0.0;
			for (Sale sale : entry.getValue()) {
				numOfSales++;
				ArrayList<Item> items = saleItemsMap.get(sale);
				for (Item item : items) {
					Double tax = 0.0;
					Double subtotal = 0.0;
					subtotal += Math.round(item.getCost()*100.0) / 100.0;
					tax += Math.round(item.getTax()*100.0) / 100.0;
					storeTotal += subtotal;
					storeGrandTotal += subtotal;
					storeGrandTotal += tax;
				}
				
			}
		System.out.printf("%-10s %-30s %-10s $%10.2f\n", entry.getKey().getStoreCode(), entry.getKey().getManager().getFullName(), entry.getValue().size(), storeTotal);
		}
		System.out.println("+----------------------------------------------------------------+");
		System.out.printf("%43d          $%10.2f\n\n", numOfSales, storeGrandTotal);
		
		for (Map.Entry<Sale, ArrayList<Item>>  s: saleItemsMap.entrySet()) {
			System.out.println("Sale     #" + s.getKey().getSaleCode());
			System.out.println("Store    #" + s.getKey().getStore().getStoreCode());
			System.out.println("Date      " + s.getKey().getDate());
			System.out.println("Customer:");
			System.out.println(s.getKey().getCustomer().toString() + "\n");
			System.out.println("Items (" + s.getValue().size() + ")                                                            Tax       Total");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                          -=-=-=-=-=- -=-=-=-=-=-");
			Double saleTotal = 0.0;
			Double saleTaxTotal = 0.0;
			for (Item item : s.getValue()) {
				Double tax = 0.0;
				Double subtotal = 0.0;
				subtotal += Math.round(item.getCost()*100.0) / 100.0;
				tax += Math.round(item.getTax()*100.0) / 100.0;
				saleTotal += subtotal;
				saleTaxTotal += tax;
				System.out.println(item);
				System.out.printf("                                                             "
						+ "$%10.2f $%10.2f\n", tax, subtotal);
			}
			System.out.println("                                                             -=-=-=-=-=- -=-=-=-=-=-");
			System.out.printf("                                                   Subtotals"
					+ " $%10.2f $%10.2f\n", saleTaxTotal, saleTotal);
			System.out.printf("                                                 "
					+ "Grand Total             $%10.2f\n", saleTaxTotal+saleTotal);
			

			
			

		}
	}		
}
