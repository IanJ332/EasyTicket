package application.dao;

import java.util.ArrayList;

import content.bean.ProjectBean;
import database.ProjectFileIO;

public class ProjectDAO implements ProjectDAOInt{
	
	private ProjectFileIO database = new ProjectFileIO();
	private ProjectBean bean;
	private ArrayList<String> dataArray = new ArrayList<String>();
	
	public ProjectDAO() {};
	
	public ProjectDAO(ProjectBean bean) {
		this.bean = bean;
	}

	@Override
	public void storeProject() {
		dataArray.add(bean.getName());
		dataArray.add(bean.getStartDate());
		dataArray.add(bean.getDescr());

		database.storeProject(dataArray);
	}

	@Override
	public ArrayList<String> readProjectEntry(int index) {
		return database.readProject().get(index);
	}

	@Override
	public int size() {
		return database.getTableSize();
	}
	
}
