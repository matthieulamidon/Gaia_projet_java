package fr.eseo.gaia_projet_java.Attaques;

import fr.eseo.gaia_projet_java.enumerations.Effet;
import fr.eseo.gaia_projet_java.enumerations.Stat;
import fr.eseo.gaia_projet_java.enumerations.Types;

public class AttaqueMixte implements Attaque{
    private Effet effet;
    private int puissance;
    private int puissance_effet;
    private Stat stat_visee;
    private Types type;

    @Override
    public void Action() {

    }
}
