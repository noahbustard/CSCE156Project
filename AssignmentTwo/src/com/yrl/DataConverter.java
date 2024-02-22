package com.yrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class DataConverter {
	private static List<String> files = Arrays.asList("data/Items.csv", "data/Persons.csv", "data/SaleItems.csv");

	public static String loadDnaFromFile() {

		Scanner s = null;
		for(String file : files) {
			try {
				s = new Scanner(new File(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			while(s.hasNextLine()) {
				String line = s.nextLine();
				String tokens[] = line.split(",");
			}
			
			
		}
		


		// close the scanner
		s.close();
		return null;
	}
	}
	

