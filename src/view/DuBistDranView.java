package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import language.LanguageManager;

/**
 * shows view that you are able to play
 * @author Maurice
 *
 */
public class DuBistDranView extends GridPane{

	
	public Button ok = new Button("Ok");
	
	public DuBistDranView(){
		initStyle();
	}
	
	private void initStyle(){
		this.setMaxSize(300, 150);
		this.setMinSize(200, 150);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		Label lbl = new Label();
		lbl.textProperty().bind(LanguageManager.getStringProperty("yourTurn"));
		
		lbl.setStyle("-fx-text-fill:white");
		lbl.setFont(Font.font(20));
		lbl.setTranslateX(50);
		
		ok.setTranslateY(60);
		ok.setTranslateX(25);
		
		this.getChildren().addAll(lbl,ok);
	}
}
