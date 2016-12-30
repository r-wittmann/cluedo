package presenter;

import javafx.scene.input.MouseEvent;
import view.GlobalView;

/**
 * Provides the handle-method for closing PoolView on clicking the close-button
 * @author Roxanna
 */
public class PoolPresenter implements RenderPresenterInterface {
	
	private GlobalView view;
	
	public PoolPresenter(GlobalView view){
		this.view = view;
	}

	/**
	 * Sets PoolView invisible if the Close Button was clicked
	 * @param event
	 */
	public void handle(MouseEvent event){
		this.view.gameView.poolView.setVisible(false);
	}

	@Override
	public void render() {
		this.view.gameView.poolView.setCards();
		this.view.gameView.poolView.setVisible(true);
	}

}
