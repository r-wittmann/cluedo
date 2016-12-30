package model;

/**
 * Extends from the abstract class Card.
 * <br>Creates a weapon card with the enum Card.
 * <br>
 * <br>Possible values: Dagger, Candlestick, Revolver, Rope, Pipe, Spanner;
 * @author Roxanna
 */
public class WeaponCard extends Card{

	//Reihenfolge muss gleich sein wie zugehörige Strings in der Combobox der Verdächtigungsview!
	public enum Type{
		DAGGER, CANDLESTICK, REVOLVER, ROPE, PIPE, SPANNER
	}

	private Type cardValue;
	
	//getCardValue nutzen um Karte zu überprüfen (also zB ist meine Karte = "Professor Bloom")
	//gibt den tatsächlichen Wert des Enums zurück (zB PURPLE / KITCHEN / SPANNER etc.)
	public Type getCardValue()
	{
		return cardValue;
	}

	/**
	 * Create a WeaponCard. 
	 * <br>Will set info as the name of the Weapon 
	 * <br>that belongs to the value of the WeaponCard
	 * @param WeaponCard
	 */
	public WeaponCard(Type card){
		cardValue = card;
		
		switch(card){
		case DAGGER:
			super.setInfo("Dolch");
			super.setName("dagger");
			break;
		case CANDLESTICK:
			super.setInfo("Leuchter");
			super.setName("candlestick");
			break;
		case REVOLVER:
			super.setInfo("Revolver");
			super.setName("revolver");
			break;
		case ROPE:
			super.setInfo("Seil");
			super.setName("rope");
			break;
		case PIPE:
			super.setInfo("Heizungsrohr");
			super.setName("pipe");
			break;
		case SPANNER:
			super.setInfo("Rohrzange");
			super.setName("spanner");
			break;
		}
	}
}
