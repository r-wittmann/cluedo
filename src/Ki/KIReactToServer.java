package Ki;

import json.org.JSONObject;
import model.*;
import model.Counter.Color;
import model.Player.Playerstate;
import network.client.ClientSent;
import network.client.ReactToServer;
import network.jsonprotocol.JsonProtocollConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumSet;
import java.util.Random;

import static Ki.Ki.KILogLevel;
/**
 * KI reaction for received json messages from server
 * @author Ludwig
 */
public class KIReactToServer implements ReactToServer, JsonProtocollConstants {
    private static final Logger log = LogManager.getLogger(KIReactToServer.class);
    
    private ClientSent clientSent;
    private Global global;
    public Ki ki;
    
    /**
     * loggs KI thoughts or moves
     * @param a text or object
     * @author Ludwig
     */
    private void logKI(Object a) {
        log.log(KILogLevel, a);
        this.global = ki.getGlobal();
    }
    
    /**
     * Set clientSent to send msg to server
     * @param clientSent reference to send msg to server
     * @author Ludwig
     */
    public void setClientSent(ClientSent clientSent) {
        this.clientSent = clientSent;
    }
    
    /**
     * konstructor 
     * @param ki the Ki
     * @author Jonas
     */
    public KIReactToServer(Ki ki){
    	this.ki = ki;
    }
    
    @Override
    public void handleOK(JSONObject jsonObject) {}
    
    @Override
    public void handleError(JSONObject jsonObject) {}
    
    @Override
    public void handleUDP(JSONObject jsonObject) {
        // not needed
    }
    
    /**
     * @param jsonObject received jsonobject for optional parse
     * @author Ludwig, Jonas
     */
    @Override
    public void handleLoginSuccessful(JSONObject jsonObject) {
        logKI("Hey I am in!");
        clientSent.chat("Hi I'm DR.roBOTo, for more information send me a private message >help<.", 0, 0, null);
    }
    
    @Override
    public void handleUserAdded(JSONObject jsonObject) {
        // wayne - maybe send automatically help
    }
    
    /**
     * @author Jonas
     */
    @Override
    public void handleGameCreated(JSONObject jsonObject) {
        // only look for own games
        if (!global.getMyNick().equals(jsonObject.getJSONObject(PLAYER).getString(NICK))) {
            log.info("not a KI game " + getGameID(jsonObject));
            return;
        }
        ki.initPossibleCards(getGameID(jsonObject));
        //do nothing?
        // know self created game
        // maybe join specific games
    }
    
    @Override
    public void handleGameStarted(JSONObject jsonObject) {
        // wayne - no need for correct gameplay

    }
    
    @Override
    public void handlePlayerAdded(JSONObject jsonObject) {
        // todo maybe autostart if game is full
        // it's the same but KI did not created it
        handleGameCreated(jsonObject);
    }
    
    @Override
    public void handleWatcherAdded(JSONObject jsonObject) {
        // wayne - no need for gameplay
    }
    
    @Override
    public void handleGameinfo(JSONObject jsonObject) {
        // wayne - only need for watcher
    }
    
    /**
     * @author Ludwig
     */
    @Override
    public void handleGameEnded(JSONObject jsonObject) {
        // maybe save result or stats?
        // leave game
        clientSent.leaveGame(getGameID(jsonObject));
    }
    
    @Override
    public void handleLeftGame(JSONObject jsonObject) {
        // wayne - if normal/rage quit game ends
    }
    
    @Override
    public void handleGameDeleted(JSONObject jsonObject) {
        // wayne - game ended before - maybe kill KI model
    }
    
    /**
     * @param jsonObject received jsonobject for optional parse
     * @author Ludwig
     */
    @Override
    public void handleDisconnected(JSONObject jsonObject) {
        logKI("Goodbye wonderful life! *suicide*");
        logKI("R.I.P. X_X");
        //System.exit(0);
    }
    
    @Override
    public void hanldeUserleft(JSONObject jsonObject) {
        // wayne - no need for gameplay
    }
    
