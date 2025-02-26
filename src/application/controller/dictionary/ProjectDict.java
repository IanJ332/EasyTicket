package application.controller.dictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import content.controller.ProjectContentController;

public class ProjectDict {
	
	private static ProjectDict singleInstance = new ProjectDict();
	
	/*
	 * A HashMap with id as key and ProjectContentController as value
	 */
	private static HashMap<String,ProjectContentController> projectDict = new HashMap<String,ProjectContentController>();
	
	private ProjectDict() {}
	
	public static ProjectDict getProjectDict() {
		return singleInstance;
	}
	
	/**
	 * Add a ProjectContentController to the dictionary
	 * 
	 * @param name			id of project
	 * @param controller	ProjectContentController corresponding to the id
	 */
	public void addProject(String id, ProjectContentController controller) {
		projectDict.put(id, controller);
	}
	
	/**
	 * Remove a Project entry from the dictionary
	 * 
	 * @param id	ID of Project to remove
	 */
	public void removeProject(String id) {
		projectDict.remove(id);
	}
	
	
	/**
	 * Return a ProjectContentController
	 * 
	 * @param project	Id of project who's corresponding ProjectContentController should be returned
	 * @return			ProjectContentController corresponding to id
	 */
	public ProjectContentController getProjectContentController(String id) {
		return projectDict.get(id);
	}
	
	/**
	 * Returns an ArrayList of all ProjectContentControllers
	 * @return	All existing ProjectContentControllers
	 */
	public ArrayList<ProjectContentController> getProjectContentControllerList() {
		ArrayList<ProjectContentController> allProjects = new ArrayList<ProjectContentController>();
		for (Map.Entry<String, ProjectContentController> entry : projectDict.entrySet()) {
			allProjects.add(entry.getValue());
		}
		return allProjects;
	}
	
	@Override
	public String toString() {
		String s = "";
		String key;
		ProjectContentController value;
		for (Map.Entry<String, ProjectContentController> entry : projectDict.entrySet()) {
			key = entry.getKey();
			value = entry.getValue();
			
			s += key + ": ";
			s += value.getName() + " | " + value.getDate() + " | " + value.getDescr() + "\n";
		}
		return s;
	}
}
