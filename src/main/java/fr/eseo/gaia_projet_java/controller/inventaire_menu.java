package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
/**
 * c'est le controller de l'inventaire mais depuis le menu
 * @author Barthelemy Coutard
 * @version
 * @since
 */
public class inventaire_menu {

    private Stage primaryStage;
    //elements de l'inventaire
    @FXML
    private Button boutonMystimons;

    @FXML
    private Button boutonParchemins;

    /**
     * Appuis sur le bouton AfficherMystimon
     */
    //methodes pour le fxml de l'inventaire
    @FXML
    public void AfficherMystimons(){
        System.out.println("Afficher Mystimons");
    }
    /**
     * Appuis sur le bouton AfficherParchemin
     */
    @FXML
    public void AfficherParchemins(){
        System.out.println("Afficher Parchemins");
    }

    public inventaire_menu(Stage primaryStage) {
        this.primaryStage = primaryStage;;
    }

    //Méthode pour changer de scene
    /**
     * Appuis sur le bouton AfficherParchemin
     */
    @FXML
    private void switchToMystimons() throws IOException {
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

    @FXML
    private void ToParchemins(Joueur joueur) throws IOException {
        //On extrait les données du joueur
        try {
            // Charger la scène depuis le fichier FXML

            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire_parchemins.fxml"));

            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            inventaire_parchemins inventaire_parchemins = new inventaire_parchemins(primaryStage, joueur);
            loader.setController(inventaire_parchemins);
            primaryStage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void quitter() throws IOException {
        primaryStage.close();
    }

    @FXML
    private void switchToParchemins() throws IOException, SQLException {
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        Joueur joueur = daoUserMariaDB.readLectureJoueur();
        if (joueur != null) {
            ToParchemins(joueur);
        }
    }
    }

