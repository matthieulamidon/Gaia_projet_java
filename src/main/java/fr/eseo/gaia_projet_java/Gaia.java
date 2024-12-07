package fr.eseo.gaia_projet_java;


import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.controller.menu_de_demarage_controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Gaia  extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Gaia.class.getResource("menu_de_demarage.fxml"));
        if (fxmlLoader == null) {
            throw new IllegalStateException("FXML file not found: menu_de_demarage.fxml");
        }

        // Initialisation de la base de données
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();

        // Correctement accéder au fichier audio dans le répertoire resources
        /*
        try {
            //String path = Gaia.class.getResource("fr/eseo/gaia_projet_java/musique/hellfire.mp3").toString();
            Media media = new Media("/fr/eseo/gaia_projet_java/musique/hellfire.mp3");
            MediaPlayer mediaPlayer = new MediaPlayer(media);

            // Démarrer la lecture
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);  // Optionnel : pour répéter la musique
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }
*/

        //System.out.println("Musique en cours de lecture...");

        //initialisation du combat
        //DAOUserMariaDB daoUser = new DAOUserMariaDB();
        //Joueur joueur = daoUser.readLectureJoueur();
        //Adversaire adversaire = daoUser.readLectureAdversaire(0);
        //InvocateurVsAdversaire combat = new InvocateurVsAdversaire(joueur,adversaire);

        // Chargement du fichier FXML
        // FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        menu_de_demarage_controller c = new menu_de_demarage_controller(primaryStage);
        fxmlLoader.setController(c);

        // Configuration de la scène
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);


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
