package Ki;

import model.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * This class can decide wich card is to show to a other Client.
 * @author Jonas
 *
 */
public class DisproveAgent {
	private static final Logger log = LogManager.getLogger(DisproveAgent.class);
	
	private String weapon;
	private String room;
	private String person;
	private Ki ki;
	
	/**
	 * Constructor
	 * @author Jonas, Ludwig
	 */
	public DisproveAgent(Ki ki) {
		this.ki = ki;
	}
	
	/**
	 * This Function decide wich card it should show a other client
	 * @author Jonas, Ludwig
	 */
	public Card chooseDisprove(int gameID) {
		log.debug("to disprove: " + person + "" + weapon + ", " + room);
		ArrayList<Card> cards = this.ki.getOwnCards(gameID);
		for (Card c : cards) {
			if (c.getName().equals(weapon) ) {
				log.info(weapon);
				return c;
				// sende weapon an Server
			} else if (c.getName().equals(room)) {
				log.info(room);
				return c;
				// sende room an Server
			} else if (c.getName().equals(person)) {
				log.info(person);
				return c;
			}

		}
		log.info("No Disprove");
		return null;
		
	}
	

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public void setPerson(String person) {
		this.person = person;
	}
}
