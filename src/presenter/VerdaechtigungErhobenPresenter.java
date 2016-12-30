package presenter;

import view.GlobalView;

public class VerdaechtigungErhobenPresenter implements RenderPresenterInterface {
	
	private GlobalView view;
	
	public VerdaechtigungErhobenPresenter(GlobalView view){
		this.view = view;
	}
	
	/**
	 * @author Ludwig
	 */
	@Override
	public void render(){
		setViewVisible();
	}
	
	private void setViewVisible(){
		view.gameView.verdaechtigungErhobenView.setCards();
		view.gameView.verdaechtigungErhobenView.setVisible(true);
	}
	public void setViewNotVisible(){
		view.gameView.verdaechtigungErhobenView.setVisible(false);
	}
}
