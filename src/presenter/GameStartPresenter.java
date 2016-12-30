package presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import network.client.ClientSent;
import javafx.scene.input.MouseEvent;
import view.GlobalView;

public class GameStartPresenter implements RenderPresenterInterface {
	private static final Logger log = LogManager.getLogger(GameStartPresenter.class);
	
	private GlobalView view;
	private int gameID;
	private ClientSent clientSent;
	
	public GameStartPresenter(int gameID, GlobalView view, ClientSent clientSent){
		this.gameID = gameID;
		this.view = view;
		this.clientSent = clientSent;
	}

	public void handleGameStart(MouseEvent e){
		// sent to server
		clientSent.startGame(gameID);
	}
	
	public void render(){
		log.info("Spiel Gestartet");
		view.gameView.cardsView.setCards();
		view.gameView.gameStartView.setVisible(false);
		view.gameView.anklage.setDisable(false);
		view.gameView.cards.setDisable(false);
		view.gameView.note.setDisable(false);
		//dice wird noch nicht klickbar gemacht. Erst wenn der Spieler dran ist und 
		//würfeln darf sollte dieser Button klickbar sein.
		view.gameView.gameStartView.setVisible(false);
		view.cCardView.renderPlayer();
	}
}
