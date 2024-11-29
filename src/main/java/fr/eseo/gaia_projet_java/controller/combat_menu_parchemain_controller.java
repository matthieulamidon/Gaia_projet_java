package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import fr.eseo.gaia_projet_java.machine_a_etat.combat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.text.html.ListView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
public class combat_menu_parchemain_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;

    @FXML
    private void selectRetour() {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_principale_controller combat_menu_principale_controller = new combat_menu_principale_controller(primaryStage,combat);
            loader.setController(combat_menu_principale_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectMystimon1() {
        // Logique pour sélectionner Mystimon1
    }

    @FXML
    private void selectMystimon2() {
        // Logique pour sélectionner Mystimon2
    }

    @FXML
    private void selectMystimon3() {
        // Logique pour sélectionner Mystimon3
    }

    @FXML
    private void selectMystimon4() {
        // Logique pour sélectionner Mystimon4
    }

    @FXML
    private void selectMystimon5() {
        // Logique pour sélectionner Mystimon5
    }

    @FXML
    private void selectMystimon6() {
        // Logique pour sélectionner Mystimon6
    }

    // Cette méthode est appelée lors du clic sur la ListView
    @FXML
    private void clicPourDetails(MouseEvent event) {
        System.out.println("Vous avez cliqué sur la ListView !");
    }

    @FXML
    public void selectItemType1() {
        System.out.println("Item Type 1 sélectionné !");
    }

    @FXML
    public void selectItemType2() {
        System.out.println("Item Type 2 sélectionné !");
    }

    @FXML
    public void selectItemType3() {
        System.out.println("Item Type 3 sélectionné !");
    }

    // Constructeur avec les paramètres nécessaires
    public combat_menu_parchemain_controller(Stage primaryStage, InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }
}

