package application.controller.edit;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import application.controller.dictionary.ProjectDict;
import application.controller.sort.ProjectListSorter;
import application.dao.ProjectDAO;
import content.bean.ProjectBean;
import content.controller.ProjectContentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditProjectController {
	@FXML private VBox editProjectBox;
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

	}

	/*
	 * Cancel edit project and close the EditProject window.
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
			
			// Package data into a ProjectBean
			ProjectBean projectBean = new ProjectBean(id, name, date, descr);

			// Create a DAO
			ProjectDAO dao = new ProjectDAO();

			// Edit the project through the DAO
			dao.editProject(id, projectBean);
			
			// Update the displayed Project content
			updateProjectContent();
			
			// Sort the projects
			ProjectListSorter.getProjectListSorter().sort();

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
	 * Update the project content in the Main Menu
	 */
	public void updateProjectContent() {
		ProjectDict dict = ProjectDict.getProjectDict();
		// Update the name, date, and descr of the displayed Project
		ProjectContentController controller = dict.getProjectContentController(id);
		controller.setName(name);
		controller.setDate(date);
		controller.setDescr(descr);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		// Set the default name to the old name
		projectNameTextField.setText(name);
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
		LocalDate oldDate = LocalDate.parse(date, formatter);
		projectDatePicker.setValue(oldDate);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		// Set the default descr to the old descr
		projectDescrTextArea.setText(descr);
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
