package fr.eseo.gaia_projet_java.Invocateur;

import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;

import java.util.ArrayList;

public class Joueur extends Invocateur {
    private ArrayList<Mystimon> reserveMystimons;


    public Joueur(int id, String nom, ArrayList<Mystimon> liste_mystimons, ArrayList<Parchemin> liste_objet, ArrayList<ArrayList<Integer>> position) {
        super(id, nom, liste_mystimons, liste_objet, position);
    }

    public ArrayList<Mystimon> getReserveMystimons() {
        return reserveMystimons;
    }

    public void setReserveMystimons(ArrayList<Mystimon> reserveMystimons){
        this.reserveMystimons = reserveMystimons;
    }

}
