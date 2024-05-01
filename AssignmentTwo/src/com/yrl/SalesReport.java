package com.yrl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Class methods generate reports based off different information given. Main
 * method loads data, processes it, and prints it using methods from various
 * other classes.
 * 
 */
public class SalesReport {

	/**
	 * Generates a report that shows the breif details of each sale including the
	 * Store Code, Invoice Number, Customer Name, Number of Items Purchased, Tax,
	 * and Total. Sorts the output in descending order based on the total.
	 * 
	 * @param saleItemsMap
	 */
	public static void generateReportByTotal(Map<Sale, ArrayList<Item>> saleItemsMap) {

		List<List<Object>> saleTotals = new ArrayList<>();

		int totalNumOfItems = 0;
		Double totalTaxGrandTotal = 0.0;
		Double totalGrandTotal = 0.0;

		for (Map.Entry<Sale, ArrayList<Item>> entry : saleItemsMap.entrySet()) {
			Sale sale = entry.getKey();
			ArrayList<Item> itemList = entry.getValue();

			Double tax = 0.0;
			Double subtotal = 0.0;

			for (Item item : itemList) {
				subtotal += Math.round(item.getCost() * 100.0) / 100.0;
				tax += Math.round(item.getTax() * 100.0) / 100.0;
				totalNumOfItems++;
			}

			Double total = subtotal + tax;

			List<Object> saleDetails = new ArrayList<>();
			saleDetails.add(sale.getSaleCode());
			saleDetails.add(sale.getStore().getStoreCode());
			saleDetails.add(sale.getCustomer().getFullName());
			saleDetails.add(itemList.size());
			saleDetails.add(tax);
			saleDetails.add(total);

			saleTotals.add(saleDetails);
		}

		saleTotals.sort((a, b) -> Double.compare((Double) b.get(5), (Double) a.get(5)));

		System.out.println(
				"+---------------------------------------------------------------------------------------------------------+\n"
						+ "| Summary Report - By Total                                                                               |\n"
						+ "+---------------------------------------------------------------------------------------------------------+\n"
						+ "");
		System.out.println("Invoice #  Store      Customer                       Num Items          Tax       Total");

		for (List<Object> s : saleTotals) {
			String saleCode = (String) s.get(0);
			String storeCode = (String) s.get(1);
			String customerName = (String) s.get(2);
			int numOfItems = (int) s.get(3);
			Double tax = (Double) s.get(4);
			Double total = (Double) s.get(5);

			System.out.printf("%-10s %-10s %-30s %-10d$ %10.2f $%10.2f\n", saleCode, storeCode, customerName,
					numOfItems, tax, total);

			totalGrandTotal += total;
			totalTaxGrandTotal += tax;
		}

		System.out.println(
				"+---------------------------------------------------------------------------------------------------------+");
		System.out.printf("%54d         $%11.2f $%10.2f\n\n", totalNumOfItems, totalTaxGrandTotal, totalGrandTotal);
		return;
	}
	/**
	 * Comparator for the list of Store Details. This is neccesary for sorting
	 * the stores in order of manager last name, then first name, and then store sale
	 * total.
	 * 
	 */
	static class storeDetailsComparator implements Comparator<List<Object>> {
		@Override
		public int compare(List<Object> storeOneDetails, List<Object> storeTwoDetails) {
			String storeOneLastName = (String) storeOneDetails.get(2);
			String storeTwoLastName = (String) storeTwoDetails.get(2);

			int lastNameComp = storeOneLastName.compareTo(storeTwoLastName);
			if (lastNameComp != 0) {
				return lastNameComp;
			}

			String storeOneFirstName = (String) storeOneDetails.get(1);
			String storeTwoFirstName = (String) storeTwoDetails.get(1);

			int firstNameComp = storeOneFirstName.compareTo(storeTwoFirstName);
			if (firstNameComp != 0) {
				return firstNameComp;
			}

			Double storeOneTotal = (Double) storeOneDetails.get(4);
			Double storeTwoTotal = (Double) storeTwoDetails.get(4);

			return Double.compare(storeTwoTotal, storeOneTotal);
		}
	}
	/**
	 * Generates a report for each store. Gives the store code,
	 * manager name, number of sales done, and the grand total for
	 * each store. Utilizes a custom comparator to sort the lists in proper
	 * output.
	 * 
	 * 
	 * @param saleItemsMap
	 * @param storeMap
	 */
	public static void generateReportByStore(Map<Sale, ArrayList<Item>> saleItemsMap,
			Map<Store, ArrayList<Sale>> storeMap) {
		List<List<Object>> storeTotals = new ArrayList<>();

		int totalNumOfSales = 0;
		double storeGrandTotal = 0;

		for (Map.Entry<Store, ArrayList<Sale>> entry : storeMap.entrySet()) {
			Store store = entry.getKey();
			ArrayList<Sale> saleList = entry.getValue();

			Double storeTotal = 0.0;
			for (Sale sale : saleList) {

				totalNumOfSales++;
				ArrayList<Item> items = saleItemsMap.get(sale);

				for (Item item : items) {
					Double tax = 0.0;
					Double subtotal = 0.0;
					subtotal += Math.round(item.getCost() * 100.0) / 100.0;
					tax += Math.round(item.getTax() * 100.0) / 100.0;
					storeTotal += subtotal;
					storeTotal += tax;
					storeGrandTotal += subtotal;
					storeGrandTotal += tax;
				}

			}
			List<Object> storeDetails = new ArrayList<>();
			storeDetails.add(store.getStoreCode());
			storeDetails.add(store.getManager().getFirstName());
			storeDetails.add(store.getManager().getLastName());
			storeDetails.add(saleList.size());
			storeDetails.add(storeTotal);

			storeTotals.add(storeDetails);

		}

		Collections.sort(storeTotals, new storeDetailsComparator());

		System.out.println("+----------------------------------------------------------------+\n"
				+ "| Store Sales Summary Report                                     |\n"
				+ "+----------------------------------------------------------------+\n");
		System.out.println("Store      Manager                        # Sales    Grand Total\n");

		for (List<Object> s : storeTotals) {
			String storeCode = (String) s.get(0);
			String managerName = String.format("%s, %s", s.get(2), s.get(1));
			int numOfSales = (int) s.get(3);
			double storeTotal = (Double) s.get(4);

			System.out.printf("%-10s %-30s %-10s $%10.2f\n", storeCode, managerName, numOfSales, storeTotal);
		}

		System.out.println("+----------------------------------------------------------------+");
		System.out.printf("%43d          $%10.2f\n\n", totalNumOfSales, storeGrandTotal);
		return;
	}

