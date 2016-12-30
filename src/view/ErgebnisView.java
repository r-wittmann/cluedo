package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
/**
 * Show at the end of the game murder, weapon and room
 * 
 * @author Maurice, Roxanna
 */
public class ErgebnisView extends VBox {
	
//	public Button close = new Button("Schließen");
	public Label erg = new Label("Das Spielergebnis lautet:");
	public Label mord = new Label();
	public HBox hbox = new HBox();
	
	public ErgebnisView(){
		initStyle();
		this.getChildren().add(erg);
		this.getChildren().add(hbox);
		this.getChildren().add(mord);
//		this.getChildren().add(close);
	}
	
	/**
	 * Creates javafx element
	 */
	private void initStyle(){
		
		//general Style View
		this.setPadding(new Insets(4,4,4,4));
		this.setMaxHeight(USE_PREF_SIZE);
		this.setMaxWidth(USE_PREF_SIZE);
		this.setSpacing(4);
//		this.setId("View");
		this.setAlignment(Pos.CENTER);
//		this.getStylesheets().clear();
//		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());

		//set Id for Style
//		erg.setId("Erg");
	}
	
	/**
	 * Render cards if game finished, must be in model before call
	 * @author Ludwig
	 */
	public void render(){
//		setCards();
//		setMurderInfo();
	}
	
	/**
	 * Sets the Cards that belong to the murder case.
	 * @author Roxanna
	 */
	public void setCards(String murder, String weapon, String scene){		
		hbox.setPadding(new Insets(4,4,4,4));
		hbox.setSpacing(8);
		hbox.setAlignment(Pos.CENTER);

		ImageView cardiv = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + murder + ".png")));
		cardiv.setPreserveRatio(false);
		cardiv.setSmooth(true);
		hbox.getChildren().add(cardiv);
		
		ImageView cardiv2 = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + scene + ".png")));
		cardiv2.setPreserveRatio(false);
		cardiv2.setSmooth(true);
		hbox.getChildren().add(cardiv2);
		
		ImageView cardiv3 = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + weapon + ".png")));
		cardiv3.setPreserveRatio(false);
		cardiv3.setSmooth(true);
		hbox.getChildren().add(cardiv3);
	}
	
	/**
	 * Sets the style and size of the font of the label mord.
	 * <br> And sets the text of the label mord with matching grammatics
	 * @author Roxanna
	 */
	public void setMurderInfo(String murder, String weapon, String scene){
		mord.setPadding(new Insets(0,4,4,4));
		String scenePred;
		String weaponPred;
		
		if(scene.equals("hall") || scene.equals("kitchen") || scene.equals("library")){
			scenePred = " in der ";
		} else {
			scenePred = " im ";
		}
		
		if(weapon.equals("spanner")){
			weaponPred = " mit der ";
		} else {
			weaponPred = " mit dem ";
		}
		
		switch(murder){
		case "blue":
			murder = "Baronin von Porz";
			break;
		case "yellow":
			murder = "Oberst von Gatow";
			break;
		case "red":
			murder = "Fräulein Gloria";
			break;
		case "green":
			murder = "Reverend Grün";
			break;
		case "white":
			murder = "Frau Weiß";
			break;
		case "purple":
			murder = "Professor Bloom";
			break;
		}
		
		switch(scene){
		case "hall":
			scene = "Eingangshalle";
			break;
		case "ballroom":
			scene = "Musikzimmer";
			break;
		case "billiard":
			scene = "Schachzimmer";
			break;
		case "conservatory":
			scene = "Wintergarten";
			break;
		case "diningroom":
			scene = "Speisezimmer";
			break;
		case "kitchen":
			scene = "Küche";
			break;
		case "library":
			scene = "Bibliothek";
			break;
		case "lounge":
			scene = "Salon";
			break;	
		case "study":
			scene = "Arbeitszimmer";
			break;	
		}
		
		switch(weapon){
		case "candlestick":
			weapon = "Kerzenständer";
			break;
		case "dagger":
			weapon = "Dolch";
			break;
		case "pipe":
			weapon = "Heizungsrohr";
			break;
		case "revolver":
			weapon = "Revolver";
			break;
		case "rope":
			weapon = "Seil";
			break;
		case "spanner":
			weapon = "Rohrzange";
			break;	
		}
		
		mord.setText("Es war "+ murder + scenePred + scene + weaponPred + weapon + ".");
		mord.setStyle("-fx-text-fill:white");
		mord.setFont(Font.font(15));
	}
}
