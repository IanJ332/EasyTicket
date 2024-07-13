package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewCommentController {
	@FXML private VBox newCommentBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextField commentNameTextField;
	@FXML private TextArea commentDescrTextArea;
	@FXML private Text errorText;
	@FXML private Text timestampText;

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {

	}

	/*
	 * Cancel new comment and close the NewComment window.
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
		// Close this window
		cancelButtonAction();
	}
}
