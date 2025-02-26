package application.controller.dictionary;

import java.util.HashMap;
import java.util.Map;

import content.controller.TicketContentController;

public class TicketDict {
	private static TicketDict singleInstance = new TicketDict();

	/*
	 * A HashMap with id as key and a TicketContentController that belong to id as value
	 */
	private static HashMap<String,TicketContentController> ticketDict = new HashMap<String,TicketContentController>();

	private TicketDict() {}

	public static TicketDict getTicketDict() {
		return singleInstance;
	}

	/**
	 * Add a TicketContentController to the dictionary
	 * 
	 * @param id		id of Ticket
	 * @param controller	TicketContentController corresponding to the id
	 */
	public void addTicket(String id, TicketContentController controller) {
		ticketDict.put(id, controller);
	}

	/**
	 * Returns a TicketController with the given id
	 * @param id
	 * @return	a TicketContentController with ticketName
	 */
	public TicketContentController getTicketContentController(String id) {
		return ticketDict.get(id);
	}
	
	/**
	 * Removes a Ticket from the dictionary
	 * 
	 * @param id	ID of Ticket to remove
	 */
	public void removeTicket(String id) {
		ticketDict.remove(id);
	}

	@Override
	public String toString() {
		String s = "";
		String key;
		TicketContentController controller;
		for (Map.Entry<String, TicketContentController> entry : ticketDict.entrySet()) {
			key = entry.getKey();
			controller = entry.getValue();

			s += key + ": ";
			s += controller.getName() + " | " + controller.getDescr() + "\n";
		}
		return s;
	}
}
