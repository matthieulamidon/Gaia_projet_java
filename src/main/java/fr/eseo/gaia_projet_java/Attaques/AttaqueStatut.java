package fr.eseo.gaia_projet_java.Attaques;

import fr.eseo.gaia_projet_java.enumerations.Effet;
import fr.eseo.gaia_projet_java.enumerations.Stat;
import fr.eseo.gaia_projet_java.enumerations.Types;

public class AttaqueStatut implements Attaque{
    private Effet effet;
    private Types type;
    private int puissance_effet;
    private Stat stat_visee;
    public AttaqueStatut() {
    }

    @Override
    public void Action() {

    }
}
