package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.controller.Map_controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;

/**
 * Sert à lancer une version debug du programme sans le menu
 * @author Barthelemy Coutard
 * @version
 * @since
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        //test pour vérifier si le chemin d'accès est bien correct
        URL fxmlLocation = HelloApplication.class.getResource("/fr/eseo/gaia_projet_java/map/map.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found: /fr/eseo/gaia_projet_java/map/map.fxml");
        }

        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();
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
    public static void main(String[] args) {
        launch();
    }
}
