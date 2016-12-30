package presenter;

import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import model.Counter.Color;
import model.*;
import network.client.ClientSent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.GlobalView;

/**
 * Saves selected PersonCard, WeaponCard and Room the Counter is inside for a suspicion
 * Checks if a player has some of the suspected Cards
 * Checks if the Card a Player selects for disproving an suspicion is valid
 * @author Sarah
 */

public class SuspicionPresenter {

	private static final Logger log = LogManager
			.getLogger(SuspicionPresenter.class);

	private Gamefield model;
	private GlobalView view;
	private ClientSent clientSent;

	// disproveCard ist die Karte die ein Spieler zum Widerlegen einer
	// Verdächtigung wählt

	public SuspicionPresenter(Gamefield model, GlobalView view,ClientSent clientSent) {
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	/**
	 * close DialogView
	 * @param e
	 * @author Maurice
	 */
	public void handleClose(MouseEvent e){
		this.view.gameView.dialogView.setVisible(false);
	}


	// TODO network notify following function-->
	/**
	 * Show suspicion after received from server
	 * 
	 * @author Ludwig
	 */
	// public void showSuspicion() {
	// // todo show suspion, is it here correct??
	//
	// // move counter and weapons to correct position
	// // counter
	// //ruft moveSuspPerson mit Counterposition auf
	// moveSuspPerson(model.getPlayer(/*getNickname of Client (string)
	// */).getCounter().getPositionX(), // todo
	// model.getPlayer(/*getNickname of Client (string)
	// */).getCounter().getPositionY());
	// // weapon
	// //ruft moveSuspWeapon mit Counterposition auf
	// moveSuspWeapon(model.getPlayer(/*getNickname of Client (string)
	// */).getCounter().getPositionX(), // todo
	// model.getPlayer(/*getNickname of Client (string)
	// */).getCounter().getPositionY());
	// }

	

	/**
	 * speichert bei Klick auf "Verdächtigung erheben" die verd. Figur und Waffe <br>
	 * und ruft suspRoom() auf, die den entsprechenden Raum speichert
	 * 
	 * @param event
	 *            , Mousevent bei Klick auf Submit Button in Verdächtigungsview
	 * @author Maurice
	 */
	public void makeSuspicion(MouseEvent event) {

		// überprüft ob etwas ausgewählt
		ComboBox<String> murder = view.gameView.verdaechtigungsView.murder;
		ComboBox<String> weapon = view.gameView.verdaechtigungsView.weapon;

		if (murder.getValue() != null && weapon.getValue() != null) {
			// speichert das Enum der gewählten Karte in Model Suspicion ab.
			// Achtung: Reihenfolge
			// des Enums und der Auflistung
			// in der Combobox muss gleich sein!
			model.suspicion.setSuspCounter(PersonCard.Type.values()[murder
					.getSelectionModel().getSelectedIndex()]);
			model.suspicion.setSuspWeapon(WeaponCard.Type.values()[weapon
					.getSelectionModel().getSelectedIndex()]);

			// ruft suspRoom mit Counterposition auf
			suspRoom(model.getPlayer().getCounter().getPositionX(), model
					.getPlayer().getCounter().getPositionY());

			view.gameView.verdaechtigungsView.setVisible(false);
			
			log.info("Verdächtigung gewählt: "
					+ model.suspicion.getSuspCounter().toString() + " "
					+ model.suspicion.getSuspWeapon().toString() + " "
					+ model.suspicion.getSuspRoom().toString());
			
			// sent to server
			clientSent.suspect(model.getGameInfo().getGameID(),
					model.getSuspicion());
		} else {
//				DialogPresenter dia = new DialogPresenter();
//				dia.handleKeineGueltigeVerdaechtigung();
			this.view.gameView.dialogView.setDialog("Du musst einen Mörder und eine Waffe auswählen!");
		}
		
			
	}

	/**
	 * checks current room of the counter and adds it to the suspicion
	 * 
	 * @param x
	 *            current counterposition x
	 * @param y
	 *            current counterposition y
	 */
	public void suspRoom(int x, int y) {

		// Study Room
				if (x == 6 && y == 3) {
					model.suspicion.setSuspRoom(RoomCard.Type.STUDY);
				} else
				// Hall
				if (x == 9 && y == 4 || x == 11 && y == 6 || x == 12 && y == 6) {
					model.suspicion.setSuspRoom(RoomCard.Type.HALL);
				} else
				// Salon
				if (x == 17 && y == 5) {
					model.suspicion.setSuspRoom(RoomCard.Type.LOUNGE);
				}
				// Libary
				if (x == 6 && y == 8 || x == 3 && y == 10) {
					model.suspicion.setSuspRoom(RoomCard.Type.LIBRARY);
				} else
				// Diner Room
				if (x == 17 && y == 9 || x == 16 && y == 12) {
					model.suspicion.setSuspRoom(RoomCard.Type.DININGROOM);
				} else
				// Chess Room
				if (x == 1 && y == 12 || x == 5 && y == 15) {
					model.suspicion.setSuspRoom(RoomCard.Type.BILLIARD);
				} else
				// Winter Garden
				if (x == 4 && y == 19) {
					model.suspicion.setSuspRoom(RoomCard.Type.CONSERVATORY);
				} else
				// Music Room
				if (x == 8 && y == 19 || x == 9 && y == 17 || x == 14 && y == 17
						|| x == 15 && y == 19) {
					model.suspicion.setSuspRoom(RoomCard.Type.BALLROOM);
				} else
				// Kitchen
				if (x == 18 && y == 19) {
					model.suspicion.setSuspRoom(RoomCard.Type.KITCHEN);
				}
	}

	/**
	 * Sets the position of the Counter of the suspected Person to the suspected room.
	 * @param x - the x position the suspected Counter shall be moved to
	 * @param y - the y position the suspected Counter shall be moved to
	 * @author Roxanna
	 */
	public void moveSuspPerson(int x, int y) {
		if (this.model.suspicion.getSuspCounter() == PersonCard.Type.RED) {
			this.model.setCounter(Color.RED, x, y);
		} else if (this.model.suspicion.getSuspCounter() == PersonCard.Type.BLUE) {
			this.model.setCounter(Color.BLUE, x, y);
		} else if (this.model.suspicion.getSuspCounter() == PersonCard.Type.GREEN) {
			this.model.setCounter(Color.GREEN, x, y);
		} else if (this.model.suspicion.getSuspCounter() == PersonCard.Type.PURPLE) {
			this.model.setCounter(Color.PURPLE, x, y);
		} else if (this.model.suspicion.getSuspCounter() == PersonCard.Type.WHITE) {
			this.model.setCounter(Color.WHITE, x, y);
		} else if (this.model.suspicion.getSuspCounter() == PersonCard.Type.YELLOW) {
			this.model.setCounter(Color.YELLOW, x, y);
		}
		this.view.gameView.counterView.initStyle();
	}

	/**
	 * Sets the position of the suspected Weapon top the suspected room
	 * @param x - the x-position the suspected weapon should be moved to
	 * @param y - the y-position the suspected weapon should be moved to
	 * @author Roxanna
	 */
	public void moveSuspWeapon(int x, int y) {
		Weapon.Type weapon = this.model.suspicion
				.weaponCardToWeapon(this.model.suspicion.getSuspWeapon());
		if (weapon == Weapon.Type.CANDLESTICK) {
			this.model.setWeapon(Weapon.Type.CANDLESTICK, x, y);
		} else if (weapon == Weapon.Type.DAGGER) {
			this.model.setWeapon(Weapon.Type.DAGGER, x, y);
		} else if (weapon == Weapon.Type.PIPE) {
			this.model.setWeapon(Weapon.Type.PIPE, x, y);
		} else if (weapon == Weapon.Type.REVOLVER) {
			this.model.setWeapon(Weapon.Type.REVOLVER, x, y);
		} else if (weapon == Weapon.Type.ROPE) {
			this.model.setWeapon(Weapon.Type.ROPE, x, y);
		} else if (weapon == Weapon.Type.SPANNER) {
			this.model.setWeapon(Weapon.Type.SPANNER, x, y);
		}
		this.view.gameView.weaponView.initWeaponMove();
	}

	
	//In DisproveSuspicionPresenter verschoben
//	// muss für jeden Player aufgerufen werden
//	// gibt Liste mit Enums, die verdächtigt werden und die Spieler auf der Hand
//	// hat, zurück (validCards)
//	// noch anpassen auf jeweiligen Player
//	/**
//	 * checks if and which cards a player has matching the suspicion
//	 * 
//	 * @return to suspicion matching cards of a player
//	 */
//	public List<Card> checkCards() {
//
//		//
//		if (model.getPlayer().checkHandContainsCard(
//				model.suspicion.getSuspCounter()))
//			model.suspicion.addToValidCards(new PersonCard(model.suspicion
//					.getSuspCounter()));
//
//		if (model.getPlayer().checkHandContainsCard(
//				model.suspicion.getSuspWeapon()))
//			model.suspicion.addToValidCards(new WeaponCard(model.suspicion
//					.getSuspWeapon()));
//
//		if (model.getPlayer().checkHandContainsCard(
//				model.suspicion.getSuspRoom()))
//			model.suspicion.addToValidCards(new RoomCard(model.suspicion
//					.getSuspRoom()));
//
//		return model.suspicion.getValidCards();
//
//	}

	// TODO Warnmeldung wenn falsche Karte zum Widerlegen gewählt
	
}
