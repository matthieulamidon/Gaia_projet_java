package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import fr.eseo.gaia_projet_java.controller.Map_controller;
import fr.eseo.gaia_projet_java.controller.combat_menu_principale_controller;
import fr.eseo.gaia_projet_java.controller.inventaire_menu;
import fr.eseo.gaia_projet_java.machine_a_etat.init;
import fr.eseo.gaia_projet_java.controller.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


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


        /* J'ai enlevé ça car on ne s'en sert pas et ça gênait le focus pour le déplacement du joueur
        Transition transition = new Transition();  //On part avec l'état init
        transition.setState(new init(transition));


        scene.setOnKeyPressed(event -> transition.handleKeyPress(event.getCode()));//on active les transitions dans la scene
        */

    }
/*
public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        // Test pour vérifier si le chemin d'accès est bien correct
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
        if (fxmlLoader == null) {
            throw new IllegalStateException("FXML file not found: combat-view/combat_menu_principale.fxml");
        }

        // Initialisation de la base de données
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();

        //initialisation du combat
        DAOUserMariaDB daoUser = new DAOUserMariaDB();
        Joueur joueur = daoUser.readLectureJoueur();
        Adversaire adversaire = daoUser.readLectureAdversaire();
        InvocateurVsAdversaire combat = new InvocateurVsAdversaire(joueur,adversaire);

        // Chargement du fichier FXML
        // FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        combat_menu_principale_controller c = new combat_menu_principale_controller(primaryStage,combat);
        fxmlLoader.setController(c);

        // Configuration de la scène
        Scene scene = new Scene(fxmlLoader.load(), 450, 450);

        // Gestion de la transition d'état
        Transition transition = new Transition();  // On part avec l'état init
        transition.setState(new init(transition));  // Nom de la classe "Init" avec majuscule

        // Activation des transitions dans la scène
        scene.setOnKeyPressed(event -> transition.handleKeyPress(event.getCode()));

        // Configuration de la fenêtre principale
        primaryStage.setTitle("State Machine Example");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
*/
    public static void main(String[] args) {
        launch();
    }
}
