package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class inventaire_mystimons {
    private Stage primaryStage;

    public inventaire_mystimons(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button BoutonRetour;

    @FXML
    private void RetourMenuI() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_menu inventaire_menu = new inventaire_menu(primaryStage);
            loader.setController(inventaire_menu);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
