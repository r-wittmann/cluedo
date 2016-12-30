package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import language.LanguageManager;

public class GeheimgangView extends GridPane {

	public Button ja = new Button();
	public Button nein = new Button();
	
	public GeheimgangView(){
		initStyle();
	}
	
	private void initStyle(){
		this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		this.setPadding(new Insets(15,15,15,15));
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		Label label = new Label();
		label.textProperty().bind(LanguageManager.getStringProperty("secretPassage"));
		
		//set id for Style
		ja.textProperty().bind(LanguageManager.getStringProperty("yes"));
		nein.textProperty().bind(LanguageManager.getStringProperty("no"));
		ja.setId("geheimBtn");
		nein.setId("geheimBtn");
		label.setStyle("-fx-text-fill:white;");
		
		GridPane.setConstraints(ja, 0, 1);
		GridPane.setConstraints(nein, 1, 1);
		GridPane.setConstraints(label, 0, 0, 2, 1);
		
		this.getChildren().addAll(label,ja,nein);
	}
}
