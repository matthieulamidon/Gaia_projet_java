package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import fr.eseo.gaia_projet_java.controller.Transition;
import fr.eseo.gaia_projet_java.controller.combat_menu_principale_controller;
import fr.eseo.gaia_projet_java.machine_a_etat.init;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Gaia  extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        // Test pour vérifier si le chemin d'accès est bien correct
        FXMLLoader fxmlLoader = new FXMLLoader(Gaia.class.getResource("combat-view/combat_menu-principale.fxml"));
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

    public static void main(String[] args) {
        launch();
    }

}
