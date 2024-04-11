package com.yrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataLoaderFromDatabase implements DataLoader{

	@Override
	public ArrayList<Item> loadItems() {
		ArrayList<Item> itemList = new ArrayList<>();
		Item i = null;
		
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DatabaseInfo.URL, DatabaseInfo.USERNAME, DatabaseInfo.PASSWORD);
			
			String query = "select  i.type, i.itemCode,"
					+ " i.name, i.baseCost,"
					+ " i.itemId from Item as i";
			PreparedStatement psItem = conn.prepareStatement(query);
			ResultSet rsItem = psItem.executeQuery();
			while (rsItem.next()) {
			String type = rsItem.getString(1);
			String itemCode = rsItem.getString(2);
			String itemName = rsItem.getString(3);
			Double price = rsItem.getDouble(4);
			int itemId = rsItem.getInt(5);
				if (type.equals("P")) {
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
				String saleCode = rsSaleItem.getString(1);
				String itemCode = rsSaleItem.getString(2);
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
			
			String query = "select firstName, lastName, Address.street, Address.street, "
					+ "Address.city, Address.state, uuid, personId from Person "
					+ "join Address on Person.addressId = Address.addressId";
			PreparedStatement psPerson = conn.prepareStatement(query);
			ResultSet rsPerson = psPerson.executeQuery();
			while (rsPerson.next()) {
				List<String> emails = new ArrayList<>();
				String firstName = rsPerson.getString(1);
				String lastName = rsPerson.getString(2);
				String street = rsPerson.getString(3);
				int zipcode = rsPerson.getInt(4);
				String city = rsPerson.getString(5);
				String state = rsPerson.getString(6);
				String uuid = rsPerson.getString(7);
				int personId = rsPerson.getInt(8);
				
				String emailQuery = "select address from Email where personId = ?";
				PreparedStatement psEmail = conn.prepareStatement(emailQuery);
				psEmail.setInt(1, personId);
				ResultSet rsEmail = psEmail.executeQuery();
				while (rsEmail.next()) {
					emails.add(rsEmail.getString(1));
				}
				
				Address a = new Address(street, city, state, zipcode);
				Person p = new Person(uuid,firstName,lastName,a,emails,personId);
				personList.add(p);

			}
			rsPerson.close();
			psPerson.close();
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
