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
import java.sql.SQLException;
import java.net.URL;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();
    public void start(Stage primaryStage) throws IOException {
        //test pour vérifier si le chemin d'accès est bien correct
        URL fxmlLocation = HelloApplication.class.getResource("/fr/eseo/gaia_projet_java/inventaire/inventaire.fxml");
        if (fxmlLocation == null) {
            throw new IllegalStateException("FXML file not found: /fr/eseo/gaia_projet_java/inventaire/inventaire.fxml");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);
        //DatabaseInitializer databaseInitializer = new DatabaseInitializer();
       // databaseInitializer.run();

        inventaire_menu c = new inventaire_menu(primaryStage);

        fxmlLoader.setController(c);

        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/resources/fr.eseo.gaia_projet_java/inventaire/inventaire.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        //Transition controller = fxmlLoader.getController();

        Transition transition = new Transition();  //On part avec l'état init
        transition.setState(new init(transition));

        scene.setOnKeyPressed(event -> transition.handleKeyPress(event.getCode()));//on active les transitions dans la scene


        primaryStage.setTitle("State Machine Example");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();


    }

}