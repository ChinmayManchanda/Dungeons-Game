package unsw.dungeon;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MenuController {
	@FXML
    private Button Map1;
	@FXML
	private Button Map2;
	@FXML
	private Button Map3;

	@FXML
    void handleButton1(ActionEvent event) throws IOException {
		DungeonApplication map1 = new DungeonApplication("maze.json");
		map1.start(new Stage());
	}
	@FXML
	void handleButton2(ActionEvent event) throws IOException {
		DungeonApplication map2 = new DungeonApplication("boulders.json");
		map2.start(new Stage());
	}
	@FXML
	void handleButton3(ActionEvent event) throws IOException {
		DungeonApplication map3 = new DungeonApplication("advanced.json");
		map3.start(new Stage());
    }
	
	@FXML
    public void initialize() { 
		
    }
}
