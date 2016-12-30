package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import language.CustomComboBox;
import language.LanguageManager;

/**
 * class contains view for suspicion
 * @author Maurice
 *
 */
public class VerdaechtigungsView extends VBox {
	
	public Button close = new Button();
	
	public Button submit = new Button();
	
	public ComboBox<String> murder;
	public ComboBox<String> weapon;
	
	public VerdaechtigungsView(){
		initStyle();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		//general styling of view
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

		
		//set choiceboxes for choose murder,weapon,room
		//Reihenfolge der Strings in Comboboxen muss gleich sein wie zugehörige Enums in Cards
		close.textProperty().bind(LanguageManager.getStringProperty("close"));
		submit.textProperty().bind(LanguageManager.getStringProperty("doSuspicion"));
		
		murder = new CustomComboBox(new String[]{"gloria","gatow","mrswhite","revgreen","porz","bloom"},"murderer");
		
		weapon = new CustomComboBox(new String[]{"dagger","candlestick","gun","rope","pipe","spanner"},"weapon");
		
		
		//set size of elements
		murder.setMinSize(180, 30);
		murder.setMaxSize(180, 30);
		weapon.setMinSize(180, 30);
		weapon.setMaxSize(180, 30);
		submit.setMinSize(180, 30);
		submit.setMaxSize(180, 30);
		
		//add elements
		this.getChildren().addAll(murder,weapon,submit);
		
	}
	

}
