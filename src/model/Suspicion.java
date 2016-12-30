package model;

import java.util.ArrayList;


public class Suspicion {
	
	private PersonCard.Type suspCounter;
	private WeaponCard.Type suspWeapon;
	private RoomCard.Type suspRoom;
	private Card disproveCard;
	private ArrayList<Card> validCards = new ArrayList<Card>();
	
	public ArrayList<Card> getValidCards() {
		return validCards;
	}
	public void addToValidCards(Card card){
		validCards.add(card);
	}
	
	public PersonCard.Type getSuspCounter() {
		return suspCounter;
	}
	public void setSuspCounter(PersonCard.Type suspCounter) {
		this.suspCounter = suspCounter;
	}
	
	public WeaponCard.Type getSuspWeapon() {
		return suspWeapon;
	}
	public void setSuspWeapon(WeaponCard.Type suspWeapon) {
		this.suspWeapon = suspWeapon;
	}
	
	public RoomCard.Type getSuspRoom() {
		return suspRoom;
	}
	public void setSuspRoom(RoomCard.Type suspRoom) {
		this.suspRoom = suspRoom;
	}
	
	public Card getDisproveCard(){
		return disproveCard;
	}
	public void setDisproveCard(Card card){
		this.disproveCard = card;
	}
	
	/**
	 * @param weaponCard from which the Weapon.Type is wanted
	 * @return the equivalent Weapon.Type of the WeaponCard.Type
	 * @author Roxanna
	 */
	public Weapon.Type weaponCardToWeapon(WeaponCard.Type weaponCard){
		Weapon.Type weapon = null;
		switch(weaponCard){
		case DAGGER:
			weapon = Weapon.Type.DAGGER;
		case CANDLESTICK:
			weapon = Weapon.Type.CANDLESTICK;
			break;
		case PIPE:
			weapon = Weapon.Type.PIPE;
			break;
		case REVOLVER:
			weapon = Weapon.Type.REVOLVER;
			break;
		case ROPE:
			weapon = Weapon.Type.ROPE;
			break;
		case SPANNER:
			weapon = Weapon.Type.SPANNER;
			break;
		}
		return weapon;
	}

	//TODO in Model auslagern
	// Methode die überprüft ob vom Player zum Widerlegen gewählte Karte
	// gültig ist
	// (also ob sie eine der verdächtigten Karte ist)
	/**checks if selected card to disprove a suspicion
	 * is valid (one of the suspected cards)
	 * 
	 * @return true /false
	 */

	public boolean checkDisproveCard(){
		//if disproveCard element of validCards

		for(Card el : validCards ){
			if(el.equals(disproveCard)){
				return true;
			}
		}
		return false;

	}
			
			
}
