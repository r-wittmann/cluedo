package model;



import javafx.beans.property.SimpleStringProperty;

/**
 * Provides SimpleStringProperty info 
 * <br>and getter and setter methods for info 
 * <br>for subclasses PersonCard, WeaponCard and RoomCard
 * @author Roxanna
 */
public abstract class Card {

	private SimpleStringProperty info = new SimpleStringProperty();
	private String name;

	//Constructor
	public Card(){}

	//getter methods
	/**
	 * @return info from the card
	 */
	public String getInfo(){
		return info.getValue();
	}
	
	/**
	 * @return name of Card
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * @return the Enum Type of the Card as String
	 * @author Maurice,Roxanna
	 */
	public String getCardType(){
		Card c = this;
		String type = "";
		if(c instanceof WeaponCard){
			type = ((WeaponCard) c).getCardValue().toString().toLowerCase();
		} else if(c instanceof RoomCard){
			type = ((RoomCard) c).getCardValue().toString().toLowerCase();
		} else if(c instanceof PersonCard){
			type = ((PersonCard) c).getCardValue().toString().toLowerCase();
		}
		return type;
	}
	
	/**
	 * @return info
	 */
	public SimpleStringProperty getInfoProperty(){
		return info;
	}

	//setter methods
	/**
	 * @param String info
	 */
	public void setInfo(String info){
		this.info.setValue(info);
	}
	
	/**
	 * @param name
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * überprüft um welche Art von Card es sich handelt und vergleicht diese dann mit 
	 * equals gibt zurück, ob übergebene Karte den gleichen Wert hat wie aktuelle Karte
	 * @param card
	 * @return
	 * @author ?
	 */
	public boolean equals(Card card)
	{
		if(this.getClass() == card.getClass())
		{
			if(this instanceof PersonCard)
			{
				PersonCard thisCard = (PersonCard)this;
				PersonCard otherCard = (PersonCard)card;
				return thisCard.getCardValue() == otherCard.getCardValue();
			}
			else if(this instanceof RoomCard)
			{
				RoomCard thisCard = (RoomCard)this;
				RoomCard otherCard = (RoomCard)card;
				return thisCard.getCardValue() == otherCard.getCardValue();
			}
			else if(this instanceof WeaponCard)
			{
				WeaponCard thisCard = (WeaponCard)this;
				WeaponCard otherCard = (WeaponCard)card;
				return thisCard.getCardValue() == otherCard.getCardValue();
			}
		}
		
		return false; 
	}
}
