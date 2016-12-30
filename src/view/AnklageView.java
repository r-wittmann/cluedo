package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import language.CustomComboBox;
import language.LanguageManager;



/**
 * class contains view for prosecution
 * @author Maurice
 */
public class AnklageView extends VBox {

	public Button close = new Button();
	public Button submit = new Button();

	public ComboBox<String> murder;
	public ComboBox<String> weapon;
	public ComboBox<String> room;
	
	public AnklageView(){
		initStyle();
	}

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle() {
		
		//general Style of View
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		this.setPadding(new Insets(15,15,15,15));
		this.setSpacing(10);
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		//set comboboxes for choosing murder,weapon,room
		close.textProperty().bind(LanguageManager.getStringProperty("close"));
		submit.textProperty().bind(LanguageManager.getStringProperty("doAccusation"));
		
		Label anklage = new Label("Anklage*");
		
		Label hinweis = new Label();
		hinweis.textProperty().bind(LanguageManager.getStringProperty("labelInfo"));					
		murder = new CustomComboBox(new String[]{"gloria","gatow","mrswhite","revgreen","porz","bloom"},"murderer");
		
		
		weapon = new CustomComboBox(new String[]{"dagger","candlestick","gun","rope","pipe","spanner"}, "weapon");
		
	
		room = new CustomComboBox(new String[]{"hall","lounge","diningroom","kitchen","musicroom","conservatory","chessroom","library","study"}, "room");
		
		
		//set style of label
		hinweis.setFont(Font.font(15));
		anklage.setFont(Font.font(20));
		anklage.setStyle("-fx-text-fill:white");
		hinweis.setStyle("-fx-text-fill:white");
		
		
		//set id for Style
		
		murder.setId("AVElements");
		weapon.setId("AVElements");
		room.setId("AVElements");
		submit.setId("AVElements");
		close.setId("AVElements");
		
		
		this.setVisible(false);
		
		//add elements 
		this.getChildren().addAll(anklage,murder,weapon,room,submit,close,hinweis);
		
	}
}
