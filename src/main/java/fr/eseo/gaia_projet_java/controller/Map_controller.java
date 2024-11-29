package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Map_controller {

    private Stage primaryStage;

public Map_controller(Stage primaryStage) {
    this.primaryStage = primaryStage;;
}

@FXML
private ImageView mapView;

@FXML
private ImageView joueurView;

private double joueurX = 375.0;
private double joueurY = 50.0;
private ArrayList<Rectangle> obstacles = new ArrayList<>();


@FXML
private Image Bas = new Image("fr/eseo/gaia_projet_java/resource_map/joueur0.png");

@FXML
private Image Haut = new Image("fr/eseo/gaia_projet_java/resource_map/joueur1.png");

@FXML
private Image Gauche = new Image("fr/eseo/gaia_projet_java/resource_map/joueur2.png");

@FXML
private Image Droite = new Image("fr/eseo/gaia_projet_java/resource_map/joueur3.png");

public void setScene(Scene scene) {
    scene.setOnKeyPressed(this::deplacementJoueur);
}

public void initialize() {
    Image mapImage = new Image("fr/eseo/gaia_projet_java/resource_map/Map_test.png");
    mapView.setImage(mapImage);
    Image joueurImage = Bas;
    joueurView.setImage(joueurImage);
    //liste des obstacles
    obstacles.add(new Rectangle(0, 95, 254, 123));
    obstacles.add(new Rectangle(320, 95, 168, 28));

}

public void deplacementJoueur(KeyEvent keyEvent){
    double nextX = joueurX;
    double nextY = joueurY;
    switch (keyEvent.getCode()) {
        case UP, Z -> {
            nextY -= 5;  // Déplacement vers le haut
            joueurView.setImage(Haut); // Changer l'image pour le haut
        }
        case DOWN, S -> {
            nextY += 5; // Déplacement vers le bas
            joueurView.setImage(Bas); // Changer l'image pour le bas
        }
        case LEFT, Q -> {
            nextX -= 5; // Déplacement vers la gauche
            joueurView.setImage(Gauche); // Changer l'image pour la gauche
        }
        case RIGHT, D -> {
            nextX += 5; // Déplacement vers la droite
            joueurView.setImage(Droite); // Changer l'image pour la droit
        }
        case I -> {
            try {
                OuvertureInventaire();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        default -> {
            System.out.println("Touche non geree : " + keyEvent.getCode());
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
}

