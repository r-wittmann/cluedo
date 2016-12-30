package loginView;

/**
 * class AnleitungsView
 * contains the game instructions and a button to go back to menu
 * 
 * @author Maurice
 */

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import language.LanguageManager;

public class AnleitungsView extends StackPane {
	
	public Button menu = new Button();
	private TextArea anleitung;
	private TextArea clientAnleitung;
	private TextArea kiAnleitung;
	
	public AnleitungsView(){
		initStyle();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	
	private void initStyle(){
		
		this.setStyle("-fx-background-color:BLACK");
		
		//set label and focus of buttons
		Label cluedo = new Label("CLUEDO");
		cluedo.setTextFill(Color.DARKRED);
		cluedo.setFont(Font.font("Verdana",FontPosture.ITALIC,100));
		cluedo.setTranslateY(-230);
		
		//set size of button
		menu.textProperty().bind(LanguageManager.getStringProperty("back"));
		menu.setAlignment(Pos.CENTER);
		menu.setMinSize(180, 30);
		menu.setMaxSize(180, 30);
		menu.setTranslateY(200);
		
		//TextArea für Clientanleitung
		clientAnleitung = new TextArea();
		setClientAnleitung();
		
		//TextArea für Anleitung
		anleitung = new TextArea();
		setAnleitung();
		
		//TextArea für KIAnleitung
		kiAnleitung = new TextArea();
		setKIAnleitung();
		
		//TabPane erstellen
		TabPane tabPane = new TabPane();
		tabPane.setMaxWidth(700);
		tabPane.setMaxHeight(320);
		
		//Anleitungstab erstellen
		Tab anleitungT = new Tab();
		anleitungT.setText("Spielanleitung");
		anleitungT.setContent(anleitung);
		anleitungT.setClosable(false);
		
		//Clientanleitungstab erstellen
		Tab clientAnleitungT = new Tab();
		clientAnleitungT.setText("Programmanleitung");
		clientAnleitungT.setContent(clientAnleitung);
		clientAnleitungT.setClosable(false);
		
		//KIAnleitungstab erstellen
		Tab kiAnleitungT = new Tab();
		kiAnleitungT.setText("KI-Anleitung");
		kiAnleitungT.setContent(kiAnleitung);
		kiAnleitungT.setClosable(false);
		
		tabPane.getTabs().addAll(clientAnleitungT,anleitungT,kiAnleitungT);
		
		this.getChildren().addAll(cluedo,menu,tabPane);
	}
	/**
	 * setzt die Anleitung mit entsprechenden Styles und Text
	 * @author Wittmann Rainer
	 */
	public void setAnleitung(){
		anleitung.setMaxWidth(700);
		anleitung.setMaxHeight(320);
		anleitung.setWrapText(true);
		anleitung.setEditable(false);
		anleitung.setStyle("-fx-background-color:#585858;"
				+ "-fx-border-width:0");
		
		anleitung.setText("\nSPIELANLEITUNG CLUEDO\n\n" + "Grundspiel\n\n"
			+ "Dieses Regelwerk entspricht größtenteils dem wortlaut der offziellen Anleitung von Cluedo "
			+ "(http://www.hasbro.de/manuals/14536.pdf) und darf mit freundlicher Genehmigung der Hasbro "
			+ "Deutschland GmbH für das Softwareentwicklungspraktikum online bereitgestellt werden.\n"
			+ "Für das Grundspiel im spezizierten Protokoll sind diese Regeln bindend. Sollte es zu "
			+ "Unklarheiten kommen, teilen Sie das bitte Ihrem Tutor mit.\n\n"
			+ "GRAF ERMORDET – POLIZEI VERHÖRT VERDÄCHTIGE\n\n\""
			+ "Heute führen die zuständigen Kriminalbeamten Untersuchungen über den Mord an Graf Eutin "
			+ "vor, der am letzten Freitagabend in seinem Landsitz Schloss Neubrunn tot aufgefunden wurde. "
			+ "Eine Reihe von verdächtigen Personen, die beim Grafen über das Wochenende zu Gast waren, "
			+ "sollen dabei verhört werden. Weiterhin werden einige Gegenstände, die als mögliche Tatwerkzeuge "
			+ "in Frage kommen, einer genauen Prüfung unterzogen.\n\n"
			+ "WER? WO? WIE?\n\n"
			+ "Jeder Raum des weitläufgen Schlosses wird von den Detektiven genauestens untersucht. Dabei will "
			+ "man herausfinden, welcher der Verdächtigen den Grafen in welchem Raum des Schlosses und mit "
			+ "welcher Waffe hinterrücks ermordet hat. \n\n"
			+ "DIE AUSSTATTUNG ZUR ERFÜLLUNG DIESER SCHWIERIGEN AUFGABE\n\n"
			+ "Den Detektiven steht für ihre Untersuchungen ein Karton mit den folgenden Gegenständen zur "
			+ "Verfügung: 1 Spielbrett, 6 Spielfiguren, 6 Waffen (Dolch, Leuchter, Pistole, Seil, Heizungsrohr "
			+ "und Rohrzange), 21 Karten – je eine für die 6 Verdächtigen, 6 Waffen und 9 Räume (Für das "
			+ "Schwimmbad gibt es keine Karte) –, 1 Detektiv-Notizblock, 1 Umschlag für die geheime "
			+ "Ermittlungsakte und 2 Würfel.\n\n"
			+ "1  VORBEREITUNG\n\n"
			+ "Die Detektive sehen vor sich ein Spielbrett, das die neun Räume im Schloss des Grafen Eutin zeigt. "
			+ "Jeder Detektiv übernimmt die Rolle eines Verdächtigen. Sie wählen sich vor Spielbeginn eine der 1 "
			+ "Verdächtigen-Figuren aus und setzen sie auf dem Spielbrett auf das Startfeld mit dem Namen des "
			+ "gewählten Verdächtigen. Wenn sich in dieser Spielrunde weniger als 6 Detektive um die Aufklärung "
			+ "des Mordes bemühen, dann setzen Sie die übrigen Verdächtigen auf ihre jeweiligen Startfelder. Diese "
			+ "Verdächtigen könnten nämlich ebenfalls in das Verbrechen verwickelt sein und müssen sich im Schloss "
			+ "befinden!\n\n"
			+ "DIE MÖGLICHEN TATWERKZEUGE\n\n"
			+ "Die verschiedenen Waffen werden zu Beginn des Spiels alle in das Schwimmbad gelegt.\n\n"
			+ "DIE GEHEIMAKTE IM ERMITTLUNGSUMSCHLAG\n\n"
			+ "Legen Sie den leeren Umschlag neben das Spielbrett. Nun werden die Karten in drei Gruppen sortiert: "
			+ "Verdächtige, Tatwaffen und Tatzimmer. Mischen Sie jeden Stapel gut durch und legen Sie sie mit dem "
			+ "Bild nach unten auf den Tisch. Danach nehmen Sie von jedem Stapel eine Karte ab - aber so, dass sie "
			+ "keiner der Spieler sehen kann - und stecken sie in den Umschlag. Nun haben Sie Ihre geheime "
			+ "Ermittlungsakte, die die Antworten auf die drei Fragen enthält, um die es hier geht: Wer? Wo? "
			+ "Welche Waffe?\n"
			+ "Nehmen Sie nun alle restlichen Karten zu einem Stapel zusammen und mischen Sie sie gut durch. "
			+ "Danach teilen Sie die Karten (verdeckt) gleichmäßig an alle Spieler aus, so dass jeder Spieler "
			+ "gleich viele Karten hat. Eventuell übrig gebliebene Karten werden verdeckt in das Schwimmbad "
			+ "gelegt.\n"
			+ "Die Detektive sehen sich nun ihre Karten genau an: Da sich diese Karten in Ihrer Hand befinden, können "
			+ "sie ja nicht in der geheimen Ermittlungsakte sein, oder? Dies bedeutet, dass keine Ihrer Karten an dem "
			+ "Verbrechen beteiligt war!\n\n"
			+ "SAMMELN SIE BEWEISE\n\n"
			+ "Jeder Detektiv erhält ein Blatt des Detektivnotizblocks. Damit keiner der anderen Detektive sehen "
			+ "kann, was Sie dort notieren, falten Sie den Zettel einmal: Jetzt können Sie ungesehen die "
			+ "Verdächtigen, Waffen und Tatzimmer, die Sie als Karten auf der Hand haben, mit einem Kreuz in den "
			+ "jeweiligen Feldern markieren.\n"
			+ "Fräulein Gloria, also der Spieler, der diese Figur benutzt, ist immer zuerst an der Reihe. Danach ist "
			+ "der jeweils linke Nachbar dran. Wenn Fräulein Gloria in dieser Spielrunde keinem der Spieler zugeordnet "
			+ "ist, beginnt ein zufällig ausgewählter Spieler.\n\n"
			+ "2  SO SPIELEN SIE DETEKTIV\n\n"
			+ "Bevor sie mit ihren Untersuchungen beginnen können, müssen die Detektive die folgenden Anweisungen "
			+ "gut durchlesen. So erfahren sie, wie sie sich durch das Schloss des Grafen Eutin bewegen. Während "
			+ "des Spiels müssen sich die Detektive von einem Raum des Schlosses zum nächsten bewegen, denn nur in "
			+ "den Räumen können sie ihre Untersuchungen durchführen. Versuchen Sie jedes Mal, wenn Sie an der "
			+ "Reihe sind, einen anderen Raum zu erreichen. Sie haben die Wahl: Entweder verwenden Sie die beiden "
			+ "Würfel und gehen um die gewürfelte Augenzahl weiter oder Sie verwenden einen der Geheimgänge in den "
			+ "vier Eckräumen des Schlosses.\n\n"
			+ "DIE WÜRFEL\n\n"
			+ "Würfeln Sie mit beiden Würfeln und bewegen Sie Ihre Spielfigur um die Anzahl der geworfenen Augen "
			+ "vorwärts. Sie dürfen sich vorwärts und rückwärts sowie rechts und links über die Felder des "
			+ "Schlosses bewegen. Abbiegen während eines Zuges ist erlaubt, diagonale Schritte sind dagegen "
			+ "verboten.\n"
			+ "Sie können mit Ihrer Spielfigur die Richtung während eines Zuges so oft ändern, wie Sie möchten. "
			+ "Hauptsache es wird über so viele Felder gelaufen, wie Augen auf den Würfeln sind. Felder, die mit "
			+ "anderen Spielfiguren besetzt sind, müssen umgangen werden. Sie dürfen es weder überspringen, noch "
			+ "dürfen 2 Figuren gleichzeitig auf einem Feld stehen.\n\n"
			+ "DIE GEHEIMGÄNGE\n\n"
			+ "Jeweils zwei Räume in den gegenüberliegenden Ecken des Schlosses sind durch Geheimgänge "
			+ "verbunden: Küche und Arbeitszimmer sowie Wintergarten und Salon. Sollte sich Ihre Spielfigur "
			+ "zu Beginn Ihres Zuges in einem dieser Räume befinden, so können Sie – wenn Sie möchten – ohne "
			+ "Würfeln direkt durch den Geheimgang in den diagonal gegenüberliegenden Raum gehen. Sobald Sie "
			+ "einen Raum durch einen Geheimgang betreten haben, müssen Sie dort für den Rest Ihres Zuges "
			+ "stehen bleiben. Um einen Geheimgang zu benutzen, kündigen Sie dies den anderen Mitspielern an "
			+ "und setzen Ihre Spielfigur vom einen Raum in den der gegenüberliegenden Ecke.\n\n"
			+ "BETRETEN UND VERLASSEN DER RÄUME\n\n"
			+ "Die Detektive betreten bzw. verlassen einen Raum so, indem sie sich entweder mit Hilfe der "
			+ "Würfel über die Flurfelder durch die Tür bewegen oder einen Geheimgang benutzen. Wenn Sie durch "
			+ "eine Tür gehen, können Sie das Türfeld selbst nicht als Würfelfeld zählen.\n"
			+ "Sobald Sie einen Raum betreten haben, können Sie sich nicht mehr weiterbewegen. Wenn Sie mehr "
			+ "Augenzahlen gewürfelt haben, als Sie zum Erreichen des Raums benötigt haben, dann verfallen die "
			+ "restlichen Augenzahlen. Betreten Sie das Schwimmbad, so dürfen Sie sich alle übrig gebliebenen "
			+ "Karten ansehen. Allerdings dürfen Sie in diesem Raum keinen Verdacht äußern.\n\n"
			+ "SIE ÄUSSERN EINEN VERDACHT\n\n"
			+ "Um herauszufinden, welche drei Karten sich in der geheimen Ermittlungsakte befinden, müssen Sie "
			+ "so oft wie möglich einen Raum erreichen und einen Verdacht aussprechen. Dabei erfahren Sie, welche "
			+ "Karten sich in den Händen der anderen Detektive befinden und daher nicht Teil der geheimen "
			+ "Ermittlungsakte sein können. Für das Aussprechen eines Verdachts müssen Sie Ihre Spielfigur in "
			+ "einen der Räume (ausgenommen Schwimmbad) des Schlosses bewegen.\n"
			+ "Sie sind am Zug und haben einen Raum betreten. Wählen Sie sich nun einen der sechs Verdächtigen "
			+ "sowie eine der sechs Waffen aus und legen bzw. stellen Sie beides in den Raum, in dem Sie sich "
			+ "befinden. Dadurch vermuten Sie, dass sich der Mord in diesem Raum mit dem gewählten Verdächtigen "
			+ "sowie mit der gewählten Waffe zugetragen hat.\n"
			+ "Ein Beispiel: Nehmen wir an Sie spielen die Rolle von Fräulein Gloria und Sie haben soeben den "
			+ "Salon erreicht. Zunächst wählen Sie als Verdächtigen Reverend Grün aus und bewegen ihn in "
			+ "denselben Raum. Dann wählen Sie eine Waffe, z.B. die Rohrzange, und legen Sie ebenfalls in den "
			+ "Salon. Jetzt wenden Sie sich an Ihre Mitspieler und äußern Ihren Verdacht wie folgt: 'Reverend "
			+ "Grün hat den Grafen im Salon mit der Rohrzange umgebracht.\n"
			+ "Immer dran denken: Sie müssen mit Ihrer Spielfigur selbst in dem Raum sein, über den Sie einen "
			+ "Verdacht aussprechen wollen. Bedenken Sie, dass alle sechs Verdächtigen, alle sechs Waffen sowie "
			+ "alle neun Räume an dem Verbrechen beteiligt sein könnten – es könnte sogar Ihre eigene Spielfigur "
			+ "gewesen sein. Die Spielfiguren, die für einen Verdacht in einen anderen Raum bewegt wurden, "
			+ "bleiben danach dort stehen. Wenn die Spielfiguren von einem der Detektive bewegt werden, so starten "
			+ "diese bei ihrem nächsten Zug von diesem Raum aus.\n"
			+ "Hinweis: Wenn Sie über einen Raum mehr als einmal einen Verdacht äußern möchten, dann müssen Sie "
			+ "ihn nach jedem Zug trotzdem wieder verlassen und dürfen ihn erst dann wieder betreten, wenn Sie "
			+ "das nächste Mal an der Reihe sind.\n\n"
			+ "DIE MITSPIELER BEANTWORTEN IHREN VERDACHT\n\n"
			+ "Sobald Sie einen Verdacht geäußert haben, werden Ihre Mitspieler versuchen zu beweisen, dass er "
			+ "falsch ist. Der erste Detektiv, der auf Ihren Verdacht antworten muss, ist der Spieler zu Ihrer "
			+ "linken Seite. Dieser Detektiv vergleicht die drei Elemente Ihres Verdachts mit den Karten, die er "
			+ "bzw. sie selbst auf der Hand hat und findet so heraus, ob er/sie eine Karte davon besitzt. Sollte "
			+ "dieser Detektiv eine der verdächtigten Karten haben, so muss er/sie sie Ihnen – und nur Ihnen! – "
			+ "zeigen. Hat der Detektiv mehr als eine der drei verdächtigten Karten, dann wählt er eine davon aus, "
			+ "die er Ihnen zeigt. Sollte der Detektiv keine der drei verdächtigten Karten auf der Hand haben, "
			+ "so antwortet er entsprechend und Sie dürfen sich an den nächsten Detektiv wenden, der wiederum zur "
			+ "linken Seite des ersten Detektivs sitzt.\n"
			+ "Sobald Ihnen einer der anderen Detektive eine Karte gezeigt hat wissen Sie, dass sich diese nicht "
			+ "in der geheimen Ermittlungsakte befinden kann und daher nicht an dem Verbrechen beteiligt ist. Ihr "
			+ "Verdacht hat sich damit als falsch herausgestellt und Sie können Ihr neues Wissen, d.h. welche "
			+ "Karte der andere Detektiv Ihnen gezeigt hat, auf Ihrem Notizzettel notieren.\n"
			+ "Einige Detektive finden es hilfreich, sich gleich die Initialen des Detektivs dazu zu notieren, "
			+ "der Ihnen diese Karte gezeigt hat. Wenn Ihnen keiner der Detektive eine der drei verdächtigten "
			+ "Karten zeigen konnte, ist es gut möglich, dass es sich bei Ihrem Verdacht um die Karten in der "
			+ "Ermittlungsakte handelt. Sollten Sie sich ganz sicher sein, auf die drei verdächtigen Karten "
			+ "gestoßen zu sein, können Sie nun Anklage erheben.\n\n"
			+ "4 SIE ERHEBEN ANKLAGE\n\n"
			+ "Sie sind sich also ganz sicher, die drei Karten herausgefunden zu haben, die in der geheimen "
			+ "Ermittlungsakte liegen. Dies bedeutet, dass Sie in Ihrem Zug Anklage erheben können. Geben "
			+ "Sie dazu Ihren Mitspielern die drei verdächtigen Mordelemente, von denen Sie glauben, dass sie "
			+ "es waren, bekannt. Und das geht so: Sie wenden sich an Ihre Mitspieler und sagen: 'Ich beschuldige "
			+ "den/die (verdächtige Person), den Mord im (verdächtiger Raum) mit (verdächtige Waffe) begangen zu "
			+ "haben.' Danach dürfen Sie in die geheime Ermittlungsakte sehen – aber so dass es keiner der anderen "
			+ "Detektive sehen kann – und sich die drei Karten ansehen. Denken Sie dran: Während eines Spiels "
			+ "darf jeder Detektiv nur einmal Anklage erheben.\n\n"
			+ "DIE ANKLAGE WAR FALSCH!\n\n"
			+ "Sollte sich mindestens eine der drei angeklagten Karten nicht in der Ermittlungsakte befinden, "
			+ "dann war Ihre Anklage leider falsch. Nun stecken Sie die drei Karten wieder in den Umschlag zurück "
			+ "– ohne dass die anderen Detektive einen Blick auf die gesuchten Karten werfen können! Nach einer "
			+ "falschen Anklage dürfen Sie sich nicht mehr weiter durch die Räume des Schlosses bewegen und auch "
			+ "keinen Verdacht mehr aussprechen bzw. Anklage erheben. Dies heißt leider, dass Sie nicht mehr "
			+ "gewinnen können. Sie beteiligen sich jedoch weiter an dieser Spielrunde, indem Sie den anderen "
			+ "Detektiven Ihre Karten zeigen, sobald eine davon in einem Verdacht angesprochen wird. Die anderen "
			+ "Detektive dürfen Ihre Spielfigur weiterhin in einen anderen Raum bewegen, wenn er dort für einen "
			+ "Verdacht benötigt werden sollte.\n\n"
			+ "LÖSEN SIE DEN FALL!\n\n"
			+ "Um das Verbrechen aufzuklären und so das Spiel zu gewinnen, muss die Anklage eines Detektivs "
			+ "perfekt sein: D.h. alle drei Mordelemente Ihrer Anklage müssen sich in der geheimen "
			+ "Ermittlungsakte befinden. Sollte dies der Fall sein, nehmen Sie die drei Karten zum Beweis "
			+ "für Ihre Mitspieler aus dem Aktenumschlag und legen Sie o en auf den Tisch. Sie haben das "
			+ "Rätsel gelöst!\n\n"
			+ "HILFREICHE TIPPS FÜR ERFOLGREICHE ERMITTLUNGEN\n\n"
			+ "Die folgenden Hinweise werden schlauen Detektiven dabei helfen, dem Verbrechen auf die Spur zu kommen:\n"
			+ "1.  Wenn Sie anfänglich nach allgemeinen Hinweisen suchen, fragen Sie bei Ihren Verdächtigungen "
			+ "nach Verdächtigen, Räumen und Waffen, die Sie selbst nicht auf der Hand haben.\n"
			+ "2.  Sollten Sie versuchen, eine bestimmte Person, einen Raum oder eine Waffe von Ihren "
			+ "Vermutungen auszuschließen, so könnten Sie z.B. bei einem Verdacht eine Waffe und einen Raum "
			+ "nennen, die Sie selbst auf der Hand haben. Wenn Ihnen nun keiner der anderen Detektive die "
			+ "verdächtigte Person zeigen kann, können Sie ziemlich sicher sein, dass es sich bei diesem"
			+ " Verdächtigen um den Täter in der geheimen Ermittlungsakte handelt. Kann Ihnen jedoch einer "
			+ "der anderen Detektive den Verdächtigen zeigen, so schließen Sie dieses Mordelement von Ihren "
			+ "Untersuchungen aus, indem Sie den dazugehörigen Namen auf Ihrem Notizzettel markieren.\n"
			+ "3.  Wenn Sie möchten, können Sie während eines Zuges zunächst einen Verdacht äußern und "
			+ "danach sofort Anklage erheben.\n"
			+ "4.  Sie können einen Verdacht aussprechen, der einen Verdächtigen oder eine Waffe enthält, "
			+ "die sich bereits in dem betretenden Raum befinden. Es ist dann nicht nötig, diese beiden "
			+ "Elemente in den verdächtigten Raum zu bewegen. Denken Sie daran: Wenn Sie eine Waffe oder "
			+ "einen Verdächtigen für einen Verdacht in einen anderen Raum bewegen, bleiben diese beiden "
			+ "Elemente nach Ende Ihres Zuges dort stehen.\n"
			+ "5.  Wenn Ihre Spielfigur für einen Verdacht in einen anderen Raum gestellt wird, haben Sie "
			+ "bei Ihrem nächsten Zug die folgenden Möglichkeiten: Bewegen Sie Ihre Figur in der üblichen "
			+ "Weise über die Flurfelder in einen anderen Raum ODER sprechen Sie sofort einen Verdacht für "
			+ "diesen Raum aus. Wenn Sie sich für den Verdacht entscheiden, können Sie in diesem Zug nicht "
			+ "mehr würfeln und mit Ihrer Spielfigur weitergehen oder einen Geheimgang benutzen.\n"
			+ "6.  In einem Raum dürfen sich gleichzeitig beliebig viele Verdächtige und Waffen befinden.\n");
	}
	/**
	 * setzt die ClientAnleitung mit entsprechenden Styles und Text
	 * @author Wittmann Rainer
	 */
	public void setClientAnleitung(){
		clientAnleitung.setMaxWidth(700);
		clientAnleitung.setMaxHeight(320);
		clientAnleitung.setWrapText(true);
		clientAnleitung.setEditable(false);
		clientAnleitung.setStyle("-fx-background-color:#585858;"
				+ "-fx-border-width:0");
		
		clientAnleitung.setText("\nUm eine Runde Cluedo spielen zu können, musst du zuerst einen Server starten "
				+ "oder dich an einem bestehenden Server anmelden. Um einen Server zu starten musst du einfach "
				+ "nur auf den Server-Button drücken. Um dich an einem bestehenden Server anzumelden, klicke "
				+ "auf Client und wähle einen, der in der Tabelle angezeigten Server aus, gib die IP-Adresse "
				+ "und den Port in die Eingabemaske mit deinem Namen und deiner Gruppe ein und drücke auf "
				+ "Submit. Wenn du die Default-Informationen in der Maske lässt, meldest du dich am Testserver "
				+ "des SEP an.\n"
				+ "Sobald du in der Serverlobby angekommen bist stehen dir mehrere Optionen zur Verfügung:\n\n"
				+ "1. Chat:\n"
				+ "Im Chat-Fenster auf der rechten Seite kannst du dich mit allen anderen am Server angemeldeten "
				+ "Clients unterhalten, Nachrichten an ein Spiel schicken oder private Nachrichten an andere "
				+ "Spieler schicken. Dazu wählst du einfach aus, an wen du die Nachricht schicken willst.\n\n"
				+ "2. Spiel erstellen:\n"
				+ "Um ein eigenes Spiel zu erstellen, wählst du im unteren der beiden Auswahlbereichen eine "
				+ "Farbe aus und drückst auf „Spiel erstellen“. Um zwei weitere Spieler zu finden, mit denen "
				+ "du das Spiel starten darfst, kannst du die oben beschriebene Chat-Funktion nutzen.\n\n"
				+ "3. Spiel beitreten:\n"
				+ "Einem bestehenden Spiel kannst du sowohl als Spieler, als auch als Zuschauer beitreten. "
				+ "Wähle dazu einfach ein Spiel aus, wähle eine der übrigen Farben und drücke auf „Spiel "
				+ "beitreten“. Dies geht natürlich nur, solange das Spiel noch nicht gestartet wurde. Um dich "
				+ "als Zuschauer anzumelden gelten diese Beschränkungen nicht. Du musst keine Farbe wählen und "
				+ "das Spiel kann auch schon in einem fortgeschrittenen Stadium sein. Einfach Spiel auswählen "
				+ "und auf „Zuschauen“ klicken\n\n"
				+ "Die Spielregeln findest du im nächsten Tab. Du wirst so durch das Spiel geführt, dass du "
				+ "nichts falsch machen kannst. Viel Spaß an unserem Spiel.");
	}
	
	/**
	 * setzt die KIAnleitung mit entsprechenden Styles und Text
	 * @author Wittmann Rainer
	 */
	public void setKIAnleitung(){
		kiAnleitung.setMaxWidth(700);
		kiAnleitung.setMaxHeight(320);
		kiAnleitung.setWrapText(true);
		kiAnleitung.setEditable(false);
		kiAnleitung.setStyle("-fx-background-color:#585858;"
				+ "-fx-border-width:0");
		kiAnleitung.setText("\n" + "Unsere KI wird über den „Dr.roBOTo rufen“ Knopf erstellt und kann über "
				+ "private Chatnachrichten gesteuert werden. Die KI verbindet sich auf Knopfdruck automatisch "
				+ "mit dem Server und wartet darauf Befehle zu bekommen. Folgende Befehle stehen zur "
				+ "Verfügung:\n\n"
				+ "create:\n"
				+ "KI erstellt ein Spiel mit einer zufällig gewählten Farbe.\n\n"
				+ "connect 'GameID':\n"
				+ "KI tritt dem Spiel mit der GameID bei und wählt eine zufällige Farbe.\n\n"
				+ "start 'GameID':\n"
				+ "Spiel mit der GameID wird gestartet.\n\n"
				+ "kill:\n"
				+ "Die KI meldet sich vom Server ab und beendet sich selbst.\n\n"
				+ "ping:\n"
				+ "Die KI antwortet mit „pong“. Viel Spaß beim Ping-Pong spielen :).\n\n"
				+ "help:\n"
				+ "Die verfügbaren Befehle können über die help-Funktion nochmals angefordert werden.\n");
	}
}
