package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
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
import java.net.URL;
import java.util.ArrayList;

public class combat_menu_attaque_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;
    private ArrayList<String> dialogue ;

    @FXML
    private Button Attaque1;

    @FXML
    private Button Attaque2;

    @FXML
    private Button Attaque3;

    @FXML
    private Button Attaque4;

    @FXML
    Button RetourButton;

    @FXML
    private ImageView mystimonAlier;

    @FXML
    private ImageView mystimonAdverse;

    @FXML
    private Label nomMystimonAlier;

    @FXML
    private Label nomMystimonEnemie;

    @FXML
    private Label levelEnemie;

    @FXML
    private Label levelAlier;

    @FXML
    private Label pvRestantAdv;

    @FXML
    private ProgressBar barDeVieAlier;

    @FXML
    private ProgressBar barDeVieAdv;

    @FXML
    private Label pvRestant;


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

    public combat_menu_attaque_controller(Stage primaryStage, InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }

    @FXML
    public void initialize() {
        //URL mystimonImage = "fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getNomMystimonAlier()+".png";
        Image image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getNomMystimonAlier()+".png");
        mystimonAlier.setImage(image);
        image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getNomMystimonAdv()+".png");
        mystimonAdverse.setImage(image);
        nomMystimonAlier.setText(combat.getNomMystimonAlier());
        nomMystimonEnemie.setText(combat.getNomMystimonAdv());
        levelAlier.setText(combat.getlvAlier());
        levelEnemie.setText(combat.getlvAdv());
        pvRestantAdv.setText(String.valueOf(combat.getPvAdv()));
        pvRestant.setText(String.valueOf(combat.getPvAlier()));
        barDeVieAlier.setProgress(combat.getRatioPvAlier());
        barDeVieAdv.setProgress(combat.getRatioPvAdv());
        ArrayList<String> listeAttaque=combat.getListeAttaque();
        if (combat.getRatioPvAlier() <= 0.3) {
            pvRestant.setStyle("-fx-text-fill: red;");
            barDeVieAlier.setStyle("-fx-accent: #e74c3c;");
        }

        if (combat.getRatioPvAdv() <= 0.3) {
            pvRestantAdv.setStyle("-fx-text-fill: red;");
            barDeVieAdv.setStyle("-fx-accent: #e74c3c;");
        }
        switch (listeAttaque.size()) {
            case 1:
                Attaque1.setText(listeAttaque.get(0));
                Attaque2.setText("");
                Attaque3.setText("");
                Attaque4.setText("");
                break;
            case 2:
                Attaque1.setText(listeAttaque.get(0));
                Attaque2.setText(listeAttaque.get(1));
                Attaque3.setText("");
                Attaque4.setText("");
                break;
            case 3:
                Attaque1.setText(listeAttaque.get(0));
                Attaque2.setText(listeAttaque.get(1));
                Attaque3.setText(listeAttaque.get(2));
                Attaque4.setText("");
                break;
            case 4:
                Attaque1.setText(listeAttaque.get(0));
                Attaque2.setText(listeAttaque.get(1));
                Attaque3.setText(listeAttaque.get(2));
                Attaque4.setText(listeAttaque.get(3));
                break;
        }

    }

    @FXML
    private void lanceAttaque1(){
        ArrayList<String> listeAttaque = combat.getListeAttaque();
        transition(listeAttaque.get(0));

        /*

        if(combat.invocateurCommence()) {
            dialogue = combat.iaAttaque();
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            ArrayList<String> dialogue2= combat.attaquer(listeAttaque.get(0));
            dialogue.addAll(dialogue2);
        }else{
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            dialogue = combat.attaquer(listeAttaque.get(0));
            ArrayList<String> dialogue2 = combat.iaAttaque();
            dialogue.addAll(dialogue2);
        }
        transition();*/
    }

    @FXML
    private void lanceAttaque2(){
        ArrayList<String> listeAttaque = combat.getListeAttaque();
        transition(listeAttaque.get(1));/*
        if(combat.invocateurCommence()) {
            dialogue = combat.iaAttaque();
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            ArrayList<String> dialogue2= combat.attaquer(listeAttaque.get(1));
            dialogue.addAll(dialogue2);
        }else{
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            dialogue = combat.attaquer(listeAttaque.get(1));
            ArrayList<String> dialogue2 = combat.iaAttaque();
            dialogue.addAll(dialogue2);
        }
        transition();*/
    }
    @FXML
    private void lanceAttaque3(){
        ArrayList<String> listeAttaque = combat.getListeAttaque();
        transition(listeAttaque.get(2));/*
        if(combat.invocateurCommence()) {
            dialogue = combat.iaAttaque();
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            ArrayList<String> dialogue2= combat.attaquer(listeAttaque.get(2));
            dialogue.addAll(dialogue2);
        }else{
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            dialogue = combat.attaquer(listeAttaque.get(2));
            ArrayList<String> dialogue2 = combat.iaAttaque();
            dialogue.addAll(dialogue2);
        }
        transition();*/
    }
    @FXML
    private void lanceAttaque4(){
        ArrayList<String> listeAttaque = combat.getListeAttaque();
        transition(listeAttaque.get(3));/*
        if(combat.invocateurCommence()) {
            dialogue = combat.iaAttaque();
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            ArrayList<String> dialogue2= combat.attaquer(listeAttaque.get(3));
            dialogue.addAll(dialogue2);
        }else{
            ArrayList<String> listeAttaque = combat.getListeAttaque();
            dialogue = combat.attaquer(listeAttaque.get(3));
            ArrayList<String> dialogue2 = combat.iaAttaque();
            dialogue.addAll(dialogue2);
        }
        transition();*/
    }

    @FXML
    private void transition(String attaque){
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_transition.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_transition_controller combat_transition_controller = new combat_transition_controller(primaryStage,combat,attaque);
            loader.setController(combat_transition_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
