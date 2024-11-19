package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.DataBaseSQL.JsonParserUtils;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Parchemins.Parchemin;
import fr.eseo.gaia_projet_java.enumerations.Effet;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fr.eseo.gaia_projet_java.DataBaseSQL.JsonParserUtils.jsonToArrayList;
import static fr.eseo.gaia_projet_java.DataBaseSQL.config.DatabaseInitializer.getConnection;

public class DAOUserMariaDB implements DAOUser {


    @Override
    public List<Exemplemon> nouveauMystimon() throws SQLException {
        List<Exemplemon> users = new ArrayList<>();
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, xp, lv, pv, Stat, types, attaque FROM mystidex;")) {

            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                int xp = resultat.getInt("xp");
                int lv = resultat.getInt("lv");
                int pv = resultat.getInt("pv");

                // Récupérer et convertir Stat, types, et attaque
                String statJson = resultat.getString("Stat");
                List<Integer> listeStats = JsonParserUtils.parseJsonToListInt(statJson);

                String typesJson = resultat.getString("types");
                List<String> listeTypes = JsonParserUtils.parseJsonToListString(typesJson);

                String attaqueJson = resultat.getString("attaque");
                Map<Integer, String> listeAttaques = JsonParserUtils.parseJsonToMapIntString(attaqueJson);

                //ArrayList<Types> listeTypesConverti = new ArrayList();
                ArrayList<Types> listeTypesConverti=TraductionStringTypes(listeTypes);

                ArrayList<String> listeAttaqueConverti = TraductionStringAttaques(listeAttaques,5);

                HashMap<String, Integer> listeStatesConverti = TraductionStateListeMaps(listeStats);

                // Créer et ajouter un nouvel objet Mystimon //, pv, listeStats
                users.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, 0, 5, listeStatesConverti, pv));
            }
            return users;
        }

    }

    //revoie toute l'equipe de mystimon
    @Override
    public List<Exemplemon> readLectuceDeLequipe() throws SQLException {
        List<Exemplemon> equipe = new ArrayList<>();
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, pv, xp, lv, ev, iv, Stat, types, attaque, objet  FROM equipe;")) {
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                int xp = resultat.getInt("xp");
                int lv = resultat.getInt("lv");
                int pv = resultat.getInt("pv");
                int iv = resultat.getInt("iv");
                int ev = resultat.getInt("ev");
                String objet = resultat.getString("objet");

                // Récupérer et convertir Stat, types, et attaque
                String statJson = resultat.getString("Stat");
                List<Integer> listeStats = JsonParserUtils.parseJsonToListInt(statJson);

                String typesJson = resultat.getString("types");
                List<String> listeTypes = JsonParserUtils.parseJsonToListString(typesJson);

                String attaqueJson = resultat.getString("attaque");
                //List<String> listeAttaques = JsonParserUtils.parseJsonToListString(attaqueJson);

                //ArrayList<Types> listeTypesConverti = new ArrayList();
                ArrayList<Types> listeTypesConverti=TraductionStringTypes(listeTypes);

                String jsonStringList=typesJson;

                ArrayList<String> listeAttaqueConverti = jsonToArrayList(attaqueJson, String.class);
                HashMap<String, Integer> listeStatesConverti = TraductionStateListeMaps(listeStats);

                equipe.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, xp, lv, listeStatesConverti, pv));
            }
            return equipe;
        }
    }

    @Override
    public List<Exemplemon> readLectuceDeEquipeAdverse(int nbAdv) throws SQLException {
        List<Exemplemon> equipe = new ArrayList<>();
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, pv, xp, lv, ev, iv, Stat, types, attaque, objet  FROM equipedupnj"+nbAdv+";")) {
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");
                int xp = resultat.getInt("xp");
                int lv = resultat.getInt("lv");
                int pv = resultat.getInt("pv");
                int iv = resultat.getInt("iv");
                int ev = resultat.getInt("ev");
                String objet = resultat.getString("objet");

                // Récupérer et convertir Stat, types, et attaque
                String statJson = resultat.getString("Stat");
                List<Integer> listeStats = JsonParserUtils.parseJsonToListInt(statJson);

                String typesJson = resultat.getString("types");
                List<String> listeTypes = JsonParserUtils.parseJsonToListString(typesJson);

                String attaqueJson = resultat.getString("attaque");
                //List<String> listeAttaques = JsonParserUtils.parseJsonToListString(attaqueJson);

                //ArrayList<Types> listeTypesConverti = new ArrayList();
                ArrayList<Types> listeTypesConverti=TraductionStringTypes(listeTypes);

                String jsonStringList =typesJson;

                ArrayList<String> listeAttaqueConverti = jsonToArrayList(attaqueJson, String.class);
                HashMap<String, Integer> listeStatesConverti = TraductionStateListeMaps(listeStats);

                equipe.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, xp, lv, listeStatesConverti, pv));
            }
            return equipe;
        }
    }

    @Override
    public List<AttaqueCombat> LectuceDeEquipeAttaque() throws SQLException {
        List<AttaqueCombat> attaqueCombats = new ArrayList<>();
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, puissance, types, effet, aspect, pressision FROM attaque;")) {
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom= resultat.getString("nom");
                int puissance = resultat.getInt("puissance");
                String types = resultat.getString("types");
                String effet = resultat.getString("effet");
                String aspect = resultat.getString("aspect");
                int pressision = resultat.getInt("pressision");

                Types typesTraduit = TraductionsanslisteStringTypes(types);

                //String attaqueJson = resultat.getString("attaque");
                //Map<Integer, String> listeAttaques = JsonParserUtils.parseJsonToMapIntString(attaqueJson);

                attaqueCombats.add(new AttaqueCombat(id, nom, puissance, typesTraduit, effet, aspect, pressision));
            }
            return attaqueCombats;
        }
    }

    public Joueur readLectureJoueur() throws SQLException {
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, listeDObjet, coordonner FROM promethee;")) {
            Joueur joueur = null;
            while (resultat.next()) {
                int id = resultat.getInt("id");
                String nom = resultat.getString("nom");

                ArrayList<Parchemin> jsp = null;
                ArrayList<Integer> possition = new ArrayList<>(1);
                possition.add(2);
                joueur = new Joueur(id, nom, readLectuceDeLequipe(), jsp, possition);
            }
            return joueur;
        }
    }

    public Adversaire readLectureAdversaire() throws SQLException {
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, listeDObjet, coordonner FROM promethee;"))
                     {
            Adversaire adversaire = null;
            while (resultat.next()) {
                int id = 0;//resultat.getInt("id");
                String nom = "gerard";//resultat.getString("nom");

                ArrayList<Parchemin> jsp = null;
                ArrayList<Integer> possition = new ArrayList<>(1);
                possition.add(2);
                adversaire = new Adversaire(id, nom, readLectuceDeEquipeAdverse(id),  jsp,  possition);
            }
            return adversaire;
        }
    }

    HashMap<String, Integer> TraductionStateListeMaps(List<Integer> listeStats){
        HashMap<String, Integer> listeStatesConverti = new HashMap<>();
        listeStatesConverti.put("PV",listeStats.get(0));
        listeStatesConverti.put("ATK",listeStats.get(1));
        listeStatesConverti.put("SP_ATK",listeStats.get(2));
        listeStatesConverti.put("DEF",listeStats.get(3));
        listeStatesConverti.put("SP_DEF",listeStats.get(4));
        listeStatesConverti.put("VIT",listeStats.get(5));
        return listeStatesConverti;
    }

    ArrayList<String> TraductionStringAttaques(Map<Integer, String> listeAttaques,int lv){
        ArrayList<String> listeAttaqueConverti = new ArrayList<>();
        int j=0 ;
        for (int i = lv; i!=0;i--) {
            if (listeAttaques.containsKey(i)) {
                listeAttaqueConverti.add(listeAttaques.get(i));
                j++;
            }
            if(j==4){
                i=0;
            }
        }
        return listeAttaqueConverti;
    }

    Types TraductionsanslisteStringTypes(String Types){
        Types TypesConverti = null;

        switch (Types) {
            case "feu":
                    TypesConverti= fr.eseo.gaia_projet_java.enumerations.Types.feu;
                    break;
            case "eau":
                    TypesConverti= fr.eseo.gaia_projet_java.enumerations.Types.eau;
                    break;
            case "plante":
                    TypesConverti= fr.eseo.gaia_projet_java.enumerations.Types.plante;
                    break;
            case "tenebres":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.tenebres;
                    break;
            case "dragon":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.dragon;
                    break;
            case "fee":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.fee;
                    break;
            case "foudre":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.feu;
                    break;
            case "terre":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.terre;
                    break;
            case "normal":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.normal;
                    break;
            case "lumiere":
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.lumiere;
            }

        return TypesConverti;
    }

    ArrayList<Types> TraductionStringTypes(List<String> listeTypes){
        ArrayList<Types> listeTypesConverti = new ArrayList();
        for (int i = 0; i < listeTypes.size(); i++) {
            switch (listeTypes.get(i)) {
                case "feu":
                    listeTypesConverti.add(Types.feu);
                    break;
                case "eau":
                    listeTypesConverti.add(Types.eau);
                    break;
                case "plante":
                    listeTypesConverti.add(Types.plante);
                    break;
                case "tenebres":
                    listeTypesConverti.add(Types.tenebres);
                    break;
                case "dragon":
                    listeTypesConverti.add(Types.dragon);
                    break;
                case "fee":
                    listeTypesConverti.add(Types.fee);
                    break;
                case "foudre":
                    listeTypesConverti.add(Types.foudre);
                    break;
                case "terre":
                    listeTypesConverti.add(Types.terre);
                    break;
                case "normal":
                    listeTypesConverti.add(Types.normal);
                    break;
                case "lumiere":
                    listeTypesConverti.add(Types.lumiere);

            }
        }
        return listeTypesConverti;
    }
}