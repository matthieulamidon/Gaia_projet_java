package fr.eseo.gaia_projet_java.Map;

import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;

import java.util.ArrayList;
import java.util.HashMap;

public class Map {
    private Joueur joueur;
    private HashMap<Integer, Adversaire> liste_pnj;
    private ArrayList<ArrayList<int[]>> carte;

    public Map(Joueur joueur, HashMap<Integer, Adversaire> liste_pnj, ArrayList<ArrayList<int[]>> carte) {
        this.joueur = joueur;
        this.liste_pnj = liste_pnj;
        this.carte = carte;
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public HashMap<Integer, Adversaire> getListe_pnj() {
        return liste_pnj;
    }
    public void setListe_pnj(HashMap<Integer, Adversaire> liste_pnj) {
        this.liste_pnj = liste_pnj;
    }
    public ArrayList<ArrayList<int[]>> getCarte() {
        return carte;
    }
    public void setCarte(ArrayList<ArrayList<int[]>> carte) {
        this.carte = carte;
    }

    //coquilles vides :

    public void deplacementJoueur(Joueur joueur) {

    }

    public void deplacementPnj(Adversaire adversaire) {

    }

    public void map_suivante(){

    }

    public void debut_combat(Adversaire adversaire, Joueur joueur){

    }

    public void fin_combat(Adversaire adversaire, Joueur joueur){

    }
}
