package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getClassLoader().getResource("view/Main.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getClassLoader().getResource("css/application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Bug Squasher 1.0");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
