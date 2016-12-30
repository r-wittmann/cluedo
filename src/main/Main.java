package main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import language.LanguageManager;
import loginView.GlobalLoginView;
import presenter.LoginPresenter;

public class Main extends Application {

	private static final Logger log = LogManager.getLogger(Main.class);

	@Override
	public void start(Stage primaryStage) throws Exception {

		new LanguageManager(); //Muss einmal initialisiert werden, keine Referenz benötigt

		//LanguageManager.setLanguage(SupportedLanguages.en);
//		GlobalLoginView globalLoginView = new GlobalLoginView(globalModel);
		GlobalLoginView globalLoginView = new GlobalLoginView();

//		SelectSCView selectSCView = new SelectSCView();
//		StartView startView = new StartView();
//		ClientLoginView clientLoginView = new ClientLoginView();

//		LoginPresenter presenter = new LoginPresenter(globalLoginView,globalModel);
		LoginPresenter presenter = new LoginPresenter(globalLoginView);

		Scene scene = new Scene(globalLoginView);

		scene.getStylesheets().clear();
		scene.getStylesheets().add(this.getClass().getResource("Style.css").toExternalForm());

		primaryStage.setScene(scene);
		primaryStage.setTitle("Cluedo - Muffige Motten");
		primaryStage.setMaxHeight(675);
		primaryStage.setMinHeight(675);
		primaryStage.setMinWidth(1000);
		primaryStage.setMaxWidth(1000);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent e) {
				if (presenter.getClientSent() != null) {
					log.info("disconnect from server");
					presenter.getClientSent().disconnect();
				}

				log.info("kill program");
				// kill javafx app
				Platform.exit();
				// kill program
				System.exit(0);
			}
		});

		globalLoginView.selectSCView.server.setOnMouseClicked(presenter::handleServer);
		globalLoginView.selectSCView.client.setOnMouseClicked(presenter::handleClient);
		globalLoginView.clientLoginView.submit.setOnMouseClicked(presenter::handleSubmit);
		globalLoginView.lobbyView.createBtn.setOnMouseClicked(presenter::handleCreate);
		globalLoginView.lobbyView.chatView.send.setOnMouseClicked(presenter::handleSend);
		globalLoginView.lobbyView.chatView.eingabe.setOnKeyReleased(presenter::handleEnterChat);
		globalLoginView.startView.exit.setOnMouseClicked(presenter::handleExit);
		globalLoginView.startView.startButton.setOnMouseClicked(presenter::handleStart);
		globalLoginView.startView.anleitung.setOnMouseClicked(presenter::handleAnleitung);
		globalLoginView.startView.credits.setOnMouseClicked(presenter::handleCredits);
		globalLoginView.startView.language.setOnAction(presenter::handleLanguage);
		globalLoginView.anleitungsView.menu.setOnMouseClicked(presenter::handleAnleitungExit);
		globalLoginView.creditView.menu.setOnMouseClicked(presenter::handleCreditsExit);
		globalLoginView.lobbyView.spectator.setOnMouseClicked(presenter::handleSpectator);
		globalLoginView.lobbyView.joinBtn.setOnMouseClicked(presenter::handleJoin);
		globalLoginView.clientLoginView.nametxt.setOnKeyReleased(presenter::handleEnterLogin);
		globalLoginView.clientLoginView.grouptxt.setOnKeyReleased(presenter::handleEnterLogin);
		globalLoginView.clientLoginView.iptxt.setOnKeyReleased(presenter::handleEnterLogin);
		globalLoginView.clientLoginView.porttxt.setOnKeyReleased(presenter::handleEnterLogin);
		globalLoginView.lobbyView.colorbox.setOnMouseClicked(presenter::handleDropDownColor);
		globalLoginView.lobbyView.chatView.dropdown.setOnMouseClicked(presenter::handleChatDropdown);
		globalLoginView.selectSCView.help.setOnMouseClicked(presenter::handleHelp);
		globalLoginView.clientLoginView.help.setOnMouseClicked(presenter::handleHelp);
		globalLoginView.lobbyView.help.setOnMouseClicked(presenter::handleHelp);
		globalLoginView.lobbyView.KI.setOnMouseClicked(presenter::handleKI);

//		try {
//			MediaPlayer media = new MediaPlayer(new Media(new File("resources/music/startMusic.mp3").toURI().toString()));
//			media.setCycleCount(MediaPlayer.INDEFINITE);
//			media.setVolume(0.2);
//			media.play();
//		} catch (Exception e){ // catching all exceptions is bad practice!
//			log.error(e);
//		}

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
