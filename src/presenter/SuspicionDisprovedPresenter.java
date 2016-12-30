package presenter;

import javafx.scene.input.MouseEvent;
import view.GlobalView;

/**
 * Provides the method for closing the VerdaechtigungWiderlegtView 
 * <br>and its render function
 * @author Roxanna
 */
public class SuspicionDisprovedPresenter implements RenderPresenterInterface{
	
	private GlobalView view;
	
	public SuspicionDisprovedPresenter(GlobalView view){
		this.view = view;
	}
	
	/**
	 * Sets VerdaechtigungWiderlegtView invisible
	 * @param e
	 * @author Roxanna
	 */
	public void handleCloseDisproved(MouseEvent e) {
		this.view.gameView.widerlegtView.setVisible(false);
	}

	@Override
	public void render() {
		this.view.gameView.widerlegtView.initStyle();
		this.view.gameView.widerlegtView.setVisible(true);
	}
	
	

}
