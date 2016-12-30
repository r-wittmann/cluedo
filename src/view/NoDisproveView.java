package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class NoDisproveView extends GridPane{
	
	public Button ok = new Button("Schließen");
	
	public NoDisproveView(){
		initStyle();
	}
	
	private void initStyle(){
		this.setMaxSize(400, 150);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		Label label = new Label("Die Verdächtigung konnte nicht widerlegt werden!");
		
		label.setStyle("-fx-text-fill:white;");
		label.setFont(Font.font(15));
		label.setTranslateX(20);
		ok.setTranslateY(70);
		ok.setTranslateX(70);
		
		this.getChildren().addAll(ok,label);
	}
}
