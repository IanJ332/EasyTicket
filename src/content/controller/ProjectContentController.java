package content.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import application.dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectContentController {
	
	@FXML private VBox projectContentBox;
	@FXML private Text nameText;
	@FXML private Text dateText;
	@FXML private Text descrText;
	
	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {
		// Display the last saved Project
		ProjectDAO dao = new ProjectDAO();
		ArrayList<String> data = dao.readProjectEntry(dao.size()-1);
		nameText.setText(data.get(0));
		dateText.setText(data.get(1));
		descrText.setText(data.get(2));
	}
	
	/*
	 * Open NewTicketPage
	 */
	@FXML 
	private void openNewTicketPage() {
		URL url = getClass().getClassLoader().getResource("view/NewTicket.fxml");

		try {
			VBox root = (VBox) FXMLLoader.load(url);
			Stage newProjectStage = new Stage();
			newProjectStage.setTitle("New Ticket");
			newProjectStage.setScene(new Scene(root));
			newProjectStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
