package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;

import application.CommonObjs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

public class MainController {
	private CommonObjs commonObjs = CommonObjs.getInstance();

	@FXML private BorderPane mainBorderPane;
	@FXML private ScrollPane projectScrollPane;
	@FXML private VBox projectVBox;
	@FXML private VBox commentVBox;

	/*
	 * Perform all necessary procedures on start-up
	 */
	@FXML 
	public void initialize() {
		loadProjects();
		loadTickets();
	}
	
	/*
	 * Load the tickets and comments
	 */
	private void loadTickets() {
		// Initialize TicketDict and CommentDict
		
		// Show the tickets
		
	}
	
	/*
	 * Load the projects
	 */
	private void loadProjects() {
		// Initialize ProjectDict
		
		// Show the projects
		
	}

	/*
	 * Shows the new project page in a new window.
	 */
	@FXML 
	private void showNewProject() {
		URL url = getClass().getClassLoader().getResource("view/NewProject.fxml");

		try {
			VBox root = (VBox) FXMLLoader.load(url);
			Stage newProjectStage = new Stage();
			newProjectStage.setTitle("New Project");
			newProjectStage.setScene(new Scene(root));
			newProjectStage.show();

			// Keep a reference to the projectScrollPane
			commonObjs.setProjectScrollPane(projectScrollPane);

			// Keep a reference to the projectVBox
			commonObjs.setProjectVBox(projectVBox);
			
			// Keep a reference to the commentVBox
			commonObjs.setCommentVBox(commentVBox);

		} catch (IOException e) {
			e.printStackTrace();
		}	

	}

}
