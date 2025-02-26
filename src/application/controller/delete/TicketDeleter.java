package application.controller.delete;

import java.util.ArrayList;

import application.CommonObjs;
import application.controller.dictionary.TicketDict;
import application.dao.CommentDAO;
import application.dao.TicketDAO;
import content.bean.CommentBean;
import javafx.scene.layout.VBox;

public class TicketDeleter {
	
	private TicketDAO ticketDAO;
	private TicketDict ticketDict;
	private CommentDAO commentDAO;
	
	private CommentDeleter commentDeleter;
	
	private CommonObjs commonObjs;
	
	public TicketDeleter() {
		ticketDAO = new TicketDAO();
		ticketDict = TicketDict.getTicketDict();
		commentDAO = new CommentDAO();
		
		commentDeleter = new CommentDeleter();
		
		commonObjs = CommonObjs.getInstance();
	}
	
	/**
	 * Delete a Ticket from the database, dictionary, and wherever it is displayed
	 * 
	 * @param id	ID of Ticket to delete
	 */
	public void deleteTicket(String id) {
		// First delete all Comments that belong to this Ticket
		ArrayList<CommentBean> ticketComments = commentDAO.getAllCommentsByTicketId(id);
		for (CommentBean commentBean : ticketComments) {
			commentDeleter.deleteComment(commentBean.getId());
		}
		// Then remove all of Ticket's displayed contents
		VBox parentVBox = commonObjs.getTicketVBox();
		VBox ticketContent = ticketDict.getTicketContentController(id).getTicketContent();
		parentVBox.getChildren().remove(ticketContent);
		// Then delete the Ticket from the dictionary
		ticketDict.removeTicket(id);
		// Lastly, delete the Ticket from the database
		ticketDAO.deleteTicket(id);
	}
}
