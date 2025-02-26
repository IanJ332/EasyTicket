package content.bean;

public class CommentBean {
	private String id;
	private String ticketId;
	private String timestamp;
	private String descr;

	public CommentBean(String id, String ticket, String timestamp, String descr) {
		setId(id);
		setTicketId(ticket);
		setTimestamp(timestamp);
		setDescr(descr);
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getTicketId() {
		return ticketId;
	}

	public void setTicketId(String ticket) {
		this.ticketId = ticket;
	}
	
	@Override
	public String toString() {
		return id + " " + ticketId + " " + timestamp + " " + descr;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
