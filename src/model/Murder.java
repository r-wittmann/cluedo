package model;

/**
 * Holds the data of the murder:
 * <ul>
 * <li>the RoomCard,
 * <li>the PersonCard
 * <li>and the WeaponCard
 * </ul>
 * @author Roxanna, Sarah
 */
public class Murder {
	
	private RoomCard crimeScene;
	private PersonCard murderer;
	private WeaponCard weapon;
	
	//Constructor
	/**
	 * Sets the data of the murder with the assigned values.
	 * @param RoomCard.Card  scene
	 * @param PersonCard.Card  murderer
	 * @param WeaponCard.Card  weapon
	 * @author Roxanna
	 */
	public Murder(RoomCard scene, PersonCard murderer, WeaponCard weapon){
		this.crimeScene = scene;
		this.murderer = murderer;
		this.weapon = weapon;
	}
	
	public Murder() {
	}

	//getter methods
	/**
	 * @return Card crimeScene
	 */
	public RoomCard getScene(){
		return crimeScene;
	}
	
	/**
	 * @return Card murderer
	 */
	public PersonCard getMurderer(){
		return murderer;
	}
	
	/**
	 * @return Card weapon
	 */
	public WeaponCard getWeapon(){
		return weapon;
	}
	
	//setter methods
	/**
	 * @param String scene
	 */
	public void setScene(String scene){
		this.crimeScene.setInfo(scene);
	}
	
	/**
	 * @param String murderer
	 */
	public void setMurderer(String murderer){
		this.murderer.setInfo(murderer);
	}
	
	/**
	 * @param String weapon
	 */
	public void setWeapon(String weapon){
		this.weapon.setInfo(weapon);
	}
	
}
