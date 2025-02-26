package application.dao;

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
	 * @return	A ProjectBean that contains the project's details in the follow order: id, name, date, description
	 */
	public ProjectBean readProjectEntry(int index);
	
	/**
	 * Returns an entry in the database
	 * @param id	Id of the project to return
	 * @return	A ProjectBean that contains the project's details in the follow order: id, name, date, description
	 */
	public ProjectBean readProjectEntry(String id);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size();
	
	/**
	 * Delete a Project entry in the database
	 * @param projectId	id of Project to delete
	 */
	public void deleteProject(String projectId);
	
	/**
	 * Edit a Project entry in the database
	 * 
	 * @param id	Id of project to edit
	 * @param bean	A Projectbean containing new information of Project to update with
	 */
	public void editProject(String id, ProjectBean bean);
}
