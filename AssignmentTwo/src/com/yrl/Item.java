package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-02-22
 */
public abstract class Item {
	private String itemCode;
	private String name;
	private String saleCode;

	public Item(String itemCode, String name, String saleCode) {
		this.itemCode = itemCode;
		this.name = name;
		this.saleCode = saleCode;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getName() {
		return this.name;
	}

	public String getSaleCode() {
		return saleCode;
	}
	public abstract Double getCost();
	
	public abstract String getType();


	public static Map<String, List<String>> createItemInfoMap(ArrayList<Item> itemList) {
		Map<String, List<String>> itemInfoMap = new HashMap<>();
		for (Item item : itemList) {
			String itemCode = item.getItemCode();
			itemInfoMap.put(itemCode, new ArrayList<>());
			itemInfoMap.get(itemCode).add(item.getType());
			itemInfoMap.get(itemCode).add(item.getName());
			itemInfoMap.get(itemCode).add(item.getCost().toString());
		}
		return itemInfoMap;		
	}
}