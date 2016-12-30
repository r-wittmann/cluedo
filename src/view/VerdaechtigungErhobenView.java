package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import language.LanguageManager;
import model.Gamefield;



/**
 * Shows if suspicion received by network
 * @author Sarah, Maurice
 */
public class VerdaechtigungErhobenView extends VBox {
	
	public Label label = new Label();
	
	private HBox hbox = new HBox();
	
	private static final Logger log = LogManager.getLogger(VerdaechtigungSelbstWiderlegenView.class);
	
	private Gamefield model;
	
	public VerdaechtigungErhobenView(Gamefield model){
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
		label.setStyle("-fx-text-fill:white");
		label.setFont(Font.font(15));

//		setCards();
	
		
		this.getChildren().addAll(label,hbox);
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
			hbox.getChildren().clear();
			
			String counter = new String();
			String weapon = new String();
			String room = new String();
			
			counter = this.model.suspicion.getSuspCounter().toString().toLowerCase();
			weapon = this.model.suspicion.getSuspRoom().toString().toLowerCase();
			room = this.model.suspicion.getSuspWeapon().toString().toLowerCase();
			
//			for(int i = 0; i < this.model.getCardList().size(); i++){
//				if(this.model.getCardList().get(i) instanceof PersonCard){
//					if(((PersonCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspCounter())){
//						counter = ((PersonCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.Erh.View: PersonCard = " + counter);
//					}
//				} else if(this.model.getCardList().get(i) instanceof WeaponCard){
//					if(((WeaponCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspWeapon())){
//						weapon = ((WeaponCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.Erh.View: WeaponCard = " + weapon);
//					}
//				} else if(this.model.getCardList().get(i) instanceof RoomCard){
//					if(((RoomCard) this.model.getCardList().get(i)).getCardValue().equals(this.model.suspicion.getSuspRoom())){
//						room = ((RoomCard) this.model.getCardList().get(i)).getName();
						log.info("Verd.Erh.View: RoomCard = " + room);
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
		
		//Namen des Verdächtigten setzten

		label.setText(model.getCurrentPlayer() + " " + LanguageManager.getString("suspicionDone"));
		
	}
}



