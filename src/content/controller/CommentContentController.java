package content.controller;

import java.io.IOException;

import application.controller.delete.CommentDeleter;
import application.controller.edit.EditCommentController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CommentContentController {
	@FXML private VBox commentContent;
	@FXML private Text timestampText;
	@FXML private Text descriptionText;
	@FXML private Button editButton;
	@FXML private Button deleteButton;

	private String id;
	private String timestamp;
	private String descr;

	@FXML
	public void initialize() {

	}

	@FXML
	private void editButtonAction() {
		// Open the EditComment page and pass in this Comment's ID and previous information
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/EditComment.fxml"));
			VBox root= (VBox) loader.load();
			EditCommentController controller = (EditCommentController)loader.getController();
			controller.setId(id);
			controller.setDescr(descr);

			Stage editCommentPage = new Stage();
			editCommentPage.setTitle("Edit Comment");
			editCommentPage.setScene(new Scene(root));
			editCommentPage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void deleteButtonAction() {
		// Delete the Comment
		CommentDeleter deleter = new CommentDeleter();
		deleter.deleteComment(id);
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
		timestampText.setText(timestamp);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		descriptionText.setText(descr);
	}

	public String getDescr() {
		return descr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VBox getCommentContent() {
		return commentContent;
	}

}
