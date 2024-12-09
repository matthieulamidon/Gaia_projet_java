package fr.eseo.gaia_projet_java.combatDeMystimon;

import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUser;
import fr.eseo.gaia_projet_java.DataBaseSQL.dao.DAOUserMariaDB;
import fr.eseo.gaia_projet_java.HelloApplication;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.controller.Map_controller;
import fr.eseo.gaia_projet_java.enumerations.Types;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static fr.eseo.gaia_projet_java.enumerations.Types.nul;
import static java.lang.reflect.Array.get;

/**
 * C'est notre classe majeure qui gere toute la logique du combat et discute avec tous les controller de combat
 * @author Matthieu Lamidon
 * @version
 * @since
 */
public class InvocateurVsAdversaire {
    private final DAOUser daoUser;
    private final Joueur joueur;
    private final Adversaire adversaire;
    //Mystimon allier
    private final List<Exemplemon> listeMystimonAllier;
    private Exemplemon mystimonAllier;
    //Mystimon adversaire
    private final List<Exemplemon> listeMystimonAdversaire;
    private Exemplemon mystimonAdversaire;
    //liste des attaques
    private final List<AttaqueCombat> listeAttaque;

    /**
     * @param joueur le joueur
     * @param adversaire l'adversaire
     * @throws SQLException
     */
    public InvocateurVsAdversaire(Joueur joueur, Adversaire adversaire) throws SQLException {
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

    /**
     * @param joueur le joueur
     * @param mystimonAdversaire le mystimon sauvage generer precedament
     * @param lv le niveau du mystiùpn voulu avant
     * @throws SQLException
     */
    public InvocateurVsAdversaire(Joueur joueur,Exemplemon mystimonAdversaire, int lv) throws SQLException {
        this.joueur = joueur;
        //this.adversaire = adversaire("gerard",mystimonAdversaire);
        daoUser = new DAOUserMariaDB();
        //recuperation de l'équipe du joueur
        this.listeMystimonAllier = daoUser.readLectuceDeLequipe();
        this.mystimonAllier = listeMystimonAllier.get(0);
        //recuperation de l'équipe de l'adversaire
        ArrayList<Exemplemon> listeMystimonAdversairetampon = new ArrayList<>();
        this.mystimonAdversaire=mystimonAdversaire;
        listeMystimonAdversairetampon.add(this.mystimonAdversaire);
        this.listeMystimonAdversaire =listeMystimonAdversairetampon;
        this.adversaire = new Adversaire("gerard",listeMystimonAdversaire);
        this.listeAttaque = daoUser.LectuceDeEquipeAttaque();
    }
    /**
    *donne le nom du 1er mystimon
    *@return le nom
    */
    public String getNomMystimonAlier(){
        return mystimonAllier.getNom();
    }

    /**
    *donne le nom du 1er mystimon de l'adversaire
    *@return le nom
    */
    public String getNomMystimonAdv(){
        return mystimonAdversaire.getNom();
    }

    /**
    *donne le level du mystimon adverse
    *@return le level sous la forme de string
    */
    public String getlvAdv(){
        return String.valueOf(mystimonAdversaire.getLv());
    }

    /**
    *donne le level du mystimon allier
    *@return le level sous la forme de string
    */
    public String getlvAlier(){
        return String.valueOf(mystimonAllier.getLv());
    }

    /**
    *donne le pv du mystimon allier
    *@return les pv en int
    */
    public int getPvAlier(){
        return mystimonAllier.getPv();
    }

    /**
    *donne le pv du mystimon adverse
    *@return les pv en int
    */
    public int getPvAdv(){
        return mystimonAdversaire.getPv();
    }

    /**
    *permet de faire un switch de mystimon
    *@param n numero du mystimon a switch
    */
    public void swichMystimonAllier(int n){
        if (mystimonNexxiste(n)){
            Exemplemon tanpon = mystimonAllier;
            mystimonAllier = listeMystimonAllier.get(n);
            listeMystimonAllier.set(0,mystimonAllier);
            listeMystimonAllier.set(n,tanpon);
        }
    }

    /**
    *permet de dire qu'elle mystimon commence
    *@return true si ton mystimon commence
    **/
    public boolean invocateurCommence() {
        // Gestion des nulls pour éviter les erreurs
        Integer pvAllier = mystimonAllier.getStats().get("VIT");
        Integer pvAdversaire = mystimonAdversaire.getStats().get("VIT");

        if (pvAllier == null || pvAdversaire == null) {
            throw new IllegalArgumentException("Les statistiques 'pv' doivent être définies pour les deux mystimons.");
        }

        return pvAllier >= pvAdversaire;
    }

    /**
    *permet de calculer le ratio de pv pour les bar de vie du mystimon allier
    *@return double ratio pv pour les bar de vie
    */
    public Double getRatioPvAlier(){
        //int pvMax = mystimonAllier.getStats().get("PV");
        //int pvMin = mystimonAllier.getPv();
        //return Double.valueOf(pvMin/pvMax);
        double progress = (double) mystimonAllier.getPv() / mystimonAllier.getStats().get("PV");
        return progress;
    }

    /**
    *permet de calculer le ratio de pv pour les bar de vie du mystimon adverse
    *@return double ratio pv pour les bar de vie
    */
    public Double getRatioPvAdv(){
        //int pvMax = mystimonAdversaire.getStats().get("PV");
        //int pvMin = mystimonAdversaire.getPv();
        //return Double.valueOf(pvMin/pvMax);
        double progress = (double) mystimonAdversaire.getPv() / mystimonAdversaire.getStats().get("PV");
        return progress;
    }

    /**
    *renvoie le nom du mystimon allié N demandé
    *@param n l'incice du mystimon demander
    *@return le nom du mystimon
    */
    public String getnomMystimonN(int n){
        return listeMystimonAllier.get(n).getNom();
    }
    /**
    *renvoie le level du mystimon allié N demandé
    *@param n l'incice du mystimon demander
    *@return le level du mystimon
    */
    public String getLvMystimonN(int n){
        return String.valueOf(listeMystimonAllier.get(n).getLv());
    }
    /**
    *renvoie le level du mystimon allié N demandé
    *@param n l'incice du mystimon demander
    *@return les pv du mystimon
    */
    public String getPvMystimonN(int n){
        return String.valueOf(listeMystimonAllier.get(n).getPv());
    }
    /**
    *verifie si lz mystimon Allié N existe
    *@param n l'incice du mystimon demander
    *@return true si le mystimon n existe
    */
    public Boolean mystimonNexxiste(int n){
        if(listeMystimonAllier.size()>n){
            return true;
        }else{
            return false;
        }
    }
    /**
    *verifie si le mystimon Adverse N existe
    *@param n l'incice du mystimon demander
    *@return true si le mystimon Adverse N existe
    */
    public Boolean mystimonNexxisteAdv(int n){
        if(listeMystimonAdversaire.size()>n){
            return true;
        }else{
            return false;
        }
    }
    /**
    *renvoie-les pv du mystimon adverse N
    *@param n l'incice du mystimon demander
    *@return en String le nombre de pv
    */
    public String getPvMystimonNAdv(int n){
        return String.valueOf(listeMystimonAdversaire.get(n).getPv());
    }
    /**
    *renvoie le ratio de pv du mystimon adverse N
    *@param n l'incice du mystimon demander
    *@return en double le ratio de pv pour les bar de vie
    */
    public Double getRatioPvMystimonN(int n){

        double progress = (double) listeMystimonAllier.get(n).getPv() / listeMystimonAllier.get(n).getStats().get("PV");
        return progress;
    }
    /**
    *renvoie la liste d'exemplemon allié
    *@return la liste d'exemplemon allié
    */
    public List<Exemplemon> getListeMystimonAllier(){
        return listeMystimonAllier;
    }
    /**
    *renvoie la liste des attaques d'exemplemon allié
    *@return la Arrayliste des attaques d'exemplemon allié
    */
    public ArrayList<String> getListeAttaque(){
        return mystimonAllier.getListeAttaques();
    }
    /**
    *renvoie en double un coeficien de degat suplementaire si il ya avantage de type
    *@param types1 du mystimon toucher
    *@param typesAttaque type de l'attaque envoyé
    *@return le coefiicient de degat suplementaire
    */
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

    /**
    *renvoie si l'attaque est un coup critique
    *@return true si le coup est critique
    */
    public static boolean estCoupCritique() {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 99 (100 possibilités)
        int chance = random.nextInt(100);

        // Si le nombre est inférieur à 6, c'est un coup critique (6,25% de chance)
        return chance < 6;
    }
    /**
    *renvoie si l'attaque touche
    *@param pressision la pressision de l'attaque
    *@return true si on touche
    */
    public boolean toucher(int pressision) {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 99 (100 possibilités)
        int chance = random.nextInt(100);

        // Si le nombre est inférieur à la pressision, c'est un toucher
        return chance < pressision;
    }
    /**
    *renvoie un coefficient aléatoire de modification de dégats
    *@return un double du coeficient de degats
    */
    public double genererNombreAleatoirePourLeCm() {
        Random random = new Random();
        // Génère un nombre aléatoire entre 0 et 0.15, puis l'ajoute à 0.85
        double nombre = 0.85 + (0.15 * random.nextDouble());
        return nombre;
    }

    /**
    *fonction d'attaque de l'adversaire fait tout en gros
    *@return renvoie ce qui ce passe dans le combat
    */
    public ArrayList<String> iaAttaque(){
        ArrayList<String> dialogue = new ArrayList<>();;
        String ligneDeDialogue;

        List<String> listeAttaques = mystimonAdversaire.getListeAttaques();
        Random random = new Random();
        String attaqueAleatoire = listeAttaques.get(random.nextInt(listeAttaques.size()));

        AttaqueCombat attaqueIminante = null;
        for (int i = 0; i < listeAttaque.size(); i++) {
            if (attaqueAleatoire.equals(listeAttaque.get(i).getNom())) { // Comparaison correcte de chaînes
                attaqueIminante = listeAttaque.get(i);
                ligneDeDialogue=mystimonAdversaire.getNom()+" attaque avec "+listeAttaque.get(i).getNom();
                dialogue.add(ligneDeDialogue);
                break; // Quitte la boucle dès que l'attaque correspond
            }
        }
        HashMap<String,Integer> statsMystimonAdverse =mystimonAdversaire.getStats();
        HashMap<String,Integer> statsMystimonAlier =mystimonAllier.getStats();

        switch (attaqueIminante.getEffet()) {
            case "atk":
                int pvSubit=0;
                //calcul du cm
                double Cm = 1; //attaqueIminante.getPuissance();
                Types types1 = mystimonAdversaire.getListeTypes().get(0);
                //Types types2 = mystimonAdversaire.getListeTypes().get(1);
                //le STAB
                if(attaqueIminante.getTypes()==types1){
                    Cm = Cm+0.5;
                }
                if(mystimonAdversaire.getListeTypes().size()==2) {
                    Types types2 = mystimonAdversaire.getListeTypes().get(1);
                    //le STAB
                    if(attaqueIminante.getTypes()==types2){
                        Cm = Cm+0.5;
                    }
                }
                Types typesAdv1 = mystimonAllier.getListeTypes().get(0);
                //Types typesAdv2 = mystimonAllier.getListeTypes().get(1);
                //Cm = Cm*(avantageDeType(typesAdv1, attaqueIminante.getTypes())+avantageDeType(typesAdv2, attaqueIminante.getTypes()));

                int sitypedeux=0;
                Types typesAdv2 = nul;
                if(mystimonAllier.getListeTypes().size()==2) {
                    typesAdv2 = mystimonAllier.getListeTypes().get(1);
                    sitypedeux = (int) avantageDeType(typesAdv2, attaqueIminante.getTypes());
                }
                Cm = Cm+(avantageDeType(typesAdv1, attaqueIminante.getTypes())+sitypedeux);


                if(estCoupCritique()){
                    Cm=Cm*( (2*mystimonAdversaire.getNiveau()+5) / (mystimonAdversaire.getNiveau()+5) );
                    ligneDeDialogue="coup critique";
                    dialogue.add(ligneDeDialogue);
                }
                Cm = Cm*genererNombreAleatoirePourLeCm();

                switch (attaqueIminante.getTypes()) {
                    case feu:
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case eau:
                        if(typesAdv1==Types.feu||typesAdv2==Types.feu){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case plante:
                        if(typesAdv1==Types.eau||typesAdv2==Types.eau){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case tenebres:
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.normal||typesAdv2==Types.normal){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case dragon:
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.fee||typesAdv2==Types.fee){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case fee:
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case foudre:
                        if(typesAdv1==Types.feu||typesAdv2==Types.feu){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =0;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case terre:
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.foudre||typesAdv2==Types.foudre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque absorber";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case normal:

                        break;
                    case lumiere:
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                }

                if(attaqueIminante.getAspect()=="physique"){
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAdversaire.getNiveau()*0.4+2)*statsMystimonAdverse.get("ATK")*attaqueIminante.getPuissance())/statsMystimonAlier.get("DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }else{
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAdversaire.getNiveau()*0.4+2)*statsMystimonAdverse.get("SP_ATK")*attaqueIminante.getPuissance())/statsMystimonAlier.get("SP_DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }
                if(toucher(attaqueIminante.getPressision())){
                    if(mystimonAllier.getPv()-pvSubit>=0){
                        mystimonAllier.setPv(mystimonAllier.getPv()-pvSubit);
                        ligneDeDialogue="l'attaque inflige "+pvSubit+" a "+mystimonAdversaire.getNom();
                        dialogue.add(ligneDeDialogue);
                    }else {
                        mystimonAllier.setPv(0);//c'est mort
                        ligneDeDialogue=mystimonAllier.getNom()+" est ko " ;
                        dialogue.add(ligneDeDialogue);
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
        return dialogue;

    }

    /**
    *fonction d'attaque de notre Mystimon fait tout en gros
    *@param attaque le nom de l'attaque utiliser
    *@return renvoie ce qui ce passe dans le combat
    */
    public ArrayList<String> attaquer(String attaque) {
        AttaqueCombat attaqueIminante = null;
        ArrayList<String> dialogue = new ArrayList<>();;
        String ligneDeDialogue;

        for (int i = 0; i < listeAttaque.size(); i++) {
            if (attaque.equals(listeAttaque.get(i).getNom())) { // Comparaison correcte de chaînes
                attaqueIminante = listeAttaque.get(i);
                ligneDeDialogue=mystimonAllier.getNom()+" attaque avec "+listeAttaque.get(i).getNom();
                dialogue.add(ligneDeDialogue);
                break; // Quitte la boucle dès que l'attaque correspond
            }
        }

        // la securiter
        if (attaqueIminante == null) {
            dialogue.add("L'attaque spécifiée n'existe pas.");
            return dialogue;
        }

        HashMap<String,Integer> statsMystimonAdverse =mystimonAdversaire.getStats();
        HashMap<String,Integer> statsMystimonAlier =mystimonAdversaire.getStats();

        switch (attaqueIminante.getEffet()) {
            case "atk":
                int pvSubit=0;
                //calcul du cm
                double Cm = 1; //attaqueIminante.getPuissance();
                Types types1 = mystimonAdversaire.getListeTypes().get(0);
                if(mystimonAdversaire.getListeTypes().size()==2) {
                    Types types2 = mystimonAdversaire.getListeTypes().get(1);
                    //le STAB
                    if(attaqueIminante.getTypes()==types2){
                        Cm = Cm+0.5;
                    }
                }
                //le STAB
                if(attaqueIminante.getTypes()==types1){
                    Cm = Cm+0.5;

                }
                int sitypedeux=0;
                Types typesAdv1 = mystimonAdversaire.getListeTypes().get(0);
                Types typesAdv2 = nul;
                if(mystimonAdversaire.getListeTypes().size()==2) {
                    typesAdv2 = mystimonAdversaire.getListeTypes().get(1);
                    sitypedeux = (int) avantageDeType(typesAdv2, attaqueIminante.getTypes());
                }
                Cm = Cm+(avantageDeType(typesAdv1, attaqueIminante.getTypes())+sitypedeux);

                if(estCoupCritique()){
                    Cm=Cm*( (2*mystimonAllier.getNiveau()+5) / (mystimonAllier.getNiveau()+5) );
                    ligneDeDialogue="coup critique";
                    dialogue.add(ligneDeDialogue);
                }
                Cm = Cm*genererNombreAleatoirePourLeCm();

                switch (attaqueIminante.getTypes()) {
                    case feu:
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case eau:
                        if(typesAdv1==Types.feu||typesAdv2==Types.feu){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case plante:
                        if(typesAdv1==Types.eau||typesAdv2==Types.eau){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case tenebres:
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.normal||typesAdv2==Types.normal){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case dragon:
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.fee||typesAdv2==Types.fee){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case fee:
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.dragon||typesAdv2==Types.dragon){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case foudre:
                        if(typesAdv1==Types.feu||typesAdv2==Types.feu){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.terre||typesAdv2==Types.terre){
                            Cm =0;
                            ligneDeDialogue="l'attaque est absorber";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case terre:
                        if(typesAdv1==Types.plante||typesAdv2==Types.plante){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        if(typesAdv1==Types.foudre||typesAdv2==Types.foudre){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                    case normal:

                        break;
                    case lumiere:
                        if(typesAdv1==Types.tenebres||typesAdv2==Types.tenebres){
                            Cm =2* Cm;
                            ligneDeDialogue="l'attaque est super efficace";
                            dialogue.add(ligneDeDialogue);
                        }
                        break;
                }

                if(attaqueIminante.getAspect()=="physique"){
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAllier.getNiveau()*0.4+2)*statsMystimonAlier.get("ATK")*attaqueIminante.getPuissance())/statsMystimonAdverse.get("DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }else{
                    //formule de l'enfer
                    double pvManquant=(((((mystimonAllier.getNiveau()*0.4+2)*statsMystimonAlier.get("SP_ATK")*attaqueIminante.getPuissance())/statsMystimonAdverse.get("SP_DEF"))/50)+2)*Cm;
                    pvSubit = (int) Math.round(pvManquant);
                }
                if(toucher(attaqueIminante.getPressision())){
                    if(mystimonAdversaire.getPv()-pvSubit>=0){
                        mystimonAdversaire.setPv(mystimonAdversaire.getPv()-pvSubit);
                        ligneDeDialogue="l'attaque inflige "+pvSubit+" a "+mystimonAdversaire.getNom();
                        dialogue.add(ligneDeDialogue);
                    }else {
                        //c'est mort
                        mystimonAdversaire.setPv(0);
                        ligneDeDialogue=mystimonAdversaire.getNom()+ " est ko";
                        dialogue.add(ligneDeDialogue);
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
        return dialogue;

    }

    /**
    *permet a l'adversaire de changer de mystimon
    *@param n le numero n du mystimon avec lequel il doit switch
    */
    public void switchMystimonAdv(int n){
        if (mystimonNexxisteAdv(n)){
            Exemplemon tanpon = mystimonAdversaire;
            mystimonAdversaire = listeMystimonAdversaire.get(n);
            listeMystimonAdversaire.set(0,mystimonAdversaire);
            listeMystimonAdversaire.set(n,tanpon);
        }
    }
    /**
    *pas fini mais proto pour faire level up le mystimon
    */
    public void gainXp(){//bah oui sans objet c'est ridicule
        int b=100;
        int N =mystimonAdversaire.getLv();
        int xp=(b*N)/7;
        xp+=mystimonAllier.getExperience();
        if(xp>=(mystimonAllier.getLv()*mystimonAllier.getLv()*mystimonAllier.getLv())){
            xp-=(mystimonAllier.getLv()*mystimonAllier.getLv()*mystimonAllier.getLv());
            mystimonAllier.setExperience(xp);
            mystimonAllier.setLv(mystimonAllier.getLv()+1);
            levelUp();
        }
    }

    /**
    *pas fini mais proto pour faire level up le mystimon
    */
    public void levelUp(){
        HashMap<String,Integer> state=new HashMap<>();
        int pv=((((2*mystimonAllier.getStats().get("PV"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100)+mystimonAllier.getLv()+10;
        int atk=((((2*mystimonAllier.getStats().get("ATK"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100);
        int sp_atk=((((2*mystimonAllier.getStats().get("SP_ATK"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100);
        int def=((((2*mystimonAllier.getStats().get("DEF"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100);
        int sp_def=((((2*mystimonAllier.getStats().get("SP_DEF"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100);
        int vit=((((2*mystimonAllier.getStats().get("VIT"))+mystimonAllier.getIv()+(mystimonAllier.getEv()/4))*mystimonAllier.getLv())/100);
        state.put("PV",pv);
        state.put("ATK",atk);
        state.put("SP_ATK",sp_atk);
        state.put("DEF",def);
        state.put("SP_DEF",sp_def);
        state.put("VIT",vit);
        mystimonAllier.setStats(state);
    }


}
