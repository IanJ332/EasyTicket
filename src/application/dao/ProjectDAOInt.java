package application.dao;

import java.util.ArrayList;

import content.bean.ProjectBean;

public interface ProjectDAOInt {
	/**
	 * Store the project.
	 * 
	 * @param projectBean	The project to store
	 */
	public void storeProject(ProjectBean projectBean);
	
	/**
	 * Returns an entry in the database.
	 * 
	 * @param index	Index of the entry to return
	 * @return	An ArrayList that contains the project's details in the follow order: name, date, description
	 */
	public ArrayList<String> readProjectEntry(int index);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size();
}
