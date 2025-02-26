package content.bean;

public class ProjectBean {
	
	public ProjectBean(String id, String name, String date, String descr) {
		setId(id);
		setName(name);
		setDate(date);
		setDescr(descr);
	}
	
	private String id;
	private String name;
	private String date;
	private String descr;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String startDate) {
		this.date = startDate;
	}
	
	public String getDescr() {
		return descr;
	}
	
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
	@Override
	public String toString() {
		return id + " " + name + " " + date + " " + descr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
