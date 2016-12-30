
package view;

import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Card;
import model.Gamefield;

/**
 * class CardsView shows the player his own cards when he presses the button "Cards" in GlobalView.
 * Cards are displayed in a row of max 6 cards.
 * 
 * @author Wittmann Rainer
 *
 */

public class CardsView extends HBox {
	private Gamefield model;
	private ArrayList<ImageView> playerCards = new ArrayList<ImageView>();
	
	public CardsView(Gamefield model){
		this.model = model;
		initStyle();
//		setCards();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		this.setPadding(new Insets(4,4,4,4));
		this.setMaxHeight(USE_PREF_SIZE);
		this.setMaxWidth(USE_PREF_SIZE);
		this.setSpacing(4);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		
	}
	
	/**
	 *gets active Cards from player as a list and loads cards as defined in list
	 *
	 *@author Wittmann Rainer
	 */
	public void setCards() {

		//funktion holt sich die Karten aus dem Model und zeigt sie in der View an.
		ArrayList<Card> playerCards = model.getPlayer().getPlayerCards();
		this.getChildren().clear();
		for(int i = 0; i<playerCards.size(); i++){
//			File file1 = new File("resources/cards/"+playerCards.get(i).getName()+".png");
//			Image card = new Image(file1.toURI().toString());
			ImageView cardiv = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + playerCards.get(i).getName() + ".png")));
			cardiv.setPreserveRatio(false);
			cardiv.setSmooth(true);
			//Sets Id for DisproveSuspicionPresenter
			cardiv.setId(playerCards.get(i).getName());
			
			this.playerCards.add(cardiv);
			this.getChildren().add(cardiv);
		}
	}
	
	/**
	 * @return the ArrayList that contains the ImageViews for each playerCard
	 */
	public ArrayList<ImageView> getPlayerCards(){
		return playerCards;
	}
}
