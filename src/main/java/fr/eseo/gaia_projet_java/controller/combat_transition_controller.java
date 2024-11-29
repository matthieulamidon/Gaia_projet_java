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

import java.io.IOException;
import java.util.ArrayList;

public class combat_transition_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;
    private ArrayList<String> dialogue;
    private int indice;


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

    public combat_transition_controller(Stage primaryStage,InvocateurVsAdversaire combat, ArrayList<String> dialogue) {
        this.primaryStage = primaryStage;
        this.combat = combat;
        this.dialogue = dialogue;
        this.indice = 0;
    }

    @FXML
    private void selectPasse(){
        if(indice >= dialogue.size()-1) {
            indice = 0;
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
        }else {
            indice++;
            combatMessageButton.setText(dialogue.get(indice));
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
        barDeVieAlier.setProgress(combat.getRatioPvAlier());
        barDeVieAdv.setProgress(combat.getRatioPvAdv());
        if (combat.getRatioPvAlier() <= 0.3) {
            pvRestant.setStyle("-fx-text-fill: red;");
            barDeVieAlier.setStyle("-fx-accent: #e74c3c;");
        }

        if (combat.getRatioPvAdv() <= 0.3) {
            pvRestantAdv.setStyle("-fx-text-fill: red;");
            barDeVieAdv.setStyle("-fx-accent: #e74c3c;");
        }
        pvRestantAdv.setText(String.valueOf(combat.getPvAdv()));
        combatMessageButton.setText(dialogue.get(0));
    }
}