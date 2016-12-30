package loginView;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import language.CustomComboBox;
import language.LanguageManager;

/**
 * class LobbyView contains the so called Server Lobby
 * it contains all Games on the Server, all Players on the Server
 * and the posibility to create and join games.
 * Additionaly the chat functionality is in the lobby
 *
 * @author Wittmann Rainer, Maurice
 */

public class LobbyView extends BorderPane {
	private static final Logger log = LogManager.getLogger(LobbyView.class);
	public ChatView chatView;
	public Button createBtn = new Button();
	public Button joinBtn = new Button();
	public Button spectator = new Button();
	public Button KI = new Button(); //Dr.roBOTo rufen
	private ComboBox<String> color2;
	public ComboBox<String> colorbox;
	private ComboBox<String> ext;
	public Button help;
	public VBox join;

	// Elemente für Spieler Liste erzeugen
	Label tempname;
	Label tempgroup;
	private VBox playerVBox = new VBox(1);
	private VBox playerValue = new VBox(1);
	private HBox playerHeader = new HBox(1);

	// Elemente für Spiele Liste erzeugen
	Label tempID;
	Label tempCount;
	Label tempState;

	RadioButton rb;
	public ToggleGroup tg = new ToggleGroup();

	private VBox gamesVBox = new VBox(1);
	private VBox gamesValue = new VBox(1);
	private HBox gamesHeader = new HBox(1);

	public LobbyView() {
		initStyle();
	}

	/**
	 * allows to display all Games on Server
	 *
	 * @param gameList
	 * @author Wittmann Rainer
	 */
	public void updateGames(ArrayList<ArrayList<String>> gameList) {

		if (gameList == null){
			gamesValue.getChildren().clear();
			return;}
		
		//um Ausgewählten Toggle auch ausgewählt zu lassen
		String memorizedID = "";
		if (tg.getSelectedToggle() != null) {
			memorizedID = tg.getSelectedToggle().getUserData().toString();
		}
		
		//ToggleGroup und VBox ausleeren um Speicherüberlastung zu verhindern
		tg.getToggles().clear();
		gamesValue.getChildren().clear();
		
		//Durch die gameList iterieren und für jedes Element eine HBox mit 
		//Toggle, ID, Speileranzahl und Spielzustand erstellen und zur VBox adden
		for (int i = 0; i < gameList.size(); i++) {
			tempID = new Label(" " + gameList.get(i).get(0));
			tempCount = new Label(" " + gameList.get(i).get(1));
			tempState = new Label(" " + gameList.get(i).get(2));

			rb = new RadioButton();
			rb.setUserData((gameList.get(i).get(0)));
			if (gameList.get(i).get(2).equals("ENDED"))
				rb.setDisable(true);
			if (gameList.get(i).get(0).equals(memorizedID))
				rb.setSelected(true);
			tg.getToggles().add(rb);

			HBox temphBox = new HBox(1);
			temphBox.getChildren().addAll(rb, tempID, tempCount, tempState);
			gamesValue.getChildren().add(temphBox);
			initGameStyle();
		}

	}

	/**
	 * allows to display all Players (Clients) on Server
	 *
	 * @param clientList
	 * @author Wittmann Rainer
	 */

