package model;


/**
 * Extends from the abstract class Card.
 * <br>Creates a room card with the enum Card.
 * <br>
 * <br>Possible values: Hall, Lounge, Diningroom, Kitchen, 
 * <br>Ballroom, Conservatory, Billiard, Library, Study, Pool
 * @author Roxanna
 */
public class RoomCard extends Card{

	public enum Type{ 
		HALL, LOUNGE, DININGROOM, KITCHEN, BALLROOM, 
		CONSERVATORY, BILLIARD, LIBRARY, STUDY, POOL
	}

	private Type cardValue;
	
	//getCardValue nutzen um Karte zu überprüfen (also zB ist meine Karte = "Professor Bloom")
	//gibt den tatsächlichen Wert des Enums zurück (zB PURPLE / KITCHEN / SPANNER etc.)
	public Type getCardValue()
	{
		return cardValue;
	}

	/**
	 * Create a RoomCard. 
	 * <br>Will set info as the name of the Room 
	 * <br>that belongs to the value of the RoomCard
	 * @param RoomCard 
	 */
	public RoomCard(Type type){
		cardValue = type;

		switch(type){
		case HALL:
			super.setInfo("Eingangshalle");
			super.setName("hall");
			break;
		case LOUNGE:
			super.setInfo("Salon");
			super.setName("lounge");
			break;
		case DININGROOM:
			super.setInfo("Speisezimmer");
			super.setName("diningroom");
			break;
		case KITCHEN:
			super.setInfo("Küche");
			super.setName("kitchen");
			break;
		case BALLROOM:
			super.setInfo("Musikzimmer");
			super.setName("ballroom");
			break;
		case CONSERVATORY:
			super.setInfo("Wintergarten");
			super.setName("conservatory");
			break;
		case BILLIARD:
			super.setInfo("Billardzimmer");
			super.setName("billiard");
			break;
		case LIBRARY:
			super.setInfo("Bibliothek");
			super.setName("library");
			break;
		case STUDY:
			super.setInfo("Arbeitszimmer");
			super.setName("study");
			break;
		case POOL:
			super.setInfo("Schwimmbad");
			break;
		}
	}
}