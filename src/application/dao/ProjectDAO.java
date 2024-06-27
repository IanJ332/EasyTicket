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
