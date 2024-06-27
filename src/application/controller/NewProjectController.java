package application.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.CommonObjs;
import application.dao.ProjectDAO;
import content.bean.ProjectBean;
import databaseAccessLayer.ProjectFileIO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NewProjectController {

	private CommonObjs commonObjs = CommonObjs.getInstance();

	@FXML private VBox newProjectBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextField projectNameTextField;
	@FXML private TextArea projectDescrTextArea;
	@FXML private DatePicker projectDatePicker;
	@FXML private Text errorText;
	

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {
		// Set the default time in the datePicker
		projectDatePicker.setValue(LocalDate.now());
	}

	/*
	 * Cancel new project and close the NewProject window.
	 */
	@FXML 
	private void cancelButtonAction() {
		// Get the Stage
		Stage stage = (Stage) cancelButton.getScene().getWindow();	
		// Close it
		stage.close();
	}

	/*
	 * Save the new project.
	 */
	@FXML 
	private void saveButtonAction() {
		// Get the name from nameTextField and check it is not empty
		String name = projectNameTextField.getText();
		if (name.equals("")) {
			throwNoProjectNameExeption();
		} else {
			// Get the date from datePicker
			LocalDate localDate = projectDatePicker.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String date = localDate.format(formatter);

			// Get the description from the textArea
			String descr = projectDescrTextArea.getText();

			// Package data into a ProjectBean
			ProjectBean projectBean = new ProjectBean(name, date, descr);

			// Create a DAO
			ProjectDAO dao = new ProjectDAO();

			// Store the project to database through the DAO
			dao.storeProject(projectBean);

			// Show the project
			showProjectContent();

			// Close this window
			cancelButtonAction();
		}
	}

	/**
	 * Tell the user the project name field cannot be left empty
	 */
	private void throwNoProjectNameExeption() {
		errorText.setVisible(true);
	}

	/**
	 * Shows the new project content in the Main Menu
	 */
	private void showProjectContent() {
		URL url = getClass().getClassLoader().getResource("view/ProjectContent.fxml");

		try {
			VBox contentVBox = (VBox) FXMLLoader.load(url);
			commonObjs.getProjectVBox().getChildren().add(contentVBox);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
