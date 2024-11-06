package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.Attaques.Attaque;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.util.ArrayList;
import java.util.HashMap;

public class Exemplemon extends Mystimon {

    public Exemplemon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<String> liste_attaques, long Experience, int Niveau, HashMap<String, Integer> state, int pv) {
        super(ID, Nom, liste_types, liste_attaques, Experience, Niveau, state, pv);
    }
}
