package presenter;

import model.Gamefield;
import model.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.GlobalView;

/**
 * Updates buttons or shows views if playerstate says it's possible
 * @author Roxanna
 */
public class PlayerStatePresenter implements RenderPresenterInterface {
	private static final Logger log = LogManager.getLogger(PlayerStatePresenter.class);
	
	private Gamefield model;
	private GlobalView view;
	
	public PlayerStatePresenter(Gamefield model, GlobalView view) {
		this.model = model;
		this.view = view;
	}
	
	/**
	 * @author Ludwig
	 */
	@Override
	public void render() {
		// get self
		Player player = model.getPlayer();
		// only if play - avoid exeption if watcher
		if (player != null)
			checkPlayerstates(player);
	}
	
	/**
	 * Checks all playerstates of a player and directs to the 
	 * <br> respective methods
	 * @param player whose playerstate shall be checked
	 */
	private void checkPlayerstates(Player player){
		//set everything to initial values
		this.view.gameView.anklage.setDisable(true);
		this.view.gameView.dice.setDisable(true);
		this.view.gameView.stop.setDisable(true);
		this.view.gameView.fieldImageView.setMouseTransparent(true);
		this.view.gameView.fieldView.setMouseTransparent(true);
		this.view.gameView.verdaechtigungsView.setVisible(false);
		
//		EnumSet<Player.Playerstate> playerstates = player.getPlayerstates();
//		for (Player.Playerstate playerstate : playerstates)
//			log.debug("Playerstate: " + playerstate.toString());
		
		//change values according to playerstate
		if(player.hasPlayerstate(Player.Playerstate.ACCUSE)){
			accuse();
		}
		if (player.hasPlayerstate(Player.Playerstate.DISPROVE)){
			disprove();
		}
		if (player.hasPlayerstate(Player.Playerstate.END_TURN)){
			endTurn();
		}
		if (player.hasPlayerstate(Player.Playerstate.MOVE)){
			move();
		}
		if (player.hasPlayerstate(Player.Playerstate.ROLL_DICE)){
			rollDice();
		}
		if (player.hasPlayerstate(Player.Playerstate.SUSPECT)){
			suspect();
		}
		if (player.hasPlayerstate(Player.Playerstate.USE_SECRET_PASSAGE)){
			useSecretPassage();
		}
		if (player.hasPlayerstate(Player.Playerstate.DO_NOTHING)){
			doNothing();
		}
	}
	
	/**
	 * Sets the button of accusation enabled
	 */
	private void accuse() {
		this.view.gameView.anklage.setDisable(false);
	}
	
	/**
	 * Sets CardView and SelbstWiderlegenView visible
	 */
	private void disprove(){
		view.gameView.verdaechtigungErhobenView.setVisible(false); //Sarah
		this.view.gameView.cardsView.setVisible(true);
		this.view.gameView.selbstWiderlegenView.setCards();
		this.view.gameView.selbstWiderlegenView.setVisible(true);		
	}

	/**
	 * Sets the gameboard to mouse transparent, so nothing can be clicked on.
	 */
	private void doNothing(){
		this.view.gameView.dice.setDisable(true);
		this.view.gameView.stop.setDisable(true);
		this.view.gameView.anklage.setDisable(true);
	}
	
	/**
	 * Sets Buttons dice and anklage disabled and stop-button enabled
	 */
	private void endTurn(){
		this.view.gameView.dice.setDisable(true);
		this.view.gameView.stop.setDisable(false);
		this.view.gameView.fieldImageView.setMouseTransparent(true);
		this.view.gameView.fieldView.setMouseTransparent(true);
	}
	
	private void move(){
		this.view.gameView.fieldImageView.setMouseTransparent(false);
		this.view.gameView.fieldView.setMouseTransparent(false);
	}
	
	/**
	 * Undoes mouse transparence of gameboard, sets dice button enabled
	 * <br>and stop button disabled
	 */
	private void rollDice(){
		this.view.gameView.duBistDranView.setVisible(true);
		this.view.gameView.dice.setDisable(false);
		this.view.gameView.stop.setDisable(true);
	}

	/**
	 * Sets VerdaechtigungsView visible
	 */
	private void suspect(){
		this.view.gameView.verdaechtigungsView.setVisible(true);
		this.view.gameView.fieldImageView.setMouseTransparent(true);
		this.view.gameView.fieldView.setMouseTransparent(true);
		this.view.gameView.dice.setDisable(true);
	}
	
	/**
	 * Undoes mouse transparence of gameboard and sets GeheimgangView visible
	 */
	private void useSecretPassage(){
		this.view.gameView.geheimgangView.setVisible(true);
		this.view.gameView.dice.setDisable(true);
	}
}
