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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class combat_transition_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;
    private ArrayList<String> dialogue;
    private ArrayList<String> dialogue2;
    private int indice;
    private int indice2;
    private String attaqueAlier;
    private boolean validerDialogue1;


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

    private void setDialogue(ArrayList<String> dialogue) {
        this.dialogue = dialogue;
    }
    private void setDialogue2(ArrayList<String> dialogue2) {
        this.dialogue2 = dialogue2;
    }

    public combat_transition_controller(Stage primaryStage,InvocateurVsAdversaire combat, String attaqueAlier) {
        this.primaryStage = primaryStage;
        this.combat = combat;
        this.attaqueAlier = attaqueAlier;
    }
    @FXML
    private void selectPasse() {
        if (!validerDialogue1) { // Gestion du premier dialogue
            if (indice <= dialogue.size()-1) {
                // Avancer dans le premier dialogue
                combatMessageButton.setText(dialogue.get(indice));
                indice++;
            } else {
                validerDialogue1 = true; // Premier dialogue validé
                indice = 0; // Réinitialiser l'indice pour le second dialogue
                mettreAJourBarresDeVie(); // Mise à jour des barres de vie
                if(combat.getPvAlier()==0){
                    //game over
                    for(int i=1; i<6; i++){
                        if(combat.mystimonNexxiste(i)){
                            if(combat.getPvMystimonN(i).equals("0")){
                                game_over();
                            }else{
                                mystimonAlierMort();
                            }
                        }
                    }
                } else if (combat.getPvAdv()==0) {
                    //victoire

                    for(int i=1; i<6; i++){
                        if(combat.mystimonNexxisteAdv(i)){
                            if(combat.getPvMystimonNAdv(i).equals("0")){
                                game_over();
                            }else{

                                mystimonAdvMort();
                            }
                        }
                    }
                }
                tourDeJeu2(); // Passage au second tour de jeu
                combatMessageButton.setText(dialogue2.get(indice));
            }
        } else { // Gestion du second dialogue
            if (indice <= dialogue2.size()-1) {
                 // Avancer dans le second dialogue
                combatMessageButton.setText(dialogue2.get(indice));
                indice++;
            } else {
                mettreAJourBarresDeVie(); // Mise à jour des barres de vie avant de quitter
                chargerMenuPrincipal(); // Charger le menu principal
            }
        }
    }

    // Méthode pour mettre à jour les barres de vie
    private void mettreAJourBarresDeVie() {
        barDeVieAlier.setProgress(combat.getRatioPvAlier());
        barDeVieAdv.setProgress(combat.getRatioPvAdv());

        if (combat.getRatioPvAlier() <= 0.3) {
            pvRestant.setStyle("-fx-text-fill: red;");
            barDeVieAlier.setStyle("-fx-accent: #e74c3c;");
        } else {
            pvRestant.setStyle("-fx-text-fill: black;");
            barDeVieAlier.setStyle("-fx-accent: #2ecc71;");
        }
    }

    // Méthode pour charger le menu principal
    private void chargerMenuPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
            combat_menu_principale_controller controller = new combat_menu_principale_controller(primaryStage, combat);
            loader.setController(controller);

            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du menu principal : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void tourDeJeu(){
        if(combat.invocateurCommence()) {
            setDialogue(combat.iaAttaque());
        }else{
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            setDialogue(combat.attaquer(attaqueAlier));
        }
    }
    private void tourDeJeu2(){
        if(combat.invocateurCommence()) {
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            setDialogue2(combat.attaquer(attaqueAlier));
        }else{
            setDialogue2(combat.iaAttaque());
        }
    }

    @FXML
    public void initialize() {
        validerDialogue1=false;
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
        tourDeJeu();
        combatMessageButton.setText(dialogue.get(0));

    }

    private void game_over(){
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_game_over.fxml"));
            combat_game_over_controller controller = new combat_game_over_controller(primaryStage, combat);
            loader.setController(controller);

            Scene scene = new Scene(loader.load(), 400, 600);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement du menu principal : " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mystimonAlierMort(){
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_mystimon_allier_mort.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_mystimon_allier_mort_controller combat_mystimon_allier_mort_controller = new combat_mystimon_allier_mort_controller(primaryStage, combat);
            loader.setController(combat_mystimon_allier_mort_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void mystimonAdvMort(){
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_mystimon_adverse_mort.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_mystimon_adverse_mort_controller combat_mystimon_adverse_mort_controller = new combat_mystimon_adverse_mort_controller(primaryStage, combat);
            loader.setController(combat_mystimon_adverse_mort_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}