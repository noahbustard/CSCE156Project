package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DataLoader {

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
				Double cost = Double.parseDouble(tokens[3]);
				if (tokens[1] == "P") {
					itemList.add(new Purchase(tokens[0], tokens[2], cost));

				} else if (tokens[1] == "S") {
					itemList.add(new Service(tokens[0], tokens[2], cost));

				} else if (tokens[1] == "D") {
					itemList.add(new DataPlan(tokens[0], tokens[2], cost));

				} else if (tokens[1] == "V") {
					itemList.add(new VoicePlan(tokens[0], tokens[2], cost));
				}
			}
		}
		s.close();

		return itemList;
	}

	public static ArrayList<Item> loadSaleItems(ArrayList<Person> personList,
			Map<String, List<String>> itemInfoMap) {
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

			List<String> itemInfo = itemInfoMap.get(tokens[1]);
			String name = itemInfo.get(1);
			Double cost = Double.parseDouble(itemInfo.get(2));
			
			if (tokens.length >= 2) {
				if (tokens[1].charAt(0) == 'e') {
					if (tokens.length == 2) {
						saleItemList.add(new Purchase(tokens[0],tokens[1],name,cost));
					} else if (tokens.length == 4) {
						LocalDate startDate = LocalDate.parse(tokens[2]);
						LocalDate endDate = LocalDate.parse(tokens[3]);
						saleItemList.add(new Lease(tokens[0],tokens[1],name,cost,startDate,endDate));		
					}
				} else if (tokens[1].charAt(0) == 's') {
					Double hoursBilled = Double.parseDouble(tokens[2]);
					for (Person p : personList) {
						if (p.getUuid().equals(tokens[3])) {
							saleItemList.add(new Service(tokens[0],tokens[1],name,cost,hoursBilled, p));
						}
					}					

					
				} else if (tokens[1].charAt(0) == 'p') {
					if (tokens.length == 3) {
						Double gbsPurcahsed = Double.parseDouble(tokens[2]);
						saleItemList.add(new DataPlan(tokens[0],tokens[1],name,cost,gbsPurcahsed));
					} else if (tokens.length == 4) {
						Integer daysPurchased = Integer.parseInt(tokens[3]);
						saleItemList.add(new VoicePlan(tokens[0],tokens[1],name,cost,tokens[2],daysPurchased));
					}
				}
				

				

			}
		}

		s.close();
		return saleItemList;
	}

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
	 * Takes in data from file and processes it by putting it into a list of items
	 * 
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
				for (int i = 7; i < tokens.length; i++)
					emails.add(tokens[i]);
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				personList.add(new Person(tokens[0], tokens[1], tokens[2], a, emails));
			}

		}
		s.close();
		return personList;
	}

	public static ArrayList<Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList) {
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
				for (Person c : personList) {
					if (tokens[1].equals(c.getUuid())) {
						customer = c;
						break;
					}
				}
				for (Person sal : personList) {
					if (tokens[1].equals(sal.getUuid())) {
						salesperson = sal;
						break;
					}
				}
				for (Store str : storeList) {
					if (tokens[1].equals(str.getCode())) {
						store = str;
						break;
					}
				}
				saleList.add(new Sale(tokens[0], store, customer, salesperson, date));

			}
		}
		s.close();

		return saleList;
	}
}