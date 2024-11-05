package fr.eseo.gaia_projet_java;

import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, SQLException {
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        databaseInitializer.run();
        //DatabaseInitializer databaseInitializer = new DatabaseInitializer();
       // databaseInitializer.run();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}