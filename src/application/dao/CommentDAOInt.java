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
	 * Returns an entry in the database.
	 * 
	 * @param index	Index of the entry to return
	 * @return	An ArrayList that contains the comment's details in the follow order: description
	 */ 
	public ArrayList<String> readCommentEntry(int index);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size();
}
