package unsw.dungeon;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EndScreenController {
	@FXML
	private Label score;
	private String number;
	public EndScreenController(String score) {
		this.number = score;
	}

	@FXML
    public void initialize() { 
		score.setText(this.number);
    }
}
