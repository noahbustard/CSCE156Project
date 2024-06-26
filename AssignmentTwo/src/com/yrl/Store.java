package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-03-08
 * 
 *         Store class represents each store and included managers.
 */

public class Store {
	private String storeCode;
	private Person manager;
	private Address address;
	private int storeId;

	public Store(String storeCode, Person manager, Address address, int storeId) {
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
		this.storeId = storeId;
	}

	public Store(String storeCode, Person manager, Address address) {
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}

	public Store(String storeCode, Address address) {
		this.storeCode = storeCode;
		this.address = address;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public Person getManager() {
		return this.manager;
	}

	public String getManagerUUID() {
		return this.manager.toString();
	}

	public Address getAddress() {
		return this.address;
	}

	@Override
	public String toString() {
		return this.getStoreCode();
	}

	/**
	 * Creates a map of each store and maps it with the sales the store had.
	 * 
	 * @param saleList
	 * @return
	 */
	public static Map<Store, ArrayList<Sale>> createStoreMap(Map<String, Sale> saleMap, ArrayList<Store> storeList) {
		Map<Store, ArrayList<Sale>> storeMap = new HashMap<>();
		for (Store key : storeList) {
			storeMap.put(key, new ArrayList<Sale>());
		}
		for (Map.Entry<String, Sale> entry : saleMap.entrySet()) {
			Store store = entry.getValue().getStore();
			storeMap.get(store).add(entry.getValue());
		}
		return storeMap;
	}

	public int getStoreId() {
		return storeId;
	}
}
