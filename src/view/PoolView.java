package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Gamefield;

public class PoolView extends VBox{
	
	private Gamefield model;
	HBox hbox = new HBox();
	public Button close = new Button("Schließen");
	
	public PoolView(Gamefield model){
		this.model = model;
		initStyle();
		//Only sets cards if poolCards is not empty
		if(this.model.getPoolCards().size() > 0){
			setCards();
		}
		this.getChildren().add(hbox);
		this.getChildren().add(close);
		this.setVisible(false);
	}
	
	private void initStyle(){
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.setPadding(new Insets(4,4,4,4));
		this.setMaxHeight(USE_PREF_SIZE);
		this.setMaxWidth(USE_PREF_SIZE);
		this.setSpacing(4);
		this.setId("View");
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
	}
	
	public void setCards(){		
		hbox.setPadding(new Insets(4,4,4,4));
		hbox.setSpacing(4);
		
		if(this.model.getPoolCards().size() != 0){
		
		for(int i = 0; i<this.model.getPoolCards().size(); i++){
//			File file1 = new File("resources/cards/"+this.model.getPoolCards().get(i).getName()+".png");
//			Image card = new Image(file1.toURI().toString());
//			ImageView cardiv = new ImageView(card);
//			cardiv.setPreserveRatio(false);
//			cardiv.setSmooth(true);
//			hbox.getChildren().add(cardiv);
			
			ImageView cardiv = new ImageView(new Image(getClass().getResourceAsStream("/cards/" + this.model.getPoolCards().get(i).getName() + ".png")));
			cardiv.setPreserveRatio(false);
			cardiv.setSmooth(true);
			hbox.getChildren().add(cardiv);
			
				}
				
			}else{
				Label label = new Label("No Poolcards");
				label.setStyle("-fx-text-fill:white");
				this.getChildren().add(label);
		}		
	}

}
