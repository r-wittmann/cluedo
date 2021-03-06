package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LooseView extends VBox {

	public Button exit = new Button("Spiel verlassen");
	public Label erg = new Label("You lose the game!");
	public ErgebnisView ergebnis;

	public LooseView() {
		this.ergebnis = new ErgebnisView();
		initStyle();
		this.getChildren().add(erg);
		this.getChildren().add(this.ergebnis);
		this.getChildren().add(exit);
	}

	private void initStyle() {
		this.setPadding(new Insets(4,4,4,4));
		this.setMaxHeight(USE_PREF_SIZE);
		this.setMaxWidth(USE_PREF_SIZE);
		this.setSpacing(4);
		this.setId("View");
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());

		this.setVisible(false);
	}
}
