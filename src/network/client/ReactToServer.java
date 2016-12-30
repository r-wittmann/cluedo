package network.client;
import json.org.JSONObject;
/**
 * Implements reaction of client to received json messages from server
 * e.g. used by GUI, KI 
 * @author Ludwig
 */
public interface ReactToServer {
    void handleOK(JSONObject jsonObject);
    void handleError(JSONObject jsonObject);
    void handleUDP(JSONObject jsonObject);
    void handleLoginSuccessful(JSONObject jsonObject);
    void handleUserAdded(JSONObject jsonObject);
    void handleGameCreated(JSONObject jsonObject);
    void handleGameStarted(JSONObject jsonObject);
    void handlePlayerAdded(JSONObject jsonObject);
    void handleWatcherAdded(JSONObject jsonObject);
    void handleGameinfo(JSONObject jsonObject);
    void handleGameEnded(JSONObject jsonObject);
    void handleLeftGame(JSONObject jsonObject);
    void handleGameDeleted(JSONObject jsonObject);
    void handleDisconnected(JSONObject jsonObject);
    void hanldeUserleft(JSONObject jsonObject);
    void handleChat(JSONObject jsonObject);
    void handlePlayerCards(JSONObject jsonObject);
    void handleStateupdate(JSONObject jsonObject);
    void handleDiceResult(JSONObject jsonObject);
    void handleMoved(JSONObject jsonObject);
    void handlePoolcards(JSONObject jsonObject);
    void handleSuspicion(JSONObject jsonObject);
    void handleDisproved(JSONObject jsonObject);
    void handleNoDisprove(JSONObject jsonObjec);
    void handleWrongAccusation(JSONObject jsonObject);
}
