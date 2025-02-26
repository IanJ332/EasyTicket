package application.controller.edit;

import application.controller.dictionary.TicketDict;
import application.dao.TicketDAO;
import content.bean.TicketBean;
import content.controller.TicketContentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditTicketController {
	@FXML private VBox newTicketBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextField ticketNameTextField;
	@FXML private TextArea ticketDescrTextArea;
	@FXML private Text errorText;

	private String id;
	private String projectId;
	private String name;
	private String descr;


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
		// Get the name from nameTextField and check it is not empty
		name = ticketNameTextField.getText();
		if (name.equals("")) {
			throwNoTicketNameExeption();
		} else {
			// Create a DAO
			TicketDAO dao = new TicketDAO();

			// Get the id of the Project this Ticket belongs to
			projectId = dao.readTicketEntry(id).getProjectId();
			
			// Get the description from the textArea
			descr = ticketDescrTextArea.getText();

			// Package data into a TicketBean
			TicketBean ticketBean = new TicketBean(id, projectId, name, descr);

			// Edit the Ticket in the database through the DAO
			dao.editTicket(id, ticketBean);

			// Show the edited Ticket
			updateTicketContent(id, name, descr);

			// Close this window
			cancelButtonAction();
		}
	}	

	private void throwNoTicketNameExeption() {
		errorText.setVisible(true);
	}

	/**
	 * Update the new Ticket content in the Main Menu
	 */
	public void updateTicketContent(String id, String name, String descr) {
		TicketDict dict = TicketDict.getTicketDict();
		TicketContentController controller = dict.getTicketContentController(id);
		// Update the controller's content
		controller.setName(name);
		controller.setDescr(descr);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		ticketNameTextField.setText(name);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		ticketDescrTextArea.setText(descr);
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String project) {
		this.projectId = project;
	}
}
