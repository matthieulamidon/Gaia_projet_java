package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUser;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
/**
 * version abstraite de nos mystimon
 * @author Barthélémy Coutard, Lamidon Matthieu
 * @version
 * @since
 */
public abstract class Mystimon {
    protected int ID;
    protected String Nom;

    protected ArrayList<Types> liste_types;
    protected ArrayList<String> liste_attaques;
    protected HashMap<String, Integer> stats;
    protected long Experience;
    protected int Niveau;
    protected int pv;
    protected int iv;
    protected int ev;
    protected String Objet;

    public Mystimon(int ID, String Nom, ArrayList<Types> liste_types, ArrayList<String> liste_attaques, long Experience, int Niveau, int ev, int iv, HashMap<String, Integer> stats, int pv){
        this.ID = ID;
        this.Nom = Nom;
        this.liste_types = liste_types;
        this.liste_attaques = liste_attaques;
        this.Experience = Experience;
        this.Niveau = Niveau;
        this.stats = stats;
        this.pv = pv;
        this.iv = iv;
        this.ev = ev;
        this.Objet = Objet;

    }

    public Mystimon( String Nom, int lv) throws SQLException {
        DAOUser dao= new DAOUserMariaDB();
        List<Exemplemon> mystidex = dao.nouveauMystimon(lv);
        for(Exemplemon exemplemon : mystidex){
            if(exemplemon.getNom().equals(Nom)){
                this.liste_types=exemplemon.getListeTypes();
                this.stats =exemplemon.getStats();
                this.pv=exemplemon.getStats().get("PV");
            }
        }
        this.ID =0;
        this.Nom = Nom;
        this.liste_attaques = liste_attaques;
        this.Experience = Experience;
        this.Niveau = lv;
        this.iv = creeIv();
        this.ev = creeEv();
        this.Objet = null;

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
    public void setExperience(long Experience){
        this.Experience = Experience;
    }
    public void setNiveau(int Niveau){
        this.Niveau = Niveau;
    }
    public int getLv(){
        return Niveau;
    }
    public double getXp(){
        return Experience;
    }
    public int getIv(){ return iv; }
    public void setIv(int iv){ this.iv = iv; }
    public int getEv(){ return ev; }
    public void setEv(int ev){ this.ev = ev; }
    public String getObjet(){ return Objet; }
    public void setObjet(String Objet){ this.Objet = Objet; }

    //methodes mères
    public void setLv(int i) {
        this.Niveau = i;
    }

    public int creeEv() {
        Random random = new Random();
        // Génère un entier aléatoire entre 0 et 509 inclus
        return random.nextInt(510); // 510 car la borne supérieure est exclusive
    }

    public int creeIv(){
        Random random = new Random();
        // Génère un entier aléatoire entre 0 et 31 inclus
        return random.nextInt(32); // 32 car la borne supérieure est exclusive
    }

/*

    public void fuite(Mystimon ennemi){
    }

    public void usageParchemin(Parchemin parchemin){
    }*/

    public ArrayList<String> ConvertionTypes() {
        ArrayList<String> listeTypes = new ArrayList<>();

        for (Types type : this.liste_types) { // Parcourir chaque élément de la liste d'enum
            listeTypes.add(type.toString()); // Convertir chaque enum en String et l'ajouter à la liste
        }
        return listeTypes;
    }


}




