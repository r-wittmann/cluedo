package model;

/**
 * Extends from the abstract class Card.
 * <br>Creates a person card with the enum Card.
 * <br>
 * <br>Possible values: Red, Yellow, White, Green, Blue, Purple
 * @author Roxanna
 */
public class PersonCard extends Card{

	public enum Type{
		RED, YELLOW, WHITE, GREEN, BLUE, PURPLE		
	}
	
	private Type cardValue;
	
	//getCardValue nutzen um Karte zu überprüfen (also zB ist meine Karte = "Professor Bloom")
	//gibt den tatsächlichen Wert des Enums zurück (zB PURPLE / KITCHEN / SPANNER etc.)
	public Type getCardValue()
	{
		return cardValue;
	}

	/**
	 * Create a PersonCard. 
	 * <br>Will set info as the name of the Person 
	 * <br>that belongs to the value of the PersonCard
	 * @param card
	 */
	public PersonCard(Type card){
		cardValue = card;

		switch(card){
		case RED:
			super.setInfo("Fräulein Gloria");
			super.setName("red");
			break;
		case YELLOW:
			super.setInfo("Oberst von Gatow");
			super.setName("yellow");
			break;
		case WHITE:
			super.setInfo("Frau Weiß");
			super.setName("white");
			break;
		case GREEN:
			super.setInfo("Reverend Grün");
			super.setName("green");
			break;
		case BLUE:
			super.setInfo("Baronin von Porz");
			super.setName("blue");
			break;
		case PURPLE:
			super.setInfo("Professor Bloom");
			super.setName("purple");
			break;
		}
	}	
}
