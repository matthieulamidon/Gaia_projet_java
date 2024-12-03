package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class combat_game_over_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;

    @FXML
    private ImageView imageGameOver;

    public combat_game_over_controller(Stage primaryStage,InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }
    public void initialize(){
        Image gameover = new Image("fr/eseo/gaia_projet_java/resource_menu/game_over.png");
        imageGameOver.setImage(gameover);
    }

}
