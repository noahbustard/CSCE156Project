package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-02-22
 */

public class Store {
	private String code;
	private String managerUUID;
	private Address address;

	public Store(String code, String managerUUID, Address address) {
		this.code = code;
		this.managerUUID = managerUUID;
		this.address = address;
	}

	public String getCode() {
		return code;
	}

	public String getManagerUUID() {
		return managerUUID;
	}

	public Address getAddress() {
		return address;
	}

	/**
	 * Takes in data from file and processes it
	 * by putting it into a list of stores
	 * 
	 */
	public static ArrayList<Store> loadData() {
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
}
