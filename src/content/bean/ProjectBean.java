package content.bean;

public class ProjectBean {
	
	public ProjectBean(String name, String startDate, String descr) {
		setName(name);
		setStartDate(startDate);
		setDescr(descr);
	}
	
	private String name;
	private String startDate;
	private String descr;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getDescr() {
		return descr;
	}
	
	public void setDescr(String descr) {
		this.descr = descr;
	}
}
