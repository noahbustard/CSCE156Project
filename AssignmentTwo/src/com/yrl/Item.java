package com.yrl;

/**
 * @author Noah Bustard
 * @Author Caden France
 * 
 *         Date: 2024-02-22
 */
public abstract class Item {
	private String itemCode;
	private String name;

	public Item(String itemCode, String name) {
		this.itemCode = itemCode;
		this.name = name;
	}

	public String getCode() {
		return this.itemCode;
	}

	public String getName() {
		return this.name;
	}

}
