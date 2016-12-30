package presenter;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.*;
import network.client.ClientSent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.GlobalView;

import java.util.List;

/**
 * Provides methods for handling disproves of suspicions
 * @author Roxanna
 */
public class DisproveSuspicionPresenter {

	Gamefield model;
	GlobalView view;
	ClientSent clientSent;
	
	private static final Logger log = LogManager.getLogger(DisproveSuspicionPresenter.class);
	
	public DisproveSuspicionPresenter(Gamefield model, GlobalView view, ClientSent clientSent){
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	/**
	 * If player says he can disprove, his cards are set on mouse clicked. 
	 * <br> if player then clicks on a card, method handleDisprove will be called
	 * <br>if player has no valid card to disprove, a dialog opens and lets the player choose again
	 * @param event
	 * @author Roxanna
	 */
	public void handleYes(MouseEvent event){
		if(this.checkCards().size() != 0){
			// realy something to disprove
			this.view.gameView.selbstWiderlegenView.setVisible(false);
			for(int i = 0; i < model.getPlayer().getPlayerCards().size(); i++){
				this.view.gameView.cardsView.getPlayerCards().get(i).setOnMouseClicked(this::handleDisprove);
			}
		} else {
			// wrong click, nothing to disprove
			DialogPresenter dial = new DialogPresenter();
			dial.handleFalseAnswerYesDisprove();
			this.view.gameView.selbstWiderlegenView.setVisible(true);
		}
		
	}
	
	/**
	 * Checks if there is a card the player could use to disprove
	 * <br>if so, it opens a dialog and lets the player choose again
	 * @author Roxanna
	 * @param event
	 */
	public void handleNo(MouseEvent event){
		if(this.checkCards().size() == 0){
			this.view.gameView.selbstWiderlegenView.setVisible(false);
			this.view.gameView.cardsView.setVisible(false);
			// realy no cards to disprove
			clientSent.disprove(model.getGameInfo().getGameID(), null);
		} else {
			DialogPresenter dial = new DialogPresenter();
			dial.handleFalseAnswerNoDisprove();
			this.view.gameView.selbstWiderlegenView.setVisible(true);
		}
	}
	
	/**
	 * checks if and which cards a player has matching the suspicion
	 * 
	 * @return to suspicion matching cards of a player
	 * @author Sarah
	 */
	public List<Card> checkCards() {
		model.suspicion.getValidCards().clear();
		if (model.getPlayer().checkHandContainsCard(model.suspicion.getSuspCounter()))
			model.suspicion.addToValidCards(new PersonCard(model.suspicion
					.getSuspCounter()));

		if (model.getPlayer().checkHandContainsCard(model.suspicion.getSuspWeapon()))
			model.suspicion.addToValidCards(new WeaponCard(model.suspicion
					.getSuspWeapon()));

		if (model.getPlayer().checkHandContainsCard(model.suspicion.getSuspRoom()))
			model.suspicion.addToValidCards(new RoomCard(model.suspicion
					.getSuspRoom()));

		return model.suspicion.getValidCards();
	}
	
	/**
	 * Proves if the card that was clicked on is valid and 
	 * <br>if so, it saves it in disproveCard in class Suspicion
	 * @param event
	 * @author Roxanna
	 */
	public void handleDisprove(MouseEvent event){
		String name = ((ImageView) event.getSource()).getId();
		List<Card> validCards = checkCards();
		//Proves if chosen Card is one of the valid Cards
		while(validCards.size() != 0){
			if(validCards.get(0).getName().equals(name)){
				//Speichert gewählte Karte in Suspicion (Model) in disproveCard
				for(int j = 0; j < this.model.getPlayer().getPlayerCards().size(); j++) {
					if(this.model.getPlayer().getPlayerCards().get(j).getName().equals(name)){
						this.model.suspicion.setDisproveCard(this.model.getPlayer().getPlayerCards().get(j));
					}
				}
				log.info("Player disproved with correct card: " + checkCards().get(0).getName());
				log.info("DisproveCard was set: " + this.model.suspicion.getDisproveCard().getInfo());
				
				// send disprove to server
				clientSent.disprove(this.model.getGameInfo().getGameID(), this.model.suspicion.getDisproveCard());
				
				this.view.gameView.cardsView.setVisible(false);
				
				break;
			}
			validCards.remove(0);
		}
		//Opens a dialog if chosen Card is not valid 
		if(validCards.size() == 0){
			DialogPresenter dial = new DialogPresenter();
			dial.handleFalseCardDisprove();
			this.view.gameView.selbstWiderlegenView.setVisible(true);
		}	
	}
}
