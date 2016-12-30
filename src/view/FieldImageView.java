package view;

import java.util.ArrayList;

/**
 * class FielImagesView
 * this class loads all rooms as images from the resourc folder
 * and adds them to a GridPane.
 * 
 * @author Wittmann Rainer, Maurice
 */

import javafx.scene.effect.Bloom;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class FieldImageView extends GridPane{
	
	public ImageView schwimmbadiv;
	public ImageView arbeitszimmeriv;
	public ImageView eingangshalleiv;
	public ImageView bibliothekiv;
	public ImageView billiardzimmeriv;
	public ImageView wintergarteniv;
	public ImageView saloniv;
	public ImageView speisezimmeriv;
	public ImageView musikzimmeriv;
	public ImageView kuecheiv;
	public ImageView wintergarten1iv;
	public ImageView kueche1iv;
	public ImageView musikzimmer1iv;
	
	/**
	 * shadowed contains all ImageViews with an effect on them
	 */
	private ArrayList<ImageView> shadowed = new ArrayList<ImageView>();
	
	public FieldImageView(){
		initStyle();
	}

	/**
	 * initStyle() creates all grafical elements, designs and adds them to the pane.
	 * initStyle() is started from the classes constructor
	 */
	private void initStyle() {
		
//		this.setMaxSize(696,696);
		
		//set Images
//		File file1 = new File("resources/rooms/Schwimmbad.png");
//		Image schwimmbad = new Image(file1.toURI().toString());
//		schwimmbadiv = new ImageView(schwimmbad);
		schwimmbadiv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Schwimmbad.png")));
		schwimmbadiv.setFitWidth(135);
		schwimmbadiv.setFitHeight(189);
		schwimmbadiv.setPreserveRatio(false);
		schwimmbadiv.setSmooth(true);
		
//		File file2 = new File("resources/rooms/Arbeitszimmer.png");
//		Image arbeitszimmer = new Image(file2.toURI().toString());
		arbeitszimmeriv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Arbeitszimmer.png")));
		arbeitszimmeriv.setFitWidth(189);
		arbeitszimmeriv.setFitHeight(135);
		arbeitszimmeriv.setPreserveRatio(false);
		arbeitszimmeriv.setSmooth(true);
		
//		File file3 = new File("resources/rooms/Eingangshalle.png");
//		Image eingangshalle = new Image(file3.toURI().toString());
		eingangshalleiv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Eingangshalle.png")));
		eingangshalleiv.setFitWidth(216);
		eingangshalleiv.setFitHeight(189);
		eingangshalleiv.setPreserveRatio(false);
		eingangshalleiv.setSmooth(true);
		
//		File file4 = new File("resources/rooms/Bibliothek.png");
//		Image bibliothek = new Image(file4.toURI().toString());
		bibliothekiv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Bibliothek.png")));
		bibliothekiv.setFitWidth(189);
		bibliothekiv.setFitHeight(162);
		bibliothekiv.setPreserveRatio(false);
		bibliothekiv.setSmooth(true);
		
//		File file5 = new File("resources/rooms/Billiardzimmer.png");
//		Image billiardzimmer = new Image(file5.toURI().toString());
		billiardzimmeriv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Billiardzimmer.png")));
		billiardzimmeriv.setFitWidth(162);
		billiardzimmeriv.setFitHeight(162);
		billiardzimmeriv.setPreserveRatio(false);
		billiardzimmeriv.setSmooth(true);
		
//		File file6 = new File("resources/rooms/Wintergarten.png");
//		Image wintergarten = new Image(file6.toURI().toString());
		wintergarteniv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Wintergarten.png")));
		wintergarteniv.setFitWidth(189);
		wintergarteniv.setFitHeight(162);
		wintergarteniv.setPreserveRatio(false);
		wintergarteniv.setSmooth(true);
		
//		File file7 = new File("resources/rooms/Wintergarten1.png");
//		Image wintergarten1 = new Image(file7.toURI().toString());
		wintergarten1iv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Wintergarten1.png")));
		wintergarten1iv.setFitWidth(54);
		wintergarten1iv.setFitHeight(27);
		wintergarten1iv.setPreserveRatio(false);
		wintergarten1iv.setSmooth(true);
		
//		File file8 = new File("resources/rooms/Salon.png");
//		Image salon = new Image(file8.toURI().toString());
		saloniv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Salon.png")));
		saloniv.setFitWidth(189);
		saloniv.setFitHeight(189);
		saloniv.setPreserveRatio(false);
		saloniv.setSmooth(true);
		
//		File file9 = new File("resources/rooms/Speisezimmer.png");
//		Image speisezimmer = new Image(file9.toURI().toString());
		speisezimmeriv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Speisezimmer.png")));
		speisezimmeriv.setFitWidth(216);
		speisezimmeriv.setFitHeight(243);
		speisezimmeriv.setPreserveRatio(false);
		speisezimmeriv.setSmooth(true);
		
//		File file10 = new File("resources/rooms/Kueche.png");
//		Image kueche = new Image(file10.toURI().toString());
		kuecheiv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Kueche.png")));
		kuecheiv.setFitWidth(189);
		kuecheiv.setFitHeight(189);
		kuecheiv.setPreserveRatio(false);
		kuecheiv.setSmooth(true);
		
//		File file11 = new File("resources/rooms/Kueche1.png");
//		Image kueche1 = new Image(file11.toURI().toString());
		kueche1iv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Kueche1.png")));
		kueche1iv.setFitWidth(54);
		kueche1iv.setFitHeight(27);
		kueche1iv.setPreserveRatio(false);
		kueche1iv.setSmooth(true);
		
//		File file12 = new File("resources/rooms/Musikzimmer.png");
//		Image musikzimmer = new Image(file12.toURI().toString());
		musikzimmeriv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Musikzimmer.png")));
		musikzimmeriv.setFitWidth(216);
		musikzimmeriv.setFitHeight(162);
		musikzimmeriv.setPreserveRatio(false);
		musikzimmeriv.setSmooth(true);
		
//		File file13 = new File("resources/rooms/Musikzimmer1.png");
//		Image musikzimmer1 = new Image(file13.toURI().toString());
		musikzimmer1iv = new ImageView(new Image(getClass().getResourceAsStream("/rooms/Musikzimmer1.png")));
		musikzimmer1iv.setFitWidth(108);
		musikzimmer1iv.setFitHeight(54);
		musikzimmer1iv.setPreserveRatio(false);
		musikzimmer1iv.setSmooth(true);
		
		//set position of images
		GridPane.setConstraints(schwimmbadiv, 9, 8, 5, 7);
		GridPane.setConstraints(arbeitszimmeriv, 0, 0, 7, 5);
		GridPane.setConstraints(eingangshalleiv, 8, 0, 8, 7);
		GridPane.setConstraints(bibliothekiv, 0, 6, 7, 6);
		GridPane.setConstraints(billiardzimmeriv, 0, 12, 6, 6);
		GridPane.setConstraints(wintergarteniv, 0, 19, 7, 6);
		GridPane.setConstraints(wintergarten1iv, 7, 24, 2, 1);
		GridPane.setConstraints(saloniv, 17, 0, 7, 7);
		GridPane.setConstraints(speisezimmeriv, 16, 8, 8, 9);
		GridPane.setConstraints(kuecheiv, 17, 18, 7, 7);
		GridPane.setConstraints(kueche1iv, 15, 24, 2, 1);
		GridPane.setConstraints(musikzimmeriv, 8, 17, 8, 6);
		GridPane.setConstraints(musikzimmer1iv, 10, 23, 4, 2);
		
		//Rectangles zum aufspannen der Pane
		for(int i = 0; i <= 23; i++){
			for(int j = 0; j <= 24; j++){
				Rectangle rec = new Rectangle(27,27,Color.TRANSPARENT);
				GridPane.setConstraints(rec, i, j);
				this.getChildren().add(rec);
			}
		}
		
		this.getChildren().addAll(schwimmbadiv,arbeitszimmeriv,eingangshalleiv,bibliothekiv,billiardzimmeriv,
				wintergarteniv,saloniv,speisezimmeriv,kuecheiv,kueche1iv,musikzimmeriv,musikzimmer1iv,wintergarten1iv);
	}
	
	/**
	 * Sets a Bloom effect on the given ImageView 
	 * <br>and adds the ImageView to the ArrayList shadowed.
	 * @param iv The ImageView the effect shall be put on
	 * @author Roxanna
	 */
	public void setShadow(ImageView iv){
		Bloom glow = new Bloom();
		glow.setThreshold(0.1);
		iv.setEffect(glow);
		shadowed.add(iv);
	}
	
	/**
	 * reverses the effect of every ImageView in the ArrayList shadowed
	 * <br>and clears shadowed. 
	 * @author Roxanna
	 */
	public void unsetShadow(){
		for(int i = 0; i < shadowed.size(); i++){
			((Bloom)shadowed.get(i).getEffect()).setThreshold(1);
		}
		shadowed.clear();
	}
}
