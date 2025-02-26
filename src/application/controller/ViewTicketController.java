package application.controller;

import java.io.IOException;

import application.controller.delete.TicketDeleter;
import application.controller.edit.EditTicketController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ViewTicketController {
	@FXML private VBox viewTicketBox;
	@FXML private Text ticketNameText;
	@FXML private Text ticketDescrText;
	@FXML private VBox commentVBox;
	@FXML private Button addCommentButton;
	@FXML private Button editButton;
	@FXML private Button deleteButton;
	@FXML private Button finishButton;

	private String id;
	private String ticketName;
	private String ticketDescr;

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
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/NewComment.fxml"));

			VBox root = (VBox) loader.load();

			NewCommentController controller = (NewCommentController)loader.getController();
			controller.setTicketId(id);

			Stage newCommentPage = new Stage();
			newCommentPage.setTitle("New Comment");
			newCommentPage.setScene(new Scene(root));
			newCommentPage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Edit the Ticket's name or description.
	 */
	@FXML
	private void editButtonAction() {
		// Open the EditTicket page and pass in this Ticket's data
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/EditTicket.fxml"));
			VBox root= (VBox) loader.load();
			EditTicketController controller = (EditTicketController)loader.getController();
			controller.setId(id);	
			controller.setName(ticketName);
			controller.setDescr(ticketDescr);

			Stage editTicketPage = new Stage();
			editTicketPage.setTitle("Edit Ticket");
			editTicketPage.setScene(new Scene(root));
			editTicketPage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Delete the Ticket, including all its comments.
	 */
	@FXML
	private void deleteButtonAction() {
		// First close this window
		finishButtonAction();
		// Then delete the Ticket
		TicketDeleter deleter = new TicketDeleter();
		deleter.deleteTicket(id);
	}

	/*
	 * Close the ViewTicketPage
	 */
	@FXML private void finishButtonAction() {
		// Get the Stage
		Stage stage = (Stage) finishButton.getScene().getWindow();	
		// Close it
		stage.close();
	}

	public String getName() {
		return ticketName;
	}

	public void setName(String ticketName) {
		this.ticketName = ticketName;
		this.ticketNameText.setText(ticketName); 
	}

	public String getDescr() {
		return ticketDescr;
	}

	public void setDescr(String ticketDescr) {
		this.ticketDescr = ticketDescr;
		this.ticketDescrText.setText(ticketDescr); 
	}

	public VBox getCommentVBox() {
		return this.commentVBox;
	}

	public Text getNameText() {
		return ticketNameText;
	}

	public void setId(String id) {
		this.id = id;
	}


}
