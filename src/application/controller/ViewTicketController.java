package application.controller;

import java.util.ArrayList;

import application.dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ViewTicketController {
	@FXML private VBox viewTicketBox;
	@FXML private Text ticketNameText;
	@FXML private Text ticketDescrText;
	@FXML private VBox commentVBox;
	@FXML private Button addCommentButton;
	@FXML private Button finishButton;
	
	
	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {

	}
	
	/*
	 * Open the NewCommentPage
	 */
	@FXML private void addCommentButtonAction() {
		
	}
	
	/*
	 * Close the ViewTicketPage
	 */
	@FXML private void finishButtonAction() {
		
	}
}
