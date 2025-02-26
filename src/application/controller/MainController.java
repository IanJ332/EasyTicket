package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.CommonObjs;
import application.controller.search.ProjectSearch;
import application.controller.search.TicketSearch;
import application.controller.sort.ProjectListSorter;
import application.dao.CommentDAO;
import application.dao.ProjectDAO;
import application.dao.TicketDAO;
import content.bean.CommentBean;
import content.bean.ProjectBean;
import content.bean.TicketBean;
import content.controller.ProjectContentController;
import content.controller.TicketContentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainController {
	private CommonObjs commonObjs = CommonObjs.getInstance();

	@FXML private BorderPane mainBorderPane;
	@FXML private ScrollPane projectScrollPane;
	@FXML private ScrollPane ticketScrollPane;
	@FXML private VBox projectVBox;
	@FXML private VBox ticketVBox;
	@FXML private TextField projectSearchTextField;
	@FXML private TextField ticketSearchTextField;

	/*
	 * Perform all necessary procedures on start-up
	 */
	@FXML 
	public void initialize() {
		// Keep a reference to the projectScrollPane
		commonObjs.setProjectScrollPane(projectScrollPane);

		// Keep a reference to the projectVBox
		commonObjs.setProjectVBox(projectVBox);

		// Keep a reference to the commentVBox
		commonObjs.setTicketVBox(ticketVBox);

		loadDatabaseEntries();
	}

	/**
	 * Load all Projects, Tickets, and Comments from the database
	 */
	private void loadDatabaseEntries() {
		// Load all Projects
		loadProjects();

		// Load all Tickets into their respective Projects
		loadTickets();

		// Load all Comments into their respective Tickets
		loadComments();
	}

	/*
	 * Load all stored Projects
	 */
	private void loadProjects() {
		// A DAO to read entries from ProjectFile
		ProjectDAO dao = new ProjectDAO();
		// Number of entries to read
		int size = dao.size();

		// A ProjectBean containing the id, name, date, and descr of a Project
		ProjectBean bean;
		// The id, name, date, and descr of a Project to load
		String id, name, date, descr;

		for (int i = 0; i < size; i++) {
			bean = dao.readProjectEntry(i);
			id = bean.getId();
			name = bean.getName();
			date = bean.getDate();
			descr = bean.getDescr();

			// Display the loaded Project
			showProjectContent(id, name, date, descr);
		}

	}

	/*
	 * Displays a project
	 */
	private void showProjectContent(String id, String name, String date, String descr) {
		NewProjectController controller = new NewProjectController();
		controller.showProjectContent(id, name, date, descr);
	}

	/*
	 * Load all stored Tickets
	 */
	private void loadTickets() {
		// A DAO to read entries from TicketFile
		TicketDAO dao = new TicketDAO();
		// Number of entries to read
		int size = dao.size();
		// A TicketBean containing the id, ProjectID, name, and descr of a Ticket
		TicketBean bean;
		// The id, ProjectID, name, and descr of a Ticket to load
		String id, projectId, name, descr;

		for (int i = 0; i < size; i++) {
			bean = dao.readTicketEntry(i);
			id = bean.getId();
			projectId = bean.getProjectId();
			name = bean.getName();
			descr = bean.getDescr();

			// Display the loaded Ticket
			showTicketContent(id, projectId, name, descr);
		}
	}

	/*
	 * Displays a Ticket (and updates TicketDict)
	 */
	private void showTicketContent(String id, String projectId, String name, String descr) {
		NewTicketController controller = new NewTicketController();
		controller.showTicketContent(id, projectId, name, descr);
	}

	/*
	 * Load all stored Comments
	 */
	private void loadComments() {
		// A DAO to read entries from CommentFile
		CommentDAO dao = new CommentDAO();
		// Number of entries to read
		int size = dao.size();
		// A CommentBean containing the id, TicketId, timestamp, and descr of a Comment
		CommentBean bean;
		// The id, TicketId, timestamp, and descr of a Comment to load
		String id, ticketId, timestamp, descr;

		for (int i = 0; i < size; i++) {
			bean = dao.readCommentEntry(i);
			id = bean.getId();
			ticketId = bean.getTicketId();
			timestamp = bean.getTimestamp();
			descr = bean.getDescr();

			// Display the loaded Comment
			showCommentContent(id, ticketId, timestamp, descr);
		}
	}

	/*
	 * Displays a Comment (and updates CommentDict)
	 */
	private void showCommentContent(String id, String ticketId, String timestamp, String descr) {
		NewCommentController controller = new NewCommentController();
		controller.showCommentContent(id, ticketId, timestamp, descr);
	}

	/*
	 * Shows the new project page in a new window.
	 */
	@FXML 
	private void showNewProjectPage() {
		URL url = getClass().getClassLoader().getResource("view/NewProject.fxml");

		try {
			VBox root = (VBox) FXMLLoader.load(url);
			Stage newProjectStage = new Stage();
			newProjectStage.setTitle("New Project");
			newProjectStage.setScene(new Scene(root));
			newProjectStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

	/**
	 * Search for a project based on a substring in the project's name.
	 */
	@FXML
	private void searchProject() {
		// Create a searcher and search for a project based on a substring
		ProjectSearch searcher = new ProjectSearch();
		// List of ProjectContentControllers whose respective project's name contain the inputted susbtring 
		ArrayList<ProjectContentController> projectsToShow = searcher.searchContainsSubstring(projectSearchTextField.getText());
		// List of ProjectContentControllers whose respective project's name DO NOT contain the inputted susbtring
		ArrayList<ProjectContentController> projectsToRemove = searcher.searchDoesNotContainSubstring(projectSearchTextField.getText());
		// Make sure all projects that need to be shown are displayed
		for (ProjectContentController controller : projectsToShow) {
			if (!projectVBox.getChildren().contains(controller.getProjectContent())) {
				projectVBox.getChildren().add(controller.getProjectContent());
			}
		}
		// Remove all projects that do NOT contain the given substring from the display
		for (ProjectContentController controller : projectsToRemove) {
			projectVBox.getChildren().remove(controller.getProjectContent());
		}

		// Lastly sort all projects in ascending alphabetical order
		ProjectListSorter.getProjectListSorter().sort();
	}

	/**
	 * Search for a ticket based on a substring in the ticket's title OR
	 * project's name.
	 */
	@FXML
	private void searchTicket() {
		// Create a searcher and search for a ticket based on a substring
		TicketSearch searcher = new TicketSearch();
		// List of TicketContentControllers whose respective ticket contain the inputted susbtring in their name or project's name
		ArrayList<TicketContentController> ticketsToShow = searcher.searchContainsSubstring(ticketSearchTextField.getText());
		// List of ProjectContentControllers whose respective ticket DO NOT contain the inputted susbtring
		ArrayList<TicketContentController> ticketsToRemove = searcher.searchDoesNotContainSubstring(ticketSearchTextField.getText());
		// Make sure all tickets that need to be shown are displayed
		for (TicketContentController controller : ticketsToShow) {
			if (!ticketVBox.getChildren().contains(controller.getTicketContent())) {
				ticketVBox.getChildren().add(controller.getTicketContent());
			}
		}
		// Remove all projects that do NOT contain the given substring from the display
		for (TicketContentController controller : ticketsToRemove) {
			ticketVBox.getChildren().remove(controller.getTicketContent());
		}

	}

}
