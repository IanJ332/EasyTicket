package content.controller;

import java.io.IOException;

import application.controller.ViewProjectController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ProjectContentController {
	@FXML private VBox projectContent;
	@FXML private Text nameText;
	@FXML private Text dateText;
	@FXML private Text descrText;

	private String id;
	private String name;
	private String date;
	private String descr;

	private Stage viewProjectPage;
	private ViewProjectController viewProjectController;

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {

	}

	public VBox getProjectContent() {
		return this.projectContent;
	}

	public void setName(String name) {
		this.name = name;
		nameText.setText(name);
		if (viewProjectController != null) {
			viewProjectController.setName(name);
		}

	}

	public String getName() {
		return name;
	}

	public void setDate(String date) {
		this.date = date;
		dateText.setText(date);
		if (viewProjectController != null) {
			viewProjectController.setDate(date);
		}
	}

	public String getDate() {
		return date;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		descrText.setText(descr);
		if (viewProjectController != null) {
			viewProjectController.setDescr(descr);
		}
	}

	public String getDescr() {
		return descr;
	}

	/*
	 * Create a page that displays Project information and allows for 
	 * creating Tickets. This page is shown when the Project is clicked.
	 */
	public void initializeViewProjectPage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ViewProject.fxml"));
			VBox root = (VBox) loader.load();
			ViewProjectController controller = (ViewProjectController)loader.getController();
			controller.setId(id);
			controller.setName(name);
			controller.setDate(date);
			controller.setDescr(descr);
			viewProjectController = controller;

			viewProjectPage = new Stage();
			viewProjectPage.setTitle("View Project");
			viewProjectPage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Open NewTicketPage
	 */
	@FXML 
	private void openViewProjectPage() {
		viewProjectPage.show();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