	public void updatePlayer(ArrayList<String> clientList) {

		if (clientList == null)
			return;
		//VBox ausleeren
		playerValue.getChildren().clear();
		//durch clientList iterieren und für jedes Element HBox mit Namen erstellen und adden
		for (int i = 0; i < clientList.size(); i++) { 
			tempname = new Label(" " + clientList.get(i)); HBox temphBox = new HBox(1);
			temphBox.getChildren().addAll(tempname);
			playerValue.getChildren().add(temphBox);
		  
			initPlayerStyle(); 
		}

	}
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle() {

		this.setStyle("-fx-background-color:BLACK");

		// left aufteilen in die verschiedenen Bestandteile
		join = new VBox(20);
		join.setMaxHeight(260);
		join.setId("vbox");
		VBox create = new VBox(20);
		create.setMaxHeight(260);
		create.setId("create");

		// gamesHeader gestalten
		KI.textProperty().bind(LanguageManager.getStringProperty("callKI"));
		KI.setAlignment(Pos.CENTER);
		Label checkHeader = new Label();
		Label idHeader = new Label();
		idHeader.textProperty().bind(LanguageManager.getStringProperty("game"));
		Label countHeader = new Label();
		countHeader.textProperty().bind(LanguageManager.getStringProperty("numberPlayers"));
		Label stateHeader = new Label();
		stateHeader.textProperty().bind(LanguageManager.getStringProperty("status"));
		checkHeader.setId("gameheader");
		idHeader.setId("gameheader");
		countHeader.setId("gameheader");
		stateHeader.setId("gameheader");
		checkHeader.setMinWidth(23);
		idHeader.setMinWidth(144);
		countHeader.setMinWidth(128);
		stateHeader.setMinWidth(98);

		gamesHeader.getChildren().addAll(checkHeader, idHeader, countHeader,
				stateHeader);

		gamesValue.setMinHeight(265);
		gamesValue.setStyle("-fx-background-color:DARKRED");

		ScrollPane gameValueSP = new ScrollPane();
		gameValueSP.setMinSize(396, 265);
		gameValueSP.setContent(gamesValue);
		gameValueSP.setStyle("-fx-background:DARKRED");

		gamesVBox.setMaxSize(400, 300);
		gamesVBox.setMinSize(400, 300);
		gamesVBox.setStyle("-fx-background-color:DARKRED;"
				+ "-fx-border-color:DARKRED;" + "-fx-border-width:2px");

		gamesVBox.getChildren().addAll(gamesHeader, gameValueSP);

		// playerHeader gestalten
		Label nameHeader = new Label(); 
		nameHeader.textProperty().bind(LanguageManager.getStringProperty("players"));

		nameHeader.setId("header");

		playerHeader.getChildren().add(nameHeader);

		ScrollPane valueSP = new ScrollPane();
		valueSP.setId("valueSP");
		valueSP.setContent(playerValue);

		playerValue.setId("playerValue");

		playerVBox.setId("playerVbox");

		playerVBox.getChildren().addAll(playerHeader, valueSP);

		// join gestalten
		joinBtn.textProperty().bind(LanguageManager.getStringProperty("joinGame"));
		spectator.textProperty().bind(LanguageManager.getStringProperty("watch"));
		
		ArrayList<String> useless = new ArrayList<String>();
		useless.add("red");
		useless.add("green");
		useless.add("blue");
		useless.add("purple");
		useless.add("yellow");
		useless.add("white");
		setColorbox(useless);


		// create gestalten
		createBtn.textProperty().bind(LanguageManager.getStringProperty("createGame"));
		color2 = new CustomComboBox(new String[]{"red","green","blue","purple","yellow","white"}, "chooseColor");
		color2.setMinSize(230, 40);
		
		ext = new CustomComboBox(new String[]{}, "extensions");
		ext.setMinSize(230, 40);
		ext.setDisable(true);
		ext.getItems().addAll("Ext1", "Ext2", "Ext3");

		create.getChildren().addAll(color2, ext, createBtn);

		// BorderPane aufteilen in ChatView und den Rest
		VBox tbl = new VBox();
		BorderPane btn = new BorderPane();
		chatView = new ChatView();

		btn.setTop(join);
		btn.setBottom(create);
		btn.setCenter(KI);
		btn.setPadding(new Insets(20, 20, 20, 20));

		help = new Button("?");
		help.setAlignment(Pos.TOP_RIGHT);
		help.setMaxSize(20,20);
		help.setTranslateX(-208);
		help.setTranslateY(15);
		
		
		tbl.getChildren().addAll(gamesVBox, playerVBox,help);

		// BorderPane: Bestandteile positionieren und adden
		this.setLeft(tbl);
		this.setCenter(btn);
		this.setRight(chatView);
	}

	/**
	 * setzt ID für name Label
	 * @author Wittmann Rainer
	 */
	public void initPlayerStyle() {
		tempname.setId("tempname");
	}
	/**
	 * setzt id und styles für die Labels der Spiele
	 * @author Wittmann Rainer
	 */
	public void initGameStyle() {
		tempID.setMinSize(144, 20);
		tempCount.setMinSize(128, 20);
		tempState.setMinSize(85, 20);

		tempID.setStyle("-fx-background-color:#a0a0a0");
		tempCount.setStyle("-fx-background-color:#a0a0a0");
		tempState.setStyle("-fx-background-color:#a0a0a0");

		rb.setTranslateX(3);
	}

	public ComboBox<String> getColor2() {
		return color2;
	}

	public ComboBox<String> getColor() {
		return colorbox;
	}

	/**
	 * getter for GameID from selected Toggel
	 * @return gameID
	 * @author Wittmann Rainer
	 */
	public int getGameID() {
		int gameID = 0;
		if (tg.getSelectedToggle() != null) {
			gameID = Integer.parseInt(tg.getSelectedToggle().getUserData()
					.toString());} 
		return gameID;
	}
	/**
	 * setzt die noch verfügbaren Farben in die ColorBox um bei einem Spiel beizutreten
	 * @param colorList
	 * @author Wittmann Rainer
	 */
	public void setColorbox(ArrayList<String> colorList){
		int size = colorList.size();
		String[] colorArray = new String[size];
		
		for(int i = 0; i < size; i++){
			colorArray[i] = colorList.get(i);
		}
		
		colorbox = new CustomComboBox(colorArray, "chooseColor");
		colorbox.setMinSize(230, 40);
		join.getChildren().clear();
		join.getChildren().addAll(colorbox, joinBtn, spectator);
		
	}

}
