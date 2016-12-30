package model;

import javafx.beans.property.SimpleIntegerProperty;

public class Counter {

	private SimpleIntegerProperty positionX = new SimpleIntegerProperty();
	private SimpleIntegerProperty positionY = new SimpleIntegerProperty();
	private Color color;
	private String name;

	public enum Color{
		RED, YELLOW, WHITE, GREEN, BLUE, PURPLE
	}

	//Constructor
	/**
	 * Creates a Counter and sets positionX, positionY and the name
	 * @param positionX
	 * @param positionY
	 * @param name
	 */
	public Counter(Color color, int positionX, int positionY, String name){
		this.color = color;
		// todo default position should be known by color or better by board, so if there is a different board the position is correctly set
		this.positionX.setValue(positionX);
		this.positionY.setValue(positionY);
		this.name = name;
	}

	public boolean isColor(Color color){
		return this.color == color;
	}

	//setter methods
	/**
	 * @param positionX
	 */
	public void setPositionX(int positionX){
		this.positionX.setValue(positionX);
	}

	/**
	 * @param positionY
	 */
	public void setPositionY(int positionY){
		this.positionY.setValue(positionY);
	}

	/**
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y){
		this.positionX.setValue(x);
		this.positionY.setValue(y);
	}

	//getter methods

	/**
	 * @return X position of the counter
	 */
	public int getPositionX(){
		return positionX.getValue();
	}

	/**
	 * @return Y position of the counter
	 */
	public int getPositionY(){
		return positionY.getValue();
	}

	/**
	 * @return GameName of counter, e.g. "Gloria"
	 */
	public String getName(){
		return name;
	}

	/**
	 * @return enum of color
	 */
	public Color getColor(){
		return color;
	}


	
	public SimpleIntegerProperty getPositionXProperty(){
		return positionX;
	}
	
	public SimpleIntegerProperty getPositionYProperty(){
		return positionY;
	}
}