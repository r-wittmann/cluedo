package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Gamefield;

public class WeaponView extends GridPane{
	public Gamefield model;
	
	ImageView daggerIv;
	ImageView candlestickIv;
	ImageView pipeIv;
	ImageView revolverIv;
	ImageView ropeIv;
	ImageView spannerIv;
	
	public WeaponView(Gamefield model){
		this.model = model;
		
		for(int i = 0; i <= 23; i++){
			for(int j = 0; j <= 24; j++){
				Rectangle rec = new Rectangle(26,26,Color.TRANSPARENT);
				GridPane.setConstraints(rec, i, j);
				this.getChildren().add(rec);
			}
		}
		
		initWeaponStyle();
		initWeaponMove();
	}
	
	private void initWeaponStyle() {
		daggerIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/dagger.png")));
		candlestickIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/candlestick.png")));
		pipeIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/pipe.png")));
		revolverIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/revolver.png")));
		ropeIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/rope.png")));
		spannerIv = new ImageView(new Image(getClass().getResourceAsStream("/weapons/spanner.png")));

		this.getChildren().addAll(daggerIv, candlestickIv, pipeIv, revolverIv, ropeIv, spannerIv);
	}
	
	/**
	 * Sets positions of the weapon-imageviews to the positions of the weapons in the model
	 * @author Roxanna
	 */
	public void initWeaponMove(){
		GridPane.setConstraints(daggerIv, this.model.getWeaponList().get(1).getPosX(), this.model.getWeaponList().get(1).getPosY());
		GridPane.setColumnSpan(daggerIv, 2);
		GridPane.setRowSpan(daggerIv, 2);
		
		 	 if(this.model.getWeaponList().get(1).getPosX() ==  6 && this.model.getWeaponList().get(1).getPosY() ==  3) 
		 		 GridPane.setConstraints(daggerIv,  3,  1);//Arbeitszimmer
		else if(this.model.getWeaponList().get(1).getPosX() ==  9 && this.model.getWeaponList().get(1).getPosY() ==  4)
				 GridPane.setConstraints(daggerIv, 12,  3);//Eingangshalle
		else if(this.model.getWeaponList().get(1).getPosX() == 11 && this.model.getWeaponList().get(1).getPosY() ==  6) 
				 GridPane.setConstraints(daggerIv, 12,  3);//Eingangshalle
		else if(this.model.getWeaponList().get(1).getPosX() == 12 && this.model.getWeaponList().get(1).getPosY() ==  6)
				 GridPane.setConstraints(daggerIv, 12,  3);//Eingangshalle
		else if(this.model.getWeaponList().get(1).getPosX() == 17 && this.model.getWeaponList().get(1).getPosY() ==  5)
				 GridPane.setConstraints(daggerIv, 20,  3);//Salon
		else if(this.model.getWeaponList().get(1).getPosX() ==  6 && this.model.getWeaponList().get(1).getPosY() ==  8)
				 GridPane.setConstraints(daggerIv,  3,  8);//Bibliothek
		else if(this.model.getWeaponList().get(1).getPosX() ==  3 && this.model.getWeaponList().get(1).getPosY() == 10) 
				 GridPane.setConstraints(daggerIv,  3,  8);//Bibliothek
		else if(this.model.getWeaponList().get(1).getPosX() ==  1 && this.model.getWeaponList().get(1).getPosY() == 12) 
				 GridPane.setConstraints(daggerIv,  2, 14);//Billiardzimmer
		else if(this.model.getWeaponList().get(1).getPosX() ==  5 && this.model.getWeaponList().get(1).getPosY() == 15) 
				 GridPane.setConstraints(daggerIv,  2, 14);//Billiardzimmer
		else if(this.model.getWeaponList().get(1).getPosX() ==  4 && this.model.getWeaponList().get(1).getPosY() == 19) 
				 GridPane.setConstraints(daggerIv,  2, 22);//Wintergarten
		else if(this.model.getWeaponList().get(1).getPosX() == 11 && this.model.getWeaponList().get(1).getPosY() ==  8) 
				 GridPane.setConstraints(daggerIv, 11, 11);//Schwimmbad
		else if(this.model.getWeaponList().get(1).getPosX() == 13 && this.model.getWeaponList().get(1).getPosY() ==  9) 
				 GridPane.setConstraints(daggerIv, 11, 11);//Schwimmbad
		else if(this.model.getWeaponList().get(1).getPosX() ==  9 && this.model.getWeaponList().get(1).getPosY() == 12) 
				 GridPane.setConstraints(daggerIv, 11, 11);//Schwimmbad
		else if(this.model.getWeaponList().get(1).getPosX() == 14 && this.model.getWeaponList().get(1).getPosY() == 11) 
				 GridPane.setConstraints(daggerIv, 11, 11);//Schwimmbad
		else if(this.model.getWeaponList().get(1).getPosX() == 17 && this.model.getWeaponList().get(1).getPosY() ==  9) 
				 GridPane.setConstraints(daggerIv, 20, 12);//Speisezimmer
		else if(this.model.getWeaponList().get(1).getPosX() == 16 && this.model.getWeaponList().get(1).getPosY() == 12) 
				 GridPane.setConstraints(daggerIv, 20, 12);//Speisezimmer
		else if(this.model.getWeaponList().get(1).getPosX() == 19 && this.model.getWeaponList().get(1).getPosY() == 18) 
				 GridPane.setConstraints(daggerIv, 20, 21);//Küche
		else if(this.model.getWeaponList().get(1).getPosX() ==  8 && this.model.getWeaponList().get(1).getPosY() == 19) 
				 GridPane.setConstraints(daggerIv, 11, 20);//Musikzimmer
		else if(this.model.getWeaponList().get(1).getPosX() ==  9 && this.model.getWeaponList().get(1).getPosY() == 17) 
				 GridPane.setConstraints(daggerIv, 11, 20);//Musikzimmer
		else if(this.model.getWeaponList().get(1).getPosX() == 14 && this.model.getWeaponList().get(1).getPosY() == 17) 
				 GridPane.setConstraints(daggerIv, 11, 20);//Musikzimmer
		else if(this.model.getWeaponList().get(1).getPosX() == 15 && this.model.getWeaponList().get(1).getPosY() == 19) 
				 GridPane.setConstraints(daggerIv, 11, 20);//Musikzimmer
		
		GridPane.setConstraints(candlestickIv, this.model.getWeaponList().get(0).getPosX(), this.model.getWeaponList().get(0).getPosY());
		GridPane.setColumnSpan(candlestickIv, 2);
		GridPane.setRowSpan(candlestickIv, 2);
		
	 	 if(this.model.getWeaponList().get(0).getPosX() ==  6 && this.model.getWeaponList().get(0).getPosY() ==  3) 
	 		 GridPane.setConstraints(candlestickIv,  3,  1);//Arbeitszimmer
	else if(this.model.getWeaponList().get(0).getPosX() ==  9 && this.model.getWeaponList().get(0).getPosY() ==  4)
			 GridPane.setConstraints(candlestickIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(0).getPosX() == 11 && this.model.getWeaponList().get(0).getPosY() ==  6) 
			 GridPane.setConstraints(candlestickIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(0).getPosX() == 12 && this.model.getWeaponList().get(0).getPosY() ==  6)
			 GridPane.setConstraints(candlestickIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(0).getPosX() == 17 && this.model.getWeaponList().get(0).getPosY() ==  5)
			 GridPane.setConstraints(candlestickIv, 20,  3);//Salon
	else if(this.model.getWeaponList().get(0).getPosX() ==  6 && this.model.getWeaponList().get(0).getPosY() ==  8)
			 GridPane.setConstraints(candlestickIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(0).getPosX() ==  3 && this.model.getWeaponList().get(0).getPosY() == 10) 
			 GridPane.setConstraints(candlestickIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(0).getPosX() ==  1 && this.model.getWeaponList().get(0).getPosY() == 12) 
			 GridPane.setConstraints(candlestickIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(0).getPosX() ==  5 && this.model.getWeaponList().get(0).getPosY() == 15) 
			 GridPane.setConstraints(candlestickIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(0).getPosX() ==  4 && this.model.getWeaponList().get(0).getPosY() == 19) 
			 GridPane.setConstraints(candlestickIv,  2, 22);//Wintergarten
	else if(this.model.getWeaponList().get(0).getPosX() == 11 && this.model.getWeaponList().get(0).getPosY() ==  8) 
			 GridPane.setConstraints(candlestickIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(0).getPosX() == 13 && this.model.getWeaponList().get(0).getPosY() ==  9) 
			 GridPane.setConstraints(candlestickIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(0).getPosX() ==  9 && this.model.getWeaponList().get(0).getPosY() == 12) 
			 GridPane.setConstraints(candlestickIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(0).getPosX() == 14 && this.model.getWeaponList().get(0).getPosY() == 11) 
			 GridPane.setConstraints(candlestickIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(0).getPosX() == 17 && this.model.getWeaponList().get(0).getPosY() ==  9) 
			 GridPane.setConstraints(candlestickIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(0).getPosX() == 16 && this.model.getWeaponList().get(0).getPosY() == 12) 
			 GridPane.setConstraints(candlestickIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(0).getPosX() == 19 && this.model.getWeaponList().get(0).getPosY() == 18) 
			 GridPane.setConstraints(candlestickIv, 20, 21);//Küche
	else if(this.model.getWeaponList().get(0).getPosX() ==  8 && this.model.getWeaponList().get(0).getPosY() == 19) 
			 GridPane.setConstraints(candlestickIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(0).getPosX() ==  9 && this.model.getWeaponList().get(0).getPosY() == 17) 
			 GridPane.setConstraints(candlestickIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(0).getPosX() == 14 && this.model.getWeaponList().get(0).getPosY() == 17) 
			 GridPane.setConstraints(candlestickIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(0).getPosX() == 15 && this.model.getWeaponList().get(0).getPosY() == 19) 
			 GridPane.setConstraints(candlestickIv, 11, 20);//Musikzimmer
		
		GridPane.setConstraints(pipeIv, this.model.getWeaponList().get(2).getPosX(), this.model.getWeaponList().get(2).getPosY());
		GridPane.setColumnSpan(pipeIv, 2);
		GridPane.setRowSpan(pipeIv, 2);
		
	 	 if(this.model.getWeaponList().get(2).getPosX() ==  6 && this.model.getWeaponList().get(2).getPosY() ==  3) 
	 		 GridPane.setConstraints(pipeIv,  3,  1);//Arbeitszimmer
	else if(this.model.getWeaponList().get(2).getPosX() ==  9 && this.model.getWeaponList().get(2).getPosY() ==  4)
			 GridPane.setConstraints(pipeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(2).getPosX() == 11 && this.model.getWeaponList().get(2).getPosY() ==  6) 
			 GridPane.setConstraints(pipeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(2).getPosX() == 12 && this.model.getWeaponList().get(2).getPosY() ==  6)
			 GridPane.setConstraints(pipeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(2).getPosX() == 17 && this.model.getWeaponList().get(2).getPosY() ==  5)
			 GridPane.setConstraints(pipeIv, 20,  3);//Salon
	else if(this.model.getWeaponList().get(2).getPosX() ==  6 && this.model.getWeaponList().get(2).getPosY() ==  8)
			 GridPane.setConstraints(pipeIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(2).getPosX() ==  3 && this.model.getWeaponList().get(2).getPosY() == 10) 
			 GridPane.setConstraints(pipeIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(2).getPosX() ==  1 && this.model.getWeaponList().get(2).getPosY() == 12) 
			 GridPane.setConstraints(pipeIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(2).getPosX() ==  5 && this.model.getWeaponList().get(2).getPosY() == 15) 
			 GridPane.setConstraints(pipeIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(2).getPosX() ==  4 && this.model.getWeaponList().get(2).getPosY() == 19) 
			 GridPane.setConstraints(pipeIv,  2, 22);//Wintergarten
	else if(this.model.getWeaponList().get(2).getPosX() == 11 && this.model.getWeaponList().get(2).getPosY() ==  8) 
			 GridPane.setConstraints(pipeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(2).getPosX() == 13 && this.model.getWeaponList().get(2).getPosY() ==  9) 
			 GridPane.setConstraints(pipeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(2).getPosX() ==  9 && this.model.getWeaponList().get(2).getPosY() == 12) 
			 GridPane.setConstraints(pipeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(2).getPosX() == 14 && this.model.getWeaponList().get(2).getPosY() == 11) 
			 GridPane.setConstraints(pipeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(2).getPosX() == 17 && this.model.getWeaponList().get(2).getPosY() ==  9) 
			 GridPane.setConstraints(pipeIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(2).getPosX() == 16 && this.model.getWeaponList().get(2).getPosY() == 12) 
			 GridPane.setConstraints(pipeIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(2).getPosX() == 19 && this.model.getWeaponList().get(2).getPosY() == 18) 
			 GridPane.setConstraints(pipeIv, 20, 21);//Küche
	else if(this.model.getWeaponList().get(2).getPosX() ==  8 && this.model.getWeaponList().get(2).getPosY() == 19) 
			 GridPane.setConstraints(pipeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(2).getPosX() ==  9 && this.model.getWeaponList().get(2).getPosY() == 17) 
			 GridPane.setConstraints(pipeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(2).getPosX() == 14 && this.model.getWeaponList().get(2).getPosY() == 17) 
			 GridPane.setConstraints(pipeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(2).getPosX() == 15 && this.model.getWeaponList().get(2).getPosY() == 19) 
			 GridPane.setConstraints(pipeIv, 11, 20);//Musikzimmer
		
		
		GridPane.setConstraints(revolverIv, this.model.getWeaponList().get(3).getPosX(), this.model.getWeaponList().get(3).getPosY());
		GridPane.setColumnSpan(revolverIv, 2);
		GridPane.setRowSpan(revolverIv, 2);
		
	 	 if(this.model.getWeaponList().get(3).getPosX() ==  6 && this.model.getWeaponList().get(3).getPosY() ==  3) 
	 		 GridPane.setConstraints(revolverIv,  3,  1);//Arbeitszimmer
	else if(this.model.getWeaponList().get(3).getPosX() ==  9 && this.model.getWeaponList().get(3).getPosY() ==  4)
			 GridPane.setConstraints(revolverIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(3).getPosX() == 11 && this.model.getWeaponList().get(3).getPosY() ==  6) 
			 GridPane.setConstraints(revolverIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(3).getPosX() == 12 && this.model.getWeaponList().get(3).getPosY() ==  6)
			 GridPane.setConstraints(revolverIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(3).getPosX() == 17 && this.model.getWeaponList().get(3).getPosY() ==  5)
			 GridPane.setConstraints(revolverIv, 20,  3);//Salon
	else if(this.model.getWeaponList().get(3).getPosX() ==  6 && this.model.getWeaponList().get(3).getPosY() ==  8)
			 GridPane.setConstraints(revolverIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(3).getPosX() ==  3 && this.model.getWeaponList().get(3).getPosY() == 10) 
			 GridPane.setConstraints(revolverIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(3).getPosX() ==  1 && this.model.getWeaponList().get(3).getPosY() == 12) 
			 GridPane.setConstraints(revolverIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(3).getPosX() ==  5 && this.model.getWeaponList().get(3).getPosY() == 15) 
			 GridPane.setConstraints(revolverIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(3).getPosX() ==  4 && this.model.getWeaponList().get(3).getPosY() == 19) 
			 GridPane.setConstraints(revolverIv,  2, 22);//Wintergarten
	else if(this.model.getWeaponList().get(3).getPosX() == 11 && this.model.getWeaponList().get(3).getPosY() ==  8) 
			 GridPane.setConstraints(revolverIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(3).getPosX() == 13 && this.model.getWeaponList().get(3).getPosY() ==  9) 
			 GridPane.setConstraints(revolverIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(3).getPosX() ==  9 && this.model.getWeaponList().get(3).getPosY() == 12) 
			 GridPane.setConstraints(revolverIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(3).getPosX() == 14 && this.model.getWeaponList().get(3).getPosY() == 11) 
			 GridPane.setConstraints(revolverIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(3).getPosX() == 17 && this.model.getWeaponList().get(3).getPosY() ==  9) 
			 GridPane.setConstraints(revolverIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(3).getPosX() == 16 && this.model.getWeaponList().get(3).getPosY() == 12) 
			 GridPane.setConstraints(revolverIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(3).getPosX() == 19 && this.model.getWeaponList().get(3).getPosY() == 18) 
			 GridPane.setConstraints(revolverIv, 20, 21);//Küche
	else if(this.model.getWeaponList().get(3).getPosX() ==  8 && this.model.getWeaponList().get(3).getPosY() == 19) 
			 GridPane.setConstraints(revolverIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(3).getPosX() ==  9 && this.model.getWeaponList().get(3).getPosY() == 17) 
			 GridPane.setConstraints(revolverIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(3).getPosX() == 14 && this.model.getWeaponList().get(3).getPosY() == 17) 
			 GridPane.setConstraints(revolverIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(3).getPosX() == 15 && this.model.getWeaponList().get(3).getPosY() == 19) 
			 GridPane.setConstraints(revolverIv, 11, 20);//Musikzimmer
		
		GridPane.setConstraints(ropeIv, this.model.getWeaponList().get(4).getPosX(), this.model.getWeaponList().get(4).getPosY());
		GridPane.setColumnSpan(ropeIv, 2);
		GridPane.setRowSpan(ropeIv, 2);
		
	 	 if(this.model.getWeaponList().get(4).getPosX() ==  6 && this.model.getWeaponList().get(4).getPosY() ==  3) 
	 		 GridPane.setConstraints(ropeIv,  3,  1);//Arbeitszimmer
	else if(this.model.getWeaponList().get(4).getPosX() ==  9 && this.model.getWeaponList().get(4).getPosY() ==  4)
			 GridPane.setConstraints(ropeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(4).getPosX() == 11 && this.model.getWeaponList().get(4).getPosY() ==  6) 
			 GridPane.setConstraints(ropeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(4).getPosX() == 12 && this.model.getWeaponList().get(4).getPosY() ==  6)
			 GridPane.setConstraints(ropeIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(4).getPosX() == 17 && this.model.getWeaponList().get(4).getPosY() ==  5)
			 GridPane.setConstraints(ropeIv, 20,  3);//Salon
	else if(this.model.getWeaponList().get(4).getPosX() ==  6 && this.model.getWeaponList().get(4).getPosY() ==  8)
			 GridPane.setConstraints(ropeIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(4).getPosX() ==  3 && this.model.getWeaponList().get(4).getPosY() == 10) 
			 GridPane.setConstraints(ropeIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(4).getPosX() ==  1 && this.model.getWeaponList().get(4).getPosY() == 12) 
			 GridPane.setConstraints(ropeIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(4).getPosX() ==  5 && this.model.getWeaponList().get(4).getPosY() == 15) 
			 GridPane.setConstraints(ropeIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(4).getPosX() ==  4 && this.model.getWeaponList().get(4).getPosY() == 19) 
			 GridPane.setConstraints(ropeIv,  2, 22);//Wintergarten
	else if(this.model.getWeaponList().get(4).getPosX() == 11 && this.model.getWeaponList().get(4).getPosY() ==  8) 
			 GridPane.setConstraints(ropeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(4).getPosX() == 13 && this.model.getWeaponList().get(4).getPosY() ==  9) 
			 GridPane.setConstraints(ropeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(4).getPosX() ==  9 && this.model.getWeaponList().get(4).getPosY() == 12) 
			 GridPane.setConstraints(ropeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(4).getPosX() == 14 && this.model.getWeaponList().get(4).getPosY() == 11) 
			 GridPane.setConstraints(ropeIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(4).getPosX() == 17 && this.model.getWeaponList().get(4).getPosY() ==  9) 
			 GridPane.setConstraints(ropeIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(4).getPosX() == 16 && this.model.getWeaponList().get(4).getPosY() == 12) 
			 GridPane.setConstraints(ropeIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(4).getPosX() == 19 && this.model.getWeaponList().get(4).getPosY() == 18) 
			 GridPane.setConstraints(ropeIv, 20, 21);//Küche
	else if(this.model.getWeaponList().get(4).getPosX() ==  8 && this.model.getWeaponList().get(4).getPosY() == 19) 
			 GridPane.setConstraints(ropeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(4).getPosX() ==  9 && this.model.getWeaponList().get(4).getPosY() == 17) 
			 GridPane.setConstraints(ropeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(4).getPosX() == 14 && this.model.getWeaponList().get(4).getPosY() == 17) 
			 GridPane.setConstraints(ropeIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(4).getPosX() == 15 && this.model.getWeaponList().get(4).getPosY() == 19) 
			 GridPane.setConstraints(ropeIv, 11, 20);//Musikzimmer
		
		GridPane.setConstraints(spannerIv, this.model.getWeaponList().get(5).getPosX(), this.model.getWeaponList().get(5).getPosY());
		GridPane.setColumnSpan(spannerIv, 2);
		GridPane.setRowSpan(spannerIv, 2);
		
	 	 if(this.model.getWeaponList().get(5).getPosX() ==  6 && this.model.getWeaponList().get(5).getPosY() ==  3) 
	 		 GridPane.setConstraints(spannerIv,  3,  1);//Arbeitszimmer
	else if(this.model.getWeaponList().get(5).getPosX() ==  9 && this.model.getWeaponList().get(5).getPosY() ==  4)
			 GridPane.setConstraints(spannerIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(5).getPosX() == 11 && this.model.getWeaponList().get(5).getPosY() ==  6) 
			 GridPane.setConstraints(spannerIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(5).getPosX() == 12 && this.model.getWeaponList().get(5).getPosY() ==  6)
			 GridPane.setConstraints(spannerIv, 12,  3);//Eingangshalle
	else if(this.model.getWeaponList().get(5).getPosX() == 17 && this.model.getWeaponList().get(5).getPosY() ==  5)
			 GridPane.setConstraints(spannerIv, 20,  3);//Salon
	else if(this.model.getWeaponList().get(5).getPosX() ==  6 && this.model.getWeaponList().get(5).getPosY() ==  8)
			 GridPane.setConstraints(spannerIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(5).getPosX() ==  3 && this.model.getWeaponList().get(5).getPosY() == 10) 
			 GridPane.setConstraints(spannerIv,  3,  8);//Bibliothek
	else if(this.model.getWeaponList().get(5).getPosX() ==  1 && this.model.getWeaponList().get(5).getPosY() == 12) 
			 GridPane.setConstraints(spannerIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(5).getPosX() ==  5 && this.model.getWeaponList().get(5).getPosY() == 15) 
			 GridPane.setConstraints(spannerIv,  2, 14);//Billiardzimmer
	else if(this.model.getWeaponList().get(5).getPosX() ==  4 && this.model.getWeaponList().get(5).getPosY() == 19) 
			 GridPane.setConstraints(spannerIv,  2, 22);//Wintergarten
	else if(this.model.getWeaponList().get(5).getPosX() == 11 && this.model.getWeaponList().get(5).getPosY() ==  8) 
			 GridPane.setConstraints(spannerIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(5).getPosX() == 13 && this.model.getWeaponList().get(5).getPosY() ==  9) 
			 GridPane.setConstraints(spannerIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(5).getPosX() ==  9 && this.model.getWeaponList().get(5).getPosY() == 12) 
			 GridPane.setConstraints(spannerIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(5).getPosX() == 14 && this.model.getWeaponList().get(5).getPosY() == 11) 
			 GridPane.setConstraints(spannerIv, 11, 11);//Schwimmbad
	else if(this.model.getWeaponList().get(5).getPosX() == 17 && this.model.getWeaponList().get(5).getPosY() ==  9) 
			 GridPane.setConstraints(spannerIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(5).getPosX() == 16 && this.model.getWeaponList().get(5).getPosY() == 12) 
			 GridPane.setConstraints(spannerIv, 20, 12);//Speisezimmer
	else if(this.model.getWeaponList().get(5).getPosX() == 19 && this.model.getWeaponList().get(5).getPosY() == 18) 
			 GridPane.setConstraints(spannerIv, 20, 21);//Küche
	else if(this.model.getWeaponList().get(5).getPosX() ==  8 && this.model.getWeaponList().get(5).getPosY() == 19) 
			 GridPane.setConstraints(spannerIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(5).getPosX() ==  9 && this.model.getWeaponList().get(5).getPosY() == 17) 
			 GridPane.setConstraints(spannerIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(5).getPosX() == 14 && this.model.getWeaponList().get(5).getPosY() == 17) 
			 GridPane.setConstraints(spannerIv, 11, 20);//Musikzimmer
	else if(this.model.getWeaponList().get(5).getPosX() == 15 && this.model.getWeaponList().get(5).getPosY() == 19) 
			 GridPane.setConstraints(spannerIv, 11, 20);//Musikzimmer
	
	}
	
}
