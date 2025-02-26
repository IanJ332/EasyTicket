package application.controller;

import java.io.IOException;

import application.controller.delete.ProjectDeleter;
import application.controller.edit.EditProjectController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewProjectController {
	@FXML private Text nameText;
	@FXML private Text dateText;
	@FXML private Text descrText;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	@FXML private Button newTicketButton;
	@FXML private Button doneButton;

	private String id;
	private String name;
	private String date;
	private String descr;

	/*
	 * Perform all necessary post-processing
	 */
	@FXML
	public void initialize() {

	}

	/*
	 * Delete this project.
	 */
	@FXML
	private void deleteButtonAction() {
		// First close this window
		doneButtonAction();
		// Then delete the Project
		ProjectDeleter deleter = new ProjectDeleter();
		deleter.deleteProject(id);
	}
	
	/*
	 * Edit this project
	 */
	@FXML
	private void editButtonAction() {
		// Open the EditProject page and pass in this Project's ID
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/EditProject.fxml"));
			VBox root= (VBox) loader.load();
			EditProjectController controller = (EditProjectController)loader.getController();
			controller.setId(id);	
			controller.setName(name);
			controller.setDate(date);
			controller.setDescr(descr);
			
			Stage editProjectPage = new Stage();
			editProjectPage.setTitle("Edit Project");
			editProjectPage.setScene(new Scene(root));
			editProjectPage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Open the NewTicket page.
	 */
	@FXML
	private void newTicketButtonAction() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/NewTicket.fxml"));

			VBox root = (VBox) loader.load();

			NewTicketController controller = (NewTicketController)loader.getController();
			controller.setProjectId(id);

			Stage newTicketPage = new Stage();
			newTicketPage.setTitle("New Ticket");
			newTicketPage.setScene(new Scene(root));
			newTicketPage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/*
	 * Close the page.
	 */
	@FXML
	private void doneButtonAction() {
		// Get the Stage
		Stage stage = (Stage) doneButton.getScene().getWindow();	
		// Close it
		stage.close();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		nameText.setText(name);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		dateText.setText(date);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		descrText.setText(descr);
	}

	public void setId(String id) {
		this.id = id;
	}



}
