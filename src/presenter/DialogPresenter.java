package presenter;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.scene.input.MouseEvent;
import language.LanguageManager;

/**
 * Provides the methods for opening dialogs for information or error messages
 * @author Roxanna
 */
public class DialogPresenter {
	
	/**
	 * Opens a dialog if no color is picked
	 * @param event
	 */
	public void noColorCreateHandle(MouseEvent event){
		//String noColorTitle;
		
		//String noColorTxt;
		Alert noColorAlert = new Alert(AlertType.ERROR);
		noColorAlert.setTitle(LanguageManager.getString("noColorTitle"));
		noColorAlert.setContentText(/*LanguageManager.getString("noColorTxt")*/"Um das Spiel zu starten musst du eine Farbe auswählen.");
		
		//for styling. must stand before showAndWait();
		DialogPane dialogPane = noColorAlert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		
		noColorAlert.showAndWait();
	}
	
	/**
	 * Opens a dialog if client login process fails
	 */
	public void handleWrongIP(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(LanguageManager.getString("wrongIPTitle"));
		alert.setContentText(LanguageManager.getString("wrongIPTxt"));

		//for styling. must stand before showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");
		
		alert.showAndWait();
	}
	
	/**
	 * Opens a dialog with a custom message
	 * @param message that shall be printed on the dialog
	 */
	public void handleErrorMessage(String message){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Oops...");
        alert.setContentText(message);

        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	/**
	 * Opens a dialog if a chosen nickname is already taken
	 * @param clientName that is taken
	 */
	public void handleDoubleClient(String clientName){
		Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(LanguageManager.getString("doubleClientTitle"));
        alert.setContentText(LanguageManager.getString("doubleClientTxt1") + clientName + LanguageManager.getString("doubleClientTxt2") );

        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	public void handleFalseAnswerNoDisprove(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(LanguageManager.getString("falseAnswerTitle"));
		alert.setContentText(LanguageManager.getString("falseAnswerTxt"));

		//for styling. must stand before showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");

		alert.showAndWait();
	}
	
	public void handleFalseCardDisprove(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(LanguageManager.getString("falseCardTitle"));
		alert.setContentText(LanguageManager.getString("falseCardTxt"));

		//for styling. must stand before showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");

		alert.showAndWait();
	}
	
	public void handleFalseAnswerYesDisprove(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(LanguageManager.getString("falseAnswerTitle"));
		alert.setContentText(LanguageManager.getString("falseAnswerYesTxt"));

		//for styling. must stand before showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");

		alert.showAndWait();
	}
	
	public void handleKeineGueltigeVerdaechtigung(){
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(LanguageManager.getString("falseAnswerTitle"));
		alert.setContentText(LanguageManager.getString("noValidSuspicionTxt"));

		//for styling. must stand before showAndWait();
		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
		dialogPane.getStyleClass().add("dialog");

		alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing who disproved own suspicion with which card
	 * @param nick
	 * @param card
	 */
	public void handleOwnDisproved(String nick, String card){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("ownDisprovedTitle"));
        alert.setContentText(LanguageManager.getString("ownDisprovedTxt1") + nick + LanguageManager.getString("ownDisprovedTxt2") + card);
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing who disproved
	 * @param nick
	 */
	public void handleDisproveFromOther(String nick){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("ownDisprovedTitle"));
        alert.setContentText(LanguageManager.getString("disproveFromOtherTxt1") + nick + LanguageManager.getString("disproveFromOtherTxt2"));
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing that own accusation was wrong.
	 * @param person
	 * @param weapon
	 * @param room
	 */
	public void handleOwnWrongAccusation(String person, String weapon, String room){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("ownWrongAccusationTitle"));
        alert.setContentText(LanguageManager.getString("ownWrongAccusationTxt")
                + person
                + ", " + weapon
                + ", " + room);
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing that accusation from someone was wrong.
	 * @param nick
	 * @param person
	 * @param weapon
	 * @param room
	 */
	public void handleOtherWrongAccusation(String nick, String person, String weapon, String room){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("ownWrongAccusationTitle"));
        alert.setContentText(LanguageManager.getString("otherWrongAccusationTxt1")
        		+ nick + LanguageManager.getString("otherWrongAccusationTxt2")
                + nick + LanguageManager.getString("otherWrongAccusationTxt3")
                + person
                + ", " + weapon
                + ", " + room);
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait(); 
	}
	
	/**
	 * Opens a dialog showing that player has won and the result.
	 * @param person
	 * @param weapon
	 * @param room
	 */
	public void handleGameEndedYouWon(String person, String weapon, String room){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("gameEndedYouWonTitle"));
        alert.setContentText(LanguageManager.getString("gameEndedYouWonTxt")
                + person
                + ", " + weapon
                + ", " + room
                );
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing who won and the result.
	 * @param nick
	 * @param person
	 * @param weapon
	 * @param room
	 */
	public void handleGameEndedOtherWon(String nick, String person, String weapon, String room){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle(LanguageManager.getString("gameEndedYouWonTitle"));
		alert.setContentText(LanguageManager.getString("gameEndedOtherWonTxt1") + nick + LanguageManager.getString("gameEndedOtherWonTxt2")
				+ person
				+ ", " + weapon
				+ ", " + room
				);
		
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
		
		alert.showAndWait();
	}
	
	/**
	 * Opens a dialog showing that there is no winner and the result.
	 * @param person
	 * @param weapon
	 * @param room
	 */
	public void handleGameEndedNoWinner(String person, String weapon, String room){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(LanguageManager.getString("gameEndedNoWinnerTitle"));
        alert.setContentText(LanguageManager.getString("gameEndedNoWinnerTxt")
                        + person
                        + ", " + weapon
                        + ", " + room
        );
        
        //for styling. must stand before showAndWait();
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("../main/Style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        
        alert.showAndWait();
	}

}
