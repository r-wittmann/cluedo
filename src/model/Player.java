package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.EnumSet;

/**
 * Creates a player, with
 * <ul>
 * <li>a Card[] playerCards
 * <li>a Counter counter
 * <li>a position
 * <li>and a SimpleStringProperty name
 * </ul>
 * 
 * @author Roxanna
 */
public class Player {

	protected static int playerCount;

	private SimpleStringProperty name = new SimpleStringProperty();
	private ArrayList<Card> playerCards = new ArrayList<Card>();
	private Counter counter;
	private int position;
	
	// possible playserstates, multiple possible
	public enum Playerstate {
		DO_NOTHING, ROLL_DICE, USE_SECRET_PASSAGE, MOVE, SUSPECT, ACCUSE, DISPROVE, END_TURN
	}


	private EnumSet<Playerstate> playerstates; // contains all states of the player
	
	// Constructor
	/**
	 * Class constructor
	 * 
	 * @param name
	 *            the name of the Player
	 * @param counter
	 *            the Counter of the Player
	 */
	public Player(String name, Counter counter) {
		playerCount++;
		this.name.setValue(name);
		this.counter = counter;
		
		// create empty enumset with playerstates
		this.playerstates = EnumSet.noneOf(Playerstate.class);
		// add initial do_nothing
		this.playerstates.add(Playerstate.DO_NOTHING);
	}

	/**
	 * add a Card to the List of Cards
	 * 
	 * @param card
	 *            the card which should be added
	 */
	public void addCard(Card card) {
		playerCards.add(card);
	}

	/**
	 * checks if hold Cards contain suspected Card
	 * 
	 * @param susp
	 *            is supected Card
	 * @return true/false
	 */
	public boolean checkHandContainsCard(model.PersonCard.Type susp) {
		for (Card c : playerCards)
			if (c instanceof PersonCard) {
				PersonCard card = (PersonCard) c;
				if (card.getCardValue() == susp)
					return true;
			}
		return false;
	}

	public boolean checkHandContainsCard(model.RoomCard.Type susp) {
		for (Card c : playerCards)
			if (c instanceof RoomCard) {
				RoomCard card = (RoomCard) c;
				if (card.getCardValue() == susp)
					return true;
			}
		return false;
	}

	public boolean checkHandContainsCard(model.WeaponCard.Type susp) {
		for (Card c : playerCards)
			if (c instanceof WeaponCard) {
				WeaponCard card = (WeaponCard) c;
				if (card.getCardValue() == susp)
					return true;
			}
		return false;
	}
	
	// getter methods
	
	/**
	 * Returns all states of player
	 * @return EnumSet of actual playerstate of player
	 * @author Ludwig
	 */
	public EnumSet<Playerstate> getPlayerstates() {
		return this.playerstates;
	}
	
	/**
	 * @return playerCount
	 */
	public static int getPlayerCount() {
		return playerCount;
	}
	
	/**
	 * @return value of name
	 */
	public String getName() {
		return this.name.getValue();
	}

	/**
	 * @return name
	 */
	public SimpleStringProperty getNameProperty() {
		return name;
	}
	
	public int getPosition() {
		return position;
	}


	/**
	 * @return playerCards
	 */
	public ArrayList<Card> getPlayerCards() {
		return playerCards;
	}

	/**
	 * @return counter
	 */
	public Counter getCounter() {
		return counter;
	}

	// setter methods
	
	/**
	 * Set actual single state of the player
	 * @param playerstate state of the player
	 * @author Ludwig
	 */
	public void setPlayerstate(Playerstate playerstate) {
		this.playerstates.clear();
		this.playerstates.add(playerstate);
	}
	
	/**
	 * Set actual multiple state of the player - can be more than one single state
	 * @param playerstates set of states of the player
	 * @author Ludwig
	 */
	public void setPlayerstates(EnumSet<Playerstate> playerstates) {
		this.playerstates.clear();
		this.playerstates.addAll(playerstates);
	}
	
	/**
	 * Removes a single state of the player
	 * @param playerstate state to remove from player
	 * @author Ludwig
	 */
	public void removePlayerstate(Playerstate playerstate) {
		this.playerstates.remove(playerstate);
	}
	
	/**
	 * Checks if player has a state
	 * @param playerstate state which should be tested
	 * @return True if player has requested state
	 * @author Ludwig
	 */
	public boolean hasPlayerstate(Playerstate playerstate) {
		return this.playerstates.contains(playerstate);
	}
	
	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name.setValue(name);
	}

	/**
	 * @param counter
	 */
	public void setCounter(Counter counter) {
		this.counter = counter;
	}

	public void setPosition(int position) {
		this.position = position;
	}
}