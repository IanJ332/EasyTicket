package application.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewTicketController {

	@FXML private VBox newTicketBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextField ticketNameTextField;
	@FXML private TextArea ticketDescrTextArea;
	@FXML private Text errorText;

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {

	}

	/*
	 * Cancel new ticket and close the NewTicket window.
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
		// Store Ticket info
		
		// Close this window
		cancelButtonAction();
	}
}

