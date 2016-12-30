package view;

/**
 * class GameStartView
 * allows to add KIs and start the game once all Players have joined the game
 * @author Wittmann Rainer
 */

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import language.LanguageManager;

public class GameStartView extends VBox{
	
	public Button startGame = new Button();
	
	public GameStartView(){
		initStyle();
	}

	private void initStyle() {
		
		//VBox gestalten
		startGame.textProperty().bind(LanguageManager.getStringProperty("startGame"));
		this.setId("View");
		this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		this.setPadding(new Insets(15,15,15,15));
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		//set Id for styling
		startGame.setId("start");
		
		this.getChildren().addAll(startGame);
		
	}

}
