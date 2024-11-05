package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.machine_a_etat.init;
import fr.eseo.gaia_projet_java.machine_a_etat.Transition;
import javafx.application.Application;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class HelloApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        //DatabaseInitializer databaseInitializer = new DatabaseInitializer();
       // databaseInitializer.run();

        Transition transition = new Transition();
        transition.setState(new init(transition)); // Start with Inventory state

        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);

        scene.setOnKeyPressed(event -> transition.handleKeyPress(event.getCode()));

        primaryStage.setTitle("State Machine Example");
        primaryStage.setScene(scene);
        primaryStage.show();

        //scene.requestFocus();

        /*
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load(), 320, 240);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene2);
        primaryStage.show();
        */
    }
    /*
    private void HandleOtherEvents(Transition transition) {
        if()
    }
    */
    public static void main(String[] args) {
        launch();
    }
}