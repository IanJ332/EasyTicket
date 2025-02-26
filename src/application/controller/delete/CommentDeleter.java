package application.controller.delete;

import application.controller.dictionary.CommentDict;
import application.controller.dictionary.TicketDict;
import application.dao.CommentDAO;
import javafx.scene.layout.VBox;

public class CommentDeleter {
	
	private CommentDAO commentDAO;
	private CommentDict commentDict;
	private TicketDict ticketDict;
	
	public CommentDeleter() {
		commentDAO = new CommentDAO();
		commentDict = CommentDict.getCommentDict();
		ticketDict = TicketDict.getTicketDict();
	}
	
	/**
	 * Delete a Comment from the database, the dictionary, and wherever it is displayed
	 * 
	 * @param id	ID of Comment to delete
	 */
	public void deleteComment(String id) {
		// First remove the Comment's displayed contents from the CommentVBox in whichever ViewTicketPage the Comment belonged to
		VBox commentContent = commentDict.getCommentContentController(id).getCommentContent();
		String ticketID = commentDAO.readCommentEntry(id).getTicketId();	// id of Ticket this comment belongs to
		VBox parentVBox = ticketDict.getTicketContentController(ticketID).getViewTicketController().getCommentVBox();
		parentVBox.getChildren().remove(commentContent);
		// Then delete the Comment from the dictionary
		commentDict.removeComment(id);
		// Lastly, delete the Comment from the database
		commentDAO.deleteComment(id);
	}
}
