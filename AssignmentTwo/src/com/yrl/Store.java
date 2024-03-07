package com.yrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Noah Bustard
 * @author Caden France
 * 
 *         Date: 2024-02-22
 */

public class Store {
	private String storeCode;
	private Person manager;
	private Address address;

	public Store(String storeCode, Person manager, Address address) {
		this.storeCode = storeCode;
		this.manager = manager;
		this.address = address;
	}

	public Store(String storeCode, Address address) {
		this.storeCode = storeCode;
		this.address = address;
	}

	public String getCode() {
		return this.storeCode;
	}

	public String getManagerUUID() {
		return this.manager.toString();
	}

	public Address getAddress() {
		return this.address;
	}

	@Override
	public String toString() {
		if (this.manager != null) {
			return "Store storeCode=" + storeCode + ", manager=" + manager.toString() + ", address=" + address;
		}
		else {
			return "Store storeCode=" + storeCode + ", manager=null, address=" + address;
		}
	}

	public static Map<Store, List<Sale>> createStoreMap(ArrayList<Sale> saleList) {
		Map<Store, List<Sale>> storeMap = new HashMap<>();
		for (Sale sale : saleList) {
			Store store = sale.getStore();
			if (!storeMap.containsKey(store)) {
				storeMap.put(store, new ArrayList<Sale>());
			}
			storeMap.get(store).add(sale);
		}
		return storeMap;
	}
}
