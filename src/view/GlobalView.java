package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Gamefield;

/**
 * class GlobalView
 * this class loads the GameView with all respectable view containt in it
 *
 * @author Wittmann Rainer
 */

public class GlobalView extends BorderPane {

    private Gamefield model;

    public GameView gameView;
    public DiceView diceView;
    public CCardView cCardView;

//    public Label txt;

    public GlobalView(Gamefield model) {
        this.model = model;
        gameView = new GameView(this.model);
        diceView = new DiceView(this.model);
        cCardView = new CCardView(this.model);
        initStyle();
    }

    /**
     * initStyle() creates all grafical elements, designs and adds them to the pane.
     * initStyle() is started from the classes constructor
     */
    private void initStyle() {

        this.setMaxSize(1000, 675);
        this.setMinSize(1000, 675);
        this.setStyle("-fx-background-color:BLACK");

        VBox vButtons = new VBox(10);
        vButtons.setMinSize(180, 190);
        vButtons.setPadding(new Insets(20, 20, 20, 20));
        vButtons.getChildren().addAll(gameView.cards, gameView.note, gameView.dice, gameView.anklage,gameView.stop);
        vButtons.setAlignment(Pos.BOTTOM_CENTER);

        HBox hBox = new HBox(10);
        hBox.setMaxWidth(312);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(vButtons, diceView);

//        txt = new Label("Hier muss noch Text angezeigt werden können!");
//        txt.setPadding(new Insets(20,20,20,20));
//        txt.setStyle("-fx-background-color:#585858;"
//        		+ "-fx-background-radius:3px;"
//        		+ "-fx-border-color:DARKRED;"
//        		+ "-fx-border-width:10px;"
//        		+ "-fx-border-radius:3px;"
//        		+ "-fx-text-fill:WHITE");

        //set Id for Style
        hBox.setId("globalHbox");
        hBox.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");

        BorderPane right = new BorderPane();
        right.setMinSize(352, 675);
        right.setMaxSize(352, 675);
        right.setPadding(new Insets(20, 20, 20, 20));
        BorderPane.setAlignment(cCardView, Pos.TOP_CENTER);
        BorderPane.setAlignment(hBox, Pos.BOTTOM_CENTER);

        right.setTop(cCardView);
//        right.setCenter(txt);
        right.setBottom(hBox);

        this.setLeft(gameView);
        this.setRight(right);
        this.setVisible(true);
    }

//    public void writeTxt(String str) {
//        txt.setText(str);
//
//    }
}
