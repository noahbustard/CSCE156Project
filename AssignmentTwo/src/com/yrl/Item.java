package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-02-22
 */
public class Item {
	private String code;
	private String type;
	private String name;
	private String basePrice;

	public Item(String code, String type, String name, String basePrice) {
		this.code = code;
		this.type = type;
		this.name = name;
		this.basePrice = basePrice;
	}

	@Override
	public String toString() {
		return "Item [code=" + code + ", type=" + type + ", name=" + name + ", basePrice=" + basePrice + "]";
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getBasePrice() {
		return basePrice;
	}
	/**
	 * Takes in data from file and proccesses it
	 * by putting it into a list of items
	 * 
	 */
	public static ArrayList<Item> loadData() {
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

			Item item = new Item(tokens[0], tokens[1], tokens[2], tokens[3]);
			itemList.add(item);

		}
		s.close();
		return itemList;
	}

}
