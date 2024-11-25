package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class inventaire_parchemins {

    private Stage primaryStage;
    private Joueur joueur;

    inventaire_parchemins(Stage primaryStage, Joueur joueur) {
        this.primaryStage = primaryStage;
        this.joueur = joueur;
    }

    @FXML
    private Button BoutonRetour;

    @FXML
    private ListView<Buff> listeViewParchemins;

    @FXML
    private void RetourMenuI() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_menu inventaire_menu = new inventaire_menu(primaryStage);
            loader.setController(inventaire_menu);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML   //Fonction qui permet d'afficher le nom des parchemins
    protected void initialize() throws SQLException {
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
                    setText(parchemin.getNom()+" x"+mapObjets.get(parchemin.getNom()));
                }
            }
        });

    }

    @FXML
    private void DetailsParchemin(Buff parchemin) throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/details_parchemins.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_det_parchemins inventaire_det_parchemins = new inventaire_det_parchemins(primaryStage, joueur, parchemin);
            loader.setController(inventaire_det_parchemins);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clicPourDetails() throws IOException {

        Buff parchemin = listeViewParchemins.getSelectionModel().getSelectedItem();
        if (parchemin != null) {
            DetailsParchemin(parchemin);
        }

    }

}
