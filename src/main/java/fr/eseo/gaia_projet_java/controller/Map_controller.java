package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Map_controller {

    public Stage MapStage;

public Map_controller(Stage primaryStage) {
    this.MapStage = primaryStage;
}

@FXML
private ImageView mapView;

@FXML
private ImageView joueurView;

@FXML
private ImageView pnjView;


private double joueurX = 375.0;
private double joueurY = 50.0;
private ArrayList<Rectangle> obstacles = new ArrayList<>();
private static int IndiceDeplacement = 0;// Permet de savoir quelle image utiliser pour le joueur en déplacement
private static int IndiceMap = 0;//Permet de savoir quelle image utiliser pour la map
private int compteurDeplacement = 0; // Compte le nombre de déplacements successifs
private final int SEUIL_ALTERNANCE = 3; // Nombre de déplacements avant changement d'image
private final Set<KeyCode> touchesAppuyees = new HashSet<>();//HashSet pour permettre de rendre plus fluides les déplacements
private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> deplacementJoueur())); //Timeline permettant de rendre les mouvements bien plus fluides
private final Timeline mapTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> AlterneMapImage()));
private boolean inventaireOuvert = false; //Booléen pour éviter que l'inventaire ne s'ouvre à l'infini à cause de la timeline.


@FXML
private ArrayList<Image> Bas = new ArrayList<>();

@FXML
private ArrayList<Image> Haut = new ArrayList<>();

@FXML
private ArrayList<Image> Gauche = new ArrayList<>();

@FXML
private ArrayList<Image> Droite =  new ArrayList<>();

@FXML
private ArrayList<Image> MapImage = new ArrayList<>();


public void setScene(Scene scene) {// Fonction qui permet de gérer les inputs du clavier
    scene.setOnKeyPressed(event -> {
        touchesAppuyees.add(event.getCode());
        if (!timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    });
    scene.setOnKeyReleased(event -> {
        touchesAppuyees.remove(event.getCode());
        if (event.getCode() == KeyCode.I) {
            inventaireOuvert = false; // Autoriser une nouvelle ouverture après le relâchement de la touche
        }
        if (touchesAppuyees.isEmpty()) {
            timeline.stop();
        }
    });
}


public void initialize() {
    //On initalise la liste des obstacles
    obstacles.add(new Rectangle(0, 598, 800, 2));//pour ne pas sortir en bas
    obstacles.add(new Rectangle(0, 0, 2, 600));//pour ne pas sortir à gauche
    obstacles.add(new Rectangle(0, 0, 800, 2));//pour ne pas sortir en haut
    obstacles.add(new Rectangle(798, 0, 2, 600));//pour ne pas sortir à gauche
    obstacles.add(new Rectangle(479, 0, 128, 190));
    obstacles.add(new Rectangle(767, 0, 37, 600));//océan est
    obstacles.add(new Rectangle(0, 479, 800, 121));//océan sud
    obstacles.add(new Rectangle(257, 254, 158, 2));
    obstacles.add(new Rectangle(258, 315, 221, 31));
    obstacles.add(new Rectangle(255, 92, 2, 65));
    obstacles.add(new Rectangle(315, 92, 2, 65));
    obstacles.add(new Rectangle(477, 0, 2, 317));
    obstacles.add(new Rectangle(0, 95, 254, 28));
    obstacles.add(new Rectangle(320, 95, 95, 29));
    obstacles.add(new Rectangle(415, 95, 2, 163));
    //On initalise les listes des déplacements
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche0.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche1.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche0.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche2.png"));

    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite0.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite1.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite0.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite1.png"));

    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas0.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas1.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas0.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas2.png"));

    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut0.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut1.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut0.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut2.png"));

    //On initalise map et l'imageview du joueur
    MapImage.add(new Image("fr/eseo/gaia_projet_java/resource_map/Map0.png"));
    MapImage.add(new Image("fr/eseo/gaia_projet_java/resource_map/Map1.png"));
    mapView.setImage(MapImage.get(0));
    mapTimeline.setCycleCount(Timeline.INDEFINITE);//On lance la timeline qui permet d'alterner les images de la map
    mapTimeline.play();

    Image joueurImage = Bas.get(0);
    joueurView.setImage(joueurImage);

    pnjView.setImage(joueurImage);
}

public void deplacementJoueur(){
    double nextX = joueurX;
    double nextY = joueurY;
    boolean deplacementHaut = touchesAppuyees.contains(KeyCode.UP) || touchesAppuyees.contains(KeyCode.Z);
    boolean deplacementBas = touchesAppuyees.contains(KeyCode.DOWN) || touchesAppuyees.contains(KeyCode.S);
    boolean deplacementGauche = touchesAppuyees.contains(KeyCode.LEFT) || touchesAppuyees.contains(KeyCode.Q);
    boolean deplacementDroite = touchesAppuyees.contains(KeyCode.RIGHT) || touchesAppuyees.contains(KeyCode.D);

    if (deplacementHaut) {
        if(deplacementGauche || deplacementDroite){//si diagonale
            nextY -= 3.5;
        }
        else{
            nextY -= 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE) {
            joueurView.setImage(Haut.get(IndiceDeplacement));
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementBas) {
        if(deplacementGauche || deplacementDroite){ //si diagonale
            nextY += 3.5;
        }
        else{
            nextY += 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE) {
            joueurView.setImage(Bas.get(IndiceDeplacement));
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementGauche) {
        if(deplacementHaut || deplacementBas){//si diagonale
            nextX -= 3.5;
        }
        else{
            nextX -= 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE && !(deplacementHaut || deplacementBas)) {
            joueurView.setImage(Gauche.get(IndiceDeplacement)); // Alterner l'image
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementDroite) {
        if(deplacementHaut || deplacementBas){//si il y a une diagonale
            nextX += 3.5;
        }
        else{ //sinon
            nextX += 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE && !(deplacementHaut || deplacementBas)) {
            joueurView.setImage(Droite.get(IndiceDeplacement)); // Alterner l'image
            IndiceDeplacement = (IndiceDeplacement + 1) % 4; //
            compteurDeplacement = 0;
        }
    }
    if (touchesAppuyees.contains(KeyCode.I) && !inventaireOuvert) {
        try {
            OuvertureInventaire();
            inventaireOuvert = true; // Empêche d'ouvrir l'inventaire à nouveau
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    for (Rectangle obstacle : obstacles) {
        // Vérification si le joueur se déplace dans la zone de l'obstacle
         if (new Rectangle(nextX + 4, nextY + 25, 19, 10).intersects(obstacle.getBoundsInLocal())) {
             System.out.println("Collision detectee, deplacement annule.");
             return;
         }
        // Ne pas déplacer le joueur s'il y a collision
    }

    joueurX = nextX;
    joueurY = nextY;

    joueurView.setLayoutX(joueurX);
    joueurView.setLayoutY(joueurY);
}

    @FXML
    private void OuvertureInventaire() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire.fxml"));

            Stage inventaireStage = new Stage();
            inventaireStage.setTitle("Inventaire");
            inventaire_menu inventaire_menu = new inventaire_menu(inventaireStage);
            loader.setController(inventaire_menu);
            inventaireStage.setScene(new Scene(loader.load()));


            inventaireStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AlterneMapImage(){
        IndiceMap = (IndiceMap + 1) % 2;
        mapView.setImage(MapImage.get(IndiceMap)); // Alterner l'image
    }


}

