package application.controller;

import java.io.IOException;
import java.util.ArrayList;

import application.dao.ProjectDAO;
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
	@FXML private Button finishButton;
	
	
	/*
	 * Perform all necessary post-processing.
	 */
	@FXML
	private void addCommentButtonAction() {
	    // Check if there is relevant ticket information available
	    if (ticketNameText.getText() != null && !ticketNameText.getText().isEmpty()) {
	        // Open the NewCommentPage
	        // You can add code to open the new comment page here
	        // For example, you can open a new window or navigate to a new FXML view.

	        // Assuming you have a NewCommentController and a method to open the new comment page
	        NewCommentController newCommentController = new NewCommentController();

	        // Pass the relevant ticket information to the new comment page (you can adapt this to your project's structure)


	        openNewCommentPage(newCommentController);
	    } else {
	        // Handle the case where there is no relevant ticket information
	        // You can display a message to the user or take other actions as needed.
	    }
	}

	// Example method to open the new comment page (you should adapt this to your project's structure)
	private void openNewCommentPage(NewCommentController newCommentController) {
	    try {
	        // Load the FXML for the new comment page
	        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/NewComment.fxml"));
	        loader.setController(newCommentController);

	        // Create a new stage for the new comment page
	        Stage newCommentStage = new Stage();
	        newCommentStage.setTitle("New Comment");
	        newCommentStage.setScene(new Scene(loader.load()));
	        newCommentStage.show();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
