package application;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class CommonObjs {
	
	private static CommonObjs commonObjs = new CommonObjs();
	
	private ScrollPane projectScrollPane;
	private VBox projectVBox;
	
	public static CommonObjs getInstance() {
		return commonObjs;
	}
	
	public ScrollPane getProjectScrollPane() {
		return projectScrollPane;
	}
	
	public void setProjectScrollPane(ScrollPane projectScrollPane) {
		this.projectScrollPane = projectScrollPane;
	}

	public VBox getProjectVBox() {
		return projectVBox;
	}

	public void setProjectVBox(VBox projectVBox) {
		this.projectVBox = projectVBox;
	}
	
}
