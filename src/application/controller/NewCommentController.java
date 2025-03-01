package application.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import application.controller.dictionary.CommentDict;
import application.controller.dictionary.TicketDict;
import application.dao.CommentDAO;
import application.dao.IDGenerator;
import content.bean.CommentBean;
import content.controller.CommentContentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewCommentController {
	@FXML private VBox newCommentBox;
	@FXML private Button cancelButton;
	@FXML private Button saveButton;
	@FXML private TextArea commentDescrTextArea;
	@FXML private Text errorText;
	@FXML private Text timestampText;

	private String id;
	private String ticketId;
	private String timestamp;
	private String descr;

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {
		timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
		timestampText.setText(timestamp);
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
		// Store Comment info
		// Get the name from nameTextField and check it is not empty
		descr = commentDescrTextArea.getText();
		if (descr.equals("")) {
			throwMissingCommentDescrExeption();
		} else {
			// Generate an ID for this ticket
			id = IDGenerator.getIDGenerator().getRandomID();
			
			// Package data into a CommentBean
			CommentBean commentBean = new CommentBean(id, ticketId, timestamp, descr);

			// Create a DAO
			CommentDAO dao = new CommentDAO();

			// Store the comment to database through the DAO
			dao.storeComment(commentBean);

			// Show the comment
			showCommentContent(id, ticketId, timestamp, descr);

			// Close this window
			cancelButtonAction();
		}
	}

	private void throwMissingCommentDescrExeption() {
		errorText.setVisible(true);
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticketId) {
		this.ticketId = ticketId;
	}

	public void showCommentContent(String id, String ticketId, String timestamp, String descr) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/CommentContent.fxml"));

			VBox root = (VBox) loader.load();

			CommentContentController controller = (CommentContentController)loader.getController();
			controller.setId(id);
			controller.setTimestamp(timestamp);
			controller.setDescr(descr);
			CommentDict.getCommentDict().addComment(id, controller);
			
			VBox commentVBox = TicketDict.getTicketDict().getTicketContentController(ticketId).getViewTicketController().getCommentVBox();
			commentVBox.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
