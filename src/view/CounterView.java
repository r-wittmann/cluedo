package view;

/**
 * class contains counters
 * @author Maurice, Rainer
 */

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Gamefield;

	public class CounterView extends GridPane {

	public Gamefield model;
	public SimpleIntegerProperty x0 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y0 = new SimpleIntegerProperty();
	public SimpleIntegerProperty x1 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y1 = new SimpleIntegerProperty();
	public SimpleIntegerProperty x2 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y2 = new SimpleIntegerProperty();
	public SimpleIntegerProperty x3 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y3 = new SimpleIntegerProperty();
	public SimpleIntegerProperty x4 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y4 = new SimpleIntegerProperty();
	public SimpleIntegerProperty x5 = new SimpleIntegerProperty();
	public SimpleIntegerProperty y5 = new SimpleIntegerProperty();
	
	Circle red = new Circle();
	Circle white = new Circle();
	Circle green = new Circle();
	Circle purple = new Circle();
	Circle yellow = new Circle();
	Circle blue = new Circle();	
	
	public CounterView(Gamefield model){
		this.model = model;
		//counter red
		x0.bindBidirectional(this.model.getCounterList().get(0).getPositionXProperty());
		y0.bindBidirectional(this.model.getCounterList().get(0).getPositionYProperty());
		//counter yellow
		x1.bindBidirectional(this.model.getCounterList().get(1).getPositionXProperty());
		y1.bindBidirectional(this.model.getCounterList().get(1).getPositionYProperty());
		//counter white
		x2.bindBidirectional(this.model.getCounterList().get(2).getPositionXProperty());
		y2.bindBidirectional(this.model.getCounterList().get(2).getPositionYProperty());
		//counter green
		x3.bindBidirectional(this.model.getCounterList().get(3).getPositionXProperty());
		y3.bindBidirectional(this.model.getCounterList().get(3).getPositionYProperty());
		//counter blue
		x4.bindBidirectional(this.model.getCounterList().get(4).getPositionXProperty());
		y4.bindBidirectional(this.model.getCounterList().get(4).getPositionYProperty());
		//counter purple
		x5.bindBidirectional(this.model.getCounterList().get(5).getPositionXProperty());
		y5.bindBidirectional(this.model.getCounterList().get(5).getPositionYProperty());
		initStyle();
		initTooltips();
		
		//add players
		this.getChildren().addAll(red,white,green,purple,yellow,blue);
	}

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	public void initStyle() {
		
		//set Circles for players
		red.setRadius(10);
		red.setStyle("-fx-fill:RED;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		red.setTranslateX(2);
		GridPane.setConstraints(red, x0.getValue(), y0.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
		//um in der Mitte platz für die Waffe zu machen
			 if(x0.get() ==  6 && y0.get() ==  3) GridPane.setConstraints(red,  5,  1);//Arbeitszimmer
		else if(x0.get() ==  9 && y0.get() ==  4) GridPane.setConstraints(red, 14,  3);//Eingangshalle
		else if(x0.get() == 11 && y0.get() ==  6) GridPane.setConstraints(red, 14,  3);//Eingangshalle
		else if(x0.get() == 12 && y0.get() ==  6) GridPane.setConstraints(red, 14,  3);//Eingangshalle
		else if(x0.get() == 17 && y0.get() ==  5) GridPane.setConstraints(red, 22,  3);//Salon
		else if(x0.get() ==  6 && y0.get() ==  8) GridPane.setConstraints(red,  5,  8);//Bibliothek 
		else if(x0.get() ==  3 && y0.get() == 10) GridPane.setConstraints(red,  5,  8);//Bibliothek 
		else if(x0.get() ==  1 && y0.get() == 12) GridPane.setConstraints(red,  4, 14);//Billiardzimmer
		else if(x0.get() ==  5 && y0.get() == 15) GridPane.setConstraints(red,  4, 14);//Billiardzimmer
		else if(x0.get() ==  4 && y0.get() == 19) GridPane.setConstraints(red,  4, 22);//Wintergarten
		else if(x0.get() == 11 && y0.get() ==  8) GridPane.setConstraints(red, 13, 11);//Schwimmbad
		else if(x0.get() == 13 && y0.get() ==  9) GridPane.setConstraints(red, 13, 11);//Schwimmbad
		else if(x0.get() ==  9 && y0.get() == 12) GridPane.setConstraints(red, 13, 11);//Schwimmbad
		else if(x0.get() == 14 && y0.get() == 11) GridPane.setConstraints(red, 13, 11);//Schwimmbad
		else if(x0.get() == 17 && y0.get() ==  9) GridPane.setConstraints(red, 22, 12);//Speisezimmer
		else if(x0.get() == 16 && y0.get() == 12) GridPane.setConstraints(red, 22, 12);//Speisezimmer
		else if(x0.get() == 19 && y0.get() == 18) GridPane.setConstraints(red, 22, 21);//Küche
		else if(x0.get() ==  8 && y0.get() == 19) GridPane.setConstraints(red, 13, 20);//Musikzimmer
		else if(x0.get() ==  9 && y0.get() == 17) GridPane.setConstraints(red, 13, 20);//Musikzimmer
		else if(x0.get() == 14 && y0.get() == 17) GridPane.setConstraints(red, 13, 20);//Musikzimmer
		else if(x0.get() == 15 && y0.get() == 19) GridPane.setConstraints(red, 13, 20);//Musikzimmer
		
		white.setRadius(10);
		white.setStyle("-fx-fill:WHITE;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		white.setTranslateX(2);
		GridPane.setConstraints(white, x2.getValue(), y2.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
				//um in der Mitte platz für die Waffe zu machen
			 if(x2.get() ==  6 && y2.get() ==  3) GridPane.setConstraints(white,  1,  1);//Arbeitszimmer
		else if(x2.get() ==  9 && y2.get() ==  4) GridPane.setConstraints(white, 10,  3);//Eingangshalle
		else if(x2.get() == 11 && y2.get() ==  6) GridPane.setConstraints(white, 10,  3);//Eingangshalle
		else if(x2.get() == 12 && y2.get() ==  6) GridPane.setConstraints(white, 10,  3);//Eingangshalle
		else if(x2.get() == 17 && y2.get() ==  5) GridPane.setConstraints(white, 18,  3);//Salon
		else if(x2.get() ==  6 && y2.get() ==  8) GridPane.setConstraints(white,  1,  8);//Bibliothek
		else if(x2.get() ==  3 && y2.get() == 10) GridPane.setConstraints(white,  1,  8);//Bibliothek
		else if(x2.get() ==  1 && y2.get() == 12) GridPane.setConstraints(white,  0, 14);//Billiardzimmer
		else if(x2.get() ==  5 && y2.get() == 15) GridPane.setConstraints(white,  0, 14);//Billiardzimmer
		else if(x2.get() ==  4 && y2.get() == 19) GridPane.setConstraints(white,  0, 22);//Wintergarten
		else if(x2.get() == 11 && y2.get() ==  8) GridPane.setConstraints(white,  9, 11);//Schwimmbad
		else if(x2.get() == 13 && y2.get() ==  9) GridPane.setConstraints(white,  9, 11);//Schwimmbad
		else if(x2.get() ==  9 && y2.get() == 12) GridPane.setConstraints(white,  9, 11);//Schwimmbad
		else if(x2.get() == 14 && y2.get() == 11) GridPane.setConstraints(white,  9, 11);//Schwimmbad
		else if(x2.get() == 17 && y2.get() ==  9) GridPane.setConstraints(white, 18, 12);//Speisezimmer
		else if(x2.get() == 16 && y2.get() == 12) GridPane.setConstraints(white, 18, 12);//Speisezimmer
		else if(x2.get() == 19 && y2.get() == 18) GridPane.setConstraints(white, 18, 21);//Küche
		else if(x2.get() ==  8 && y2.get() == 19) GridPane.setConstraints(white,  9, 20);//Musikzimmer
		else if(x2.get() ==  9 && y2.get() == 17) GridPane.setConstraints(white,  9, 20);//Musikzimmer
		else if(x2.get() == 14 && y2.get() == 17) GridPane.setConstraints(white,  9, 20);//Musikzimmer
		else if(x2.get() == 15 && y2.get() == 19) GridPane.setConstraints(white,  9, 20);//Musikzimmer
		
		green.setRadius(10);
		green.setStyle("-fx-fill:GREEN;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		green.setTranslateX(2);
		GridPane.setConstraints(green, x3.getValue(), y3.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
				//um in der Mitte platz für die Waffe zu machen
			 if(x3.get() ==  6 && y3.get() ==  3) GridPane.setConstraints(green,  4,  0);//Arbeitszimmer
		else if(x3.get() ==  9 && y3.get() ==  4) GridPane.setConstraints(green, 13,  2);//Eingangshalle
		else if(x3.get() == 11 && y3.get() ==  6) GridPane.setConstraints(green, 13,  2);//Eingangshalle
		else if(x3.get() == 12 && y3.get() ==  6) GridPane.setConstraints(green, 13,  2);//Eingangshalle
		else if(x3.get() == 17 && y3.get() ==  5) GridPane.setConstraints(green, 21,  2);//Salon
		else if(x3.get() ==  6 && y3.get() ==  8) GridPane.setConstraints(green,  4,  7);//Bibliothek
		else if(x3.get() ==  3 && y3.get() == 10) GridPane.setConstraints(green,  4,  7);//Bibliothek
		else if(x3.get() ==  1 && y3.get() == 12) GridPane.setConstraints(green,  3, 13);//Billiardzimmer
		else if(x3.get() ==  5 && y3.get() == 15) GridPane.setConstraints(green,  3, 13);//Billiardzimmer
		else if(x3.get() ==  4 && y3.get() == 19) GridPane.setConstraints(green,  3, 21);//Wintergarten
		else if(x3.get() == 11 && y3.get() ==  8) GridPane.setConstraints(green, 12, 10);//Schwimmbad
		else if(x3.get() == 13 && y3.get() ==  9) GridPane.setConstraints(green, 12, 10);//Schwimmbad
		else if(x3.get() ==  9 && y3.get() == 12) GridPane.setConstraints(green, 12, 10);//Schwimmbad
		else if(x3.get() == 14 && y3.get() == 11) GridPane.setConstraints(green, 12, 10);//Schwimmbad
		else if(x3.get() == 17 && y3.get() ==  9) GridPane.setConstraints(green, 21, 11);//Speisezimmer
		else if(x3.get() == 16 && y3.get() == 12) GridPane.setConstraints(green, 21, 11);//Speisezimmer
		else if(x3.get() == 19 && y3.get() == 18) GridPane.setConstraints(green, 21, 20);//Küche
		else if(x3.get() ==  8 && y3.get() == 19) GridPane.setConstraints(green, 12, 19);//Musikzimmer
		else if(x3.get() ==  9 && y3.get() == 17) GridPane.setConstraints(green, 12, 19);//Musikzimmer
		else if(x3.get() == 14 && y3.get() == 17) GridPane.setConstraints(green, 12, 19);//Musikzimmer
		else if(x3.get() == 15 && y3.get() == 19) GridPane.setConstraints(green, 12, 19);//Musikzimmer
		
		purple.setRadius(10);
		purple.setStyle("-fx-fill:PURPLE;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		purple.setTranslateX(2);
		GridPane.setConstraints(purple, x5.getValue(), y5.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
				//um in der Mitte platz für die Waffe zu machen
			 if(x5.get() ==  6 && y5.get() ==  3) GridPane.setConstraints(purple,  4,  2);//Arbeitszimmer
		else if(x5.get() ==  9 && y5.get() ==  4) GridPane.setConstraints(purple, 13,  4);//Eingangshalle
		else if(x5.get() == 11 && y5.get() ==  6) GridPane.setConstraints(purple, 13,  4);//Eingangshalle
		else if(x5.get() == 12 && y5.get() ==  6) GridPane.setConstraints(purple, 13,  4);//Eingangshalle
		else if(x5.get() == 17 && y5.get() ==  5) GridPane.setConstraints(purple, 21,  4);//Salon
		else if(x5.get() ==  6 && y5.get() ==  8) GridPane.setConstraints(purple,  4,  9);//Bibliothek
		else if(x5.get() ==  3 && y5.get() == 10) GridPane.setConstraints(purple,  4,  9);//Bibliothek
		else if(x5.get() ==  1 && y5.get() == 12) GridPane.setConstraints(purple,  3, 15);//Billiardzimmer
		else if(x5.get() ==  5 && y5.get() == 15) GridPane.setConstraints(purple,  3, 15);//Billiardzimmer
		else if(x5.get() ==  4 && y5.get() == 19) GridPane.setConstraints(purple,  3, 23);//Wintergarten
		else if(x5.get() == 11 && y5.get() ==  8) GridPane.setConstraints(purple, 12, 12);//Schwimmbad
		else if(x5.get() == 13 && y5.get() ==  9) GridPane.setConstraints(purple, 12, 12);//Schwimmbad
		else if(x5.get() ==  9 && y5.get() == 12) GridPane.setConstraints(purple, 12, 12);//Schwimmbad
		else if(x5.get() == 14 && y5.get() == 11) GridPane.setConstraints(purple, 12, 12);//Schwimmbad
		else if(x5.get() == 17 && y5.get() ==  9) GridPane.setConstraints(purple, 21, 13);//Speisezimmer
		else if(x5.get() == 16 && y5.get() == 12) GridPane.setConstraints(purple, 21, 13);//Speisezimmer
		else if(x5.get() == 19 && y5.get() == 18) GridPane.setConstraints(purple, 21, 22);//Küche
		else if(x5.get() ==  8 && y5.get() == 19) GridPane.setConstraints(purple, 12, 21);//Musikzimmer
		else if(x5.get() ==  9 && y5.get() == 17) GridPane.setConstraints(purple, 12, 21);//Musikzimmer
		else if(x5.get() == 14 && y5.get() == 17) GridPane.setConstraints(purple, 12, 21);//Musikzimmer
		else if(x5.get() == 15 && y5.get() == 19) GridPane.setConstraints(purple, 12, 21);//Musikzimmer
		
		yellow.setRadius(10);
		yellow.setStyle("-fx-fill:YELLOW;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		yellow.setTranslateX(2);
		GridPane.setConstraints(yellow, x1.getValue(), y1.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
				//um in der Mitte platz für die Waffe zu machen
			 if(x1.get() ==  6 && y1.get() ==  3) GridPane.setConstraints(yellow,  2,  0);//Arbeitszimmer
		else if(x1.get() ==  9 && y1.get() ==  4) GridPane.setConstraints(yellow, 11,  2);//Eingangshalle
		else if(x1.get() == 11 && y1.get() ==  6) GridPane.setConstraints(yellow, 11,  2);//Eingangshalle
		else if(x1.get() == 12 && y1.get() ==  6) GridPane.setConstraints(yellow, 11,  2);//Eingangshalle
		else if(x1.get() == 17 && y1.get() ==  5) GridPane.setConstraints(yellow, 19,  2);//Salon
		else if(x1.get() ==  6 && y1.get() ==  8) GridPane.setConstraints(yellow,  2,  7);//Bibliothek
		else if(x1.get() ==  3 && y1.get() == 10) GridPane.setConstraints(yellow,  2,  7);//Bibliothek
		else if(x1.get() ==  1 && y1.get() == 12) GridPane.setConstraints(yellow,  1, 13);//Billiardzimmer
		else if(x1.get() ==  5 && y1.get() == 15) GridPane.setConstraints(yellow,  1, 13);//Billiardzimmer
		else if(x1.get() ==  4 && y1.get() == 19) GridPane.setConstraints(yellow,  1, 21);//Wintergarten
		else if(x1.get() == 11 && y1.get() ==  8) GridPane.setConstraints(yellow, 10, 10);//Schwimmbad
		else if(x1.get() == 13 && y1.get() ==  9) GridPane.setConstraints(yellow, 10, 10);//Schwimmbad
		else if(x1.get() ==  9 && y1.get() == 12) GridPane.setConstraints(yellow, 10, 10);//Schwimmbad
		else if(x1.get() == 14 && y1.get() == 11) GridPane.setConstraints(yellow, 10, 10);//Schwimmbad
		else if(x1.get() == 17 && y1.get() ==  9) GridPane.setConstraints(yellow, 19, 11);//Speisezimmer
		else if(x1.get() == 16 && y1.get() == 12) GridPane.setConstraints(yellow, 19, 11);//Speisezimmer
		else if(x1.get() == 19 && y1.get() == 18) GridPane.setConstraints(yellow, 19, 20);//Küche
		else if(x1.get() ==  8 && y1.get() == 19) GridPane.setConstraints(yellow, 10, 19);//Musikzimmer
		else if(x1.get() ==  9 && y1.get() == 17) GridPane.setConstraints(yellow, 10, 19);//Musikzimmer
		else if(x1.get() == 14 && y1.get() == 17) GridPane.setConstraints(yellow, 10, 19);//Musikzimmer
		else if(x1.get() == 15 && y1.get() == 19) GridPane.setConstraints(yellow, 10, 19);//Musikzimmer
		
		blue.setRadius(10);
		blue.setStyle("-fx-fill:BLUE;"
				+ "-fx-stroke:DARKGRAY;"
				+ "-fx-stroke-width:2px");
		blue.setTranslateX(2);
		GridPane.setConstraints(blue, x4.getValue(), y4.getValue());
		//Wenn Spieler einen Raum betritt wird er erst ins Mittlere Feld und dann zur Seite geschoben
				//um in der Mitte platz für die Waffe zu machen
			 if(x4.get() ==  6 && y4.get() ==  3) GridPane.setConstraints(blue,  2,  2);//Arbeitszimmer
		else if(x4.get() ==  9 && y4.get() ==  4) GridPane.setConstraints(blue, 11,  4);//Eingangshalle
		else if(x4.get() == 11 && y4.get() ==  6) GridPane.setConstraints(blue, 11,  4);//Eingangshalle
		else if(x4.get() == 12 && y4.get() ==  6) GridPane.setConstraints(blue, 11,  4);//Eingangshalle
		else if(x4.get() == 17 && y4.get() ==  5) GridPane.setConstraints(blue, 19,  4);//Salon
		else if(x4.get() ==  6 && y4.get() ==  8) GridPane.setConstraints(blue,  2,  9);//Bibliothek
		else if(x4.get() ==  3 && y4.get() == 10) GridPane.setConstraints(blue,  2,  9);//Bibliothek
		else if(x4.get() ==  1 && y4.get() == 12) GridPane.setConstraints(blue,  1, 15);//Billiardzimmer
		else if(x4.get() ==  5 && y4.get() == 15) GridPane.setConstraints(blue,  1, 15);//Billiardzimmer
		else if(x4.get() ==  4 && y4.get() == 19) GridPane.setConstraints(blue,  1, 23);//Wintergarten
		else if(x4.get() == 11 && y4.get() ==  8) GridPane.setConstraints(blue, 10, 12);//Schwimmbad
		else if(x4.get() == 13 && y4.get() ==  9) GridPane.setConstraints(blue, 10, 12);//Schwimmbad
		else if(x4.get() ==  9 && y4.get() == 12) GridPane.setConstraints(blue, 10, 12);//Schwimmbad
		else if(x4.get() == 14 && y4.get() == 11) GridPane.setConstraints(blue, 10, 12);//Schwimmbad
		else if(x4.get() == 17 && y4.get() ==  9) GridPane.setConstraints(blue, 19, 13);//Speisezimmer
		else if(x4.get() == 16 && y4.get() == 12) GridPane.setConstraints(blue, 19, 13);//Speisezimmer
		else if(x4.get() == 19 && y4.get() == 18) GridPane.setConstraints(blue, 19, 22);//Küche
		else if(x4.get() ==  8 && y4.get() == 19) GridPane.setConstraints(blue, 10, 21);//Musikzimmer
		else if(x4.get() ==  9 && y4.get() == 17) GridPane.setConstraints(blue, 10, 21);//Musikzimmer
		else if(x4.get() == 14 && y4.get() == 17) GridPane.setConstraints(blue, 10, 21);//Musikzimmer
		else if(x4.get() == 15 && y4.get() == 19) GridPane.setConstraints(blue, 10, 21);//Musikzimmer
		
		/**
		 * add rectangles to help positioning the players
		 * @author Wittmann Rainer
		 */
		
		for(int i = 0; i <= 23; i++){
			for(int j = 0; j <= 24; j++){
				Rectangle rec = new Rectangle(27,27,Color.TRANSPARENT);
				GridPane.setConstraints(rec, i, j);
				this.getChildren().add(rec);
			}
		}

	}
	/**
	 * This method generates Tooltips for roomnames and adds them to respective rectangles
	 * @author Wittmann Rainer
	 */
	public void initTooltips(){
		
		//Schwimmbad
		Rectangle swim = new Rectangle(135,189);
		swim.setFill(Color.TRANSPARENT);
		
		Tooltip swimT = new Tooltip("Schwimmbad");
		Tooltip.install(swim, swimT);
		
		GridPane.setConstraints(swim,9,8,5,7);
		
		//Arbeitszimmer
		Rectangle work1 = new Rectangle(189,108);
		work1.setFill(Color.TRANSPARENT);
		Rectangle work2 = new Rectangle(27,27);
		work2.setFill(Color.TRANSPARENT);
		
		Tooltip workT = new Tooltip("Arbeitszimmer\n" + "Geheimgang zur Küche");
		Tooltip.install(work1, workT);
		Tooltip.install(work2, workT);
		
		GridPane.setConstraints(work1,0,0,7,4);
		GridPane.setConstraints(work2,0,4);
		
		//Eingangshalle
		Rectangle hall1 = new Rectangle(216,27);
		Rectangle hall2 = new Rectangle(162,162);
		hall1.setFill(Color.TRANSPARENT);
		hall2.setFill(Color.TRANSPARENT);
		
		Tooltip hallT = new Tooltip("Eingangshalle");
		Tooltip.install(hall1, hallT);
		Tooltip.install(hall2, hallT);
		
		GridPane.setConstraints(hall1, 8, 0, 8, 1);
		GridPane.setConstraints(hall2, 9, 1, 6, 6);
		
		//Bibliothek
		Rectangle bib1 = new Rectangle(162,135);
		Rectangle bib2 = new Rectangle(27,81);
		Rectangle bib3 = new Rectangle(27,27);
		bib1.setFill(Color.TRANSPARENT);
		bib2.setFill(Color.TRANSPARENT);
		bib3.setFill(Color.TRANSPARENT);
		
		Tooltip bibT = new Tooltip("Bibliothek");
		Tooltip.install(bib1, bibT);
		Tooltip.install(bib2, bibT);
		Tooltip.install(bib3, bibT);
		
		GridPane.setConstraints(bib1,0,6,6,5);
		GridPane.setConstraints(bib2,6,7,1,3);
		GridPane.setConstraints(bib3,0,11);
		
		//Schachzimmer
		Rectangle bil1 = new Rectangle(162,135);
		Rectangle bil2 = new Rectangle(27,27);
		bil1.setFill(Color.TRANSPARENT);
		bil2.setFill(Color.TRANSPARENT);
		
		Tooltip bilT = new Tooltip("Schachzimmer");
		Tooltip.install(bil1, bilT);
		Tooltip.install(bil2, bilT);
		
		GridPane.setConstraints(bil1,0,12,6,5);
		GridPane.setConstraints(bil2,0,17);
		
		//Wintergarten
		Rectangle win1 = new Rectangle(5*27,27);
		Rectangle win2 = new Rectangle(6*27,5*27);
		Rectangle win3 = new Rectangle(27,54);
		Rectangle win4 = new Rectangle(54,27);
		win1.setFill(Color.TRANSPARENT);
		win2.setFill(Color.TRANSPARENT);
		win3.setFill(Color.TRANSPARENT);
		win4.setFill(Color.TRANSPARENT);
		
		Tooltip winT = new Tooltip("Wintergarten\n" + "Geheimgang zum Salon");
		Tooltip.install(win1, winT);
		Tooltip.install(win2, winT);
		Tooltip.install(win3, winT);
		Tooltip.install(win4, winT);
		
		GridPane.setConstraints(win1,0,19,5,1);
		GridPane.setConstraints(win2,0,20,6,5);
		GridPane.setConstraints(win3,6,23,1,2);
		GridPane.setConstraints(win4,7,24,2,1);
		
		//Salon
		Rectangle sal1 = new Rectangle(7*27,6*27);
		Rectangle sal2 = new Rectangle(27,27);
		sal1.setFill(Color.TRANSPARENT);
		sal2.setFill(Color.TRANSPARENT);
		
		Tooltip salT = new Tooltip("Salon\n" + "Geheimgang zum Wintergarten");
		Tooltip.install(sal1, salT);
		Tooltip.install(sal2, salT);
		
		GridPane.setConstraints(sal1,17,0,7,6);
		GridPane.setConstraints(sal2,23,6);
		
		//Speisezimmer
		Rectangle spe1 = new Rectangle(3*27,6*27);
		Rectangle spe2 = new Rectangle(4*27,7*27);
		Rectangle spe3 = new Rectangle(27,9*27);
		spe1.setFill(Color.TRANSPARENT);
		spe2.setFill(Color.TRANSPARENT);
		spe3.setFill(Color.TRANSPARENT);
		
		Tooltip speT = new Tooltip("Speisezimmer");
		Tooltip.install(spe1, speT);
		Tooltip.install(spe2, speT);
		Tooltip.install(spe3, speT);
		
		GridPane.setConstraints(spe1,16,9,3,6);
		GridPane.setConstraints(spe2,19,9,4,7);
		GridPane.setConstraints(spe3,23,8,1,9);
		
		//Musikzimmer
		Rectangle mus1 = new Rectangle(8*27,6*27);
		Rectangle mus2 = new Rectangle(4*27,2*27);
		mus1.setFill(Color.TRANSPARENT);
		mus2.setFill(Color.TRANSPARENT);
		
		Tooltip musT = new Tooltip("Musikzimmer");
		Tooltip.install(mus1, musT);
		Tooltip.install(mus2, musT);
		
		GridPane.setConstraints(mus1,8,17,8,6);
		GridPane.setConstraints(mus2,10,23,4,2);
		
		//Küche
		Rectangle kue1 = new Rectangle(6*27,5*27);
		Rectangle kue2 = new Rectangle(7*27,27);
		Rectangle kue3 = new Rectangle(9*27,27);
		kue1.setFill(Color.TRANSPARENT);
		kue2.setFill(Color.TRANSPARENT);
		kue3.setFill(Color.TRANSPARENT);
		
		Tooltip kueT = new Tooltip("Küche\n" + "Geheimgang zum Arbeitszimmer");
		Tooltip.install(kue1, kueT);
		Tooltip.install(kue2, kueT);
		Tooltip.install(kue3, kueT);
		
		GridPane.setConstraints(kue1,18,18,6,5);
		GridPane.setConstraints(kue2,17,23,7,1);
		GridPane.setConstraints(kue3,15,24,9,1);
		
		this.getChildren().addAll(swim,work1,work2,hall1,hall2,bib1,bib2,bib3,bil1,bil2,win1,
				win2,win3,win4,sal1,sal2,spe1,spe2,spe3,mus1,mus2,kue1,kue2,kue3);
	}
}
