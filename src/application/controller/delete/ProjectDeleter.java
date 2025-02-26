package application.controller.delete;

import java.util.ArrayList;

import application.CommonObjs;
import application.controller.dictionary.ProjectDict;
import application.dao.ProjectDAO;
import application.dao.TicketDAO;
import content.bean.TicketBean;
import javafx.scene.layout.VBox;

/**
 * A helper class to delete projects
 */
public class ProjectDeleter {
	private ProjectDAO projectDAO;
	private ProjectDict projectDict;
	private TicketDAO ticketDAO;
	
	private TicketDeleter ticketDeleter;
	
	private CommonObjs commonObjs;
	
	public ProjectDeleter(){
		projectDAO = new ProjectDAO();
		projectDict = ProjectDict.getProjectDict();
		ticketDAO = new TicketDAO();
		
		ticketDeleter = new TicketDeleter();
		
		commonObjs = CommonObjs.getInstance();
	}
	
	/**
	 * 
	 * Deletes a Project and ALL its contents, including all its tickets and comment entries from the database.
	 * Also remove all displayed contents that belonged to this Project.
	 * 
	 * @param	id	ID of Project to delete
	 */
	public void deleteProject(String id) {
		// First delete all Tickets that belong to this Project (which will also delete all of their Comments)
		ArrayList<TicketBean> projectTickets = ticketDAO.getAllTicketsByProjectId(id);
		for (TicketBean ticketBean : projectTickets) {
			ticketDeleter.deleteTicket(ticketBean.getId());
		}
		// Then remove all of this Project's displayed contents
		VBox parentVBox = commonObjs.getProjectVBox();
		VBox projectContent = projectDict.getProjectContentController(id).getProjectContent();
		parentVBox.getChildren().remove(projectContent);
		// Then delete this Project from the dictionary
		projectDict.removeProject(id);
		// Lastly, delete this Project from the database
		projectDAO.deleteProject(id);
	}
	
	
	

}
