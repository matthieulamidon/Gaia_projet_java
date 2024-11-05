package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.Attaques.Attaque;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Mystimon {
    protected int ID;
    protected String Nom;
    protected ArrayList<Types> liste_types;
    protected ArrayList<Attaque> liste_attaques;
    protected HashMap<String, Integer> stats;
    protected long Experience;
    protected int Niveau;

    public Mystimon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<Attaque> liste_attaques, long Experience, int Niveau){
        this.ID = ID;
        this.Nom = Nom;
        this.liste_types = liste_types;
        this.liste_attaques = liste_attaques;
        this.stats = new HashMap<>();
    }
    //gets et sets
    public int getID(){
        return ID;
    }
    public String getNom(){
        return Nom;
    }
    public ArrayList<Types> getListeTypes(){
        return liste_types;
    }
    public ArrayList<Attaque> getListeAttaques(){
        return liste_attaques;
    }
    public HashMap<String, Integer> getStats(){
        return stats;
    }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setNom(String Nom){
        this.Nom = Nom;
    }
    public void setListeTypes(ArrayList<Types> liste_types){
        this.liste_types = liste_types;
    }
    public void setListeAttaques(ArrayList<Attaque> liste_attaques){
        this.liste_attaques = liste_attaques;
    }
    public void setStats(HashMap<String, Integer> stats){
        this.stats = stats;
    }

    //methodes mères

    public void subirAttaque(Attaque attaque){

    }

    public void attaquer(Attaque attaque, Mystimon ennemi){

    }

    public void fuite(Mystimon ennemi){

    }

    public void usageParchemin(Parchemin parchemin){

    }


}
