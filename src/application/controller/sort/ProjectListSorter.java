package application.controller.sort;

import java.util.ArrayList;

import application.CommonObjs;
import application.controller.dictionary.ProjectDict;
import content.controller.ProjectContentController;
import javafx.scene.layout.VBox;

public class ProjectListSorter {
	private static ProjectListSorter singleInstance = new ProjectListSorter();
	private ProjectListSorter() {}
	
	public void sort() {
		// Root node where projects are displayed
		VBox projectVBox = CommonObjs.getInstance().getProjectVBox();
		// An ArrayList of ALL ProjectContentControllers
		ArrayList<ProjectContentController> controllerList = ProjectDict.getProjectDict().getProjectContentControllerList();
		// Filter out all projects that are not currently displayed
		ArrayList<ProjectContentController> displayedProjects = new ArrayList<ProjectContentController>();
		for (ProjectContentController controller : controllerList) {
			if (projectVBox.getChildren().contains(controller.getProjectContent())) {
				displayedProjects.add(controller);
			}
		}
		// Sort list of displayed projects (alphabetically ascending)
		ComparatorAscending<ProjectContentController> comp = new ComparatorAscending<ProjectContentController>();
		displayedProjects.sort(comp);
		// A sorted ArrayList of all displayed projects
		ArrayList<VBox> contentList = new ArrayList<VBox>();
		for (ProjectContentController controller : displayedProjects) {
			contentList.add(controller.getProjectContent());
		}
		
		// Sort ProjectVBox
		// First remove all children of ProjectVBox
		projectVBox.getChildren().clear();
		// Then add all sorted children
		for (VBox content : contentList) {
			projectVBox.getChildren().add(content);
		}
	}
	
	public static ProjectListSorter getProjectListSorter() {
		return singleInstance;
	}
}