    /**
     * @param jsonObject received jsonobject for optional parse
     * @author Ludwig
     */
    @Override
    public void handleChat(JSONObject jsonObject) {
        // wayne - or maybe make such a stupid spambot as other groups
        
        String sender = jsonObject.optString(SENDER);
        // only parse private messages
        if (jsonObject.optString(NICK).equals(global.getMyNick()) && !sender.equals("")) {
            logKI("Got a crazy message from " + sender);
            
            String message = jsonObject.getString(MESSAGE);
            
            // kill the KI
            if (message.contains("kill")) {
                logKI("Requesting a rope for suicide...");
                clientSent.chat("Requesting a rope for suicide...", 0, 0, sender);
                
                clientSent.disconnect();
            }
            // create a game
            else if (message.contains("create")) {
                Counter.Color color = Counter.Color.RED;
                Random r = new Random();
                int temp = r.nextInt(5);
                switch (temp) {
                    case 0:
                        color = Color.BLUE;
                        break;
                    case 1:
                        color = Color.GREEN;
                        break;
                    case 2:
                        color = Color.PURPLE;
                        break;
                    case 3:
                        color = Color.WHITE;
                        break;
                    case 4:
                        color = Color.YELLOW;
                        break;
//                    temp rm red unknown bug
//                    case 5:
//                        color = Color.RED;
//                        break;
                }
                logKI("My favourite color this game is: " + color.toString());
                logKI("Then create a game!");
                clientSent.createGame(color);
                clientSent.chat("Created a new game with my favourite color " + color.toString(), 0, 0, sender);
            }
            // join a specific game
            else if (message.contains("join")) {
                // replace all not number and parse gameID
                String parseGameID = message.replaceAll("[^\\d]", "");
                int gameID = Integer.parseInt(parseGameID);
                
                // check exist of game
                if (!global.existsGameID(gameID)) {
                    logKI("I tried, but your stupid game is NULL");
                    clientSent.chat("I tried, but your stupid game is NULL", 0, 0, sender);
                    return;
                }
                
                // search free color
                EnumSet<Color> colors = EnumSet.allOf(Color.class);
                for (Player player : global.getGamefield(gameID).getPlayerList()) {
                    colors.remove(player.getCounter().getColor());
                }
                
                // no free color
                if (colors.isEmpty()) {
                    logKI("No color for me :(");
                    clientSent.chat("No color for me :(", 0, 0, sender);
                    return;
                }
    
                // decide for color
                Color color = Color.RED;
                for (Color color1 : colors) {
                    color = color1;
                }
                
                logKI("I really like to join the game " + gameID + " as " + color.toString());
                clientSent.chat("I really like to join the game " + gameID + " as " + color.toString(), 0, 0, sender);
                
                clientSent.joinGame(gameID, color);
            }
            // let the KI start a game
            else if (message.contains("start")) {
                // replace all not number and parse gameID
                String parseGameID = message.replaceAll("[^\\d]", "");
                int gameID = Integer.parseInt(parseGameID);
    
                if (global.getGamefield(gameID).getPlayer() == null) {
                    logKI("Your gameID so wrong dude...");
                    clientSent.chat("Your gameID so wrong dude...", 0, 0, sender);
                    return;
                }
    
                logKI("Ok, let's play a little game, muahaha!");
                clientSent.chat("Ok, let's play a little game, muahaha!", 0, 0, sender);
                
                clientSent.startGame(gameID);
            }
            // ping the KI
            else if (message.contains("hi")) {
                logKI("Pingback: hi!");
                clientSent.chat("hi!", 0, 0, sender);
            }
            // ping the KI
            else if (message.contains("ping")) {
                logKI("Pingback: pong!");
                clientSent.chat("pong!", 0, 0, sender);
            }
            // sent help to player
            else if (message.contains("help")) {
                logKI("Sent dummy >" + sender + "< some help how to talk to me");
                clientSent.chat("Ooh, little baby doesn't know how to talk with me?\n Let me help you!", 0, 0, sender);
    
                String KI_CHAT_HELP = "Sent me a private message if you want to talk with me.\n"
                        + "You can send me several commands, which ich proceed.\n\n"
                        + "(0) create\n"
                        + "creates a game with KI\n\n"
                        + "(1) connect *gameID*\n"
                        + "connect to a specific gameID\n\n"
                        + "(2) start *gameID*\n"
                        + "starts a specific gameID\n\n"
                        + "(3) kill\n"
                        + "end KIs life\n\n"
                        + "(4) ping\n"
                        + "answer with >pong<";
                
                clientSent.chat(KI_CHAT_HELP, 0, 0, sender);
            }
            // got no useful message
            else {
                logKI("This message was only crap");
                clientSent.chat("This message was only crap!", 0, 0, sender);
            }
        }
    }
    
    /**
     * @author Jonas, Ludwig
     */
    @Override
    public void handlePlayerCards(JSONObject jsonObject) {
        log.debug("set own cards");
        ki.getSaveAgent().setOwnCards(getGameID(jsonObject), getGamefield(jsonObject).getPlayer().getPlayerCards());
        log.debug("set own cards");
        ki.getSaveAgent().deleteHandCards(getGameID(jsonObject));
    }
    
