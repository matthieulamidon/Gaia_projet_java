package fr.eseo.gaia_projet_java.Invocateur;

import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * C'est notre classe abstraite qui permet de mettre en commun des demandes entre allié et adversaire
 * @author Lamidon Matthieu
 * @version
 * @since
 */
public abstract class Invocateur {
    protected  int id;
    protected String nom;
    protected List<Exemplemon> liste_mystimons;
    protected HashMap<String, Integer> liste_objet;
    protected ArrayList<Integer> position;

    public Invocateur(int id, String nom, List<Exemplemon> liste_mystimons, HashMap<String, Integer> liste_objet, ArrayList<Integer> position) {
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
    public List<Exemplemon> getListe_mystimons() {
        return liste_mystimons;
    }
    public HashMap<String, Integer> getListe_objet() {
        return liste_objet;
    }
    public ArrayList<Integer> getPosition() {
        return position;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setListe_objet(HashMap<String, Integer> liste_objet) {
        this.liste_objet = liste_objet;
    }
    public void setPosition(ArrayList<Integer> position) {
        this.position=position;
    }

}
