package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class combat_menu_principale_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;


    @FXML
    private ImageView attaque;

    @FXML
    private ImageView fuite;

    @FXML
    private ImageView switch_mystimon;

    @FXML
    private ImageView parchemain;

    @FXML
    private ImageView mystimonAlier;

    @FXML
    private ImageView mystimonAdverse;

    @FXML
    private Button selectAttaque;

    @FXML
    private Button selectSwitch;

    @FXML
    private Button selectParchemain;

    @FXML
    private Button selectFuite;

    @FXML
    private Label nomMystimonAlier;

    @FXML
    private Label nomMystimonEnemie;

    @FXML
    private Label levelEnemie;

    @FXML
    private Label levelAlier;

    @FXML
    private Label lvRestant;

    @FXML
    private Label pvRestant;

    @FXML
    private Button combatMessageButton;

    @FXML
    private ProgressBar barDeVieAlier;

    @FXML
    private ProgressBar barDeVieAdv;

    @FXML
    private Label pvRestantAdv;

    public combat_menu_principale_controller(Stage primaryStage,InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }

    @FXML
    private void selectAttaque() {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu_attaque.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_attaque_controller combat_menu_attaque_controller = new combat_menu_attaque_controller(primaryStage,combat);
            loader.setController(combat_menu_attaque_controller);
            Scene scene = new Scene(loader.load(), 450, 450);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void selectParchemain() {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu_parchemain.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_parchemain_controller combat_menu_parchemain_controller = new combat_menu_parchemain_controller(primaryStage,combat);
            loader.setController(combat_menu_parchemain_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void selectFuite() {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu_parchemain.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_parchemain_controller combat_menu_parchemain_controller = new combat_menu_parchemain_controller(primaryStage,combat);
            loader.setController(combat_menu_parchemain_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void selectSwitch() {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu_switch.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_switch_controller combat_menu_switch_controller = new combat_menu_switch_controller(primaryStage, combat, true);
            loader.setController(combat_menu_switch_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        attaque.setFitHeight(100); // Taille augmentée
        attaque.setFitWidth(200);

        fuite.setFitHeight(100);
        fuite.setFitWidth(130);

        parchemain.setFitHeight(100);
        parchemain.setFitWidth(130);

        switch_mystimon.setFitHeight(100);
        switch_mystimon.setFitWidth(130);
        //initialise les image
        Image image = new Image("fr/eseo/gaia_projet_java/resource_menu/attaquer.PNG");
        attaque.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_menu/fuite.PNG");
        fuite.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_menu/parchemain.PNG");
        parchemain.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_menu/switch_mystimon.PNG");
        switch_mystimon.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getNomMystimonAlier()+".png");
        mystimonAlier.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getNomMystimonAdv()+".png");
        mystimonAdverse.setImage(image);
        nomMystimonAlier.setText(combat.getNomMystimonAlier());
        nomMystimonEnemie.setText(combat.getNomMystimonAdv());
        levelAlier.setText(combat.getlvAlier());
        levelEnemie.setText(combat.getlvAdv());
        pvRestant.setText(String.valueOf(combat.getPvAlier()));
        if (combat.getRatioPvAlier() <= 0.3) {
            pvRestant.setStyle("-fx-text-fill: red;");
            barDeVieAlier.setStyle("-fx-accent: #e74c3c;");
        }

        if (combat.getRatioPvAdv() <= 0.3) {
            pvRestantAdv.setStyle("-fx-text-fill: red;");
            barDeVieAdv.setStyle("-fx-accent: #e74c3c;");
        }
        barDeVieAlier.setProgress(combat.getRatioPvAlier());
        barDeVieAdv.setProgress(combat.getRatioPvAdv());
        pvRestantAdv.setText(String.valueOf(combat.getPvAdv()));
    }
}
