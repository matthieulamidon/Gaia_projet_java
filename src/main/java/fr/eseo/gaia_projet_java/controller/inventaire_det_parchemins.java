package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class inventaire_det_parchemins {
    private Stage primaryStage;
    private Buff parchemin;

    @FXML
    private Button BoutonRetour;

    inventaire_det_parchemins(Stage primaryStage, Buff parchemin) {
        this.primaryStage = primaryStage;
        this.parchemin = parchemin;
    }

    @FXML
    private void RetourMenuI() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire_parchemins.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_parchemins inventaire_parchemins = new inventaire_parchemins(primaryStage);
            loader.setController(inventaire_parchemins);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
