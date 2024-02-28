package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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
				Item item = new Item(tokens[0], tokens[1], tokens[2], tokens[3]);
				itemList.add(item);
			}
		}
		s.close();
		return itemList;
	}

	public static ArrayList<Store> loadStores() {
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
				Address a = new Address(tokens[2], tokens[3], tokens[4], tokens[5]);
				Store store = new Store(tokens[0], tokens[1], a);
				storeList.add(store);
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
				new Person(tokens[0], tokens[1], tokens[2], a);

			} else if (tokens.length > 7) {
				ArrayList<String> emails = new ArrayList<String>();
				for (int i = 7; i < tokens.length; i++)
					emails.add(tokens[i]);
				Address a = new Address(tokens[3], tokens[4], tokens[5], tokens[6]);
				Person person = new Person(tokens[0], tokens[1], tokens[2], a, emails);
				personList.add(person);
			}

		}
		s.close();
		return personList;
	}

	public static ArrayList<Sale> loadSales() {
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
				Sale sale = new Sale(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
				saleList.add(sale);
			}
		}
		s.close();
		return saleList;
	}

	public static ArrayList<SaleItem> loadSaleItems() {
		ArrayList<SaleItem> saleItemList = new ArrayList<SaleItem>();
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

			if (tokens[1].charAt(0) == 'e' && tokens.length == 2) {
				Product product = new Product(tokens[0], tokens[1]);
				saleItemList.add(product);
			}
			else if (tokens[1].charAt(0) == 'e' && tokens.length == 4) {
				Lease lease = new Lease(tokens[0], tokens[1], tokens[2], tokens[3]);
				saleItemList.add(lease);
			}
			else if (tokens[1].charAt(0) == 's') {
				Service service = new Service(tokens[0], tokens[1], tokens[2], tokens[3]);
				saleItemList.add(service);
			}
			else if (tokens[1].charAt(0) == 'p' &&  tokens.length == 3) {
				DataPlan dataPlan = new DataPlan(tokens[0], tokens[1], tokens[2]);
				saleItemList.add(dataPlan);
			}
			else if (tokens[1].charAt(0) == 'p' && tokens.length == 4) {
				VoicePlan voicePlan = new VoicePlan(tokens[0], tokens[1], tokens[2],tokens[3]);
				saleItemList.add(voicePlan);
			}
		}
		s.close();
		return saleItemList;
	}

}
