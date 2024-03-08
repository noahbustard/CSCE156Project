package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 * Date: 2024-03-07
 * 
 * Item class is a super class of all other items that can be bought in a store.
 * Base Cost is included in the parameters to identitfy what the base retail cost
 * would be. The cost specific to each saleItem is later calculated in the subclasses.
 */
public abstract class Item {
	private String itemCode;
	private String name;
	private Double baseCost;

	public Item( String itemCode, String name, Double baseCost) {
		this.itemCode = itemCode;
		this.name = name;
		this.baseCost = baseCost;
	}

	public String getItemCode() {
		return this.itemCode;
	}

	public String getName() {
		return this.name;
	}

	public abstract Double getCost();

	public abstract String getType();

	public Double getBaseCost() {
		return baseCost;
	}
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
	public static Map<String, List<String>> createItemInfoMap(ArrayList<Item> itemList) {
		Map<String, List<String>> itemInfoMap = new HashMap<>();
		for (Item item : itemList) {
			String itemCode = item.getItemCode();
			itemInfoMap.put(itemCode, new ArrayList<>());
			itemInfoMap.get(itemCode).add(item.getType());
			itemInfoMap.get(itemCode).add(item.getName());
			itemInfoMap.get(itemCode).add(item.getBaseCost().toString());
		}
		return itemInfoMap;
	}

	@Override
	public String toString() {
		return "Item\nitemCode=" + itemCode + "\nname=" + name;
	}

}