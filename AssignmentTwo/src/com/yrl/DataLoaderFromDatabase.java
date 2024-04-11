package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
			String itemCode = rsItem.getString(2);
			String itemName = rsItem.getString(3);
			Double price = rsItem.getDouble(4);
			int itemId = rsItem.getInt(5);
				if (type.equals("P")) {
					System.out.println(price+ "   " + itemId);
					i = new Purchase(itemCode, itemName, price, itemId);
				} else if (type.equals("S")) {
					i = new Service(itemCode, itemName, price, itemId);
				} else if (type.equals("D")) {
					i = new DataPlan(itemCode, itemName, price, itemId);
				} else if (type.equals("V")) {
					i = new DataPlan(itemCode, itemName, price, itemId);
				}
				if (i != null) {
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
		Map<String, ArrayList<String>> saleItemsMap = new HashMap<String, ArrayList<String>>();
		
		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			
			String query = "select saleCode, itemCode from SaleItem "
					+ "join Sale on SaleItem.saleId = Sale.saleId"
					+ "join Item on SaleItem.itemId = Item.itemId";
			PreparedStatement psSaleItem = conn.prepareStatement(query);
			ResultSet rsSaleItem = psSaleItem.executeQuery();
			while (rsSaleItem.next()) {
				String saleCode = rsSaleItem.getString(0);
				String itemCode = rsSaleItem.getString(1);
				if (!saleItemsMap.containsKey(saleCode)) {
					saleItemsMap.put(saleCode, new ArrayList<String>());
				}
				saleItemsMap.get(saleCode).add(itemCode);
				
			}
			rsSaleItem.close();
			psSaleItem.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return saleItemsMap;
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
		ArrayList<Person> personList = new ArrayList<Person>();

		Connection conn = null;
		
		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			
			String query = "firstName, lastName, Email.address, Address.street, Zip.zipcode, "
					+ "City.name, State.abbreviation from Person"
					+ "join Email on Person.emailId = Email.emailId"
					+ "join Address on Person.addressId = Address.addressId"
					+ "join State on Address.stateId = State.stateId"
					+ "join City on Address.cityId = City.cityId"
					+ "join Zip on Address.zipId = Zip.zipId";
			PreparedStatement psPersons = conn.prepareStatement(query);
			ResultSet rsPersons = psPersons.executeQuery();
			while (rsPersons.next()) {
				String firstName = rsPersons.getString(0);
				String lastName = rsPersons.getString(1);
				String email = rsPersons.getString(2);
				String street = rsPersons.getString(3);
				int zipcode = rsPersons.getInt(4);
				String city = rsPersons.getString(5);
				String state = rsPersons.getString(6);

				Address a = new Address(street, city, state, zipcode);
				

			}
			
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return null;
	}

	@Override
	public Map<String, Sale> loadSales(ArrayList<Person> personList, ArrayList<Store> storeList,
			Map<String, Item> itemInfoMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
