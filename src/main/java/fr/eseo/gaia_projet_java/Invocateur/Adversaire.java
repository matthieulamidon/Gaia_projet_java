package fr.eseo.gaia_projet_java.Invocateur;

import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;

import java.util.ArrayList;

public class Adversaire extends Invocateur {

    public Adversaire(int id, String nom, ArrayList<Mystimon> liste_mystimons, ArrayList<Parchemin> liste_objet, ArrayList<ArrayList<Integer>> position) {
        super(id, nom, liste_mystimons, liste_objet, position);
    }
}
