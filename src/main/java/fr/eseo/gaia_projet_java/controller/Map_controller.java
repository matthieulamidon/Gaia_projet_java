package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.combatDeMystimon.InvocateurVsAdversaire;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import static java.lang.Integer.valueOf;
import static java.lang.Math.round;

public class Map_controller {

    public Stage MapStage;

    public Joueur joueur;

    public double joueurX;
    public double joueurY;

public Map_controller(Stage primaryStage, Joueur joueur) {
    this.MapStage = primaryStage;
    this.joueur = joueur;
}

@FXML
private ImageView mapView;

@FXML
private ImageView joueurView;

@FXML
private ImageView pnjView1;



private ArrayList<Rectangle> obstacles = new ArrayList<>();
private ArrayList<Rectangle> zoneRencontres = new ArrayList<>();
private static int IndiceDeplacement = 0;// Permet de savoir quelle image utiliser pour le joueur en déplacement
private static int IndiceMap = 0;//Permet de savoir quelle image utiliser pour la map
private int compteurDeplacement = 0; // Compte le nombre de déplacements successifs
private final int SEUIL_ALTERNANCE = 3; // Nombre de déplacements avant changement d'image
private final Set<KeyCode> touchesAppuyees = new HashSet<>();//HashSet pour permettre de rendre plus fluides les déplacements
private final Timeline timeline = new Timeline(new KeyFrame(Duration.millis(30), event -> {
    try {
        deplacementJoueur();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
})); //Timeline permettant de rendre les mouvements bien plus fluides, je dois faire la gestion d'Exception ici sinon il n'est pas content

private final Timeline mapTimeline = new Timeline(new KeyFrame(Duration.millis(500), event -> AlterneMapImage()));
private boolean inventaireOuvert = false;//Booléen pour éviter que l'inventaire ne s'ouvre à l'infini à cause de la timeline.
private boolean combatDeclenche = false;//Booléan pour éviter qu'un combat ne se déclenche à l'infini

@FXML
private ArrayList<Image> Bas = new ArrayList<>();

@FXML
private ArrayList<Image> Haut = new ArrayList<>();

@FXML
private ArrayList<Image> Gauche = new ArrayList<>();

@FXML
private ArrayList<Image> Droite =  new ArrayList<>();

@FXML
private ArrayList<Image> MapImage = new ArrayList<>();


public void setScene(Scene scene) {// Fonction qui permet de gérer les inputs du clavier
    scene.setOnKeyPressed(event -> {
        touchesAppuyees.add(event.getCode());
        if (!timeline.getStatus().equals(Animation.Status.RUNNING)) {
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    });
    scene.setOnKeyReleased(event -> {
        touchesAppuyees.remove(event.getCode());
        if (event.getCode() == KeyCode.I) {
            inventaireOuvert = false; // Autoriser une nouvelle ouverture après le relâchement de la touche
        }
        if (touchesAppuyees.isEmpty()) {
            timeline.stop();
        }
    });
}


public void initialize() {
    //On initalise la liste des obstacles
    obstacles.add(new Rectangle(0, 598, 800, 2));//pour ne pas sortir en bas
    obstacles.add(new Rectangle(0, 0, 2, 600));//pour ne pas sortir à gauche
    obstacles.add(new Rectangle(0, 0, 800, 2));//pour ne pas sortir en haut
    obstacles.add(new Rectangle(798, 0, 2, 600));//pour ne pas sortir à gauche
    obstacles.add(new Rectangle(479, 0, 128, 190));
    obstacles.add(new Rectangle(767, 0, 37, 600));//océan est
    obstacles.add(new Rectangle(0, 479, 800, 121));//océan sud
    obstacles.add(new Rectangle(257, 254, 158, 2));
    obstacles.add(new Rectangle(258, 315, 221, 31));
    obstacles.add(new Rectangle(255, 92, 2, 65));
    obstacles.add(new Rectangle(315, 92, 2, 65));
    obstacles.add(new Rectangle(477, 0, 2, 317));
    obstacles.add(new Rectangle(0, 95, 254, 28));
    obstacles.add(new Rectangle(320, 95, 95, 29));
    obstacles.add(new Rectangle(415, 95, 2, 163));
    //On initialise les zones de rencontre
    zoneRencontres.add(new Rectangle(0, 159, 92, 184));
    zoneRencontres.add(new Rectangle(35, 348, 64, 32));
    //On initalise les listes des déplacements
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche0.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche1.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche0.png"));
    Gauche.add(new Image("fr/eseo/gaia_projet_java/resource_map/gauche2.png"));

    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite0.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite1.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite0.png"));
    Droite.add(new Image("fr/eseo/gaia_projet_java/resource_map/droite1.png"));

    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas0.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas1.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas0.png"));
    Bas.add(new Image("fr/eseo/gaia_projet_java/resource_map/bas2.png"));

    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut0.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut1.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut0.png"));
    Haut.add(new Image("fr/eseo/gaia_projet_java/resource_map/haut2.png"));

    //On initalise map et l'imageview du joueur
    MapImage.add(new Image("fr/eseo/gaia_projet_java/resource_map/MapSauv.png"));
    MapImage.add(new Image("fr/eseo/gaia_projet_java/resource_map/MapSauv.png"));
    mapView.setImage(MapImage.get(0));
    mapTimeline.setCycleCount(Timeline.INDEFINITE);//On lance la timeline qui permet d'alterner les images de la map
    mapTimeline.play();

    Image joueurImage = Bas.get(0);
    joueurView.setImage(joueurImage);

    pnjView1.setImage(joueurImage);
    //On passe combat déclenché à false
    combatDeclenche = false;
    IndiceDeplacement = 0; // Réinitialise au début d'une séquence d'animation
    compteurDeplacement = 0; // Réinitialise le compteur
}

public void deplacementJoueur() throws SQLException, IOException {
    double nextX = joueurX;
    double nextY = joueurY;
    boolean deplacementHaut = touchesAppuyees.contains(KeyCode.UP) || touchesAppuyees.contains(KeyCode.Z);
    boolean deplacementBas = touchesAppuyees.contains(KeyCode.DOWN) || touchesAppuyees.contains(KeyCode.S);
    boolean deplacementGauche = touchesAppuyees.contains(KeyCode.LEFT) || touchesAppuyees.contains(KeyCode.Q);
    boolean deplacementDroite = touchesAppuyees.contains(KeyCode.RIGHT) || touchesAppuyees.contains(KeyCode.D);

    if (deplacementHaut) {
        if (deplacementGauche || deplacementDroite) {//si diagonale
            nextY -= 3.5;
        } else {
            nextY -= 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE) {
            joueurView.setImage(Haut.get(IndiceDeplacement));
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementBas) {
        if (deplacementGauche || deplacementDroite) { //si diagonale
            nextY += 3.5;
        } else {
            nextY += 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE) {
            joueurView.setImage(Bas.get(IndiceDeplacement));
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementGauche) {
        if (deplacementHaut || deplacementBas) {//si diagonale
            nextX -= 3.5;
        } else {
            nextX -= 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE && !(deplacementHaut || deplacementBas)) {
            joueurView.setImage(Gauche.get(IndiceDeplacement)); // Alterner l'image
            IndiceDeplacement = (IndiceDeplacement + 1) % 4;
            compteurDeplacement = 0;
        }
    }
    if (deplacementDroite) {
        if (deplacementHaut || deplacementBas) {//si il y a une diagonale
            nextX += 3.5;
        } else { //sinon
            nextX += 5;
        }
        compteurDeplacement++;
        if (compteurDeplacement >= SEUIL_ALTERNANCE && !(deplacementHaut || deplacementBas)) {
            joueurView.setImage(Droite.get(IndiceDeplacement)); // Alterner l'image
            IndiceDeplacement = (IndiceDeplacement + 1) % 4; //
            compteurDeplacement = 0;
        }
    }
    if (touchesAppuyees.contains(KeyCode.I) && !inventaireOuvert) {
        try {
            OuvertureInventaire();
            inventaireOuvert = true; // Empêche d'ouvrir l'inventaire à nouveau
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    for (Rectangle obstacle : obstacles) {
        // Vérification si le joueur se déplace dans la zone de l'obstacle
        if (new Rectangle(nextX + 4, nextY + 25, 19, 10).intersects(obstacle.getBoundsInLocal())) {
            System.out.println("Collision detectee, deplacement annule.");
            return;
        }
        // Ne pas déplacer le joueur s'il y a collision
    }

    for (Rectangle rencontre : zoneRencontres) {
        // Vérification si le joueur se déplace dans la zone de l'obstacle
        if (new Rectangle(nextX + 4, nextY + 25, 19, 10).intersects(rencontre.getBoundsInLocal())) {
            if(combatDeclenche){
                return;
            }
            else if (Math.random() < 0.02) {
                combatDeclenche = true;
                CombatMysti();
            }
        }
    }

    joueurX = nextX;
    joueurY = nextY;

    joueurView.setLayoutX(joueurX);
    joueurView.setLayoutY(joueurY);


}

    @FXML
    private void OuvertureInventaire() throws IOException {
        try {
            // Charger la scène depuis le fichier FXML
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inventaire/inventaire.fxml"));

            Stage inventaireStage = new Stage();
            inventaireStage.setTitle("Inventaire");
            inventaire_menu inventaire_menu = new inventaire_menu(inventaireStage);
            loader.setController(inventaire_menu);
            inventaireStage.setScene(new Scene(loader.load()));


            inventaireStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AlterneMapImage(){
        IndiceMap = (IndiceMap + 1) % 2;
        mapView.setImage(MapImage.get(IndiceMap)); // Alterner l'image
    }

    @FXML
    public void CombatPnj() throws SQLException, IOException {
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        miseAjourCooDB();//Mise à jour des coordonnees dans la base de données
        Adversaire Pnj = daoUserMariaDB.readLectureAdversaire(0);
        InvocateurVsAdversaire combat = new InvocateurVsAdversaire(joueur, Pnj);

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_principale_controller combat_menu_principale_controller = new combat_menu_principale_controller(MapStage,combat);
            loader.setController(combat_menu_principale_controller);
            Scene scene = new Scene(loader.load(), 450, 520);

            // Configuration de la fenêtre principale
            MapStage.setResizable(false);
            MapStage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Fonction pour lancer des combats sauvages
    public void CombatMysti() throws SQLException, IOException {
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        miseAjourCooDB();//Mise à jour des coordonnees dans la base de données
        Exemplemon mystimon = adversaireRand();
        InvocateurVsAdversaire combat = new InvocateurVsAdversaire(joueur, mystimon, 5);

        try {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("combat-view/combat_menu-principale.fxml"));
            // Récupérer la fenêtre actuelle (Stage) et changer la scène
            combat_menu_principale_controller combat_menu_principale_controller = new combat_menu_principale_controller(MapStage,combat);
            loader.setController(combat_menu_principale_controller);
            Scene scene = new Scene(loader.load(), 450, 520);

            // Configuration de la fenêtre principale
            MapStage.setResizable(false);
            MapStage.setScene(scene);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    //permet d'initialise la position avant d'afficher la scène
    public void defPositionInitiale() {
        joueurView.setLayoutX(joueurX);
        joueurView.setLayoutY(joueurY);
    }

    //Fonction pour mettre la position à jour dans la base de données
    public void miseAjourCooDB() throws SQLException {
        ArrayList<String> listeCoo = new ArrayList<>();
        listeCoo.add(String.valueOf(round(joueurX)));
        listeCoo.add(String.valueOf(round(joueurY)));
        DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
        daoUserMariaDB.MiseAJourCoo(listeCoo);
    }

    //Fonction pour appeler un mystimon aléatoire
    public Exemplemon adversaireRand() throws SQLException {
    Random rand = new Random();
    DAOUserMariaDB daoUserMariaDB = new DAOUserMariaDB();
    ArrayList<Exemplemon> listeExemplemon = daoUserMariaDB.nouveauMystimon(5);
    Exemplemon exemplemon = listeExemplemon.get(rand.nextInt(listeExemplemon.size()));
    return exemplemon;
    }


}

