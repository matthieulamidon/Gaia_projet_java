package fr.eseo.gaia_projet_java.combatDeMystimon;

import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUser;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class InvocateurVsAdversaire {
    private DAOUser daoUser;
    private Joueur joueur;
    private Adversaire adversaire;
    //Mystimon allier
    private List<Exemplemon> listeMystimonAllier;
    private Mystimon mystimonAllier;
    //Mystimon adversaire
    private List<Exemplemon> listeMystimonAdversaire;
    private Mystimon mystimonAdversaire;
    //liste des attaque
    private List<AttaqueCombat> listeAttaque;

    InvocateurVsAdversaire(Joueur joueur, Adversaire adversaire) throws SQLException {
        this.joueur = joueur;
        this.adversaire = adversaire;
        daoUser = new DAOUserMariaDB();
        //recuperation de l'équipe du joueur
        this.listeMystimonAllier = daoUser.readLectuceDeLequipe();
        this.mystimonAllier = listeMystimonAllier.get(0);
        //recuperation de l'équipe de l'adversaire
        this.listeMystimonAdversaire = daoUser.readLectuceDeEquipeAdverse(adversaire.getId());
        this.mystimonAdversaire = listeMystimonAdversaire.get(0);
        this.listeAttaque = daoUser.LectuceDeEquipeAttaque();
    }

    private double avantageDeType(Types types1,Types typesAttaque){
        double cm =1;
        switch (typesAttaque) {
            case feu:
                if(types1==Types.plante||types1==Types.tenebres){
                    cm = 2;
                }
                if(types1==Types.eau||types1==Types.terre){
                    cm = 0.5;
                }
                break;
            case eau:
                if(types1==Types.feu||types1==Types.terre){
                    cm = 2;
                }
                if(types1==Types.foudre||types1==Types.plante){
                    cm = 0.5;
                }
                break;
            case plante:
                if(types1==Types.eau||types1==Types.normal){
                    cm = 2;
                }
                if(types1==Types.feu||types1==Types.foudre){
                    cm = 0.5;
                }
                break;
            case tenebres:
                if(types1==Types.dragon||types1==Types.normal){
                    cm = 2;
                }
                if(types1==Types.fee||types1==Types.lumiere){
                    cm = 0.5;
                }
                break;
            case dragon:
                if(types1==Types.dragon||types1==Types.fee){
                    cm = 2;
                }
                if(types1==Types.tenebres||types1==Types.fee){
                    cm = 0.5;
                }
                break;
            case fee:
                if(types1==Types.tenebres||types1==Types.dragon){
                    cm = 2;
                }
                if(types1==Types.dragon||types1==Types.fee){
                    cm = 0.5;
                }
                break;
            case foudre:
                if(types1==Types.eau||types1==Types.plante){
                    cm = 2;
                }
                if(types1==Types.terre){
                    cm = 0;
                }
                break;
            case terre:
                if(Types.feu==Types.plante||types1==Types.foudre){
                    cm = 2;
                }
                if(types1==Types.eau||types1==Types.plante){
                    cm = 0.5;
                }
                break;
            case normal:
                if(types1==Types.tenebres){
                    cm = 0.5;
                }
                break;
            case lumiere:
                if(types1==Types.tenebres){
                    cm = 2;
                }
                break;

        }
        return cm;
    }

    public static boolean estCoupCritique() {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 99 (100 possibilités)
        int chance = random.nextInt(100);

        // Si le nombre est inférieur à 6, c'est un coup critique (6,25% de chance)
        return chance < 6;
    }

    public static boolean toucher(int pressision) {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 99 (100 possibilités)
        int chance = random.nextInt(100);

        // Si le nombre est inférieur à la pressision, c'est un toucher
        return chance < pressision;
    }

    public static double genererNombreAleatoirePourLeCm() {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 0.15, puis l'ajoute à 0.85
        double nombre = 0.85 + (0.15 * random.nextDouble());
        return nombre;
    }

    public void iaAttaque(Mystimon mystimonAllier, Mystimon mystimonAdversaire){

    }

    public void attaquer(String attaque, Mystimon mystimonAllier, Mystimon mystimonAdversaire) {
        AttaqueCombat attaqueIminante = null;
        for (int i = 0; i < listeAttaque.size(); i++) {
            if (attaque == listeAttaque.get(i).getNom()) {
                attaqueIminante = listeAttaque.get(i);
                i = listeAttaque.size();
            }
        }
        HashMap<String,Integer> statsMystimonAdverse =mystimonAdversaire.getStats();
        HashMap<String,Integer> statsMystimonAlier =mystimonAdversaire.getStats();

        switch (attaqueIminante.getEffet()) {
            case "atk":
                int pvSubit=0;
                //calcul du cm
                double Cm = 1; //attaqueIminante.getPuissance();
                Types types1 = mystimonAdversaire.getListeTypes().get(0);
                Types types2 = mystimonAdversaire.getListeTypes().get(1);
                //le STAB
                if(attaqueIminante.getTypes()==types1){
                    Cm = Cm+0.5;
                }
                if(attaqueIminante.getTypes()==types2){
                    Cm = Cm+0.5;
                }
                Types typesAdv1 = mystimonAdversaire.getListeTypes().get(0);
                Types typesAdv2 = mystimonAdversaire.getListeTypes().get(1);
                Cm = Cm*(avantageDeType(typesAdv1, attaqueIminante.getTypes())+avantageDeType(typesAdv2, attaqueIminante.getTypes()));

                if(estCoupCritique()){
                    Cm=Cm*( (2*mystimonAllier.getNiveau()+5) / (mystimonAllier.getNiveau()+5) );
                }
                Cm = Cm*genererNombreAleatoirePourLeCm();

                switch (attaqueIminante.getTypes()) {
                    case feu:
                        if(types1==Types.plante||types2==Types.plante){
                            Cm =2* Cm;
                        }
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            Cm =2* Cm;
                        }
                        break;
                    case eau:
                        if(types1==Types.feu||types2==Types.feu){
                            Cm =2* Cm;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            Cm =2* Cm;
                        }
                        break;
                    case plante:
                        if(types1==Types.eau||types2==Types.eau){
                            Cm =2* Cm;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            Cm =2* Cm;
                        }
                        break;
                    case tenebres:
                        if(types1==Types.dragon||types2==Types.dragon){
                            Cm =2* Cm;
                        }
                        if(types1==Types.normal||types2==Types.normal){
                            Cm =2* Cm;
                        }
                        break;
                    case dragon:
                        if(types1==Types.dragon||types2==Types.dragon){
                            Cm =2* Cm;
                        }
                        if(types1==Types.fee||types2==Types.fee){
                            Cm =2* Cm;
                        }
                        break;
                    case fee:
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            Cm =2* Cm;
                        }
                        if(types1==Types.dragon||types2==Types.dragon){
                            Cm =2* Cm;
                        }
                        break;
                    case foudre:
                        if(types1==Types.feu||types2==Types.feu){
                            Cm =2* Cm;
                        }
                        if(types1==Types.plante||types2==Types.plante){
                            Cm =2* Cm;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            Cm =0;
                        }
                        break;
                    case terre:
                        if(types1==Types.plante||types2==Types.plante){
                            Cm =2* Cm;
                        }
                        if(types1==Types.foudre||types2==Types.foudre){
                            Cm =2* Cm;
                        }
                        break;
                    case normal:

                        break;
                    case lumiere:
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            Cm =2* Cm;
                        }
                        break;
                }

                if(attaqueIminante.getAspect()=="physique"){
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAllier.getNiveau()*0.4+2)*statsMystimonAdverse.get("ATK")*attaqueIminante.getPuissance())/statsMystimonAdverse.get("DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }else{
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAllier.getNiveau()*0.4+2)*statsMystimonAdverse.get("SP_ATK")*attaqueIminante.getPuissance())/statsMystimonAdverse.get("SP_DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }
                if(toucher(attaqueIminante.getPressision())){
                    if(mystimonAdversaire.getPv()-pvSubit>=0){
                        mystimonAdversaire.setPv(mystimonAdversaire.getPv()-pvSubit);
                    }else {
                        //c'est mort
                    }
                }

                break;

            case "def":
                break;

            case "atkBuff":
                break;

            case "atkdeBuff":
                break;
        }

    }

}
