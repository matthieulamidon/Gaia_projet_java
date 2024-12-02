package fr.eseo.gaia_projet_java.Invocateur;

import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Adversaire extends Invocateur {

    public Adversaire(int id, String nom, List<Exemplemon> liste_mystimons, HashMap<String, Integer> liste_objet, ArrayList<Integer> position) {
        super(id, nom, liste_mystimons,liste_objet, position);
    }
}
