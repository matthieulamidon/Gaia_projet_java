package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.controller.inventaire_menu;
import fr.eseo.gaia_projet_java.machine_a_etat.init;
import fr.eseo.gaia_projet_java.controller.Transition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;


public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, SQLException {
        //test pour vérifier si le chemin d'accès est bien correct
        URL fxmlLocation = HelloApplication.class.getResource("/fr/eseo/gaia_projet_java/inventaire/inventaire.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found: /fr/eseo/gaia_projet_java/inventaire/inventaire.fxml");
        }

        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        inventaire_menu c = new inventaire_menu(primaryStage);
        fxmlLoader.setController(c);
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);


        Transition transition = new Transition();  //On part avec l'état init
        transition.setState(new init(transition));

        scene.setOnKeyPressed(event -> transition.handleKeyPress(event.getCode()));//on active les transitions dans la scene


        primaryStage.setTitle("Projet Gaia");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}