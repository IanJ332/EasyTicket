package application.controller.search;

import java.util.ArrayList;

import application.controller.dictionary.ProjectDict;
import application.controller.dictionary.TicketDict;
import application.dao.ProjectDAO;
import application.dao.TicketDAO;
import content.bean.TicketBean;
import content.controller.TicketContentController;

/**
 * This class is used to search for a Ticket.
 * 
 * Methods can return a list of all ProjectContentControllers whose respective 
 * projects contain a certain substring in their name
 */
public class TicketSearch {
	private TicketDAO ticketDAO;
	private ProjectDAO projectDAO; 
	
	public TicketSearch() {
		ticketDAO = new TicketDAO();
		projectDAO = new ProjectDAO();
	}
	
	/**
	 * Returns an ArrayList of TicketContentControllers whose respective tickets
	 * contain 'keyword' in the ticket's title OR project's name
	 * @param keyword	The substring to search for
	 * @return	An ArrayList of TicketContentControllers
	 */
	public ArrayList<TicketContentController> searchContainsSubstring(String keyword){
		ArrayList<TicketContentController> result = new ArrayList<TicketContentController>();
		// Size of ticket database to read
		int size = ticketDAO.size();
		// Read through every entry to see if the name field or project name contains 'keyword'
		TicketBean ticketBean;
		String ticketId, ticketName, projId, projName;
		for (int i = 0; i < size; i++) {
			// First read an entry in the ticket database to try to find a title that contains 'keyword', then search
			// that ticket's project to see if the project's name contains 'keyword'
			ticketBean = ticketDAO.readTicketEntry(i);
			
			// Get the information required
			ticketId = ticketBean.getId();
			ticketName = ticketBean.getName();
			projId = ticketBean.getProjectId();
			projName = ProjectDict.getProjectDict().getProjectContentController(projId).getName();
			
			if (ticketName.contains(keyword) || projName.contains(keyword)) {
				// If the ticket title or project name contains keyword, add its controller to the result list
				result.add(TicketDict.getTicketDict().getTicketContentController(ticketId));
			}
		}
		return result;
	}
	
	/**
	 * Returns an ArrayList of TicketContentControllers whose respective tickets
	 * do NOT contain 'keyword' in the ticket's title OR project's name
	 * @param keyword	The substring to search for
	 * @return	An ArrayList of TicketContentControllers
	 */
	public ArrayList<TicketContentController> searchDoesNotContainSubstring(String keyword){
		ArrayList<TicketContentController> result = new ArrayList<TicketContentController>();
		// Size of ticket database to read
		int size = ticketDAO.size();
		// Read through every entry to see if the name field or project name contains 'keyword'
		TicketBean ticketBean;
		String ticketId, ticketName, projId, projName;
		for (int i = 0; i < size; i++) {
			// First read an entry in the ticket database to try to find a title that contains 'keyword', then search
			// that ticket's project to see if the project's name contains 'keyword'
			ticketBean = ticketDAO.readTicketEntry(i);
			
			// Get the information required
			ticketId = ticketBean.getId();
			ticketName = ticketBean.getName();
			projId = ticketBean.getProjectId();
			projName = projectDAO.readProjectEntry(projId).getName();
			
			if (!ticketName.contains(keyword) && !projName.contains(keyword)) {
				// If the ticket title or project name contains keyword, add its controller to the result list
				result.add(TicketDict.getTicketDict().getTicketContentController(ticketId));
			}
		}
		return result;
	}
	
}
