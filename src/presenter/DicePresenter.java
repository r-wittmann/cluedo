package presenter;

import javafx.scene.input.MouseEvent;
import model.Gamefield;
import network.client.ClientSent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.GlobalView;

public class DicePresenter implements RenderPresenterInterface {
	private static final Logger log = LogManager.getLogger(DicePresenter.class);
	
	public Gamefield model;
	public GlobalView view;
	private FieldPresenter fieldPres;
	private ClientSent clientSent;
	
	
	public DicePresenter(Gamefield model, GlobalView view, ClientSent clientSent, FieldPresenter fieldPres){
		this.clientSent = clientSent;
		this.model = model;
		this.view = view;
		this.fieldPres = fieldPres;
	}
	
	/**
	 * Rolls the dice, sets DiceView visible, generates accessible fields with possibleMove() 
	 * <br>marks them in the view with setShadow(), and directs them to their handle-method in FieldPresenter.
	 * @param event
	 */
	public void handle(MouseEvent event) {
		// send to server that dices should be rolled
		clientSent.rollDice(model.getGameInfo().getGameID());
		
		// only for local testing
		//model.dices.roll();
	}
	
	@Override
	public void render(){
		log.debug("trigger render dices");
		this.view.diceView.showDice();
		
		// render possible moves for counter
		fieldPres.showPossibleMoves();
	}
}