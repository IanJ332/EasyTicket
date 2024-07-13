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
	 * Return the number of entries in the text file
	 */
	public int size();
	
	/*
	 * Use to keep track of how many entries are in the file
	 */
	public void setSize(int size);
}
