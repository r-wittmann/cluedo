package network.client;
import json.org.JSONObject;
/**
 * Empty reaction for received json messages from server
 * Used for testing and debugging
 * @author Ludwig
 */
public class EmptyReactToServer implements ReactToServer {
    @Override
    public void handleOK(JSONObject jsonObject){}
    @Override
    public void handleError(JSONObject jsonObject){}
    @Override
    public void handleUDP(JSONObject jsonObject){}
    @Override
    public void handleLoginSuccessful(JSONObject jsonObject){}
    @Override
    public void handleUserAdded(JSONObject jsonObject){}
    @Override
    public void handleGameCreated(JSONObject jsonObject){}
    @Override
    public void handleGameStarted(JSONObject jsonObject){}
    @Override
    public void handlePlayerAdded(JSONObject jsonObject){}
    @Override
    public void handleWatcherAdded(JSONObject jsonObject){}
    @Override
    public void handleGameinfo(JSONObject jsonObject){}
    @Override
    public void handleGameEnded(JSONObject jsonObject){}
    @Override
    public void handleLeftGame(JSONObject jsonObject){}
    @Override
    public void handleGameDeleted(JSONObject jsonObject){}
    @Override
    public void handleDisconnected(JSONObject jsonObject){}
    @Override
    public void hanldeUserleft(JSONObject jsonObject){}
    @Override
    public void handleChat(JSONObject jsonObject){}
    @Override
    public void handlePlayerCards(JSONObject jsonObject){}
    @Override
    public void handleStateupdate(JSONObject jsonObject){}
    @Override
    public void handleDiceResult(JSONObject jsonObject){}
    @Override
    public void handleMoved(JSONObject jsonObject){}
    @Override
    public void handlePoolcards(JSONObject jsonObject){}
    @Override
    public void handleSuspicion(JSONObject jsonObject){}
    @Override
    public void handleDisproved(JSONObject jsonObject){}
    @Override
    public void handleNoDisprove(JSONObject jsonObjec){}
    @Override
    public void handleWrongAccusation(JSONObject jsonObject){}
}
