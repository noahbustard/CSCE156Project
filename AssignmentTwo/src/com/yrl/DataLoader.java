package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataLoader {

	@SuppressWarnings("unlikely-arg-type")
	public static ArrayList<Item> loadItems(ArrayList<Person> personList) {
		String file = "data/SaleItems.csv";
		
		ArrayList<Item> itemList = new ArrayList<Item>();
		List<String> saleItemList = new ArrayList<>();
		Scanner s = null;

		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			saleItemList.add(line);
		}
		s.close();
			
		s = null;
		file = "data/Items.csv";
		
		try {
			s = new Scanner(new File(file));
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}

		s.nextLine();
		while (s.hasNextLine()) {
			String line = s.nextLine();
			String itemTokens[] = line.split(",");

			if (itemTokens.length == 4) {
				
				if (itemTokens[1].equals("P")) {
					for (int i = 0; i < saleItemList.size(); i++) {
						String saleItemTokens[] = saleItemList.get(i).split(",");
						if (saleItemTokens[1].equals(itemTokens[0])) {
							if (saleItemTokens.length == 2 && saleItemTokens[1].charAt(0) == 'e') {
								Double basePrice = Double.parseDouble(itemTokens[3]);
								Purchase p = new Purchase(itemTokens[0], saleItemTokens[0],itemTokens[2],basePrice);
								itemList.add(p);
							} else if  (saleItemTokens.length == 4 && saleItemTokens[1].charAt(0) == 'e') {
								Double basePrice = Double.parseDouble(itemTokens[3]);
								LocalDate startDate = LocalDate.parse(saleItemTokens[2]);
								LocalDate endDate = LocalDate.parse(saleItemTokens[3]);
								Lease l = new Lease(itemTokens[0], saleItemTokens[0],itemTokens[2],basePrice,
										startDate, endDate);
								itemList.add(l);
							}
						}
					}
				} else if (itemTokens[1].equals("S")) {
					for (int i = 0; i < saleItemList.size(); i++) {
						String saleItemTokens[] = saleItemList.get(i).split(",");
						if (saleItemTokens[1].equals(itemTokens[0])) {
							if (saleItemTokens.length == 4 && saleItemTokens[1].charAt(0) == 's') {
								Double hoursBilled = Double.parseDouble(saleItemTokens[2]);
								Double basePrice = Double.parseDouble(itemTokens[3]);
								Person serviceman = null;
								for (Person p : personList) {
									if (saleItemTokens[3].equals(p.getUuid())) {
										serviceman = p;
										break;
									}
								}
								Service ser = new Service(itemTokens[0], saleItemTokens[0],
										itemTokens[2], hoursBilled, serviceman, basePrice);
								itemList.add(ser);
							}
						}
					}
				} else if (itemTokens[1].equals("D")) {
					for (int i = 0; i < saleItemList.size(); i++) {
						String saleItemTokens[] = saleItemList.get(i).split(",");
						if (saleItemTokens[1].equals(itemTokens[0])) {
							if (saleItemTokens.length == 3 && saleItemTokens[1].charAt(0) == 'p') {
								Double basePrice = Double.parseDouble(itemTokens[3]);
								Double gbsPurchased = Double.parseDouble(saleItemTokens[2]);
								DataPlan dp = new DataPlan(itemTokens[0], saleItemTokens[0],
										itemTokens[2], gbsPurchased, basePrice);
								itemList.add(dp);
							}
						}
					}
				} else if (itemTokens[1].equals("V")) {
					for (int i = 0; i < saleItemList.size(); i++) {
						String saleItemTokens[] = saleItemList.get(i).split(",");
						if (saleItemTokens[1].equals(itemTokens[0])) {
							if (saleItemTokens.length == 4 && saleItemTokens[1].charAt(0) == 'p') {
								int daysPurchased = Integer.parseInt(saleItemTokens[3]);
								Double basePrice = Double.parseDouble(itemTokens[3]);
								VoicePlan vp = new VoicePlan(itemTokens[0], saleItemTokens[0],
										itemTokens[2], saleItemTokens[2], daysPurchased, basePrice);
								itemList.add(vp);
							}
						}
					}
				}
				
			}
		}
		
		s.close();
		return itemList;
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
					Store store = new Store(tokens[0], manager, a);
					storeList.add(store);
				} else {
					Store store = new Store(tokens[0], a);
					storeList.add(store);
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
				Person person = new Person(tokens[0], tokens[1], tokens[2], a);
				personList.add(person);

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
				Sale sale = new Sale(tokens[0], store, customer, salesperson, date);
				saleList.add(sale);
			}
		}
		s.close();
		return saleList;
	}

//	public static ArrayList<SaleItem> loadSaleItems() {
//		ArrayList<SaleItem> saleItemList = new ArrayList<SaleItem>();
//		String file = "data/SaleItems.csv";
//		Scanner s = null;
//
//		try {
//			s = new Scanner(new File(file));
//		} catch (FileNotFoundException e) {
//			throw new RuntimeException(e);
//		}
//		s.nextLine();
//		while (s.hasNextLine()) {
//			String line = s.nextLine();
//			String tokens[] = line.split(",");
//
//			if (tokens[1].charAt(0) == 'e' && tokens.length == 2) {
//				Product product = new Product(tokens[0], tokens[1]);
//				saleItemList.add(product);
//			} else if (tokens[1].charAt(0) == 'e' && tokens.length == 4) {
//				Lease lease = new Lease(tokens[0], tokens[1], tokens[2], tokens[3]);
//				saleItemList.add(lease);
//			} else if (tokens[1].charAt(0) == 's') {
//				Service service = new Service(tokens[0], tokens[1], tokens[2], tokens[3]);
//				saleItemList.add(service);
//			} else if (tokens[1].charAt(0) == 'p' && tokens.length == 3) {
//				DataPlan dataPlan = new DataPlan(tokens[0], tokens[1], tokens[2]);
//				saleItemList.add(dataPlan);
//			} else if (tokens[1].charAt(0) == 'p' && tokens.length == 4) {
//				VoicePlan voicePlan = new VoicePlan(tokens[0], tokens[1], tokens[2], tokens[3]);
//				saleItemList.add(voicePlan);
//			}
//		}
//		s.close();
//		return saleItemList;
//	}

}
