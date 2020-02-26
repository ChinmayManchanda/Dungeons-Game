package unsw.dungeon;
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EndScreenApplication extends Application {
	private String score;
	public EndScreenApplication(String score1) {
		this.score = score1;
	}
	@Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
        EndScreenController controller = new EndScreenController(this.score);

        loader.setController(controller);


        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
