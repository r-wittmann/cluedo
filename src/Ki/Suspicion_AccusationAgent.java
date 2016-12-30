package Ki;

import model.PersonCard;
import model.WeaponCard;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;

/**
 * Calculates a usable suspicion for KI
 * @author Sarah
 */
public class Suspicion_AccusationAgent {
	private static final Logger log = LogManager.getLogger(Suspicion_AccusationAgent.class);
	
	private final Ki ki;
	
	/**
	 * Constructor
	 * @param ki reference to KI
	 */
	public Suspicion_AccusationAgent(Ki ki){
		this.ki = ki;
	}
	
	/**
	 * Person to suspect
	 * @return Enum.Type of Person to suspect
	 */
	public model.PersonCard.Type getSuspectPerson(int gameID){
		Random r = new Random();
		log.debug("possible murders: " + ki.getPossibleMurders(gameID).size());
		int temp = r.nextInt(ki.getPossibleMurders(gameID).size());
		PersonCard pcard = ki.getPossibleMurders(gameID).get(temp);
		return pcard.getCardValue();
	}
	
	/**
	 * Weapon to suspect
	 * @return Enum.Type of Weapon to suspect
	 */
	public model.WeaponCard.Type getSuspectWeapon(int gameID){
		Random r = new Random();
		log.debug("possible weapons: " + ki.getPossibleMurders(gameID).size());
		int temp = r.nextInt(ki.getPossibleWeapons(gameID).size());
		WeaponCard wcard = ki.getPossibleWeapons(gameID).get(temp);
		return wcard.getCardValue();
	}
	
	/**
	 * selects only remaining room from possibleRooms
	 * @return Type of last possible Room
	 */
//	public model.RoomCard.Type accuseRoom(){
//		 return ki.getPossibleRooms().get(0).getCardValue();		
//	}
	
}
