package fr.eseo.gaia_projet_java.Attaques;

import fr.eseo.gaia_projet_java.enumerations.Effet;
import fr.eseo.gaia_projet_java.enumerations.Types;
/*
il permet de stoquer les attaques dans une classe et de de sortir les info demander par la classe qui gere les combat
@author Matthieu Lamidon
@version
@since
*/
public class AttaqueCombat {
    private int id;
    private String nom;
    private int puissance;
    private Types types;
    private String effet;
    private String aspect;
    private int pressision;

    /*
    constructeur
    */
    public AttaqueCombat(int id, String nom, int puissance, Types types, String effet, String aspect, int pressision) {
        this.id = id;
        this.nom = nom;
        this.puissance = puissance;
        this.types = types;
        this.effet = effet;
        this.aspect = aspect;
        this.pressision = pressision;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public int getPuissance() {
        return puissance;
    }
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }
    public Types getTypes() {
        return types;
    }
    public void setTypes(Types types) {
        this.types = types;
    }
    public String getEffet() {
        return effet;
    }
    public void setEffet(String effet) {
        this.effet = effet;
    }
    public String getAspect() {
        return aspect;
    }
    public void setAspect(String aspect) {
        this.aspect = aspect;
    }
    public int getPressision() {
        return pressision;
    }
    public void setPressision(int pressision) {
        this.pressision = pressision;
    }
}
