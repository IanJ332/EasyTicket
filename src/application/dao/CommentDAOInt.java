package application.dao;

import java.util.ArrayList;

import content.bean.CommentBean;

public interface CommentDAOInt {
	/**
	 * Store the comment.
	 * 
	 * @param projectBean	The comment to store
	 */
	public void storeComment(CommentBean commentBean);
	
	/**
	 * Returns a Comment entry in the database.
	 * 
	 * @param index	Index of the comment to return
	 * @return	A CommentBean that contains the comment's details in the follow order: id, TicketId, Timestamp, description
	 */ 
	public CommentBean readCommentEntry(int index);
	
	/**
	 * Returns a Comment entry in the database
	 * @param id	Id of the comment to return
	 * @return		A CommentBean that contains the comment's details in the follow order: id, TicketId, Timestamp, description
	 */
	public CommentBean readCommentEntry(String id);
	
	/**
	 * Returns an ArrayList of all CommentBeans who belong to a specific Ticket
	 * 
	 * @param ticketId	ID of ticket to get all Comments of
	 * @return	ArrayList of all Comments that belong to ticketId
	 */
	public ArrayList<CommentBean> getAllCommentsByTicketId(String ticketId);
	
	/**
	 * Delete a Comment from the database
	 * 
	 * @param id	ID of comment to delete
	 */
	public void deleteComment(String id);
	
	/**
	 * Edit a Comment in the database
	 * 
	 * @param id	ID of comment to edit
	 * @param bean	CommentBean containing new information to update with
	 */
	public void editComment(String id, CommentBean bean);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size();
}