    /**
     * handles incomming Statusupdates
     * @author Jonas, Ludwig
     */
    @Override
	public void handleStateupdate(JSONObject jsonObject) {
    	
		Gamefield gf = getGamefield(jsonObject);
		int gameID = getGameID(jsonObject);
		
        // accuse if we have only one possible card for person, weapon and room left
		if(ki.getPossibleMurders(gameID).size()==1 && ki.getPossibleRooms(gameID).size() ==1 && ki.getPossibleWeapons(gameID).size() == 1)
		{
			if(gf.getPlayer().hasPlayerstate(Playerstate.ACCUSE))
			{
    			logKI("I know the murder!");
        		Suspicion statement = new Suspicion();
    			statement.setSuspCounter(ki.getPossibleMurders(gameID).get(0).getCardValue());
        		statement.setSuspWeapon(ki.getPossibleWeapons(gameID).get(0).getCardValue());
        		statement.setSuspRoom(ki.getPossibleRooms(gameID).get(0).getCardValue());
        		clientSent.accuse(gameID, statement);
                
                return; // do nothing else in this move
    		}
		}
		
		if(gf.getPlayer().hasPlayerstate(Playerstate.SUSPECT))
		{
			logKI("I try to find some hints by suspect somebody");
    		Suspicion statement = new Suspicion();
    		statement.setSuspCounter(ki.getSus_accAgent().getSuspectPerson(gameID));
    		statement.setSuspWeapon(ki.getSus_accAgent().getSuspectWeapon(gameID));
			clientSent.suspect(gameID, statement);
    	}
		
		else if (gf.getPlayer().hasPlayerstate(Playerstate.ROLL_DICE))
		{
			logKI("I can roll the dice! FUCK YEAH");
			clientSent.rollDice(gameID);
			log.info("Habe roll dice gesendet");
		}
		
		else if (gf.getPlayer().hasPlayerstate(Playerstate.MOVE)) 
		{
            logKI("MOVE BITCH get out the way");
            int[] temp = ki.getMoveAgent().move(gf, getGamefield(jsonObject).dices.getPips());
            clientSent.move(gameID, temp[0], temp[1]);
            log.info("Ich wollte mich eigentlich bewegen...");
		}
		
		else if (gf.getPlayer().hasPlayerstate(Playerstate.DISPROVE))
		{
			logKI("I can disprove you, can I?");
			
			clientSent.disprove(gameID, ki.getDisproveAgent(gameID).chooseDisprove(gameID));
		}
			
		else if(gf.getPlayer().hasPlayerstate(Playerstate.DO_NOTHING)){
			logKI("I'm doing nothing.");
		}
		
		else if (gf.getPlayer().hasPlayerstate(Playerstate.END_TURN))
		{
			logKI("End this shit");
			clientSent.endTurn(gameID);
		}
		
		else{
            log.fatal("no playerstate found for KI");
        }
	}
    
    @Override
    public void handleDiceResult(JSONObject jsonObject) {
    }
    
    @Override
    public void handleMoved(JSONObject jsonObject) {
        // maybe interpret other players moves or try to block other players
        // but that is very high KI, so --> skip
    }
    
    /**
     * reaction to the shown poolcards
     * @param jsonObject the incomming object
     * @author Jonas
     */
    @Override
    public void handlePoolcards(JSONObject jsonObject) {
        String[] cards = null;
        if(jsonObject.getJSONArray(CARDS) != null)
        {
        	cards = new String[jsonObject.getJSONArray(CARDS).length()];
        	for(int i = 0; i < jsonObject.getJSONArray(CARDS).length(); i++)
        	{
        		cards[i] = jsonObject.getJSONArray(CARDS).optString(i);
        	}
        
        	for(int i = 0; i < cards.length; i++)
            {
                ki.getSaveAgent().deleteShownCard(getGameID(jsonObject), cards[i]);
            }
        }
    }
    
    /**
     * set the statement in the DisproveAgent
     * @author Jonas
     */
    @Override
    public void handleSuspicion(JSONObject jsonObject) {
        // maybe interpret other players suspicions
        // but high KI, so --> skip
        ki.getDisproveAgent(getGameID(jsonObject)).setPerson(jsonObject.getJSONObject(STATEMENT).getString(PERSON));
        ki.getDisproveAgent(getGameID(jsonObject)).setRoom(jsonObject.getJSONObject(STATEMENT).getString(ROOM));
        ki.getDisproveAgent(getGameID(jsonObject)).setWeapon(jsonObject.getJSONObject(STATEMENT).getString(WEAPON));
    }
    
    /**
     * 
     * @param jsonObject
     */
    @Override
    public void handleDisproved(JSONObject jsonObject) {
        if (jsonObject.length() == 4) {
            ki.getSaveAgent().deleteShownCard(getGameID(jsonObject), jsonObject.getString(CARD));
        }
        // second level KI - interpret disproves of other players suspicion
    }
    
    @Override
    public void handleNoDisprove(JSONObject jsonObject) {
        if(getGamefield(jsonObject).getCurrentPlayer().equals(ki.global.getMyNick()))
        {
        	handleStateupdate(jsonObject);
        }
        // second level KI - interpret disproves of other players suspicion
    }
    
    @Override
    public void handleWrongAccusation(JSONObject jsonObject) {
        // second level KI - interpret wrong accusation of other player --> min one card was wrong
    }
    
    /**
     * Helper to get gamefield shorter
     * @param jsonObject jsonobject containing gameId
     * @return reference to according gamefield
     * @author Ludwig
     */
    private Gamefield getGamefield(JSONObject jsonObject) {
        if (!jsonObject.has(GAMEID)) {
            log.fatal("no gamefield found");
        }
        return global.getGamefield(jsonObject.getInt(GAMEID));
    }
    
    /**
     * Helper to get gameID
     * @param jsonObject jsonobject containing gameId
     * @return gameID of according jsonobject
     * @author Ludwig
     */
    private int getGameID(JSONObject jsonObject) {
        if (!jsonObject.has(GAMEID)) {
            log.fatal("no gameID found");
            return 0;
        }
        return jsonObject.getInt(GAMEID);
    }
}
