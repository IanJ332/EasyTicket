package application.dao;

import java.util.ArrayList;

import content.bean.ProjectBean;
import databaseAccessLayer.ProjectFileIO;

public class ProjectDAO implements ProjectDAOInt{

	private ProjectFileIO database = new ProjectFileIO();

	public ProjectDAO() {};

	@Override
	public void storeProject(ProjectBean bean) {
		ArrayList<String> dataArray = new ArrayList<String>();
		dataArray.add(bean.getId());
		dataArray.add(bean.getName());
		dataArray.add(bean.getDate());
		dataArray.add(bean.getDescr());

		database.store(dataArray);
	}

	@Override
	public ProjectBean readProjectEntry(int index) {
		ArrayList<String> data = database.read().get(index);

		ProjectBean bean = new ProjectBean(data.get(0),data.get(1), data.get(2), data.get(3));
		return bean;
	}

	@Override
	public ProjectBean readProjectEntry(String id) {
		ArrayList<String> data = database.readID(id);

		ProjectBean bean = new ProjectBean(data.get(0),data.get(1), data.get(2), data.get(3));
		return bean;
	}

	@Override
	public int size() {
		return database.size();
	}

	@Override
	public void deleteProject(String projectId) {
		database.deleteEntry(projectId);
	}

	@Override
	public void editProject(String id, ProjectBean bean) {
		deleteProject(id);
		storeProject(bean);
	}



}
