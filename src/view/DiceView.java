package view;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.Gamefield;

/**
 * class DiceView shows the rolled dice when you click on "Wuerfeln" in the Global View
 * the method roll() is activated by clicking on "Wuerfeln" and the view gets the rolled numbers
 * back via properties and binding.
 * 
 * @author Maurice
 *
 */

public class DiceView extends BorderPane{
    private static final Logger log = LogManager.getLogger(DiceView.class);
    
    private Gamefield model;
	
// not useful used
//	public SimpleIntegerProperty pip1 = new SimpleIntegerProperty();
//	public SimpleIntegerProperty pip2 = new SimpleIntegerProperty();
//	public SimpleIntegerProperty sum = new SimpleIntegerProperty();
	
	public DiceView(Gamefield model) {
		this.model = model;

//		pip1.bindBidirectional(model.dices.getPips1Property());
//		pip2.bindBidirectional(model.dices.getPips2Property());
//		sum.bindBidirectional(model.dices.getPipsProperty());
		
		initStyle();
		showDice();
	}
	
	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle() {
		//set size of field
		this.setMaxSize(100, 74);
		this.setMinSize(100, 74);
	}
	
	//
	public void showDice(){
        // todo use normal int
		SimpleIntegerProperty pip1 = model.dices.getPips1Property();
		SimpleIntegerProperty pip2 = model.dices.getPips2Property();
		int sum = model.dices.getPips();
        
        log.debug("view dices: pip1 " + pip1.getValue() + " pip2 " + pip2.getValue() + " sum " + sum);
        
        // todo remove ugly redundant in every statement 
		if(sum == 2){
//				File file1 = new File("resources/dices/1-1.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/1-1.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
		}else if(sum == 3){
//				File file1 = new File("resources/dices/1-2.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/1-2.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
		}else if(sum == 4){
			if(pip1.getValue() == 1 || pip2.getValue() == 1){
//				File file1 = new File("resources/dices/1-3.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/1-3.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/2-2.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/2-2.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 5){
			if(pip1.getValue() == 1 || pip2.getValue() == 1){
//				File file1 = new File("resources/dices/4-1.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/4-1.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/2-3.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/2-3.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 6){
			if(pip1.getValue() == 1 || pip2.getValue() == 1){
//				File file1 = new File("resources/dices/5-1.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/5-1.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else if(pip1.getValue() == 2 || pip2.getValue() == 2){
//				File file1 = new File("resources/dices/4-2.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/4-2.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/3-3.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/3-3.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 7){
			if(pip1.getValue() == 1 || pip2.getValue() == 1){
//				File file1 = new File("resources/dices/6-1.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/6-1.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else if(pip1.getValue() == 2 || pip2.getValue() == 2){
//				File file1 = new File("resources/dices/5-2.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/5-2.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/3-4.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/3-4.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 8){
			if(pip1.getValue() == 2 || pip2.getValue() == 2){
//				File file1 = new File("resources/dices/2-6.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/2-6.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else if(pip1.getValue() == 3 || pip2.getValue() == 3){
//				File file1 = new File("resources/dices/3-5.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/3-5.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/4-4.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/4-4.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 9){
			if(pip1.getValue() == 3 || pip2.getValue() == 3){
//				File file1 = new File("resources/dices/6-3.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/6-3.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			} else{
//				File file1 = new File("resources/dices/4-5.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/4-5.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
				}
		}else if(sum == 10){
			if(pip1.getValue() == 5 || pip2.getValue() == 5){
//				File file1 = new File("resources/dices/5-5.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/5-5.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}else{
//				File file1 = new File("resources/dices/4-6.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/4-6.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
			}
		}else if(sum == 11){
//				File file1 = new File("resources/dices/5-6.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/5-6.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
		}else{
//				File file1 = new File("resources/dices/6-6.png");
//				Image first = new Image(file1.toURI().toString());
				ImageView firstiv = new ImageView(new Image(getClass().getResourceAsStream("/dices/6-6.png")));
				firstiv.setPreserveRatio(true);
				firstiv.setTranslateX(-27);
				firstiv.setSmooth(true);
				this.getChildren().add(firstiv);
		}
	}

}
