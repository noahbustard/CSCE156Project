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
 * 
 * @noahbustard
 * @cadenfrance
 * 
 *              2024-03-08
 * 
 * 
 *              Class loads data from CSV files into usable lists
 */
public class DataLoaderFromCSV implements DataLoader {
	/**
	 * Loads items into itemList. Doesn't include specifics like saleCode and is
	 * more of an inventory list. Can load Purchases, Services, Data Plans, and
	 * Voice Plans.
	 * 
	 * @return
	 */
	@Override
	public ArrayList<Item> loadItems() {

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
					itemList.add(new Purchase(itemCode, name, cost));

				} else if (tokens[1].charAt(0) == 'S') {
					itemList.add(new Service(itemCode, name, cost));

				} else if (tokens[1].charAt(0) == 'D') {
					itemList.add(new DataPlan(itemCode, name, cost));

				} else if (tokens[1].charAt(0) == 'V') {
					itemList.add(new VoicePlan(itemCode, name, cost));
				}
			}
		}
		s.close();
		return itemList;
	}

	/**
	 * Loads a map of each sale code mapped to the item codes
	 * of all the sale items in that sale.
	 */
	@Override
	public Map<String, ArrayList<String>> loadSaleItemsMap() {
		Map<String, ArrayList<String>> saleItemsMap = new HashMap<String, ArrayList<String>>();

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
			if (tokens.length < 2) {
				continue;
			}
			String saleCode = tokens[0];
			String itemCode = tokens[1];
			if (!saleItemsMap.containsKey(tokens[0])) {
				saleItemsMap.put(saleCode, new ArrayList<String>());
			}
			saleItemsMap.get(saleCode).add(itemCode);
		}
		return saleItemsMap;
	}

	/**
	 * Loads saleItems from CSV file. saleItems are items that were involved in a
	 * sale and have more specific information like saleCode or specifics on
	 * quantity like gbsPurchased on a Data Plan.
	 * 
	 * @param personList
	 * @param itemInfoMap
	 * @return
	 */
	@Override
	public Map<Sale, ArrayList<Item>> loadSaleItems(ArrayList<Person> personList, Map<String, Item> itemInfoMap,
			Map<String, Sale> saleMap, Map<String, ArrayList<String>> saleCodeItemCodeMap) {
		String file = "data/SaleItems.csv";

		Map<Sale, ArrayList<Item>> saleItemsMap = new HashMap<>();
		for (Map.Entry<String, Sale> entry : saleMap.entrySet()) {
			saleItemsMap.put(entry.getValue(), new ArrayList<Item>());
		}
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
			if (tokens.length < 2) {
				continue;
			}
			String saleCode = tokens[0];
			String itemCode = tokens[1];
			Sale sale = saleMap.get(saleCode);
			Item item = itemInfoMap.get(itemCode);
			String name = itemInfoMap.get(itemCode).getName();
			Double baseCost = itemInfoMap.get(itemCode).getBaseCost();

			if (item.getType().equals("Purchase") && tokens.length == 2) {
				saleItemsMap.get(sale).add(new Purchase(itemCode, name, baseCost));
			} else if (item.getType().equals("Purchase") && tokens.length == 4) {
				LocalDate startDate = LocalDate.parse(tokens[2]);
				LocalDate endDate = LocalDate.parse(tokens[3]);
				saleItemsMap.get(sale).add(new Lease(itemCode, name, baseCost, startDate, endDate));
			} else if (item.getType().equals("Data Plan")) {
				Double gbPurchased = Double.parseDouble(tokens[2]);
				saleItemsMap.get(sale).add(new DataPlan(itemCode, name, baseCost, gbPurchased));
			} else if (item.getType().equals("Voice Plan")) {
				String phoneNumber = tokens[2];
				int daysPurchased = Integer.parseInt(tokens[3]);
				saleItemsMap.get(sale).add(new VoicePlan(itemCode, name, baseCost, phoneNumber, daysPurchased));
			} else if (item.getType().equals("Service")) {
				Double hoursBilled = Double.parseDouble(tokens[2]);
				Person servicePerson = null;
				for (Person person : personList) {
					if (tokens[3].equals(person.getUuid())) {
						servicePerson = person;
					}
				}
				saleItemsMap.get(sale).add(new Service(itemCode, name, baseCost, hoursBilled, servicePerson));
			}

		}
		s.close();
		return saleItemsMap;
	}

	/**
	 * Loads store data into storeList. Has a manager person constructed into store
	 * but can function if no manager UUID matches any person's UUID.
	 * 
	 * @param personList
	 * @return
	 */
	@Override
	public ArrayList<Store> loadStores(ArrayList<Person> personList) {
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
	 * Loads in People and creates a list of people to be used to map future objects
	 * as Customers, Salespeople, Servicemen etc.
	 */
	@Override
	public ArrayList<Person> loadPersons() {
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
	 * 
	 * @param personList
	 * @param storeList
	 * @return
	 */
	@Override
	public Map<String, Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList,
			Map<String, Item> itemInfoMap) {
		Map<String, Sale> saleMap = new HashMap<String, Sale>();

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
				saleMap.put(tokens[0], new Sale(tokens[0], store, customer, salesperson, date));

			}
		}
		s.close();

		return saleMap;
	}
}