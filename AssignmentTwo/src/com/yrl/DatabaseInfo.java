package com.yrl;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Database connection configuration 
 */
public class DatabaseInfo {

	/**
	 * User name used to connect to the SQL server
	 */
	public static final String USERNAME = "nbustard2";

	/**
	 * Password used to connect to the SQL server
	 */
	public static final String PASSWORD = "eeghooCh1que";

	/**
	 * Connection parameters that may be necessary for server configuration
	 * 
	 */
	public static final String PARAMETERS = "";

	/**
	 * SQL server to connect to
	 */
	public static final String SERVER = "cse-linux-01.unl.edu";

	/**
	 * Fully formatted URL for a JDBC connection
	 */
	public static final String URL = String.format("jdbc:mysql://%s/%s?%s", SERVER, USERNAME, PARAMETERS);
	
	public static final ArrayList<String> TABLELIST = new ArrayList<>(Arrays.asList("Email", "SaleItem", "Item", 
			"Sale", "Store", "Person", "Address"));


}
