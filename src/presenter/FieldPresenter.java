package presenter;

import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import model.Gamefield;
import model.Player;
import network.client.ClientSent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import view.GlobalView;

import java.util.ArrayList;


/**
 * Provides the handle methods for every field and room on the gameboard. 
 * @author Roxanna
 */
public class FieldPresenter implements RenderPresenterInterface {
	
	private static final Logger log = LogManager.getLogger(FieldPresenter.class);
	
	public Gamefield model;
	public GlobalView view;
	private ArrayList<ArrayList<Integer>> temp = new ArrayList<ArrayList<Integer>>();
	
	private ClientSent clientSent;
	
	public FieldPresenter(Gamefield model, GlobalView view, ClientSent clientSent){
		this.model = model;
		this.view = view;
		this.clientSent = clientSent;
	}
	
	/**
	 * Sets Fields and Rooms at initial conditions (unsetting shadows) and updates counter position
	 */
	@Override
	public void render(){
		this.view.gameView.fieldImageView.unsetShadow();
		this.view.gameView.fieldView.setField();
		this.view.gameView.counterView.initStyle();
	}
	
	/**
	 * Show possible moves of counter
	 * @author Roxanna, Ludwig
	 */
	public void showPossibleMoves() {
				
		// @author Ludwig anfang
		// search active player
		Player activePlayer = null;
		ArrayList<Player> players = model.getPlayerList();
		for (Player player : players) {
			if (player.hasPlayerstate(Player.Playerstate.MOVE)
					|| player.hasPlayerstate(Player.Playerstate.ROLL_DICE))
				activePlayer = player;
		}
		if (activePlayer == null) {
			log.error("no player can move - nothing to render");
			return;
		}
		
		log.debug("calculate possible moves for"
				+ " name " + activePlayer.getName()
				+ " counter " + activePlayer.getCounter().getColor().toString()
				+ " x " + activePlayer.getCounter().getPositionX()
				+ " y " + activePlayer.getCounter().getPositionY()
				+ " pips " + model.dices.getPips());
		
		//calls method possibleMove() from model to get all accessible fields
		temp = model.board.possibleMove(
				activePlayer.getCounter().getPositionX(),
				activePlayer.getCounter().getPositionY(),
				model.dices.getPips()
		);
		// @author Ludwig ende
		
		//makes fields reachable
		this.view.gameView.counterView.setMouseTransparent(true);
		
		//calls method setShadow() from view to mark accessible fields
		for(int i = 0; i < temp.size(); i++){
			
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			// todo only clickable if self possible to move !!!
		
			//sets effects to room and transmits to the method handleRoom(MouseEvent event) in FieldPresenter
			if((x==6&&y==3)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.arbeitszimmeriv);
				//sets MouseListener to every Rectangle in ImageView from Workroom
				for(int m = 0; m <= 6; m++){				
					for(int n = 0; n <= 3; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleWork);
					}
				}
			}else if((x==9&&y==4)||(x==11&&y==6)||(x==12&&y==6)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.eingangshalleiv);
				//sets MouseListener to every Rectangle in ImageView from Hall
				for(int m = 9; m <= 14; m++){				
					for(int n = 0; n <= 6; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleHall);
					}
				}
			}else if(x==17&&y==5){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.saloniv);
				//sets MouseListener to every Rectangle in ImageView from Salon
				for(int m = 17; m <= 23; m++){				
					for(int n = 0; n <= 5; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleSalon);
					}
				}
			}else if((x==6&&y==8)||(x==3&&y==10)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.bibliothekiv);
				//sets MouseListener to every Rectangle in ImageView from Library
				for(int m = 0; m <= 5;m++){					
					for(int n = 6; n <= 10;n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleLibrary);
					}
				}
			}else if((x==11&&y==8)||(x==13&&y==9)||(x==11&&y==14)||(x==9&&y==12)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.schwimmbadiv);
				//sets MouseListener to every Rectangle in ImageView from Pool
				for(int m = 9; m <= 13; m++){				
					for(int n = 8; n <= 14; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handlePool);
					}
				}
			}else if((x==17&&y==9)||(x==16&&y==12)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.speisezimmeriv);
				//sets MouseListener to every Rectangle in ImageView from Dining Room
				for(int m = 16; m <= 23; m++){				
					for(int n = 9; n <= 14; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleDiner);
					}
				}
			}else if((x==1&&y==12)||(x==5&&y==15)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.billiardzimmeriv);
				//sets MouseListener to every Rectangle in ImageView from Billard Room
				for(int m = 0; m <= 5; m++){			
					for(int n = 12; n <= 16; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleBillard);
					}
				}
			}else if(x==4&&y==19){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.wintergarteniv);
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.wintergarten1iv);
				//sets MouseListener to every Rectangle in ImageView from Winter Garden
				for(int m = 0; m <= 4; m++){				
					for(int n = 19; n <= 24; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleGarden);
					}
				}
			}else if((x==8&&y==19)||(x==9&&y==17)||(x==14&&y==17)||(x==15&&y==19)){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.musikzimmeriv);
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.musikzimmer1iv);
				//sets MouseListener to every Rectangle in ImageView from Music Room
				for(int m = 8; m <= 15; m++){			
					for(int n = 17; n <= 22; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleMusic);
					}
				}
				//Addition to Music Room
				for(int m = 10; m <= 13; m++){				
					for(int n = 23; n <= 24; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleMusic);
					}
				}
			}else if(x==19&&y==18){
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.kuecheiv);
				this.view.gameView.fieldImageView.setShadow(this.view.gameView.fieldImageView.kueche1iv);
				//sets MouseListener to every Rectangle in ImageView from Kitchen
				for(int m = 18; m <= 23;m++){				
					for(int n = 18; n <= 24; n++){
						this.view.gameView.fieldView.fields[m][n].setOnMouseClicked(this::handleKitchen);
					}
				}
			}else{
				//sets MouseListener to all other Rectangles on the board
				this.view.gameView.fieldView.setShadow(this.view.gameView.fieldView.fields[x][y]);
				view.gameView.fieldView.fields[x][y].setOnMouseClicked(this::handle);
			}
		}
	}
	
	/**
	 * sets position of counter as the clicked on field and undoes setShadow()
	 * @param event
	 */
	public void handle(MouseEvent event) {
		//getSource.getLayoutX/Y returns the x/y-position from the Rectangle that was clicked on; 
		//Because a Rectangle is 26 long and 26 wide, the position is divided by 26.
		int x = ((int) ((Rectangle) event.getSource()).getLayoutX() / 26);
		int y = ((int) ((Rectangle) event.getSource()).getLayoutY() / 26);
		
		// sent new counter position x, y to server
		clientSent.move(model.getGameInfo().getGameID(), x, y);
	}
	
	//the following methods set effects to the rooms and set the counter in the clicked-on room.
	//they also set class verdaechtigungsView to visible 
	/**
	 * Sets poolView visible if poolCards > 0, 
	 * <br>and sets the Counter to the Pool
	 * @param event
	 */
	public void handlePool(MouseEvent event){
		//sets poolView to visible, if poolCards is not empty
		if(this.model.getPoolCards().size() > 0){
			this.view.gameView.poolView.setVisible(true);
		}
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==11&&y==8){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==13&&y==9){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==9&&y==12){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==1&&y==14){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			}
		}
	}
	
	/**
	 * Sets the Counter to the Musicroom
	 * @param event
	 */
	public void handleMusic(MouseEvent event){
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==8&&y==19){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==14&&y==17){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==9&&y==17){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==15&&y==19){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			}
		}
	}
	
	/**
	 * Sets the Counter to the Library
	 * @param event
	 */
	public void handleLibrary(MouseEvent event){
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==6&&y==8){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==3&&y==10){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			}
		}
	}
	
	/**
	 * Sets the Counter to the Conservatory
	 * @param event
	 */
	public void handleGarden(MouseEvent event){
		clientSent.move(model.getGameInfo().getGameID(), 4, 19);
	}
	
	/**
	 * Sets the Counter to the Billardroom
	 * @param event
	 */
	public void handleBillard(MouseEvent event){
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==1&&y==12){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==5&&y==15){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} 
		}
	}
	
	/**
	 * Sets the Counter to the Study room
	 * @param event
	 */
	public void handleWork(MouseEvent event){
		clientSent.move(model.getGameInfo().getGameID(), 6, 3);
	}
	
	/**
	 * Sets the Counter to the Hall
	 * @param event
	 */
	public void handleHall(MouseEvent event){
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==9&&y==4){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==11&&y==6){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==12&&y==6){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			}
		}
	}
	
	/**
	 * Sets the Counter to the Kitchen
	 * @param event
	 */
	public void handleKitchen(MouseEvent event){
		clientSent.move(model.getGameInfo().getGameID(), 19, 18);
	}
	
	/**
	 * Sets the Counter to the Salon
	 * @param event
	 */
	public void handleSalon(MouseEvent event){
		clientSent.move(model.getGameInfo().getGameID(), 17, 5);
	}
	
	/**
	 * Sets the Counter to the Dining room
	 * @param event
	 */
	public void handleDiner(MouseEvent event){
		for(int i = 0; i < temp.size(); i++){
			int x = temp.get(i).get(0);
			int y = temp.get(i).get(1);
			
			if(x==17&&y==9){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			} else if(x==16&&y==12){
				clientSent.move(model.getGameInfo().getGameID(), x, y);
				break;
			}
		}
	}
	
	/**opens winnerView 
	 * @author Sarah
	 */
	@Deprecated
	public void youWin(){
		this.view.gameView.winnerView.setVisible(true);
		log.info("Deine Anklage war richtig! Du hast gewonnen!");
	}
	
	/**opens looseView
	 * @author Sarah
	 */
	@Deprecated
	public void youLoose(){
		this.view.gameView.looseView.setVisible(true);
		log.info("Deine Anklage war falsch! Du hast verloren!");
	}
	
	/**
	 * @author Sarah
	 */
	@Deprecated
	public void gameEnded(){
		log.info("Das Spiel wurde beendet");
	}
	
}
