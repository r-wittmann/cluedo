package view;

import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Gamefield;


/**
 * 
 * class contains gamefields
 * @author Maurice/Roxanna
 *
 */
public class FieldView extends GridPane {
	private Gamefield model;
	public Rectangle[][] fields = new Rectangle[24][25];

	public FieldView(Gamefield model) {
		this.model = model;
		setField();
	}
	
	/**
	 * generates a Rectangle for every position in the model.board[][]
	 * <br>It contains:
	 * <ul>
	 * <li>The six Rectangle that are start positions of the Counter
	 * <li>visible Rectangles for the fields around the rooms
	 * <li>transparent Rectangles for the fields in the rooms
	 * </ul>
	 * @author Roxanna
	 */
	//wichtig für die spiellogik, dass mit x und y vom model auf die felder zugegriffen werden kann
	public void setField(){
		for(int x = 0; x < 24; x++){
			for(int y = 0; y < 25; y++){
				//Startpositionen für Counter
				if(x == 0 && y == 5){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:PURPLE;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if(x == 0 && y == 18){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:BLUE;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if(x == 16 && y == 0){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:RED;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if(x == 23 && y == 7){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:YELLOW;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if(x == 9 && y == 24){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:GREEN;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if(x == 14 && y == 24){
					Rectangle rec = new Rectangle(26,26);
					rec.setStyle("-fx-fill:WHITE;"
							+ "-fx-stroke:DARKRED");
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				//Raumfelder, werden nicht angezeigt, aber zur Liste hinzugefügt.
				} else if((x==3&&y==1)||(x==12&&y==3)||(x==20&&y==3)||(x==3&&y==8)||
						(x==11&&y==11)||(x==20&&y==12)||(x==2&&y==14)||(x==2&&y==22)||
						(x==11&&y==20)||(x==20&&y==21)){
					Rectangle rec = new Rectangle(26,26, Color.TRANSPARENT);
					rec.setVisible(false);
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				//Türfelder, werden nicht angezeigt, aber zur Liste hinzugefügt.
				}  else if((x==6&&y==3)||(x==9&&y==4)||(x==11&&y==6)||(x==12&&y==6)||
						(x==17&&y==5)||(x==6&&y==8)||(x==11&&y==8)||(x==13&&y==9)||(x==17&&y==9)||
						(x==3&&y==10)||(x==1&&y==12)||(x==9&&y==12)||(x==16&&y==12)||(x==11&&y==14)||
						(x==5&&y==15)||(x==9&&y==17)||(x==14&&y==17)||(x==19&&y==18)||(x==4&&y==19)||
						(x==8&&y==19)||(x==15&&y==19)){
					Rectangle rec = new Rectangle(26,26, Color.TRANSPARENT);
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				//Alle begehbaren Felder in model.board ohne den Raumfeldern
				} else if((model.board.getBoard()[x][y] != -1)){
					Rectangle rec = new Rectangle(26,26,Color.TRANSPARENT);
//					rec.setStroke(Color.DARKRED);
					GridPane.setConstraints(rec, x, y);
					
					ImageView bodeniv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Boden.png")));
					bodeniv.setFitHeight(27);
					bodeniv.setFitWidth(27);
					bodeniv.setSmooth(true);
					
					GridPane.setConstraints(bodeniv, x, y);
					this.getChildren().addAll(bodeniv,rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				} else if((model.board.getBoard()[x][y] == -1)){
					Rectangle rec = new Rectangle(26,26, Color.TRANSPARENT);
					GridPane.setConstraints(rec, x, y);
					this.getChildren().add(rec);
					//fügt aktuelles Rectangle zum Array hinzu
					fields[x][y] = rec;
				}
			}
		}
	}
	
	/**
	 * Sets an InnerShadow to the given Rectangle
	 * @param rec the Rectangle that the Shadow shall be put on
	 * @author Roxanna
	 */
	public void setShadow(Rectangle rec){
		InnerShadow glow = new InnerShadow();
		glow.setOffsetX(0f);
		glow.setOffsetY(0f);
		glow.setColor(Color.WHITE);
		rec.setFill(Color.DARKRED);
		rec.setStroke(Color.DARKRED);
		rec.setEffect(glow);
	}
}