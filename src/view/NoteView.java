package view;

import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import language.LanguageManager;
import model.Gamefield;

/**
 * class contains notepad to write down game events
 * 
 * @author Maurice
 *
 */

public class NoteView extends GridPane {
	private Gamefield model;
	
	public NoteView(Gamefield model){
		this.model = model;
		initStyle();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle(){
		
		//set fieldsizes and padding
		this.setMaxSize(648,675);
		this.setMinSize(648,675);
		this.setVgap(5);
		this.setHgap(5);
		this.setPadding(new Insets(30,15,15,15));
		this.setStyle("-fx-background-color:#585858;"
				+ "-fx-background-radius:3px;"
				+ "-fx-border-color:DARKRED;"
				+ "-fx-border-width:10px;"
				+ "-fx-border-radius:3px");
		this.setId("View");
		
		//Labels for murders
		Label murder = new Label();
		murder.textProperty().bind(LanguageManager.getStringProperty("murderer"));
		Label fg  = new Label();
		fg.textProperty().bind(LanguageManager.getStringProperty("gloria"));
		Label ovg = new Label();
		ovg.textProperty().bind(LanguageManager.getStringProperty("gatow"));
		Label fw  = new Label();
		fw.textProperty().bind(LanguageManager.getStringProperty("mrswhite"));
		Label rg  = new Label();
		rg.textProperty().bind(LanguageManager.getStringProperty("revgreen"));
		Label bvp = new Label();
		bvp.textProperty().bind(LanguageManager.getStringProperty("porz"));
		Label pb  = new Label();
		pb.textProperty().bind(LanguageManager.getStringProperty("bloom"));
		
		ovg.setMinWidth(100);
		
		murder.setTextFill(Color.WHITE);
		fg.setTextFill(Color.WHITE);
		ovg.setTextFill(Color.WHITE);
		fw.setTextFill(Color.WHITE);
		rg.setTextFill(Color.WHITE);
		bvp.setTextFill(Color.WHITE);
		pb.setTextFill(Color.WHITE);
		
		//set font of murderlabel
		murder.setFont(Font.font("Helvetica",FontWeight.BOLD,15));
				
		//Labels for weapons
		Label weapons     = new Label();
		weapons.textProperty().bind(LanguageManager.getStringProperty("weapons"));
		Label dagger      = new Label();
		dagger.textProperty().bind(LanguageManager.getStringProperty("dagger"));
		Label candlestick = new Label();
		candlestick.textProperty().bind(LanguageManager.getStringProperty("candlestick"));
		Label revolver    = new Label();
		revolver.textProperty().bind(LanguageManager.getStringProperty("gun"));
		Label rope        = new Label();
		rope.textProperty().bind(LanguageManager.getStringProperty("rope"));
		Label pipe        = new Label();
		pipe.textProperty().bind(LanguageManager.getStringProperty("pipe"));
		Label spanner     = new Label();
		spanner.textProperty().bind(LanguageManager.getStringProperty("spanner"));
		
		weapons.setTextFill(Color.WHITE);
		dagger.setTextFill(Color.WHITE);
		candlestick.setTextFill(Color.WHITE);
		revolver.setTextFill(Color.WHITE);
		rope.setTextFill(Color.WHITE);
		pipe.setTextFill(Color.WHITE);
		spanner.setTextFill(Color.WHITE);
		
		weapons.setTextFill(Color.WHITE);
		dagger.setTextFill(Color.WHITE);
		candlestick.setTextFill(Color.WHITE);
		revolver.setTextFill(Color.WHITE);
		rope.setTextFill(Color.WHITE);
		pipe.setTextFill(Color.WHITE);
		spanner.setTextFill(Color.WHITE);
		
		//set font of weaponlabel
		weapons.setFont(Font.font("Helvetica",FontWeight.BOLD,15));
				
		//Labels for rooms
		Label rooms        = new Label();
		rooms.textProperty().bind(LanguageManager.getStringProperty("rooms"));
		Label hall         = new Label();
		hall.textProperty().bind(LanguageManager.getStringProperty("hall"));
		Label lounge       = new Label();
		lounge.textProperty().bind(LanguageManager.getStringProperty("lounge"));
		Label diningroom   = new Label();
		diningroom.textProperty().bind(LanguageManager.getStringProperty("diningroom"));
		Label kitchen      = new Label();
		kitchen.textProperty().bind(LanguageManager.getStringProperty("kitchen"));
		Label ballroom     = new Label();
		ballroom.textProperty().bind(LanguageManager.getStringProperty("musicroom"));
		Label conservatory = new Label();
		conservatory.textProperty().bind(LanguageManager.getStringProperty("conservatory"));
		Label billiard     = new Label();
		billiard.textProperty().bind(LanguageManager.getStringProperty("chessroom"));
		Label library      = new Label();
		library.textProperty().bind(LanguageManager.getStringProperty("library"));
		Label study        = new Label();
		study.textProperty().bind(LanguageManager.getStringProperty("study"));
		
		rooms.setTextFill(Color.WHITE);
		hall.setTextFill(Color.WHITE);
		lounge.setTextFill(Color.WHITE);
		diningroom.setTextFill(Color.WHITE);
		kitchen.setTextFill(Color.WHITE);
		ballroom.setTextFill(Color.WHITE);
		conservatory.setTextFill(Color.WHITE);
		billiard.setTextFill(Color.WHITE);
		library.setTextFill(Color.WHITE);
		study.setTextFill(Color.WHITE);

		//set font of roomlabel
		rooms.setFont(Font.font("Helvetica",FontWeight.BOLD,15));
		
		//set Textareas
		TextArea murderTxt = new TextArea();
		murderTxt.setMaxWidth(300);
		murderTxt.setMinWidth(300);
		murderTxt.setMaxHeight(130);
		TextArea weaponTxt = new TextArea();
		weaponTxt.setMaxWidth(300);
		weaponTxt.setMinWidth(300);
		weaponTxt.setMaxHeight(130);
		TextArea roomTxt = new TextArea();
		roomTxt.setMaxWidth(300);
		roomTxt.setMinWidth(300);
		
		
		//set position of murderlabels
		GridPane.setConstraints(murder, 0, 1);
		GridPane.setConstraints(fg, 0, 2);
		GridPane.setConstraints(ovg, 0, 3);
		GridPane.setConstraints(fw, 0, 4);
		GridPane.setConstraints(rg, 0, 5);
		GridPane.setConstraints(bvp,0, 6);
		GridPane.setConstraints(pb,0, 7);
		
		//set position of weaponlabels
		GridPane.setConstraints(weapons, 0, 9);
		GridPane.setConstraints(dagger, 0, 10);
		GridPane.setConstraints(candlestick, 0, 11);
		GridPane.setConstraints(revolver, 0, 12);
		GridPane.setConstraints(rope, 0, 13);
		GridPane.setConstraints(pipe, 0, 14);
		GridPane.setConstraints(spanner, 0, 15);
				
		//set position of roomlabels
		GridPane.setConstraints(rooms, 0, 17);
		GridPane.setConstraints(hall, 0, 18);
		GridPane.setConstraints(lounge, 0, 19);
		GridPane.setConstraints(diningroom, 0, 20);
		GridPane.setConstraints(kitchen, 0, 21);
		GridPane.setConstraints(ballroom, 0, 22);
		GridPane.setConstraints(conservatory, 0, 23);
		GridPane.setConstraints(billiard, 0, 24);
		GridPane.setConstraints(library, 0, 25);
		GridPane.setConstraints(study, 0, 26);
		
		//set position of textfields
		GridPane.setConstraints(murderTxt, 7, 2, 1, 6);
		GridPane.setConstraints(weaponTxt, 7, 10,1, 6);
		GridPane.setConstraints(roomTxt,   7, 18,1, 9);
		
		//add all elements
		this.getChildren().addAll(murder,fg,ovg,fw,rg,bvp,pb,
				weapons,dagger,candlestick,revolver,rope,pipe,spanner,rooms,hall,lounge,
				diningroom,kitchen,ballroom,conservatory,billiard,library,study,murderTxt,
				weaponTxt,roomTxt);
	}
	
	/**
	 * function creates columns and checkboxes for players and show players in their chosen color
	 * @param nick
	 * @author Maurice,Jonas
	 */
	public void createColumn(){
		for(int i=0;i<this.model.getPlayerList().size();i++){
			Circle circ = new Circle(10);
			circ.setStyle("-fx-fill:" + model.getPlayerList().get(i).getCounter().getColor());
			
			CheckBox cb1 = new CheckBox();
			CheckBox cb2 = new CheckBox();
			CheckBox cb3 = new CheckBox();
			CheckBox cb4 = new CheckBox();
			CheckBox cb5 = new CheckBox();
			CheckBox cb6 = new CheckBox();
			CheckBox cb7 = new CheckBox();
			CheckBox cb8 = new CheckBox();
			CheckBox cb9 = new CheckBox();
			CheckBox cb10 = new CheckBox();
			CheckBox cb11 = new CheckBox();
			CheckBox cb12 = new CheckBox();
			CheckBox cb13 = new CheckBox();
			CheckBox cb14 = new CheckBox();
			CheckBox cb15 = new CheckBox();
			CheckBox cb16 = new CheckBox();
			CheckBox cb17 = new CheckBox();
			CheckBox cb18 = new CheckBox();
			CheckBox cb19 = new CheckBox();
			CheckBox cb20 = new CheckBox();
			CheckBox cb21 = new CheckBox();
			
			GridPane.setConstraints(cb1, i+1, 2);
			GridPane.setConstraints(cb2, i+1, 3);
			GridPane.setConstraints(cb3, i+1, 4);
			GridPane.setConstraints(cb4, i+1, 5);
			GridPane.setConstraints(cb5, i+1, 6);
			GridPane.setConstraints(cb6, i+1, 7);
			GridPane.setConstraints(cb7, i+1, 10);
			GridPane.setConstraints(cb8, i+1, 11);
			GridPane.setConstraints(cb9, i+1, 12);
			GridPane.setConstraints(cb10, i+1,13);
			GridPane.setConstraints(cb11, i+1,14);
			GridPane.setConstraints(cb12, i+1,15);
			GridPane.setConstraints(cb13, i+1, 18);
			GridPane.setConstraints(cb14, i+1, 19);
			GridPane.setConstraints(cb15, i+1, 20);
			GridPane.setConstraints(cb16, i+1, 21);
			GridPane.setConstraints(cb17, i+1, 22);
			GridPane.setConstraints(cb18, i+1, 23);
			GridPane.setConstraints(cb19, i+1, 24);
			GridPane.setConstraints(cb20, i+1, 25);
			GridPane.setConstraints(cb21, i+1, 26);
			
			GridPane.setConstraints(circ, i+1, 0);
			
			this.getChildren().add(circ);
			this.getChildren().addAll(cb1,cb2,cb3,cb4,cb5,cb6,cb7,cb8,cb9,cb10,cb11,cb12,cb13,cb14,cb15,cb16,cb17,
					cb18,cb19,cb20,cb21);
		}
	}
}

