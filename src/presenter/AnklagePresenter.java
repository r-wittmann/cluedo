package presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.input.MouseEvent;
import model.Gamefield;
import view.GlobalView;

public class AnklagePresenter {
	
	private static final Logger log = LogManager.getLogger(AnklagePresenter.class);
	
	public Gamefield model;
	public GlobalView view;
	
	public AnklagePresenter(Gamefield model, GlobalView view){
		this.model = model;
		this.view = view;
	}
	
	public void handleClose(MouseEvent e){
		log.info("Close");
		this.view.gameView.anklageView.setVisible(false);
	}
	
	public void handleAnklage(MouseEvent e){
		//hier gehört if-Abfrage rein ob der Spieler gewonnen oder verloren hat.
		//dann wird entsprechende View angezeigt
		this.view.gameView.looseView.setVisible(true);
		this.view.gameView.anklageView.setVisible(false);
		
	}

}