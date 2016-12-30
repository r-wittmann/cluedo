 package loginView;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import language.LanguageManager;
import language.LanguageManager.SupportedLanguages;


/**
 * class StartView contains buttons to choose whether you want to start a game
 * watch the credits, read the Manuel or leave the program.
 * @author Maurice
 *
 */
public class StartView extends StackPane {
	
	public Button startButton;
	public Button exit;
	public Button anleitung;
	public Button credits;
	//Sarah
	public ComboBox<String> language;
	
	private Label cluedo;
	
	private VBox vBox;
	
	
	public StartView(){
		initStyle();
	}
	
	private void initStyle(){
		
		//setzen und stylen des Cluedo Labels
		setCluedo();
		
		//setzen und stylen der Buttons
		setButtons();
		
		//setzen und stylen der vBox
		setVBox();
		
		//Elemente zur Pane adden
		this.getChildren().addAll(cluedo,vBox);
	}
	/**
	 * setzt und stylet Cluedo Label
	 * @author Wittmann Rainer
	 */
	public void setCluedo(){
		cluedo = new Label("CLUEDO");
		cluedo.setId("cluedo");
		cluedo.setTranslateY(-230);
	}
	/**
	 * setzt und stylet die Buttons
	 * @author Wittmann Rainer
	 */
	public void setButtons(){
		startButton = new Button();
		startButton.textProperty().bind(LanguageManager.getStringProperty("startGame"));
		anleitung = new Button();
		anleitung.textProperty().bind(LanguageManager.getStringProperty("gameManual"));
		credits = new Button();
		credits.textProperty().bind(LanguageManager.getStringProperty("credits"));
		exit = new Button();
		exit.textProperty().bind(LanguageManager.getStringProperty("leaveGame"));	
		//Sarah
		language = new ComboBox<String>(FXCollections.observableArrayList("Deutsch","English", "Francais", "Italiano", "Español"));
		language.setPromptText("Sprache/Language/Langue/Lingua/Idioma");
		language.setMinSize(230, 30);
		LanguageManager.setLanguage(SupportedLanguages.de);
		//language.getSelectionModel().getSelectedIndex()]);
	}
	/**
	 * setzt und stylet die vBox
	 * @author Wittmann Rainer
	 */
	public void setVBox(){
		vBox = new VBox(20);
		vBox.setId("StartVbox");
		vBox.setMaxSize(USE_PREF_SIZE, USE_PREF_SIZE);
		vBox.setPadding(new Insets(20,20,20,20));
		vBox.setTranslateY(50);
		vBox.getChildren().addAll(startButton,anleitung,credits,exit,language);
	}
}
