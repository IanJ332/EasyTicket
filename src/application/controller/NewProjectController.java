package application.controller;

import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.stage.Stage;

public class NewProjectController {

	@FXML private javafx.scene.control.Button cancelButton;
	@FXML private javafx.scene.control.Button saveButton;
	@FXML private javafx.scene.control.TextField projectNameTextField;
	@FXML private javafx.scene.control.TextArea projectDescTextArea;
	@FXML private javafx.scene.control.DatePicker projectDatePicker;
	
	/*
	 * Perform all necessary post-processing.
	 */
	@FXML private void initialize() {
		// Set the default time
		projectDatePicker.setValue(LocalDate.now());
	}

	/*
	 * Cancel new project and close the NewProject window.
	 */
	@FXML private void cancelButtonAction() {
		// Get the Stage
		Stage stage = (Stage) cancelButton.getScene().getWindow();	
		// Close it
		stage.close();
	}

	/*
	 * Save the new project.
	 */
	@FXML private void saveButtonAction() {

	}
}
