package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
/**
 * c'est le controller de l'inventaire mais depuis le menu mais pour les details d'un seul objet
 * @author Barthelemy Coutard
 * @version
 * @since
 */
public class inventaire_details {

    private Stage primaryStage;
    private Exemplemon mystimon;

    @FXML
    private Label nameLabel, levelLabel, currentHpLabel, maxHpLabel, expLabel, typeLabel, type2Label;
    @FXML
    private Label statHp, statAtk, statSpAtk, statDef, statSpDef, statVit;
    @FXML
    private Label ivLabel, evLabel, heldItemLabel;
    @FXML
    private ProgressBar hpBar, expBar;
    @FXML
    private Button attack1Button, attack2Button, attack3Button, attack4Button;

    inventaire_details(Stage primaryStage, Exemplemon mystimon) {
        this.primaryStage = primaryStage;
        this.mystimon = mystimon;
    }

    /**
     * initialisation
     */
    @FXML
    protected void initialize(){
    nameLabel.setText(mystimon.getNom());
    levelLabel.setText(String.valueOf((mystimon.getNiveau())));
    currentHpLabel.setText(String.valueOf(mystimon.getPv()));
    maxHpLabel.setText(String.valueOf(mystimon.getStats().get("PV")));
    expLabel.setText(String.valueOf(mystimon.getExperience()));

    //extraction des types
    ArrayList<String> liste_types = mystimon.ConvertionTypes();//convertit la liste d'enum en liste de strings

    if (!liste_types.isEmpty()) { //parcourt la liste et met les labels en fonction du nombre de types
        if (liste_types.size() > 0 && liste_types.get(0) != null) {
            typeLabel.setText(liste_types.get(0));
        }
        if (liste_types.size() > 1 && liste_types.get(1) != null) {
            type2Label.setText(liste_types.get(1));
        }
    }


    statHp.setText(String.valueOf(mystimon.getStats().get("PV")));
    statAtk.setText(String.valueOf(mystimon.getStats().get("ATK")));
    statSpAtk.setText(String.valueOf(mystimon.getStats().get("SP_ATK")));
    statDef.setText(String.valueOf(mystimon.getStats().get("DEF")));
    statSpDef.setText(String.valueOf(mystimon.getStats().get("SP_DEF")));
    statVit.setText(String.valueOf(mystimon.getStats().get("VIT")));

    ivLabel.setText(String.valueOf(mystimon.getIv()));
    evLabel.setText(String.valueOf(mystimon.getEv()));
    heldItemLabel.setText(mystimon.getObjet());

    double progress = (double) mystimon.getPv() / mystimon.getStats().get("PV");// ligne pour que la progressbar s'affiche bien (ça ne fonctionne qu'avec des doubles visiblement)
    hpBar.setProgress(progress);


    expBar.setProgress(0.75);

    //Même chose que pour les types mais avec les attaques
    if (!mystimon.getListeAttaques().isEmpty()) { //parcourt la liste et met les labels en fonction du nombre de types
        if (mystimon.getListeAttaques().size() > 0 && mystimon.getListeAttaques().get(0) != null) {
            attack1Button.setText(mystimon.getListeAttaques().get(0));
        }
        if (mystimon.getListeAttaques().size() > 1 && mystimon.getListeAttaques().get(1) != null) {
            attack2Button.setText(mystimon.getListeAttaques().get(1));
        }
        if (mystimon.getListeAttaques().size() > 2 && mystimon.getListeAttaques().get(2) != null) {
            attack3Button.setText(mystimon.getListeAttaques().get(2));
        }
        if (mystimon.getListeAttaques().size() > 3 && mystimon.getListeAttaques().get(3) != null) {
            attack4Button.setText(mystimon.getListeAttaques().get(3));
        }
    }
    }

    @FXML
    private Button BoutonRetour;

    /** Appuis bouton de retour
     * @throws IOException
     */
    @FXML
    private void RetourMenuI() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire_mystimons.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_mystimons inventaire_mystimons = new inventaire_mystimons(primaryStage);
            loader.setController(inventaire_mystimons);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
