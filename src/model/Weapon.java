package model;

/**
 * This class contains the:
 * Weapon name,
 * coordinates of the weapon,
 * type of the weapon
 * @author Jonas/Sarah
 */
public class Weapon {
	
	private String name;
	private int posX;
	private int posY;
	private Type type;
	
	public enum Type{
		DAGGER, CANDLESTICK, REVOLVER, ROPE, PIPE, SPANNER
	}
	
	public Type getCardType()
	{
		return type;
	}
	
	public Weapon (Type type){
		this.type = type;
		this.posX = 11;
		this.posY = 11;
		
		switch(type){
		case DAGGER:
			this.name = "Dolch";
			break;
		case CANDLESTICK:
			this.name = "Leuchter";
			break;
		case REVOLVER:
			this.name = "Revolver";
			break;
		case ROPE:
			this.name = "Seil";
			break;
		case PIPE:
			this.name = "Heizungsrohr";
			break;
		case SPANNER:
			this.name = "Rohrzange";
			break;
		}
	}

	
	//getter
	/**
	 * 
	 * @return the x coordinate
	 */
	public int getPosX() {
		return posX;
	}
	
	/**
	 * 
	 * @return the y coordinate
	 */
	public int getPosY() {
		return posY;
	}
	
	/**
	 * @return A String as the name of the weapon
	 */
	public String getName(){
		return name;
	}

	//setter
	/**
	 *  moves the Weapon to the field, specified by x and y
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public void setPos(int x, int y){
		this.posX = x;
		this.posY = y;
	}
}