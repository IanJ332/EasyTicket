package application.controller.dictionary;

import java.util.HashMap;
import java.util.Map;

import content.controller.CommentContentController;

public class CommentDict {
	private static CommentDict singleInstance = new CommentDict();

	/*
	 * A HashMap with id as key and a CommentContentController that belong to id as value
	 */
	private static HashMap<String,CommentContentController> commentDict = new HashMap<String,CommentContentController>();

	private CommentDict() {}

	public static CommentDict getCommentDict() {
		return singleInstance;
	}

	/**
	 * Add a CommentContentController to the dictionary
	 * 
	 * @param id		ID of comment
	 * @param controller	CommentContentController corresponding to the id
	 */
	public void addComment(String id, CommentContentController controller) {
		commentDict.put(id, controller);
	}
	
	/**
	 * Returns the CommentContentController that belongs to the given ID
	 * 
	 * @param id	ID of Comment whose CommentContentController to return
	 * @return		CommentContentController corresponding to id
	 */
	public CommentContentController getCommentContentController(String id) {
		return commentDict.get(id);
	}
	
	/**
	 * Removes a Comment from the dictionary.
	 * 
	 * @param id	ID of comment to remove
	 */
	public void removeComment(String id) {
		commentDict.remove(id);
	}
	

	@Override
	public String toString() {
		String s = "";
		String key;
		CommentContentController controller;
		for (Map.Entry<String, CommentContentController> entry : commentDict.entrySet()) {
			key = entry.getKey();
			controller = entry.getValue();

			s += key + ": ";
			s += controller.getTimestamp() + " | " + controller.getDescr() + "\n";

		}
		return s;
	}
}
