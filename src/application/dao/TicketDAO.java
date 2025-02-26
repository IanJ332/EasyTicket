package application.dao;

import java.util.ArrayList;

import content.bean.TicketBean;
import databaseAccessLayer.TicketFileIO;

public class TicketDAO implements TicketDAOInt {
	private TicketFileIO database = new TicketFileIO();
	
	public TicketDAO(){};
	

	public void storeTicket(TicketBean bean) {
		ArrayList<String> dataArray = new ArrayList<String>();
		dataArray.add(bean.getId());
		dataArray.add(bean.getProjectId());
		dataArray.add(bean.getName());
		dataArray.add(bean.getDescr());

		
		database.store(dataArray);
	}
	

	public TicketBean readTicketEntry(int index) {
		ArrayList<String> data = database.read().get(index);
		TicketBean bean = new TicketBean(data.get(0),data.get(1),data.get(2),data.get(3));
		return bean;
	}
	
	@Override
	public TicketBean readTicketEntry(String id) {
		ArrayList<String> data = database.readID(id);
		TicketBean bean = new TicketBean(data.get(0),data.get(1),data.get(2),data.get(3));
		return bean;
	}

	public int size() {
		return database.size();
	}


	@Override
	public void deleteTicket(String id) {
		database.deleteEntry(id);
		
	}


	@Override
	public void editTicket(String id, TicketBean bean) {
		deleteTicket(id);
		storeTicket(bean);
	}


	@Override
	public ArrayList<TicketBean> getAllTicketsByProjectId(String projectId) {
		ArrayList<TicketBean> result = new ArrayList<TicketBean>();
		
		// First get all Ticket entries from the database
		ArrayList<ArrayList<String>> data = database.read();
		// Get all Tickets that belong to projectId
		TicketBean bean;
		for (ArrayList<String> ticketData : data) {
			bean = new TicketBean(ticketData.get(0), ticketData.get(1), ticketData.get(2), ticketData.get(3)); 
			if (bean.getProjectId().equals(projectId)) {
				result.add(bean);
			}
		}
		return result;
	}
	
	
}
