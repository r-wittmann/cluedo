package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

/**
 * 
 * @author Maurice
 */

public class VerdaechtigungWiderlegtView extends GridPane{
	
	public Button close = new Button("Schließen");
	
	public VerdaechtigungWiderlegtView(){
		initStyle();
	}
	
	public void initStyle(){
		
		this.setMinSize(300, 200);
		this.setMaxSize(300, 200);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		Label lbl = new Label("Die Verdächtigung wurde widerlegt!");
		
		TextField name = new TextField();
		
		lbl.setTranslateX(25);
		lbl.setFont(Font.font(15));
		lbl.setStyle("-fx-text-fill:white");
		
		name.setTranslateX(25);
		name.setTranslateY(50);
		name.setPromptText("Name");
		name.setEditable(false);
		name.setFocusTraversable(false);
		
		close.setTranslateY(120);
		close.setTranslateX(25);
		
		this.getChildren().addAll(lbl,name,close);
	}
}
