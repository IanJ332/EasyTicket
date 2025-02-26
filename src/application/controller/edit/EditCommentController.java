package application.controller.edit;

import java.text.SimpleDateFormat;

import application.controller.dictionary.CommentDict;
import application.dao.CommentDAO;
import content.bean.CommentBean;
import content.controller.CommentContentController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditCommentController {
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
		// Set new timestamp for edited comment
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
			// Create a DAO
			CommentDAO dao = new CommentDAO();
			
			// Get the id of the Ticket this Comment belongs to
			ticketId = dao.readCommentEntry(id).getTicketId();
			
			// Package data into a CommentBean
			CommentBean commentBean = new CommentBean(id, ticketId, timestamp, descr);

			// Store the edited comment to database through the DAO
			dao.editComment(id, commentBean);

			// Show the edited comment
			updateCommentContent(id,timestamp, descr);

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		timestampText.setText(timestamp);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		commentDescrTextArea.setText(descr);
	}

	public void updateCommentContent(String id, String timestamp, String descr) {
		CommentDict dict = CommentDict.getCommentDict();
		// Edit the Comment's information through its controller
		CommentContentController controller = dict.getCommentContentController(id);
		controller.setTimestamp(timestamp);
		controller.setDescr(descr);
	}
}
