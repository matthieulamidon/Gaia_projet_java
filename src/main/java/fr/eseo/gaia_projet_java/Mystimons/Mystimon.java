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
    protected ArrayList<String> liste_attaques;
    protected HashMap<String, Integer> stats;
    protected long Experience;
    protected int Niveau;
    protected int pv;

    public Mystimon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<String> liste_attaques, long Experience, int Niveau, HashMap<String, Integer> stats, int pv){
        this.ID = ID;
        this.Nom = Nom;
        this.liste_types = liste_types;
        this.liste_attaques = liste_attaques;
        this.Experience = Experience;
        this.Niveau = Niveau;
        this.stats = stats;
        this.pv = pv;
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
    public ArrayList<String> getListeAttaques(){
        return liste_attaques;
    }
    public HashMap<String, Integer> getStats(){
        return stats;
    }
    public long getExperience(){ return Experience; }
    public int getPv(){ return pv; }
    public int getNiveau(){ return Niveau; }
    public void setID(int ID){
        this.ID = ID;
    }
    public void setNom(String Nom){
        this.Nom = Nom;
    }
    public void setPv(int pv){ this.pv = pv; }
    public void setListeTypes(ArrayList<Types> liste_types){
        this.liste_types = liste_types;
    }
    public void setListeAttaques(ArrayList<String> liste_attaques){
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
