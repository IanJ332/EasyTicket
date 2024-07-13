package application.dao;

import java.util.ArrayList;

import content.bean.TicketBean;

public interface TicketDAOInt {
	/**
	 * Store the ticket.
	 * 
	 * @param ticketBean	The ticket to store
	 */
	public void storeTicket(TicketBean ticketBean);
	
	/**
	 * Returns an entry in the database.
	 * 
	 * @param index	Index of the entry to return
	 * @return	An ArrayList that contains the ticket's details in the follow order: name, description
	 */
	public ArrayList<String> readTicketEntry(int index);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size();
}
