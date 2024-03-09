package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Authors
 * @noahbustard
 * @cadenfrance
 * 
 * 2024-03-08
 * 
 * 
 * Class loads data from CSV files into usable lists
 */
public class DataLoader {
	/**
	 * Loads items into itemList. Doesn't include specifics
	 * like saleCode and is more of an inventory list. Can
	 * load Purchases, Services, Data Plans, and Voice Plans.
	 * @return
	 */
	public static ArrayList<Item> loadItems() {

		ArrayList<Item> itemList = new ArrayList<Item>();

		String file = "data/Items.csv";

		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String tokens[] = line.split(",");


			if (tokens.length == 4) {
				String itemCode = tokens[0];
				String name = tokens[2];
				Double cost = Double.parseDouble(tokens[3]);
				if (tokens[1].charAt(0) == 'P') {
					itemList.add(new Purchase(name, itemCode, cost));

				} else if (tokens[1].charAt(0) == 'S') {
					itemList.add(new Service(name, itemCode, cost));

				} else if (tokens[1].charAt(0) == 'D') {
					itemList.add(new DataPlan(name, itemCode, cost));

				} else if (tokens[1].charAt(0) == 'V') {
					itemList.add(new VoicePlan(name, itemCode, cost));
				}
			}
		}
		s.close();
		return itemList;
	}
	
	
	public static Map<String, String> loadSaleItemsMap() {
		Map<String, String> saleItemsMap = new HashMap<String, String>();
		
		String file = "data/SaleItems.csv";
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String tokens[] = line.split(",");
			String saleCode = tokens[0];
			String itemCode = tokens[1];
			saleItemsMap.put(saleCode, itemCode);
		}
		return saleItemsMap;
	}
	/**
	 * Loads saleItems from CSV file. saleItems are items that were
	 * involved in a sale and have more specific information like
	 * saleCode or specifics on quantity like gbsPurchased on a Data Plan.
	 * 
	 * @param personList
	 * @param itemInfoMap
	 * @return
	 */
	public static ArrayList<Item> loadSaleItems(ArrayList<Person> personList,
			Map<String, Item> itemInfoMap, ArrayList<Sale> saleList) {
		String file = "data/SaleItems.csv";

		ArrayList<Item> saleItemList = new ArrayList<>();
		
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
		
		
		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String tokens[] = line.split(",");
			
			String key = tokens[1];
			String name = itemInfoMap.get(key).getName();
			Double cost = itemInfoMap.get(key).getCost();
			Sale sale = null;
			for (Sale temp: saleList) {
				if (tokens[0].equals(temp.getSaleCode())) {
					sale = new Sale(temp.getSaleCode(), temp.getStore(),
							temp.getCustomer(), temp.getSalesperson(), temp.getDate(), itemInfoMap.get(key));
				}
			}
			
			if (tokens.length >= 2) {
				
				if (tokens[1].charAt(0) == 'e') {
					
					if (tokens.length == 2) {
						saleItemList.add(new Purchase(sale,tokens[1],name,cost));
						
					} else if (tokens.length == 4) {
						LocalDate startDate = LocalDate.parse(tokens[2]);
						LocalDate endDate = LocalDate.parse(tokens[3]);
						saleItemList.add(new Lease(sale,tokens[1],name,cost,startDate,endDate));		
					}
				} else if (tokens[1].charAt(0) == 's') {
					Double hoursBilled = Double.parseDouble(tokens[2]);
					
					for (Person p : personList) {
						
						if (p.getUuid().equals(tokens[3])) {
							saleItemList.add(new Service(sale,tokens[1],name,cost,hoursBilled, p));
						}
					}						
				} else if (tokens[1].charAt(0) == 'p') {
					
					if (tokens.length == 3) {
						Double gbsPurcahsed = Double.parseDouble(tokens[2]);
						saleItemList.add(new DataPlan(sale,tokens[1],name,cost,gbsPurcahsed));
						
					} else if (tokens.length == 4) {
						Integer daysPurchased = Integer.parseInt(tokens[3]);
						saleItemList.add(new VoicePlan(sale,tokens[1],name,cost,tokens[2],daysPurchased));
					}
				}
			}
		}

		s.close();
		return saleItemList;
	}
	/**
	 *  Loads store data into storeList. Has a manager person
	 *  constructed into store but can function if no manager UUID
	 *  matches any person's UUID.
	 * @param personList
	 * @return
	 */
	public static ArrayList<Store> loadStores(ArrayList<Person> personList) {
		ArrayList<Store> storeList = new ArrayList<Store>();
		String file = "data/Stores.csv";
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			
			String line = s.nextLine();
			String tokens[] = line.split(",");

			if (tokens.length == 6) {
				Person manager = null;
				Address a = new Address(tokens[2], tokens[3], tokens[4], tokens[5]);
				
				for (Person p : personList) {
					
					if (tokens[1].equals(p.getUuid())) {
						manager = p;
						break;
					}
				}
				if (manager != null) {
					storeList.add(new Store(tokens[0], manager, a));
					
				} else {
					storeList.add(new Store(tokens[0], a));
				}
			}
		}
		s.close();
		return storeList;
	}

	/**
	 * Loads in People and creates a list of people to be used to map
	 * future objects as Customers, Salespeople, Servicemen etc.
	 */
	public static ArrayList<Person> loadPersons() {
		ArrayList<Person> personList = new ArrayList<Person>();
		String file = "data/Persons.csv";
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			
			String line = s.nextLine();
			String tokens[] = line.split(",");

			if (tokens.length == 7) {
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				personList.add(new Person(tokens[0], tokens[1], tokens[2], a));

			} else if (tokens.length > 7) {
				ArrayList<String> emails = new ArrayList<String>();
				
				for (int i = 7; i < tokens.length; i++) {
					emails.add(tokens[i]);
				}
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				personList.add(new Person(tokens[0], tokens[1], tokens[2], a, emails));
			}

		}
		s.close();
		return personList;
	}
	/**
	 * Loads Sale data into Sale list. Has Customer and salesperson(manager)
	 * constructed into each iteration.
	 * @param personList
	 * @param storeList
	 * @return
	 */
	public static ArrayList<Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList, Map<String, Item> itemInfoMap, Map<String, String> saleItemMap) {
		ArrayList<Sale> saleList = new ArrayList<Sale>();

		String file = "data/Sales.csv";
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			
			String line = s.nextLine();
			String tokens[] = line.split(",");

			if (tokens.length == 5) {
				Person customer = null;
				Person salesperson = null;
				LocalDate date = LocalDate.parse(tokens[4]);
				Store store = null;
				Item item = null;
				for (Person c : personList) {
					
					if (tokens[2].equals(c.getUuid())) {
						customer = c;
						break;
					}
				}
				for (Person sal : personList) {
					
					if (tokens[3].equals(sal.getUuid())) {
						salesperson = sal;
						break;
					}
				}
				for (Store str : storeList) {
					
					if (tokens[1].equals(str.getStoreCode())) {
						store = str;
						break;
					}
				}
				for (Map.Entry<String, String> entry : saleItemMap.entrySet()) {
					if (tokens[0].equals(entry.getKey())) {
						item = itemInfoMap.get(entry.getKey());
					}
				}
				
				saleList.add(new Sale(tokens[0], store, customer, salesperson, date, item));
			}
		}
		s.close();

		return saleList;
	}
}