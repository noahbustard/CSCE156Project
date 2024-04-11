package com.yrl;

import java.util.ArrayList;
import java.util.Map;

public interface DataLoader {
	public ArrayList<Item> loadItems();

	public Map<String, ArrayList<String>> loadSaleItemsMap();

	public Map<Sale, ArrayList<Item>> loadSaleItems(ArrayList<Person> personList, Map<String, Item> itemInfoMap,
			Map<String, Sale> saleMap, Map<String, ArrayList<String>> saleCodeItemCodeMap);

	public ArrayList<Store> loadStores(ArrayList<Person> personList);

	public ArrayList<Person> loadPersons();

	public Map<String, Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList,
			Map<String, Item> itemInfoMap);

}
