package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import model.Card;
import model.Gamefield;

public class CCardView extends StackPane{
	private static final Logger log = LogManager.getLogger(CCardView.class);
	
	private Gamefield model;
	
	Label player1;
	Label player2;
	Label player3;
	Label player4;
	Label player5;
	Label player6;
	
	private ImageView cardiv;
	
	
	
	public CCardView(Gamefield model){
		this.model = model;
		renderPlayer();
		initStyle();
		
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		//general Style View
		this.setMinSize(312, 350);
		this.setMaxSize(312, 350);
		this.setAlignment(Pos.CENTER);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		
		setDefaultCard();
//		
//		Rectangle rec = new Rectangle(100,150,Color.DARKRED);
//		
//		
//		this.getChildren().add(rec);
	}
	
	/**
	 * Sets default card
	 * @author Roxanna
	 */
	public void setDefaultCard(){
		cardiv = new ImageView(new Image(getClass().getResourceAsStream("/cards/unknownCard.jpg")));
		cardiv.setPreserveRatio(false);
		cardiv.setSmooth(true);
		if(!this.getChildren().contains(cardiv)){
			this.getChildren().add(cardiv);
		}
	}
	
	/**
	 * Shows a Card at CCardView
	 * @param card that shall be shown
	 * @author Roxanna, Ludwig
	 */
	public void setCard(Card card) {
		if (card == null) {
			log.info("no card to render");
			setDefaultCard();
			return;
		}
		
		cardiv.setImage(new Image(getClass().getResourceAsStream("/cards/" + card.getName() + ".png")));
		cardiv.setPreserveRatio(false);
		cardiv.setSmooth(true);
		if(!this.getChildren().contains(cardiv)){
			this.getChildren().add(cardiv);
		}
	}
	
	/**
	 * renderPlayer() positions the players names around the componed cards
	 * @author Maurice, Jonas, Rainer, Ludwig
	 */
	public void renderPlayer() {
		
		// first of all clear old elements
		this.getChildren().clear();
		setCard(null);
		int playerCount = this.model.getPlayerList().size();
		
		if (playerCount == 1) {
			player1 = new Label(model.getPlayerList().get(0).getName());
			
			setColor(player1,0);
			
			player1.setTranslateY(110);
			this.getChildren().add(player1);
		}
		
		else if(playerCount == 2){
			player1 = new Label(model.getPlayerList().get(0).getName());
			player2 = new Label(model.getPlayerList().get(1).getName());
			player1.setTranslateY(110);
			player2.setTranslateY(-110);

			setColor(player1, 0);
			setColor(player2, 1);
			this.getChildren().addAll(player1,player2);
		}
		
		else if(playerCount == 3){
			//2 Components to set on Board
			player1 = new Label(model.getPlayerList().get(0).getName());
			player2 = new Label(model.getPlayerList().get(1).getName());
			player3 = new Label(model.getPlayerList().get(2).getName());
			
			player1.setTranslateY(110);
			player2.setTranslateX(-98);
			player3.setTranslateX(98);
			
			setColor(player1, 0);
			setColor(player2, 1);
			setColor(player3, 2);
			
			this.getChildren().addAll(player1,player2,player3);
			
		}
		else if(playerCount == 4){
			//3 Components to set on Board
			player1 = new Label(model.getPlayerList().get(0).getName());
			player2 = new Label(model.getPlayerList().get(1).getName());
			player3 = new Label(model.getPlayerList().get(2).getName());
			player4 = new Label(model.getPlayerList().get(3).getName());
			
			player1.setTranslateY(110);
			player2.setTranslateX(-98);
			player3.setTranslateY(-110);
			player4.setTranslateX(98);
			
			setColor(player1, 0);
			setColor(player2, 1);
			setColor(player3, 2);
			setColor(player4, 3);
			
			this.getChildren().addAll(player1,player2,player3,player4);
			
		}
		else if(playerCount == 5){
			//4 Components to set on Board
			player1 = new Label(model.getPlayerList().get(0).getName());
			player2 = new Label(model.getPlayerList().get(1).getName());
			player3 = new Label(model.getPlayerList().get(2).getName());
			player4 = new Label(model.getPlayerList().get(3).getName());
			player5 = new Label(model.getPlayerList().get(4).getName());
			
			player1.setTranslateY(110);
			player2.setTranslateX(-98);
			player2.setTranslateY(50);
			player3.setTranslateX(-98);
			player3.setTranslateY(-50);
			player4.setTranslateX(98);
			player4.setTranslateY(-50);
			player5.setTranslateX(98);
			player5.setTranslateY(50);
			
			setColor(player1, 0);
			setColor(player2, 1);
			setColor(player3, 2);
			setColor(player4, 3);
			setColor(player5, 4);
			
			this.getChildren().addAll(player1,player2,player3,player4,player5);
			
		}
		else if(playerCount == 6){
			//5 Components to set on Board
			player1 = new Label(model.getPlayerList().get(0).getName());
			player2 = new Label(model.getPlayerList().get(1).getName());
			player3 = new Label(model.getPlayerList().get(2).getName());
			player4 = new Label(model.getPlayerList().get(3).getName());
			player5 = new Label(model.getPlayerList().get(4).getName());
			player6 = new Label(model.getPlayerList().get(5).getName());
			
			player1.setTranslateY(110);
			player2.setTranslateX(-98);
			player2.setTranslateY(50);
			player3.setTranslateX(-98);
			player3.setTranslateY(-50);
			player4.setTranslateY(-110);
			player5.setTranslateX(98);
			player5.setTranslateY(-50);
			player6.setTranslateX(98);
			player6.setTranslateY(50);
			
			setColor(player1, 0);
			setColor(player2, 1);
			setColor(player3, 2);
			setColor(player4, 3);
			setColor(player5, 4);
			setColor(player6, 5);
			
			this.getChildren().addAll(player1,player2,player3,player4,player5,player6);
		}
		else {
			log.error(playerCount + " Spieler im Model");
		}
	}

