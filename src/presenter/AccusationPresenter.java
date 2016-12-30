package presenter;

import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import model.*;
import network.client.ClientSent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import view.GlobalView;

/**
 * Saves selected PersonCard, WeaponCard and Room of Accusation.
 * Checks if accusation is correct and opens WinnerView if so
 *
 * @author Sarah
 */

public class AccusationPresenter {
	
	private static final Logger log = LogManager.getLogger(AccusationPresenter.class);
	private final ClientSent clientSent;
	
	public Gamefield model;
	public GlobalView view;
//	private PersonCard.Type accusedCounter;
//	private WeaponCard.Type accusedWeapon;
//	private RoomCard.Type accusedRoom;
	
	public AccusationPresenter(Gamefield model, GlobalView view, ClientSent clientSent){
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	public void handleClose(MouseEvent e){
		log.info("Close");
		this.view.gameView.anklageView.setVisible(false);
	}
	
	public void handle(MouseEvent e){
		this.view.gameView.anklageView.setVisible(true);
		this.view.gameView.anklageView.requestFocus();
	}

	
	//Werte aus Dropdown abspeichern in Murder model
	/**
	 * saves accused Counter, Weapon and Room when clicked on "Anklage erheben"	 
	 * @author Sarah
	 * @param event
	 *            , Mousevent clicked on Submit Button in Anklageview
	 */
	public void saveAccused(MouseEvent event){
		
		ComboBox<String> murder = view.gameView.anklageView.murder;
		ComboBox<String> weapon = view.gameView.anklageView.weapon;
		ComboBox<String> room = view.gameView.anklageView.room;
		
		//absolut keine Ahnung warum es mit try-catch funktioniert, denn es sollte keinen Unterschied machen
		//aber aktuell gehts, also am folgenden Code NICHTS VERÄNDERN!
		//überprüft ob in allen ComboBoxen etwas ausgewählt			
		int i = -1;
		try{
		if(murder.getSelectionModel().getSelectedIndex() != i && weapon.getSelectionModel().getSelectedIndex() != i && room.getSelectionModel().getSelectedIndex() != i )
		{
		// Achtung: Reihenfolge des Enums und der Auflistung in der Combobox muss gleich sein!
		model.accusation.setAccusedCounter(PersonCard.Type.values()[murder.getSelectionModel().getSelectedIndex()]);
		model.accusation.setAccusedWeapon(WeaponCard.Type.values()[weapon.getSelectionModel().getSelectedIndex()]);
		model.accusation.setAccusedRoom(RoomCard.Type.values()[room.getSelectionModel().getSelectedIndex()]);

		log.info(model.accusation.getAccusedCounter().toString() + " "
				+ model.accusation.getAccusedWeapon().toString() + " "
				+ model.accusation.getAccusedRoom().toString());
			view.gameView.anklageView.setVisible(false);		
		
		
		
		
		// sent to server
		Suspicion statement = new Suspicion();
		statement.setSuspCounter(model.accusation.getAccusedCounter());
		statement.setSuspWeapon(model.accusation.getAccusedWeapon());
		statement.setSuspRoom(model.accusation.getAccusedRoom());
		clientSent.accuse(model.getGameInfo().getGameID(), statement);
		}
		}
		catch(NullPointerException e){
			log.info("Das hier wird nie geprintet, falls doch, Sarah sagen!");
		}
	
}
}
