package database;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectFileIO {
	
	private BufferedWriter writer;
	private BufferedReader reader;
	private static int size;
	
	public ProjectFileIO() {
		try {
			writer = new BufferedWriter(new FileWriter("./database/ProjectFile.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			reader = new BufferedReader(new FileReader("./database/ProjectFile.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Stores the project details in ProjectFile.txt
	 * 
	 * @param data	ArrayList of data to be stored, formatted: name, date, descr
	 */
	public void storeProject(ArrayList<String> data) {
		String entry = "";
		for (String field : data) {
			entry += field + ",";
		}
		entry = entry.substring(0,entry.length()-1);
		try {
			writer.append(entry);
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		size++;
	}
	
	/**
	 * Returns an ArrayList of all the entries in ProjectFile.txt
	 * 
	 * @return	An ArrayList of data arrays
	 */
	public ArrayList<ArrayList<String>> readProject() {
		ArrayList<ArrayList<String>> container = new ArrayList<ArrayList<String>>();
		String line;
		try {
			line = reader.readLine();
			while (line != null) {
				List<String> items = Arrays.asList(line.split(","));
				container.add(new ArrayList<String> (items));
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return container;
	}
	
	public int getTableSize() {
		return size;
	}
}