	/**
	 * Styling of playerlabel
	 * @param player
	 * @param i
	 * @author Wittmann Rainer,Jonas
	 */
	private void setColor(Label player, int i) {
		player.setMaxWidth(80);
		player.setMinWidth(80);
		player.setPadding(new Insets(4,2,4,2));
		player.setAlignment(Pos.CENTER);
		player.setStyle("-fx-background-color:" + model.getPlayerList().get(i).getCounter().getColor() + ";"
				+ "-fx-text-fill:BLACK;"
				+ "-fx-background-radius:3px");		
	}

	/**
	 * update the View
	 * @author Jonas
	 */
	//TODO auf meinen Player zentrieren
	public void updateCCardView(String[] order){
		this.getChildren().clear();
//		ArrayList<Label> temp = new ArrayList<Label>();
		int playercount = order.length;
		for(int i=0; i< playercount; i++){
			Label l = new Label(order[i]);
			l.setTranslateY(100);
			l.getTransforms().add(new Rotate(i*360/playercount,40,-100));
			l.getTransforms().add(new Rotate(-i*360/playercount,40,10));
//			temp.add(l);
			this.getChildren().add(l);
		}
	}
	public void highlightPlayer(int i){
		switch(i){
		case 0:
			if(player6 != null) player6.setBorder(null);
			else if(player5 != null) player5.setBorder(null);
			else if(player4 != null) player4.setBorder(null);
			else if(player3 != null) player3.setBorder(null);
			else if(player2 != null) player2.setBorder(null);
			player1.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		case 1:	
			player1.setBorder(null);
//			player1.getBackground().getFills().get(0);
			player2.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		case 2:
			player2.setBorder(null);
			player3.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		case 3:
			player3.setBorder(null);
			player4.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		case 4:
			player4.setBorder(null);
			player5.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		case 5:
			player5.setBorder(null);
			player6.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(4))));
			return;
		default:
			log.error("unable to set shadow");
		}
	}
}