	public static void generateReportByItem(Map<Sale, ArrayList<Item>> saleItemsMap) {
		for (Map.Entry<Sale, ArrayList<Item>> s : saleItemsMap.entrySet()) {
			System.out.println("Sale     #" + s.getKey().getSaleCode());
			System.out.println("Store    #" + s.getKey().getStore().getStoreCode());
			System.out.println("Date      " + s.getKey().getDate());
			System.out.println("Customer:");
			System.out.println(s.getKey().getCustomer().toString() + "\n");
			System.out.println("Sales Person:");
			System.out.println(s.getKey().getSalesperson().toString() + "\n");
			System.out.println("Items (" + s.getValue().size()
					+ ")                                                            Tax       Total");
			System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-                          -=-=-=-=-=- -=-=-=-=-=-");
			Double saleTotal = 0.0;
			Double saleTaxTotal = 0.0;
			for (Item item : s.getValue()) {
				Double tax = 0.0;
				Double subtotal = 0.0;
				subtotal += Math.round(item.getCost() * 100.0) / 100.0;
				tax += Math.round(item.getTax() * 100.0) / 100.0;
				saleTotal += subtotal;
				saleTaxTotal += tax;
				System.out.println(item);
				System.out.printf("                                                             " + "$%10.2f $%10.2f\n",
						tax, subtotal);
			}
			System.out.println("                                                             -=-=-=-=-=- -=-=-=-=-=-");
			System.out.printf("                                                   Subtotals" + " $%10.2f $%10.2f\n",
					saleTaxTotal, saleTotal);
			System.out.printf("                                                 " + "Grand Total             $%10.2f\n",
					saleTaxTotal + saleTotal);
		}
		return;
	}
	
	public static void main(String[] args) {

		DataLoaderFromDatabase db = new DataLoaderFromDatabase();

		
		ArrayList<Person> personList = db.loadPersons();
		ArrayList<Store> storeList = db.loadStores(personList);
		ArrayList<Item> itemList = db.loadItems();

		Map<String, Item> itemInfoMap = Item.createItemInfoMap(itemList);
		Map<String, ArrayList<String>> saleCodeItemCodeMap = db.loadSaleItemsMap();
		Map<String, Sale> saleMap = db.loadSales(personList, storeList, itemInfoMap);
		Map<Sale, ArrayList<Item>> saleItemsMap = db.loadSaleItems(personList, itemInfoMap, saleMap,
				saleCodeItemCodeMap);
		Map<Store, ArrayList<Sale>> storeMap = Store.createStoreMap(saleMap, storeList);

		
		Comparator<Sale> com = new SortByCustomer();
		SortedList<Sale> myList = new SortedList<>(com);
		myList.add(saleMap.get("s002"));
		myList.add(saleMap.get("s003"));
		myList.add(saleMap.get("s005"));
		myList.add(saleMap.get("s004"));
		
		//SalesReport.generateReportByTotal(saleItemsMap);
		//SalesReport.generateReportByStore(saleItemsMap, storeMap);
		//SalesReport.generateReportByItem(saleItemsMap);
		
		
		
		
	}
}
