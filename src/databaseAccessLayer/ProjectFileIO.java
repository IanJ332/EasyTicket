package databaseAccessLayer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectFileIO implements FileIO{
	
	private BufferedWriter writer;
	private BufferedReader reader;
	private static int size;
	
	// character used to separate fields
	private String separator = "|";
	
	public ProjectFileIO() {
		// Create a BufferedWriter that writes to the database
		try {
			writer = new BufferedWriter(new FileWriter("./database/ProjectFile.txt", true));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Create a BufferedReader that reads from the database
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
	@Override
	public void store(ArrayList<String> data) {
		// Build the string to write
		String entry = "";
		for (int i = 0; i < 2; i++) {
			entry += data.get(i) + separator;
		}
		entry += data.get(2);

		// Write to the text file
		try {
			writer.append(entry);
			writer.append("\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Increment size
		size++;
	}
	
	/**
	 * Returns an ArrayList of all the entries in ProjectFile.txt
	 * 
	 * @return	An ArrayList of data arrays
	 */
	@Override
	public ArrayList<ArrayList<String>> read() {
		ArrayList<ArrayList<String>> container = new ArrayList<ArrayList<String>>();
		String line;
		try {
			line = reader.readLine();
			while (line != null) {
				List<String> items = Arrays.asList(line.split("\\|"));
				ArrayList<String> output = new ArrayList<>(items);
 				
				// If descr field is empty, add an empty string to represent it
				if (output.size() < 3) {
					output.add("");
				}
				
				container.add(output);
				line = reader.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return container;
	}
	
	@Override
	public int size() {
		return size;
	}
	
	@Override
	public void setSize(int size) {
		ProjectFileIO.size = size;
	}
}
