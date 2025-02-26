package application.controller.search;

import java.util.ArrayList;

import application.controller.dictionary.ProjectDict;
import application.dao.ProjectDAO;
import content.bean.ProjectBean;
import content.controller.ProjectContentController;

/**
 * This class is used to search for a Project.
 * 
 * Methods can return a list of all ProjectContentControllers whose respective 
 * projects contain a certain substring in their name
 */
public class ProjectSearch {
	
	private ProjectDAO dao; 
	
	public ProjectSearch() {
		dao = new ProjectDAO();
	}
	
	/**
	 * Returns an ArrayList of ProjectContentControllers whose respective projects
	 * contain 'keyword' in their name
	 * @param keyword	The substring to search for
	 * @return	An ArrayList of ProjectContentControllers
	 */
	public ArrayList<ProjectContentController> searchContainsSubstring(String keyword){
		ArrayList<ProjectContentController> result = new ArrayList<ProjectContentController>();
		// Size of database to read
		int size = dao.size();
		// Read through every entry to see if the name field contains 'keyword'
		ProjectBean bean;
		String projName, projId;
		for (int i = 0; i < size; i++) {
			bean = dao.readProjectEntry(i);
			projName = bean.getName();
			projId = bean.getId();
			if (projName.contains(keyword)) {
				// If the project name contains keyword, add its controller to the result list
				result.add(ProjectDict.getProjectDict().getProjectContentController(projId));
			}
		}
		return result;
	}
	
	/**
	 * Returns an ArrayList of ProjectContentControllers whose respective projects
	 * do NOT contain 'keyword' in their name
	 * @param keyword	The substring to search for
	 * @return	An ArrayList of ProjectContentControllers
	 */
	public ArrayList<ProjectContentController> searchDoesNotContainSubstring(String keyword){
		ArrayList<ProjectContentController> result = new ArrayList<ProjectContentController>();
		// Size of database to read
		int size = dao.size();
		// Read through every entry to see if the name field contains 'keyword'
		ProjectBean bean;
		String projName, projId;
		for (int i = 0; i < size; i++) {
			bean = dao.readProjectEntry(i);
			projName = bean.getName();
			projId = bean.getId();
			if (!projName.contains(keyword)) {
				// If the project name does NOT contain keyword, add its controller to the result list
				result.add(ProjectDict.getProjectDict().getProjectContentController(projId));
			}
		}
		return result;
	}
}
