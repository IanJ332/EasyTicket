package application.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import application.CommonObjs;
import application.controller.dictionary.ProjectDict;
import application.controller.sort.ProjectListSorter;
import application.dao.IDGenerator;
import application.dao.ProjectDAO;
import content.bean.ProjectBean;
import content.controller.ProjectContentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewProjectController {

	private CommonObjs commonObjs = CommonObjs.getInstance();

	@FXML private VBox newProjectBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextField projectNameTextField;
	@FXML private TextArea projectDescrTextArea;
	@FXML private DatePicker projectDatePicker;
	@FXML private Text errorText;

	private String id;
	private String name;
	private String date;
	private String descr;

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
		name = projectNameTextField.getText();
		if (name.equals("")) {
			throwNoProjectNameExeption();
		} else {
			// Get the date from datePicker
			LocalDate localDate = projectDatePicker.getValue();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			date = localDate.format(formatter);

			// Get the description from the textArea
			descr = projectDescrTextArea.getText();
			
			// Generate an ID for this project
			id = IDGenerator.getIDGenerator().getRandomID();
			
			// Package data into a ProjectBean
			ProjectBean projectBean = new ProjectBean(id, name, date, descr);

			// Create a DAO
			ProjectDAO dao = new ProjectDAO();

			// Store the project to database through the DAO
			dao.storeProject(projectBean);

			// Show the project
			showProjectContent(id, name, date, descr);

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
	public void showProjectContent(String id, String name, String date, String descr) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ProjectContent.fxml"));

			VBox contentVBox = (VBox) loader.load();

			ProjectContentController controller = (ProjectContentController)loader.getController();
			controller.setId(id);
			controller.setName(name);
			controller.setDate(date);
			controller.setDescr(descr);
			controller.initializeViewProjectPage();
			ProjectDict.getProjectDict().addProject(id, controller);
			commonObjs.getProjectVBox().getChildren().add(contentVBox);
			ProjectListSorter.getProjectListSorter().sort();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
