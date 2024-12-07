package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
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
import java.util.List;
/*
c'est le controller de les mystimon mais depuis le menu
@author Barthelemy Coutard
@version
@since
*/
public class inventaire_mystimons {
    private Stage primaryStage;

    public inventaire_mystimons(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML
    private Button BoutonRetour;

    @FXML
    private ListView<Exemplemon> listViewMystimons;

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

    @FXML
    private void DetailsMysi(Exemplemon mystimon) throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/details_mystimons.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_details inventaire_details = new inventaire_details(primaryStage, mystimon);
            loader.setController(inventaire_details);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML   //Fonction qui permet d'afficher le nom des Mystimons de l'équipe
    protected void initialize() throws SQLException {
        //Initalisation de la liste des Mystimons
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        List<Exemplemon> listeMysti = daoUserMariaDB.readLectuceDeLequipe();
        ObservableList<Exemplemon> observableList = FXCollections.observableArrayList(listeMysti); //Convertion en liste observable
        listViewMystimons.setItems(observableList); //on passe les items dans la listeView
        //On utiliste un CellFactory pour afficher uniquement le nom
        listViewMystimons.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Exemplemon mystimon, boolean empty) {
                super.updateItem(mystimon, empty);
                if (empty || mystimon == null) {
                    setText(null);
                } else {
                    setText(mystimon.getNom());
                }
            }
        });

        }

    @FXML
    private void clicPourDetails() throws IOException {
        Exemplemon mystimon = listViewMystimons.getSelectionModel().getSelectedItem();
        if (mystimon != null) {
            DetailsMysi(mystimon);
        }
    }

}



