package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * Notre classe de mystimon qui est la version utilisée dans notre code
 * @author Barthélémy Coutard, Lamidon Matthieu
 * @version
 * @since
 */
public class Exemplemon extends Mystimon {

    public Exemplemon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<String> liste_attaques, long Experience, int Niveau, int ev, int iv, HashMap<String, Integer> stats, int pv) {
        super(ID, Nom, liste_types, liste_attaques, Experience,  Niveau, ev, iv, stats, pv);
    }
    public Exemplemon(String nom, int lv) throws SQLException {
        super(nom,lv);
    }


}
