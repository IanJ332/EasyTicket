package content.controller;

import java.util.ArrayList;

import application.dao.ProjectDAO;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProjectContentController {

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
}
