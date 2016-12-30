package view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import language.LanguageManager;
import model.Gamefield;

/**
 * class contains Gamefield
 * @author Maurice
 */
public class GameView extends StackPane{
	private Gamefield model;
	
	public Button cards;
	public Button note;
	public Button dice;
	public Button anklage;
	public Button stop;
	
	public FieldView fieldView;
	public NoteView noteView;
	public CardsView cardsView;
	public FieldImageView fieldImageView;
	public CounterView counterView;
	public WeaponView weaponView;
	public VerdaechtigungsView verdaechtigungsView;
	public AnklageView anklageView;
	public WinnerView winnerView;
	public LooseView looseView;
	public PoolView poolView;
	public GameStartView gameStartView;
	public GeheimgangView geheimgangView;
	public DuBistDranView duBistDranView;
	public VerdaechtigungSelbstWiderlegenView selbstWiderlegenView;
	public VerdaechtigungWiderlegtView widerlegtView;
	public VerdaechtigungErhobenView verdaechtigungErhobenView;
	public NoDisproveView noDisproveView;
	public DialogView dialogView;
	
	public GameView(Gamefield model){
		this.model = model;
		fieldView = new FieldView(this.model);
		counterView = new CounterView(this.model);
		weaponView = new WeaponView(this.model);
		noteView = new NoteView(this.model);
		cardsView = new CardsView(this.model);
		fieldImageView = new FieldImageView();
		verdaechtigungsView = new VerdaechtigungsView();
		anklageView = new AnklageView();
		winnerView = new WinnerView();
		poolView = new PoolView(this.model);
		gameStartView = new GameStartView();
		this.gameStartView.setVisible(false);
		weaponView.setMouseTransparent(true);
		geheimgangView = new GeheimgangView();
		geheimgangView.setVisible(false);
		duBistDranView = new DuBistDranView();
		duBistDranView.setVisible(false);
		selbstWiderlegenView = new VerdaechtigungSelbstWiderlegenView(this.model);
		selbstWiderlegenView.setVisible(false);
		widerlegtView = new VerdaechtigungWiderlegtView();
		widerlegtView.setVisible(false);
		noDisproveView = new NoDisproveView();
		noDisproveView.setVisible(false);
		verdaechtigungErhobenView = new VerdaechtigungErhobenView(this.model);
		verdaechtigungErhobenView.setVisible(false);
		dialogView = new DialogView();
		dialogView.setVisible(false);
		looseView = new LooseView();
		looseView.setVisible(false);
		initStyle();
	}
	

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		//general Style 
		this.setMaxSize(648,675);
		
		//Initialize Buttons
		cards = new Button();
		cards.textProperty().bind(LanguageManager.getStringProperty("cards"));
		note  = new Button();
		note.textProperty().bind(LanguageManager.getStringProperty("notes"));
		dice  = new Button();
		dice.textProperty().bind(LanguageManager.getStringProperty("dice"));
		anklage = new Button();
		anklage.textProperty().bind(LanguageManager.getStringProperty("doAccusation"));
		stop = new Button();
		stop.textProperty().bind(LanguageManager.getStringProperty("endTurn"));
		
		//set Buttons to Disable
		cards.setDisable(true);
		note.setDisable(true);
		dice.setDisable(true);
		anklage.setDisable(true);
		stop.setDisable(true);
		//bevor das Spiel angefangen hat sollten noch keine Buttons klickbar sein.
		
		//set button style
		cards.setId("gameBtn");
		note.setId("gameBtn");
		dice.setId("gameBtn");
		anklage.setId("gameBtn");
		stop.setId("gameBtn");
		
		cards.setStyle("-fx-background-color:DARKRED;"
			+	"-fx-text-fill: white;"
			+	"-fx-min-width: 140px;"
			+	"-fx-min-height: 30px;"
			+	"-fx-max-width: 140px;"
			+	"-fx-max-height: 30px");
		
		note.setStyle("-fx-background-color:DARKRED;"
				+	"-fx-text-fill: white;"
				+	"-fx-min-width: 140px;"
				+	"-fx-min-height: 30px;"
				+	"-fx-max-width: 140px;"
				+	"-fx-max-height: 30px");
			
		dice.setStyle("-fx-background-color:DARKRED;"
				+	"-fx-text-fill: white;"
				+	"-fx-min-width: 140px;"
				+	"-fx-min-height: 30px;"
				+	"-fx-max-width: 140px;"
				+	"-fx-max-height: 30px");
			
		anklage.setStyle("-fx-background-color:DARKRED;"
				+	"-fx-text-fill: white;"
				+	"-fx-min-width: 140px;"
				+	"-fx-min-height: 30px;"
				+	"-fx-max-width: 140px;"
				+	"-fx-max-height: 30px");
		
		stop.setStyle("-fx-background-color:DARKRED;"
				+	"-fx-text-fill: white;"
				+	"-fx-min-width: 140px;"
				+	"-fx-min-height: 30px;"
				+	"-fx-max-width: 140px;"
				+	"-fx-max-height: 30px");	
				
		//positioning of Panes 
		StackPane.setAlignment(cardsView,Pos.TOP_LEFT);
		StackPane.setAlignment(noteView, Pos.CENTER_LEFT);
		StackPane.setAlignment(fieldView, Pos.CENTER_LEFT);
		StackPane.setAlignment(fieldImageView, Pos.CENTER_LEFT);
		StackPane.setAlignment(counterView, Pos.CENTER_LEFT);
		StackPane.setAlignment(anklageView, Pos.CENTER);
		
		//add elements

		this.getChildren().addAll(fieldImageView,fieldView,weaponView,counterView,cardsView,noteView,
				verdaechtigungsView,winnerView,looseView,poolView,gameStartView, geheimgangView, widerlegtView, selbstWiderlegenView,
				noDisproveView,verdaechtigungErhobenView,anklageView,dialogView,duBistDranView);
		this.setVisible(true);
		gameStartView.setVisible(false);
	}
	
	public Button getNoteButton(){
		return note;
	}

}
