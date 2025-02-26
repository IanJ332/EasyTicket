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
	 * @return	A TicketBean that contains the ticket's details in the follow order: id, ProjectId, name, description
	 */
	public TicketBean readTicketEntry(int index);
	
	/**
	 * Returns an entry in the database.
	 * 
	 * @param id	Id of the ticket to return
	 * @return		A TicketBean that contains the ticket's details in the follow order: id, ProjectId, name, description
	 *
	 */
	public TicketBean readTicketEntry(String id);
	
	/**
	 * Returns an ArrayList of all TicketBeans who belong to a specific Project
	 * 
	 * @param projectId	ID of Project to fetch all Tickets from
	 * @return	An ArrayList of TicketBeans
	 */
	public ArrayList<TicketBean> getAllTicketsByProjectId(String projectId);
	
	/**
	 * Delete a Ticket from the database
	 * 
	 * @param id	Id of ticket to delete
	 */
	public void deleteTicket(String id);
	
	/**
	 * Edit a Ticket in the database
	 * 
	 * @param id	ID of Ticket to edit
	 * @param bean	TicketBean containing new information to update with
	 */
	public void editTicket(String id, TicketBean bean);
	
	/**
	 * Return the size of the database
	 * 
	 * @return	Number of entries in the database
	 */
	public int size() ;
}
