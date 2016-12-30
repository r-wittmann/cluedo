package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import language.LanguageManager;
import model.Gamefield;

public class VerdaechtigungSelbstWiderlegenView extends VBox {
	
	public Label label = new Label();
	public Button ja = new Button();
	public Button nein = new Button();
	private HBox hbox = new HBox();
	private HBox hboxButtons = new HBox();
	
	private static final Logger log = LogManager.getLogger(VerdaechtigungSelbstWiderlegenView.class);
	
	private Gamefield model;
	
	public VerdaechtigungSelbstWiderlegenView(Gamefield model){
		this.model = model;
		initStyle();
	}
	
	public void initStyle(){
		
		//General Style
		this.setPadding(new Insets(4,4,4,4));
		this.setMaxHeight(USE_PREF_SIZE);
		this.setMaxWidth(USE_PREF_SIZE);
		this.setSpacing(4);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		//set label and buttons 
		label.textProperty().bind(LanguageManager.getStringProperty("canYouDisprove"));
		label.setStyle("-fx-text-fill:white");
		label.setFont(Font.font(15));
//		label.setTranslateX(25);
		
//		ja.setTranslateX(50);
//		ja.setTranslateY(70);
//		
//		nein.setTranslateX(50);
//		nein.setTranslateY(140);
		
//		setCards();
		ja.textProperty().bind(LanguageManager.getStringProperty("yes"));
		nein.textProperty().bind(LanguageManager.getStringProperty("no"));
		hboxButtons.setPadding(new Insets(4,4,4,4));
		hboxButtons.setSpacing(16);
		hboxButtons.setAlignment(Pos.CENTER);
		
		hboxButtons.getChildren().addAll(ja, nein);
		this.getChildren().addAll(label,hbox,hboxButtons);
		this.setAlignment(Pos.CENTER);
	}
	
	/**
	 * sets the cards that were suspected
	 * @author Roxanna
	 */
	public void setCards(){
		try{
			hbox.setPadding(new Insets(4,4,4,4));
			hbox.setSpacing(8);
			hbox.setAlignment(Pos.CENTER);
			
			String counter = new String();
			String weapon = new String();
			String room = new String();
			
			// remove old cards
			hbox.getChildren().clear();
			
			counter = this.model.suspicion.getSuspCounter().toString().toLowerCase();
			room = this.model.suspicion.getSuspRoom().toString().toLowerCase();
			weapon = this.model.suspicion.getSuspWeapon().toString().toLowerCase();
			
//			for(int i = 0; i < this.model.getCardList().size(); i++){
//				
//				if(this.model.getCardList().get(i) instanceof PersonCard){
//					if(((PersonCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspCounter())){
//						counter = ((PersonCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.SelbstWiderl.View: PersonCard = " + counter);
//					}
//				} else if(this.model.getCardList().get(i) instanceof WeaponCard){
//					if(((WeaponCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspWeapon())){
//						weapon = ((WeaponCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.SelbstWiderl.View: WeaponCard = " + weapon);
//					}
//				} else if(this.model.getCardList().get(i) instanceof RoomCard){
//					if(((RoomCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspRoom())){
//						room = ((RoomCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.SelbstWiderl.View: RoomCard = " + room);
//					}
//				}
//			}
			
			ImageView cardivPers = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + counter + ".png")));
			cardivPers.setPreserveRatio(false);
			cardivPers.setSmooth(true);
			hbox.getChildren().add(cardivPers);
				
			ImageView cardivWeap = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + weapon + ".png")));
			cardivWeap.setPreserveRatio(false);
			cardivWeap.setSmooth(true);
			hbox.getChildren().add(cardivWeap);
			
			ImageView cardivRoom = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + room + ".png")));
			cardivRoom.setPreserveRatio(false);
			cardivRoom.setSmooth(true);
			hbox.getChildren().add(cardivRoom);
		} catch (NullPointerException e){
			log.info("setCards nicht möglich");
		}
		
	}
}
