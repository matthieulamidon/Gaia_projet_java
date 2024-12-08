package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
/**
* Controller qui gère la scene de game over
*@author Matthieu Lamidon
*@version
*@since
*/
public class combat_game_over_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;

    @FXML
    private ImageView imageGameOver;

    /**
     * @param primaryStage le stage
     * @param combat notre invocateur vs adv
     */
    public combat_game_over_controller(Stage primaryStage,InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }

    /**
     * on initialise tout
     */
    public void initialize(){
        Image gameover = new Image("fr/eseo/gaia_projet_java/resource_menu/game_over.png");
        imageGameOver.setImage(gameover);
    }

}
