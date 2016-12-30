package loginView;
/**
 * class GlobalLoginView holds all for the login process needed views
 * @author Wittmann Rainer
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

	public class GlobalLoginView extends StackPane{
	
	public SelectSCView selectSCView = new SelectSCView();
	public ClientLoginView clientLoginView = new ClientLoginView();
	public CreateServerView createServerView = new CreateServerView();
	public LobbyView lobbyView = new LobbyView();
	public StartView startView = new StartView();
	public AnleitungsView anleitungsView = new AnleitungsView();
	public CreditView creditView = new CreditView();
	public ImageView imgiv;
	
	public GlobalLoginView(){
		initStyle();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		imgiv = new ImageView(new Image(getClass().getResourceAsStream("/startBild/startImg.jpg")));
		imgiv.setFitWidth(1000);
		imgiv.setFitHeight(675);
		imgiv.setPreserveRatio(false);
		imgiv.setSmooth(true);
		
		startView.setVisible(true);
		selectSCView.setVisible(false);
		clientLoginView.setVisible(false);
		createServerView.setVisible(false);
		lobbyView.setVisible(false);
		anleitungsView.setVisible(false);
		creditView.setVisible(false);
		
		this.getChildren().add(imgiv);
		this.getChildren().addAll(clientLoginView,selectSCView,lobbyView,startView,anleitungsView,creditView);
	}
}
