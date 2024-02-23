package com.yrl;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONOutputter {
	
	
	
	public static <T> void toJSON(ArrayList<T> list, String fileName) {
		
		File f = new File(fileName.substring(0,fileName.length() - 3) + "json");
		PrintWriter pw;
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		try {
			pw = new PrintWriter(f);
			
			for(T object : list) {
				pw.println(gson.toJson(object));
			}
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		}
		
	}

