package application.dao;

import java.util.ArrayList;

import content.bean.CommentBean;
import databaseAccessLayer.CommentFileIO;

public class CommentDAO implements CommentDAOInt{
	private CommentFileIO database = new CommentFileIO();

	public CommentDAO() {}

	@Override
	public void storeComment(CommentBean commentBean) {
		ArrayList<String> dataArray = new ArrayList<String>();
		dataArray.add(commentBean.getId());
		dataArray.add(commentBean.getTicketId());
		dataArray.add(commentBean.getTimestamp());
		dataArray.add(commentBean.getDescr());

		database.store(dataArray);
	}

	@Override
	public CommentBean readCommentEntry(int index) {
		ArrayList<String> data = database.read().get(index);
		CommentBean bean = new CommentBean(data.get(0),data.get(1),data.get(2),data.get(3));
		return bean;
	}

	@Override
	public CommentBean readCommentEntry(String id) {
		ArrayList<String> data = database.readID(id);
		CommentBean bean = new CommentBean(data.get(0),data.get(1),data.get(2),data.get(3));
		return bean;
	}

	@Override
	public int size() {
		return database.size();
	}

	@Override
	public void deleteComment(String id) {
		database.deleteEntry(id);
	}

	@Override
	public void editComment(String id, CommentBean bean) {
		deleteComment(id);
		storeComment(bean);
	}

	@Override
	public ArrayList<CommentBean> getAllCommentsByTicketId(String ticketId) {
		ArrayList<CommentBean> result = new ArrayList<CommentBean>();

		// First get all Comment entries from the database
		ArrayList<ArrayList<String>> data = database.read();
		// Get all Comments that belong to ticketId
		CommentBean bean;
		for (ArrayList<String> commentData : data) {
			bean = new CommentBean(commentData.get(0), commentData.get(1), commentData.get(2), commentData.get(3)); 
			if (bean.getTicketId().equals(ticketId)) {
				result.add(bean);
			}
		}
		return result;
	}

}
