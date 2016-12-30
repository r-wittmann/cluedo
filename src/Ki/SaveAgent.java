package Ki;
/**
 * Saves results from game and so delete cards from possible cards
 * @author Sarah
 */

import model.Card;
import model.PersonCard;
import model.RoomCard;
import model.WeaponCard;

import java.util.ArrayList;

public class SaveAgent {
	Ki ki;
	
	public SaveAgent(Ki ki){
		this.ki = ki;
	}
	
	//Funktion die Handkarten aus model zieht und Karten aus den possibleListen löscht
	/**
	 * deletes own Cards in possibleMurders
	 * @param gameID gameID to process
	 * @author Sarah
	 */
	public void deleteHandCards(int gameID) {
//		ArrayList<Card> ownCards = getOwnCards();
//		while(ki.getPossibleRooms().size() != 9){}
//		while(ki.getPossibleMurders().size() != 6){}
//		while(ki.getPossibleWeapons().size() != 6){}
		ArrayList<PersonCard> possMurders = ki.getPossibleMurders(gameID);
		ArrayList<WeaponCard> possWeapons = ki.getPossibleWeapons(gameID);
		ArrayList<RoomCard> possRooms = ki.getPossibleRooms(gameID);
		ArrayList<Card> ownCards = ki.getOwnCards(gameID);
		
		for(Card handCard : ownCards){
			loop:for(PersonCard personCard : possMurders)
			{
				if(handCard.getName().equals(personCard.getName()))
				{
//					System.out.println(personCard.getName());
					possMurders.remove((PersonCard) personCard);
//					ki.setPossibleMurders(possMurders);
					break loop;
				}
			}
			loop:for(RoomCard roomCard : possRooms)
			{
				if(handCard.getName().equals(roomCard.getName()))
				{
//					System.out.println(roomCard.getName());
					possRooms.remove((RoomCard) roomCard);
//					ki.setPossibleRooms(possRooms);
					break loop;
				}
			}
			loop:for(WeaponCard weaponCard : possWeapons)
			{
				if(handCard.getName().equals(weaponCard.getName()))
				{
//					System.out.println(weaponCard.getName());
					possWeapons.remove((WeaponCard) weaponCard);
//					ki.setPossibleWeapons(possWeapons);
					break loop;
				}
			}
		}
	}
	
	
	/**
	 * deletes new known Card from possible
	 * @param card Card that Ki gets to know (through disproved suspicion)
	 * @author Sarah
	 */
	public void deleteShownCard(int gameID, String card){
		ArrayList<PersonCard> possMurders = ki.getPossibleMurders(gameID);
		ArrayList<WeaponCard> possWeapons = ki.getPossibleWeapons(gameID);
		ArrayList<RoomCard> possRooms = ki.getPossibleRooms(gameID);
		
		for(PersonCard c : possMurders)
		{
			if(c.getName().equals(card))
			{
				possMurders.remove(c);
//				System.out.println("Person " + c.getName() +" gelöscht");
//				ki.setPossibleMurders(possMurders);
				break;
			}
		}
		for(RoomCard c : possRooms)
		{
			if(c.getName().equals(card))
			{
				possRooms.remove(c);
//				System.out.println("Raum " + c.getName() +" gelöscht");
//				ki.setPossibleRooms(possRooms);
				break;
			}
		}
		for(WeaponCard c : possWeapons)
		{
			if(c.getName().equals(card))
			{
				possWeapons.remove(c);
//				System.out.println("Waffe " + c.getName() +" gelöscht");
//				ki.setPossibleWeapons(possWeapons);
				break;
			}
		}
		
	}
	
	/**
	 * Saves own cards to KINotes
	 * @param gameID   gameID of game
	 * @param ownCards own cards in this game
	 * @author Sarah, Ludwig
	 */
	public void setOwnCards(int gameID, ArrayList<Card> ownCards) {
		ki.setOwnCards(gameID, ownCards);
	}
}
