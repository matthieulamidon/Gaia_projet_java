package fr.eseo.gaia_projet_java.Invocateur;

import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;

import java.util.ArrayList;

public abstract class Invocateur {
    protected  int id;
    protected String nom;
    protected ArrayList<Mystimon> liste_mystimons;
    protected ArrayList<Parchemin> liste_objet;
    protected ArrayList<ArrayList<Integer>> position;

    public Invocateur(int id, String nom, ArrayList<Mystimon> liste_mystimons, ArrayList<Parchemin> liste_objet, ArrayList<ArrayList<Integer>> position) {
        this.id = id;
        this.nom = nom;
        this.liste_mystimons = liste_mystimons;
        this.liste_objet = liste_objet;
        this.position = position;
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public ArrayList<Mystimon> getListe_mystimons() {
        return liste_mystimons;
    }
    public ArrayList<Parchemin> getListe_objet() {
        return liste_objet;
    }
    public ArrayList<ArrayList<Integer>> getPosition() {
        return position;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setListe_objet(ArrayList<Parchemin> liste_objet) {
        this.liste_objet = liste_objet;
    }
    public void setPosition(ArrayList<Integer> position) {
        this.position.add(position);
    }
}
