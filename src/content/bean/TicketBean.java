package content.bean;

public class TicketBean {
	private String id;
	private String projectId;
	private String name;
	private String descr;
	
	public TicketBean(String id, String projectId, String name, String descr) {
		setId(id);
		setProjectId(projectId);
		setName(name);
		setDescr(descr);
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String project) {
		this.projectId = project;
	}
	
	@Override
	public String toString() {
		return id + " " + projectId + " " + name + " " + descr;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
