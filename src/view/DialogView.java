package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DialogView extends GridPane {
	
	public Button ok = new Button("Schließen");
	public TextField dia = new TextField();
	
	public DialogView(){
		initStyle();
	}
	
	private void initStyle(){
		
		this.setMinSize(400, 150);
		this.setMaxSize(400, 150);
		this.setPadding(new Insets(15,15,15,15));
		this.setHgap(10);
		this.setVgap(10);
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.getStylesheets().clear();
		this.getStylesheets().add(this.getClass().getResource("../main/Style.css").toExternalForm());
		
		ok.setTranslateY(50);
		ok.setTranslateX(60);
		dia.setTranslateX(10);
		dia.setEditable(false);
		dia.setStyle("-fx-background-color:darkred;-fx-text-fill:white;");
		dia.setMinSize(320, 30);
		dia.setMaxSize(320, 30);
		dia.setFocusTraversable(false);
		ok.setFocusTraversable(false);

		
		this.getChildren().addAll(dia,ok);
	}
	
	/**
	 * setzt die gebrauchte Fehlermeldung
	 * @param str
	 * @author Maurice
	 */
	public void setDialog(String str){
		this.setVisible(true);
		dia.setText(str);
	}
}
