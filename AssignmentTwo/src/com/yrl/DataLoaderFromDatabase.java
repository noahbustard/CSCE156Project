package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;


public class DataLoaderFromDatabase implements DataLoader{

	@Override
	public ArrayList<Item> loadItems() {
		ArrayList<Item> itemList = new ArrayList<>();
		Item i = null;
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			
			String query = "select  i.type as 'Type', i.itemCode as 'Item Code',"
					+ " i.name as 'Item Name', i.baseCost as 'Base Cost',"
					+ " i.itemId as 'Item Id' from Item as i";
			PreparedStatement psItem = conn.prepareStatement(query);
			ResultSet rsItem = psItem.executeQuery();
			while (rsItem.next()) {
			String type = rsItem.getString(1);
				if (type.equals("P")) {
					String itemCode = rsItem.getString(2);
					String itemName = rsItem.getString(3);
					Double price = rsItem.getDouble(4);
					int itemId = rsItem.getInt(5);
					System.out.println(price+ "   " + itemId);
					i = new Purchase(itemCode,itemName,price,itemId);
					itemList.add(i);
				}
			}
			
			
			rsItem.close();
			psItem.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return itemList;
	}

	@Override
	public Map<String, ArrayList<String>> loadSaleItemsMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Sale, ArrayList<Item>> loadSaleItems(ArrayList<Person> personList, Map<String, Item> itemInfoMap,
			Map<String, Sale> saleMap, Map<String, ArrayList<String>> saleCodeItemCodeMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Store> loadStores(ArrayList<Person> personList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Person> loadPersons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList,
			Map<String, Item> itemInfoMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
