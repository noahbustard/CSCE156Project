package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Item class is a super class of all other items that can be bought in a store.
 * Base Cost is included in the parameters to identify what the base retail cost
 * would be. The cost specific to each saleItem is later calculated in the subclasses.
 */
public abstract class Item {
	private String itemCode;
	private String name;
	
	public Item( String itemCode, String name) {
		this.itemCode = itemCode;
		this.name = name;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getName() {
		return this.name;
	}

	public abstract Double getCost();

	public abstract String getType();
	
	public abstract Double getTax();
	
	public abstract Double getBaseCost();
		
	
	/**
	 * createItemInfoMap is a method that returns a map
	 * that has every loaded item's code as the key
	 * and the Type, Name, and BaseCost as the values.
	 * This was used to create the saleItem list due to
	 * the saleItem data not containing any of these values.
	 * 
	 * @param itemList
	 * @return
	 */
	public static Map<String, Item> createItemInfoMap(ArrayList<Item> itemList) {
		Map<String, Item> itemInfoMap = new HashMap<>();
		for (Item item : itemList) {
			String itemCode = item.getItemCode();
			itemInfoMap.put(itemCode, item);
			
		}
		return itemInfoMap;
	}


	public  abstract String toString();

}