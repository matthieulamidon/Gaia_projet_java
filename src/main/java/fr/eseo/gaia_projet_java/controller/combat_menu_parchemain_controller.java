package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class combat_menu_parchemain_controller {
    private Stage primaryStage;
    private InvocateurVsAdversaire combat;

    // Mystimon buttons and their elements
    @FXML
    private Button mystimon1, mystimon2, mystimon3, mystimon4, mystimon5, mystimon6;

    @FXML
    private Label mystimon1nom, mystimon1nblevel, mystimon1nbdepv;
    @FXML
    private Label mystimon2nom, mystimon2nblevel, mystimon2nbdepv;
    @FXML
    private Label mystimon3nom, mystimon3nblevel, mystimon3nbdepv;
    @FXML
    private Label mystimon4nom, mystimon4nblevel, mystimon4nbdepv;
    @FXML
    private Label mystimon5nom, mystimon5nblevel, mystimon5nbdepv;
    @FXML
    private Label mystimon6nom, mystimon6nblevel, mystimon6nbdepv;

    @FXML
    private ProgressBar mystimon1bardepv, mystimon2bardepv, mystimon3bardepv, mystimon4bardepv, mystimon5bardepv, mystimon6bardepv;

    // Action buttons
    @FXML
    private ImageView imageHeal, imagebuff, imageCapture;

    @FXML
    private ImageView imagemystimon1, imagemystimon2, imagemystimon3, imagemystimon4, imagemystimon5, imagemystimon6;

    // ListView
    @FXML
    private ListView<Buff> listeViewParchemins;

    // Retour button
    @FXML
    private Button retour;

    public combat_menu_parchemain_controller(Stage primaryStage, InvocateurVsAdversaire combat) {
        this.primaryStage = primaryStage;
        this.combat = combat;
    }


    // Navigation retour
    @FXML
    private void selectRetour() {
        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
            combat_menu_principale_controller combat_menu_principale_controller = new combat_menu_principale_controller(primaryStage, combat);
            loader.setController(combat_menu_principale_controller);
            Scene scene = new Scene(loader.load(), 450, 520);
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sélection des Mystimons
    @FXML
    private void selectMystimon1() {
        System.out.println("Mystimon 1 sélectionné !");
    }

    @FXML
    private void selectMystimon2() {
        System.out.println("Mystimon 2 sélectionné !");
    }

    @FXML
    private void selectMystimon3() {
        System.out.println("Mystimon 3 sélectionné !");
    }

    @FXML
    private void selectMystimon4() {
        System.out.println("Mystimon 4 sélectionné !");
    }

    @FXML
    private void selectMystimon5() {
        System.out.println("Mystimon 5 sélectionné !");
    }

    @FXML
    private void selectMystimon6() {
        System.out.println("Mystimon 6 sélectionné !");
    }

    // Gérer le clic sur la liste des parchemins
    @FXML
    private void clicPourDetails(javafx.scene.input.MouseEvent event) {
        Buff selectedItem = listeViewParchemins.getSelectionModel().getSelectedItem();
        if (selectedItem != null) {
            System.out.println("Parchemin sélectionné : " + selectedItem);
        }
    }

    // Actions supplémentaires (potions, buffs, captures)
    @FXML
    private void selectItemType1() {
        System.out.println("Potion sélectionnée !");
    }

    @FXML
    private void selectItemType2() {
        System.out.println("Buff sélectionné !");
    }

    @FXML
    private void selectItemType3() {
        System.out.println("Capture sélectionnée !");
    }

    @FXML   //Fonction qui permet d'afficher le nom des parchemins
    protected void initialize() throws SQLException {
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
        //Initalisation de la liste des Mystimons
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        Joueur joueur = daoUserMariaDB.readLectureJoueur();

        HashMap<String, Integer> mapObjets = joueur.getListe_objet();
        ArrayList<Buff> listeParchemins = daoUserMariaDB.LectureMapObjets(mapObjets);

        ObservableList<Buff> observableList = FXCollections.observableArrayList(listeParchemins); //Convertion en liste observable
        listeViewParchemins.setItems(observableList); //on passe les items dans la listeView

        //On utiliste un CellFactory pour afficher uniquement le nom
        listeViewParchemins.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Buff parchemin, boolean empty) {
                super.updateItem(parchemin, empty);
                if (empty || parchemin == null) {
                    setText(null);
                } else {
                    setText(parchemin.getNom() + " x" + mapObjets.get(parchemin.getNom()));
                }
            }
        });
    }
}

