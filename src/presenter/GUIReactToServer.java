package presenter;
import json.org.JSONObject;
import model.Gamefield;
import model.Player;
import network.client.ReactToServer;
import network.jsonprotocol.JsonProtocollConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;

import static network.jsonprotocol.JsonToModel.convertKarteToModel;
/**
 * Empty reaction for received json messages from server
 * Used for testing and debugging
 * @author Ludwig
 */
public class GUIReactToServer implements ReactToServer, JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(GUIReactToServer.class);
    
    LoginPresenter loginPresenter = null;
    
    public GUIReactToServer(LoginPresenter loginPresenter) {
        this.loginPresenter = loginPresenter;
    }
    
    @Override
    public void handleOK(JSONObject jsonObject) {
        // nothing
    }
    
    /**
     * @author Roxanna
     */
    @Override
    public void handleError(JSONObject jsonObject) {
        //Prints Error message in an alert
        DialogPresenter dial = new DialogPresenter();
        dial.handleErrorMessage(jsonObject.getString(MESSAGE));
    }
    
    @Override
    public void handleUDP(JSONObject jsonObject) {
        loginPresenter.addUDPServer(
                jsonObject.optString(IP),
                jsonObject.optInt(TCP_PORT),
                jsonObject.optString(GROUP)
        );
    }
    
    @Override
    public void handleLoginSuccessful(JSONObject jsonObject) {
        loginPresenter.loginClientReady();
    }
    
    @Override
    public void handleUserAdded(JSONObject jsonObject) {
        // update view over clients
        loginPresenter.updatePlayer();
    }
    
    @Override
    public void handleGameCreated(JSONObject jsonObject) {
        // update overview over available games
        loginPresenter.updateGamesPresenter();
        // to render gamefield
        // only start if created by self
        if (jsonObject.getJSONObject(PLAYER).getString(NICK).equals(loginPresenter.getClientGlobal().getMyNick())) {
            log.info("presenter: own game ready -> render");
            loginPresenter.createGame(getGamefield(jsonObject), jsonObject.getInt(GAMEID));
        }
    }
    
    @Override
    public void handleGameStarted(JSONObject jsonObject) {
        // update overview over available games - gamestate changed
        loginPresenter.updateGamesPresenter();
        
        // start game
        // only if you play this game
        if (getGamefield(jsonObject).getPlayer() != null && loginPresenter.getClientGlobal().getMyNick().equals(getGamefield(jsonObject).getPlayer().getName())) {
            loginPresenter.startGame(jsonObject.getInt(GAMEID));
    
            //
            getPresenter(jsonObject).getNotePres().render();
        }
    }
    
    @Override
    public void handlePlayerAdded(JSONObject jsonObject) {
        // update overview over available games - more player
        loginPresenter.updateGamesPresenter();
        
        // only start view if self joint to play game
        if (jsonObject.getJSONObject(PLAYER).getString(NICK).equals(getGamefield(jsonObject).getMyNick()))
            loginPresenter.createGameAsJoiner(getGamefield(jsonObject), jsonObject.getInt(GAMEID));
    
        // update ccard view 
        if (existsGUI(jsonObject)) getPresenter(jsonObject).getcCardPresenter().render();
    }
    
    @Override
    public void handleWatcherAdded(JSONObject jsonObject) {
        // nothing
    }
    
    @Override
    public void handleGameinfo(JSONObject jsonObject) {
        // notify presenter to render gamefield
        log.info("notify presenter: game you wish to watch is ready");
        Gamefield gamefield = getGamefield(jsonObject.getJSONObject(GAME));
        loginPresenter.createGameAsWatcher(gamefield, jsonObject.getJSONObject(GAME).getInt(GAMEID));
    }
    
    @Override
    public void handleGameEnded(JSONObject jsonObject) {
        // update lobby view of games
        loginPresenter.updateGamesPresenter();
        
        // not participated or view of game available -> do not try to update view
        if (!existsGUI(jsonObject)) return;
        
        // murder cards
        JSONObject statement = jsonObject.getJSONObject(STATEMENT);
        
        // show win or loose message
        if (!jsonObject.optString(NICK).equals("")) {
            // there is a winner!
        	getPresenter(jsonObject).getWinnerPres().setCards(statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
//            getPresenter(jsonObject).getWinnerPres().render();
    
            // todo for debug
            DialogPresenter dial = new DialogPresenter();
            if (getGamefield(jsonObject).getMyNick().equals(jsonObject.getString(NICK))) {
                dial.handleGameEndedYouWon(statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
            } else {
                dial.handleGameEndedOtherWon(jsonObject.getString(NICK), statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
            }
        } else {
            // no winner
            // looseView erhält direkt daten aus Jsonobject
            // @author Rainer,Maurice,Sarah
            getPresenter(jsonObject).getLoosePres().setCards(statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
        }
    }
    
    @Override
    public void handleLeftGame(JSONObject jsonObject) {
        // update overview over available games - gamestate changed or less player if not started
        loginPresenter.updateGamesPresenter();
        
        if (!existsGUI(jsonObject)) return; // msg sent to every client - so check
        // update ccard
        getPresenter(jsonObject).getcCardPresenter().render();
    }
    
    @Override
    public void handleGameDeleted(JSONObject jsonObject) {
        // update overview over available games - gamestate changed or less player if not started
        loginPresenter.updateGamesPresenter();
    }
    
    @Override
    public void handleDisconnected(JSONObject jsonObject) {
    }
    
    @Override
    public void hanldeUserleft(JSONObject jsonObject) {
        // notify presenter to render gamefield
        loginPresenter.updatePlayer();
    }
    
    @Override
    public void handleChat(JSONObject jsonObject) {
        // json protocol should not be here, but we have no model
        String timestamp = jsonObject.getString(TIMESTAMP);
        LocalDateTime dateTime = LocalDateTime.parse(timestamp);
    
        // notify presenter to render chat message
        loginPresenter.addChatMessage(jsonObject.getString(MESSAGE),
                dateTime,
                jsonObject.optInt(GAMEID),
                jsonObject.optString(SENDER),
                jsonObject.optString(NICK)
        );
    }
    
    @Override
    public void handlePlayerCards(JSONObject jsonObject) {
        // nothing
    }
    
    @Override
    public void handleStateupdate(JSONObject jsonObject) {
        // update buttons and show views
        getPresenter(jsonObject).getPlayerStatePresenter().render();
        getPresenter(jsonObject).getcCardPresenter().highlightPlayer();
    }
    
    @Override
    public void handleDiceResult(JSONObject jsonObject) {
        getPresenter(jsonObject).getDicePres().render();
    }
    
    @Override
    public void handleMoved(JSONObject jsonObject) {
        getPresenter(jsonObject).getFieldPres().render();
    }
    
    @Override
    public void handlePoolcards(JSONObject jsonObject) {
        // show pool view
        getPresenter(jsonObject).getPoolPres().render();
    }
    
    @Override
    public void handleSuspicion(JSONObject jsonObject) {
        getPresenter(jsonObject).getVerdaechtigungErhobenPresenter().render();
        
        // update person
        getPresenter(jsonObject).getFieldPres().render();
        
        // update weapon
        getPresenter(jsonObject).getWeaponPres().render();
    }
    
    @Override
    public void handleDisproved(JSONObject jsonObject) {
        getPresenter(jsonObject).getVerdaechtigungErhobenPresenter().setViewNotVisible();
        
        if (!jsonObject.optString(CARD).equals("")) {
            // my own suspicion was disproved
            
            //todo for debug
            DialogPresenter dial = new DialogPresenter();
            dial.handleOwnDisproved(jsonObject.getString(NICK), jsonObject.getString(CARD));
            
            //setCard aufrufen um wiederlegende Karte anzuzeigen
            getPresenter(jsonObject).getcCardPresenter().setCard(convertKarteToModel(jsonObject.getString(CARD)));
            
        } else {
            // suspicion of other player was disproved
            //getPresenter(jsonObject).getSuspicionDisprovedPres().render();
            
            // todo for debug
            DialogPresenter dial = new DialogPresenter();
            dial.handleDisproveFromOther(jsonObject.getString(NICK));
        }
        // todo
    }
    
    @Override
    public void handleNoDisprove(JSONObject jsonObject) {
        getPresenter(jsonObject).getVerdaechtigungErhobenPresenter().setViewNotVisible();
        getPresenter(jsonObject).getNoDisprovePresenter().render();
    }
    
    @Override
    public void handleWrongAccusation(JSONObject jsonObject) {
        JSONObject statement = jsonObject.getJSONObject(STATEMENT);
        
        // todo for debug
        if (getGamefield(jsonObject).getPlayer().hasPlayerstate(Player.Playerstate.ACCUSE)) {
        
            DialogPresenter dial = new DialogPresenter();
            dial.handleOwnWrongAccusation(statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
        
        } else {
            // calculate last player who could accuse
            String lastNick = "";
            for (Player player : getGamefield(jsonObject).getPlayerList())
                if (player.hasPlayerstate(Player.Playerstate.ACCUSE))
                    lastNick = player.getName();
        
            DialogPresenter dial = new DialogPresenter();
            dial.handleOtherWrongAccusation(lastNick, statement.getString(PERSON), statement.getString(WEAPON), statement.getString(ROOM));
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // private functions
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Helper to get presenter according to gameID
     * @param jsonObject jsonobject containing gameID
     * @return presenter of gameID
     * @author Ludwig
     */
    private GlobalPresenter getPresenter(JSONObject jsonObject) {
        return loginPresenter.getPresenter(jsonObject.getInt(GAMEID));
    }
    
    /**
     * Test if GUI exists for a specific game (is game joined and viewed?)
     * @param jsonObject json containing gameID to check
     * @return true if GUI exists
     * @author Ludwig
     */
    private boolean existsGUI(JSONObject jsonObject) {
        return (getPresenter(jsonObject) != null);
    }
    
    /**
     * Helper to get gamefield shorter
     * @param jsonObject jsonobject containing gameId
     * @return reference to according gamefield
     * @author Ludwig
     */
    private Gamefield getGamefield(JSONObject jsonObject) {
        return loginPresenter.getClientGlobal().getGamefield(jsonObject.getInt(GAMEID));
    }
}