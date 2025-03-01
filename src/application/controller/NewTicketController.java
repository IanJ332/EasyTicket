package application.controller;

import java.io.IOException;

import application.CommonObjs;
import application.controller.dictionary.TicketDict;
import application.dao.IDGenerator;
import application.dao.TicketDAO;
import content.bean.TicketBean;
import content.controller.TicketContentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewTicketController {

	private CommonObjs commonObjs = CommonObjs.getInstance();

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

			// Get the description from the textArea
			descr = ticketDescrTextArea.getText();

			// Generate an ID for this ticket
			id = IDGenerator.getIDGenerator().getRandomID();

			// Package data into a TicketBean
			TicketBean ticketBean = new TicketBean(id, projectId, name, descr);

			// Create a DAO
			TicketDAO dao = new TicketDAO();

			// Store the Ticket to database through the DAO
			dao.storeTicket(ticketBean);

			// Show the Ticket
			showTicketContent(id, projectId, name, descr);

			// Close this window
			cancelButtonAction();
		}
	}	

	private void throwNoTicketNameExeption() {
		errorText.setVisible(true);
	}

	/**
	 * Shows the new Ticket content in the Main Menu
	 */
	public void showTicketContent(String id, String projectId, String name, String descr) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/TicketContent.fxml"));
			VBox contentVBox = (VBox) loader.load();
			TicketContentController controller = (TicketContentController)loader.getController();
			controller.setId(id);
			controller.setName(name);
			controller.setDescr(descr);
			controller.initializeViewTicketPage();
			TicketDict.getTicketDict().addTicket(id, controller);

			commonObjs.getTicketVBox().getChildren().add(contentVBox);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setProjectId(String project) {
		this.projectId = project;
	}


}

