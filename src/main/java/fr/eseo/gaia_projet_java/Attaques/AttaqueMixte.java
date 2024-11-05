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
    public Effet getEffet() {
        return effet;
    }
    public void setEffet(Effet effet) {
        this.effet = effet;
    }
    public int getPuissance() {
        return puissance;
    }
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
    public int getPuissance_effet() {
        return puissance_effet;
    }
    public void setPuissance_effet(int puissance_effet) {
        this.puissance_effet = puissance_effet;
    }
}
