package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class inventaire_det_parchemins {
    private Stage primaryStage;
    private Joueur joueur;
    private Buff parchemin;

    @FXML
    private Button BoutonRetour;

    @FXML
    private Label labelNom;

    @FXML
    private Label labelEffet;

    @FXML
    private Label labelEfficacite;

    @FXML
    private TextArea textDescription;

    inventaire_det_parchemins(Stage primaryStage, Joueur joueur, Buff parchemin) {
        this.primaryStage = primaryStage;
        this.joueur = joueur;
        this.parchemin = parchemin;
    }

    @FXML
    private void RetourMenuI() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire_parchemins.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_parchemins inventaire_parchemins = new inventaire_parchemins(primaryStage, joueur);
            loader.setController(inventaire_parchemins);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void initialize(){
        labelNom.setText(parchemin.getNom());
        labelEffet.setText(parchemin.getEffet());
        labelEfficacite.setText(String.valueOf(parchemin.getEfficacite()));
        textDescription.setText(parchemin.getDescription());
    }

}
