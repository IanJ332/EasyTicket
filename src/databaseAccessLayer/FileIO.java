package databaseAccessLayer;

import java.util.ArrayList;

public interface FileIO {
	/*
	 * Write text to file
	 */
	public void store(ArrayList<String> data);
	
	/*
	 * Return an ArrayList of all data entries in the text file
	 */
	public ArrayList<ArrayList<String>> read();
	
	/*
	 * Returns an ArrayList of the data on a line with matching ID
	 * ID should be the first field in an entry
	 */
	public ArrayList<String> readID(String id);
	
	/*
	 * Deletes an entry with the given id
	 */
	public void deleteEntry(String id);
	
	/*
	 * Return the number of entries in the text file
	 */
	public int size();
	
	
}
