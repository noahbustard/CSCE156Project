package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Noah Bustard
 * @author Caden France
 * Date: 2024-02-22
 * 
 * Converts data from csv files and sorts them
 * into corresponding classes
 */

public class DataConverter {

	public static void main(String args[]) {
		loadFiles();
		

	}
	/**
	 * Opens and scans files and sorts data
	 */
	private static void loadFiles() {

		List<String> files = Arrays.asList("data/Persons.csv", "data/Stores.csv", "data/Items.csv");
		ArrayList<Person> personList = new ArrayList<Person>();
		ArrayList<Item> itemList = new ArrayList<Item>();
		ArrayList<Store> storeList = new ArrayList<Store>();
		
		Scanner s = null;
		
		
		for (String file : files) {
			try {
				s = new Scanner(new File(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			/**
			 * Sets header string that will be used to distinguish which object is being
			 * scanned
			 */
			String header = s.nextLine();

			while (s.hasNextLine()) {
				String line = s.nextLine();
				String tokens[] = line.split(",");

				if (header.equals("uuid,firstName,lastName,street,city,state,zip,email(s)")) {
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
						JSONOutputter.toJSON(personList, file);
					}

				} else if (header.equals("code,type,name,base cost")) {
					Item item = new Item(tokens[0], tokens[1], tokens[2], tokens[3]);
					itemList.add(item);
					JSONOutputter.toJSON(itemList, file);
					
				} else if (header.equals("storeCode,managerUUID,street,city,state,zip")) {
					Address a = new Address(tokens[2], tokens[3], tokens[4], tokens[5]);
					Store store = new Store(tokens[0], tokens[1], a);
					storeList.add(store);
					JSONOutputter.toJSON(storeList, file);
				}
			}

		}

		s.close();
	}
	}
	
