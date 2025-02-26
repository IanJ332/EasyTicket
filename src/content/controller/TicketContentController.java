package content.controller;

import java.io.IOException;

import application.controller.ViewTicketController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TicketContentController {
	@FXML private VBox ticketContent;
	@FXML private Text nameText;
	@FXML private Text descrText;
	
	private String id;
	private String name;
	private String descr;
	
	private Stage viewTicketPage;
	private ViewTicketController viewTicketController;

	/*
	 * Perform all necessary post-processing.
	 */
	@FXML 
	public void initialize() {

	}
	
	public VBox getTicketContent() {
		return this.ticketContent;
	}

	public void setName(String name) {
		this.name = name;
		nameText.setText(name);
		if (viewTicketController != null) {
			viewTicketController.setName(name);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setDescr(String descr) {
		this.descr = descr;
		descrText.setText(descr);
		if (viewTicketController != null) {
			viewTicketController.setDescr(descr);
		}
	}
	
	public String getDescr() {
		return descr;
	}
	
	public ViewTicketController getViewTicketController() {
		return viewTicketController;
	}

	/*
	 * Create a page that displays Ticket information and allows for 
	 * adding comments. This page is shown when the Ticket is clicked.
	 */
	public void initializeViewTicketPage() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("view/ViewTicket.fxml"));
			VBox root = (VBox) loader.load();
			viewTicketController = (ViewTicketController)loader.getController();
			viewTicketController.setName(name);
			viewTicketController.setDescr(descr);
			viewTicketController.setId(id);
			
			viewTicketPage = new Stage();
			viewTicketPage.setTitle("View Ticket");
			viewTicketPage.setScene(new Scene(root));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * Opens the ViewTicketPage
	 */
	@FXML
	private void openViewTicketPage() {
		viewTicketPage.show();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
