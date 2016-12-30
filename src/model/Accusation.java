package model;

public class Accusation {
	private PersonCard.Type accusedCounter;
	private WeaponCard.Type accusedWeapon;
	private RoomCard.Type accusedRoom;
	
	
	/**checks if accusation is correct
	 * @author Sarah
	 * @return true / false
	 */
	//Überprüfen ob Anklage richtig
	//überprüft ob Anklage korrekt
	public boolean checkAccusation(PersonCard.Type murderer, RoomCard.Type crimeScene, WeaponCard.Type weapon ){		
		
		return(accusedCounter == murderer  && accusedRoom == crimeScene && accusedWeapon == weapon);
			//this.view.gameView.winnerView.setVisible(true);
	}
	
	public PersonCard.Type getAccusedCounter() {
		return accusedCounter;
	}

	public void setAccusedCounter(PersonCard.Type accusedCounter) {
		this.accusedCounter = accusedCounter;
	}

	public WeaponCard.Type getAccusedWeapon() {
		return accusedWeapon;
	}

	public void setAccusedWeapon(WeaponCard.Type accusedWeapon) {
		this.accusedWeapon = accusedWeapon;
	}

	public RoomCard.Type getAccusedRoom() {
		return accusedRoom;
	}

	public void setAccusedRoom(RoomCard.Type accusedRoom) {
		this.accusedRoom = accusedRoom;
	}

}
