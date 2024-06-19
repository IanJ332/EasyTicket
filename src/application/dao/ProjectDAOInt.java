package application.dao;

import java.util.ArrayList;

public interface ProjectDAOInt {
	public void storeProject();
	public ArrayList<String> readProjectEntry(int index);
	public int size();
}
