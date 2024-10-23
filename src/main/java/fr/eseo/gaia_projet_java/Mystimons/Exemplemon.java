package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.Attaques.Attaque;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.util.ArrayList;

public class Exemplemon extends Mystimon {

    public Exemplemon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<Attaque> liste_attaques, long Experience, int Niveau) {
        super(ID, Nom, liste_types, liste_attaques, Experience, Niveau);
    }
}
