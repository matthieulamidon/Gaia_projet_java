package fr.eseo.gaia_projet_java.combatDeMystimon;

import fr.eseo.gaia_projet_java.Attaques.Attaque;
import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUser;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Invocateur;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.SQLException;
import java.util.List;

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

    public void attaqueAllier(String attaque, Mystimon mystimonAllier, Mystimon mystimonAdversaire) {
        AttaqueCombat attaqueIminante = null;
        for (int i = 0; i < listeAttaque.size(); i++) {
            if (attaque == listeAttaque.get(i).getNom()) {
                attaqueIminante = listeAttaque.get(i);
                i = listeAttaque.size();
            }
        }
        switch (attaqueIminante.getEffet()) {
            case "atk":
                int degat = attaqueIminante.getPuissance();
                if(attaqueIminante.getTypes()==mystimonAllier.getListeTypes().get(0)|attaqueIminante.getTypes()==mystimonAllier.getListeTypes().get(1)){
                    degat=2*degat;
                }
                Types types1 = mystimonAdversaire.getListeTypes().get(0);
                Types types2 = mystimonAdversaire.getListeTypes().get(1);
                switch (attaqueIminante.getTypes()) {
                    case feu:
                        if(types1==Types.plante||types2==Types.plante){
                            degat=2*degat;
                        }
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            degat=2*degat;
                        }
                        break;
                    case eau:
                        if(types1==Types.feu||types2==Types.feu){
                            degat=2*degat;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            degat=2*degat;
                        }
                        break;
                    case plante:
                        if(types1==Types.eau||types2==Types.eau){
                            degat=2*degat;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            degat=2*degat;
                        }
                        break;
                    case tenebres:
                        if(types1==Types.dragon||types2==Types.dragon){
                            degat=2*degat;
                        }
                        if(types1==Types.normal||types2==Types.normal){
                            degat=2*degat;
                        }
                        break;
                    case dragon:
                        if(types1==Types.dragon||types2==Types.dragon){
                            degat=2*degat;
                        }
                        if(types1==Types.fee||types2==Types.fee){
                            degat=2*degat;
                        }
                        break;
                    case fee:
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            degat=2*degat;
                        }
                        if(types1==Types.dragon||types2==Types.dragon){
                            degat=2*degat;
                        }
                        break;
                    case foudre:
                        if(types1==Types.feu||types2==Types.feu){
                            degat=2*degat;
                        }
                        if(types1==Types.plante||types2==Types.plante){
                            degat=2*degat;
                        }
                        if(types1==Types.terre||types2==Types.terre){
                            degat=0;
                        }
                        break;
                    case terre:
                        if(types1==Types.plante||types2==Types.plante){
                            degat=2*degat;
                        }
                        if(types1==Types.foudre||types2==Types.foudre){
                            degat=2*degat;
                        }
                        break;
                    case normal:

                        break;
                    case lumiere:
                        if(types1==Types.tenebres||types2==Types.tenebres){
                            degat=2*degat;
                        }
                        break;
                }
                if(attaqueIminante.getAspect()=="physique"){

                }else{

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
