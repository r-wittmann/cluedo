package presenter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.scene.input.MouseEvent;
import view.GlobalView;

public class NotePresenter {

	private static final Logger log = LogManager.getLogger(NotePresenter.class);
	private GlobalView view;

	public NotePresenter(GlobalView view){
		this.view = view;
	}

	/**
	 * makes noteView visible 
	 * @param event
	 */
	public void handle(MouseEvent event){
		if(!this.view.gameView.noteView.isVisible()){
		this.view.gameView.noteView.setVisible(true);}
		else{this.view.gameView.noteView.setVisible(false);}
		log.info("Notes pressed");
	}
	
	public void render(){
		this.view.gameView.noteView.createColumn();
	}

}
