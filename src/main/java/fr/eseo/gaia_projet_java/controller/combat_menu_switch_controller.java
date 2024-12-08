package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
/**
* Controller qui gère la scène du choix de mystimon à échanger
* @author Matthieu Lamidon
* @version
* @since
*/
public class combat_menu_switch_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;
    private boolean retourPossible;

    @FXML
    Button mystimon1;
    @FXML
    Button mystimon2;
    @FXML
    Button mystimon3;
    @FXML
    Button mystimon4;
    @FXML
    Button mystimon5;
    @FXML
    Button mystimon6;
    @FXML
    ImageView imagemystimon1;
    @FXML
    ImageView imagemystimon2;
    @FXML
    ImageView imagemystimon3;
    @FXML
    ImageView imagemystimon4;
    @FXML
    ImageView imagemystimon5;
    @FXML
    ImageView imagemystimon6;
    @FXML
    Label mystimon1nom;
    @FXML
    Label mystimon2nom;
    @FXML
    Label mystimon3nom;
    @FXML
    Label mystimon4nom;
    @FXML
    Label mystimon5nom;
    @FXML
    Label mystimon6nom;
    @FXML
    Label mystimon1nblevel;
    @FXML
    Label mystimon2nblevel;
    @FXML
    Label mystimon3nblevel;
    @FXML
    Label mystimon4nblevel;
    @FXML
    Label mystimon5nblevel;
    @FXML
    Label mystimon6nblevel;
    @FXML
    ProgressBar mystimon1bardepv;
    @FXML
    ProgressBar mystimon2bardepv;
    @FXML
    ProgressBar mystimon3bardepv;
    @FXML
    ProgressBar mystimon4bardepv;
    @FXML
    ProgressBar mystimon5bardepv;
    @FXML
    ProgressBar mystimon6bardepv;
    @FXML
    Label mystimon1nbdepv;
    @FXML
    Label mystimon2nbdepv;
    @FXML
    Label mystimon3nbdepv;
    @FXML
    Label mystimon4nbdepv;
    @FXML
    Label mystimon5nbdepv;
    @FXML
    Label mystimon6nbdepv;


    /**
     * @param primaryStage le stage en cours
     * @param combat le combat en cours
     * @param retourPossible true si on peut faire l'action retour
     */
    public combat_menu_switch_controller(Stage primaryStage, InvocateurVsAdversaire combat, boolean retourPossible) {
        this.primaryStage = primaryStage;
        this.combat = combat;
        this.retourPossible = retourPossible;
    }

    /**
     * Appuis du bouton retour
     */
    @FXML
    private void selectRetour() {
        if (retourPossible) {
            try {
                // Charger la scène depuis le fichier FXML
                FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));

                // Récupérer la fenêtre actuelle (Stage) et changer la scène
                combat_menu_principale_controller combat_menu_principale_controller = new combat_menu_principale_controller(primaryStage, combat);
                loader.setController(combat_menu_principale_controller);
                Scene scene = new Scene(loader.load(), 450, 520);
                primaryStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Appuis le mystimon 1
     */
    @FXML
    private void selectMystimon1() {

    }

    /**
     * Appuis le mystimon 2
     */
    @FXML
    private void selectMystimon2() {
        if(combat.mystimonNexxiste(1)&& !combat.getPvMystimonN(1).equals("0")) {
            combat.swichMystimonAllier(1);
            resetscene();
            if(!retourPossible) {
                retourPossible = true;
            }
        }
    }
    /**
     * Appuis le mystimon 3
     */
    @FXML
    private void selectMystimon3() {
        if(combat.mystimonNexxiste(2)&& !combat.getPvMystimonN(2).equals("0")) {
            combat.swichMystimonAllier(2);
            resetscene();
            if(!retourPossible) {
                retourPossible = true;
            }
        }
    }
    /**
     * Appuis le mystimon 4
     */
    @FXML
    private void selectMystimon4() {
        if(combat.mystimonNexxiste(3)&& !combat.getPvMystimonN(3).equals("0")) {
            combat.swichMystimonAllier(3);
            resetscene();
            if(!retourPossible) {
                retourPossible = true;
            }
        }
    }
    /**
     * Appuis le mystimon 5
     */
    @FXML
    private void selectMystimon5() {
        if(combat.mystimonNexxiste(4)&& !combat.getPvMystimonN(4).equals("0")) {
            combat.swichMystimonAllier(4);
            resetscene();
            if(!retourPossible) {
                retourPossible = true;
            }
        }
    }
    /**
     * Appuis le mystimon 6
     */
    @FXML
    private void selectMystimon6() {
        if(combat.mystimonNexxiste(5)&& !combat.getPvMystimonN(5).equals("0")) {
            combat.swichMystimonAllier(5);
            resetscene();
            if(!retourPossible) {
                retourPossible = true;
            }
        }
    }
    /**
     * met a jour la scene après un switch
     */
    @FXML
    private void resetscene() {
        //mystimon 1
        mystimon1nom.setText(combat.getnomMystimonN(0));
        mystimon1nbdepv.setText(combat.getPvMystimonN(0));
        if (combat.getPvMystimonN(0).equals("0")) {
            mystimon1nom.setStyle("-fx-text-fill: red;");;
            mystimon1.setStyle("-fx-background-color: red;");

        }
        mystimon1nblevel.setText(combat.getLvMystimonN(0));
        mystimon1bardepv.setProgress(combat.getRatioPvMystimonN(0));
        Image image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getnomMystimonN(0)+".png");
        imagemystimon1.setImage(image);
        //mystimon 2
        if(combat.mystimonNexxiste(1)) {
            mystimon2nom.setText(combat.getnomMystimonN(1));
            mystimon2nbdepv.setText(combat.getPvMystimonN(1));
            mystimon2nblevel.setText(combat.getLvMystimonN(1));
            mystimon2bardepv.setProgress(combat.getRatioPvMystimonN(1));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(1) + ".png");
            imagemystimon2.setImage(image);
        }
        //mystimon 3
        if(combat.mystimonNexxiste(2)) {
            mystimon3nom.setText(combat.getnomMystimonN(2));
            mystimon3nbdepv.setText(combat.getPvMystimonN(2));
            mystimon3nblevel.setText(combat.getLvMystimonN(2));
            mystimon3bardepv.setProgress(combat.getRatioPvMystimonN(2));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(2) + ".png");
            imagemystimon3.setImage(image);
        }
        //mystimon 4
        if(combat.mystimonNexxiste(3)) {
            mystimon4nom.setText(combat.getnomMystimonN(3));
            mystimon4nbdepv.setText(combat.getPvMystimonN(3));
            mystimon4nblevel.setText(combat.getLvMystimonN(3));
            mystimon4bardepv.setProgress(combat.getRatioPvMystimonN(3));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(3) + ".png");
            imagemystimon4.setImage(image);
        }
        //mystimon 5
        if(combat.mystimonNexxiste(4)) {
            mystimon5nom.setText(combat.getnomMystimonN(4));
            mystimon5nbdepv.setText(combat.getPvMystimonN(4));
            mystimon5nblevel.setText(combat.getLvMystimonN(4));
            mystimon5bardepv.setProgress(combat.getRatioPvMystimonN(4));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(4) + ".png");
            imagemystimon5.setImage(image);
        }
        //mystimon 6
        if(combat.mystimonNexxiste(5)) {
            mystimon6nom.setText(combat.getnomMystimonN(5));
            mystimon6nbdepv.setText(combat.getPvMystimonN(5));
            mystimon6nblevel.setText(combat.getLvMystimonN(5));
            mystimon6bardepv.setProgress(combat.getRatioPvMystimonN(5));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(5) + ".png");
            imagemystimon6.setImage(image);
        }
    }


    /**
     * initialise les valeurs
     */
    @FXML
    public void initialize() {
        //mystimon 1
        mystimon1nom.setText(combat.getnomMystimonN(0));
        mystimon1nbdepv.setText(combat.getPvMystimonN(0));
        if (combat.getPvMystimonN(0).equals("0")) {
            mystimon1nom.setStyle("-fx-text-fill: red;");;
            mystimon1.setStyle("-fx-background-color: red;");

        }
        mystimon1nblevel.setText(combat.getLvMystimonN(0));
        mystimon1bardepv.setProgress(combat.getRatioPvMystimonN(0));
        Image image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/"+combat.getnomMystimonN(0)+".png");
        imagemystimon1.setImage(image);
        //mystimon 2
        if(combat.mystimonNexxiste(1)) {
            mystimon2nom.setText(combat.getnomMystimonN(1));
            mystimon2nbdepv.setText(combat.getPvMystimonN(1));
            mystimon2nblevel.setText(combat.getLvMystimonN(1));
            mystimon2bardepv.setProgress(combat.getRatioPvMystimonN(1));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(1) + ".png");
            imagemystimon2.setImage(image);
        }
        //mystimon 3
        if(combat.mystimonNexxiste(2)) {
            mystimon3nom.setText(combat.getnomMystimonN(2));
            mystimon3nbdepv.setText(combat.getPvMystimonN(2));
            mystimon3nblevel.setText(combat.getLvMystimonN(2));
            mystimon3bardepv.setProgress(combat.getRatioPvMystimonN(2));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(2) + ".png");
            imagemystimon3.setImage(image);
        }
        //mystimon 4
        if(combat.mystimonNexxiste(3)) {
            mystimon4nom.setText(combat.getnomMystimonN(3));
            mystimon4nbdepv.setText(combat.getPvMystimonN(3));
            mystimon4nblevel.setText(combat.getLvMystimonN(3));
            mystimon4bardepv.setProgress(combat.getRatioPvMystimonN(3));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(3) + ".png");
            imagemystimon4.setImage(image);
        }
        //mystimon 5
        if(combat.mystimonNexxiste(4)) {
            mystimon5nom.setText(combat.getnomMystimonN(4));
            mystimon5nbdepv.setText(combat.getPvMystimonN(4));
            mystimon5nblevel.setText(combat.getLvMystimonN(4));
            mystimon5bardepv.setProgress(combat.getRatioPvMystimonN(4));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(4) + ".png");
            imagemystimon5.setImage(image);
        }
        //mystimon 6
        if(combat.mystimonNexxiste(5)) {
            mystimon6nom.setText(combat.getnomMystimonN(5));
            mystimon6nbdepv.setText(combat.getPvMystimonN(5));
            mystimon6nblevel.setText(combat.getLvMystimonN(5));
            mystimon6bardepv.setProgress(combat.getRatioPvMystimonN(5));
            image = new Image("fr/eseo/gaia_projet_java/resource_mystimon/" + combat.getnomMystimonN(5) + ".png");
            imagemystimon6.setImage(image);
        }
    }
}
