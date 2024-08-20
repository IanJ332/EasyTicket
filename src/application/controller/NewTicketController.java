package application.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
	    // Get the name from ticketNameTextField and check it is not empty
	    String name = ticketNameTextField.getText();
	    if (name.isEmpty()) {
	        throwNoTicketNameException();
	    } else {
	        // Get the description from the ticketDescrTextArea
	        String description = ticketDescrTextArea.getText();

	        // TODO: Store the ticket information in your desired way (e.g., in a data structure, file, or database)
	        storeTicketInfo(name, description);

	        // Close this window
	        cancelButtonAction();
	    }
	}

	/**
	 * Store the ticket information (name and description) in your desired way.
	 *
	 * @param name        The name of the ticket.
	 * @param description The description of the ticket.
	 */
	private void storeTicketInfo(String name, String description) {
	    // Implement the logic to store the ticket information
	    // You can choose to store it in your preferred data structure or data storage method.
	    // Replace this with your actual storage logic.
	    
	    // Example: Storing the ticket in a data structure (map)
	    Map<String, String> ticketInfo = new HashMap<>();
	    ticketInfo.put("Name", name);
	    ticketInfo.put("Description", description);
	    
	    // You can now work with 'ticketInfo' to save or process the ticket data further.
	}
	/**
	 * Tell the user that the ticket name field cannot be left empty.
	 */
	private void throwNoTicketNameException() {
	    // Display an error message to the user (e.g., in a Text control or a dialog)
	    errorText.setText("Ticket name cannot be empty.");
	    errorText.setVisible(true);
	}
}

