package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
/*
c'est le controller qui permet de voire le menu de démarage
@author Matthieu Lamidon
@version
@since
*/
public class menu_de_demarage_controller {
    private Stage primaryStage;

    @FXML
    ImageView img;

    @FXML
    private void nouvellePartie() {
        System.out.println("nouvelle partie");
    }
    @FXML
    private void reprendre() throws SQLException, IOException {
        //test pour vérifier si le chemin d'accès est bien correct
        URL fxmlLocation = HelloApplication.class.getResource("/fr/eseo/gaia_projet_java/map/map.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found: /fr/eseo/gaia_projet_java/map/map.fxml");
        }

        // On récupère les données du joueur dans la base de donnée
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        Joueur joueur =  daoUserMariaDB.readLectureJoueur();

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        Map_controller c = new Map_controller(primaryStage, joueur);
        fxmlLoader.setController(c);
        Parent root = fxmlLoader.load();
        c.joueurX = joueur.getPosition().get(0);
        c.joueurY = joueur.getPosition().get(1);
        c.defPositionInitiale();
        Scene scene = new Scene(root);

        c.setScene(scene);

        root.setOnMouseClicked(event -> root.requestFocus());
        System.out.println("Focus requis au lancement");

        primaryStage.setTitle("Projet Gaia");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        root.requestFocus();
    }
    @FXML
    private void credits() throws SQLException, IOException {
        System.out.println("Credits");
    }



    public menu_de_demarage_controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }


    public void initialize(){
        Image gameover = new Image("fr/eseo/gaia_projet_java/resource_menu/menu_de_demarage.png");
        img.setImage(gameover);
    }

}
