package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.DataBaseSQL.JsonParserUtils;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Invocateur.Joueur;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Parchemins.Buff;
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
    public List<Exemplemon> nouveauMystimon(int lvS) throws SQLException {
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

                ArrayList<String> listeAttaqueConverti = TraductionStringAttaques(listeAttaques,lvS);

                HashMap<String, Integer> listeStatesConverti = TraductionStateListeMaps(listeStats);

                // Créer et ajouter un nouvel objet Mystimon //, pv, listeStats
                users.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, 0, 5, 25, 25, listeStatesConverti, pv));
            }
            return users;
        }

    }

    private void replaceTableEquipe(Connection connection, List<Exemplemon> nouvellesEquipes) throws SQLException {
        String deleteQuery = "DELETE FROM equipe"; // Supprime toutes les données existantes
        String insertQuery = """
        INSERT INTO equipe (nom, pv, xp, lv, ev, iv, Stat, types, attaque, objet)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    """;

        try (Statement deleteStatement = connection.createStatement();
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Supprime toutes les données existantes dans la table
            deleteStatement.executeUpdate(deleteQuery);

            // Insère les nouvelles données dans la table
            for (Exemplemon equipe : nouvellesEquipes) {
                insertStatement.setString(1, equipe.getNom());
                insertStatement.setInt(2, equipe.getPv());
                int n = (int) equipe.getXp();
                insertStatement.setInt(3, n);
                insertStatement.setInt(4, equipe.getLv());
                insertStatement.setInt(5, equipe.getEv());
                insertStatement.setInt(6, equipe.getIv());
                insertStatement.setString(7, TraductionStateEnJson(equipe.getStats())); // Conversion en JSON si nécessaire
                insertStatement.setString(8, TraductionTypesEnJson(equipe.getListeTypes())); // Conversion en JSON si nécessaire
                insertStatement.setString(9, TraductionArrayListStringEnJson(equipe.getListeAttaques())); // Conversion en JSON si nécessaire
                insertStatement.setString(10, equipe.getObjet());

                insertStatement.addBatch(); // Ajoute cette commande dans un lot
            }

            insertStatement.executeBatch(); // Exécute toutes les commandes d'insertion en une seule fois
        }
    }

    public String TraductionStateEnJson(HashMap<String, Integer> statsMap) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // Convertit la HashMap en une chaîne JSON
            return objectMapper.writeValueAsString(statsMap);
        } catch (Exception e) {
            e.printStackTrace();
            return "{}"; // Retourne un JSON vide en cas d'erreur
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

                equipe.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, xp, lv, ev, iv, listeStatesConverti, pv));
            }
            return equipe;
        }
    }

    //permet de recuperer les objets
    @Override
    public Buff LectureParchemins(String nomObjet) throws SQLException {
        Buff parchemin = null;
        String requete = "SELECT id, nom, effet, prixAchat, prixVente, description, efficacite FROM objet WHERE nom = ?";

        try (Connection connexion = getConnection();
             PreparedStatement statement = connexion.prepareStatement(requete)) {

            // On ajoute le parametre à la requête
            statement.setString(1, nomObjet);

            try (ResultSet resultat = statement.executeQuery()) {
                while (resultat.next()) {
                    int id = resultat.getInt("id");
                    String nom = resultat.getString("nom");
                    String effet = resultat.getString("effet");
                    int prixAchat = resultat.getInt("prixAchat");
                    int prixVente = resultat.getInt("prixVente");
                    String description = resultat.getString("description");
                    int efficacite = resultat.getInt("efficacite");

                    // On ajoute un nouvel objet à la liste
                    parchemin = new Buff(nom, id, effet, efficacite, description);
                }
            }
        }
        return parchemin;
    }

    @Override
    public List<Exemplemon> readLectuceDeEquipeAdverse(int nbAdv) throws SQLException {
        List<Exemplemon> equipe = new ArrayList<>();
        try (Connection connexion = getConnection();
             Statement statement = connexion.createStatement();
             ResultSet resultat = statement.executeQuery(
                     "SELECT id, nom, pv, xp, lv, ev, iv, Stat, types, attaque, objet FROM equipeduPnj"+nbAdv+";")) {
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
                List<String> listeAttaques = JsonParserUtils.parseJsonToListString(attaqueJson);

                //ArrayList<Types> listeTypesConverti = new ArrayList();
                ArrayList<Types> listeTypesConverti=TraductionStringTypes(listeTypes);

                String jsonStringList =typesJson;

                ArrayList<String> listeAttaqueConverti = jsonToArrayList(attaqueJson, String.class);
                HashMap<String, Integer> listeStatesConverti = TraductionStateListeMaps(listeStats);

                equipe.add(new Exemplemon(id, nom, listeTypesConverti, listeAttaqueConverti, xp, lv, ev, iv, listeStatesConverti, pv));
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
                //Map_controller<Integer, String> listeAttaques = JsonParserUtils.parseJsonToMapIntString(attaqueJson);

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
                String jsonString = resultat.getString("listeDObjet");
                //List<Integer> listeObjets = JsonParserUtils.parseJsonToListInt(jsonString);

                HashMap<String, Integer> mapObjets = (HashMap<String, Integer>) JsonParserUtils.parseJsonToMapStringInt(jsonString);
                ArrayList<Integer> position = new ArrayList<>(1);
                position.add(2);
                joueur = new Joueur(id, nom, readLectuceDeLequipe(), mapObjets, position);
            }
            return joueur;
        }
    }

    public Adversaire readLectureAdversaire(int idAdv) throws SQLException {
        String requete = "SELECT id, nom, coordonnees FROM pnj WHERE id = ?";
        Adversaire adversaire = null;
        try (Connection connexion = getConnection();
             PreparedStatement statement = connexion.prepareStatement(requete)) {

            // On ajoute le parametre à la requête
            statement.setInt(1, idAdv);

            try (ResultSet resultat = statement.executeQuery()) {
                while (resultat.next()) {
                    int id = resultat.getInt("id");
                    String nom = resultat.getString("nom");
                    ArrayList<Integer> position = new ArrayList<>(1);
                    position.add(2);
                    adversaire = new Adversaire(id, nom, readLectuceDeEquipeAdverse(id), null, position);
                }
            }
        }
        return adversaire;
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

    List<Integer> TraductionStateJsonEnListe(HashMap<String, Integer> statsMap) {
        List<Integer> listeStats = new ArrayList<>();
        // Ajoute les valeurs dans l'ordre des clés attendues
        listeStats.add(statsMap.getOrDefault("PV", 0)); // Ajoute la valeur pour "PV", ou 0 si la clé n'existe pas
        listeStats.add(statsMap.getOrDefault("ATK", 0));
        listeStats.add(statsMap.getOrDefault("SP_ATK", 0));
        listeStats.add(statsMap.getOrDefault("DEF", 0));
        listeStats.add(statsMap.getOrDefault("SP_DEF", 0));
        listeStats.add(statsMap.getOrDefault("VIT", 0));
        return listeStats;
    }

    public String TraductionTypesEnJson(ArrayList<Types> listeTypes) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> listeTypesString = new ArrayList<>();

        // Conversion des Types en chaînes de caractères
        for (Types type : listeTypes) {
            switch (type) {
                case feu:
                    listeTypesString.add("feu");
                    break;
                case eau:
                    listeTypesString.add("eau");
                    break;
                case plante:
                    listeTypesString.add("plante");
                    break;
                case tenebres:
                    listeTypesString.add("tenebre");
                    break;
                case dragon:
                    listeTypesString.add("dragon");
                    break;
                case fee:
                    listeTypesString.add("fee");
                    break;
                case foudre:
                    listeTypesString.add("foudre");
                    break;
                case terre:
                    listeTypesString.add("terre");
                    break;
                case normal:
                    listeTypesString.add("normal");
                    break;
                case lumiere:
                    listeTypesString.add("lumiere");
                    break;
                default:
                    System.err.println("Type inconnu : " + type); // Avertissement pour les cas non gérés
                    break;
            }
        }

        try {
            // Convertit la liste de chaînes en JSON
            return objectMapper.writeValueAsString(listeTypesString);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]"; // Retourne une liste JSON vide en cas d'erreur
        }
    }

    public String TraductionArrayListStringEnJson(ArrayList<String> listeStrings) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Convertit l'ArrayList en JSON
            return objectMapper.writeValueAsString(listeStrings);
        } catch (Exception e) {
            e.printStackTrace();
            return "[]"; // Retourne une liste JSON vide en cas d'erreur
        }
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
                    TypesConverti = fr.eseo.gaia_projet_java.enumerations.Types.foudre;
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

    ArrayList<Types> TraductionStringTypes(List<String> listeTypes) {
        ArrayList<Types> listeTypesConverti = new ArrayList<>();

        for (String type : listeTypes) {
            switch (type.toLowerCase()) { // Gestion insensible à la casse
                case "feu":
                    listeTypesConverti.add(Types.feu);
                    break;
                case "eau":
                    listeTypesConverti.add(Types.eau);
                    break;
                case "plante":
                    listeTypesConverti.add(Types.plante);
                    break;
                case "tenebre":
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
                    break;
                default:
                    System.err.println("Type inconnu : " + type); // Avertissement pour les cas non gérés
                    break;
            }
        }
        return listeTypesConverti;
    }

    public ArrayList<Buff> LectureMapObjets(HashMap<String, Integer> mapObjets) throws SQLException {
        ArrayList<Buff> listeObjetsConvertie = new ArrayList();
        for (Map.Entry<String, Integer> entry : mapObjets.entrySet()) {
            Buff objetCherche = null;
            objetCherche = LectureParchemins(entry.getKey());
            if (objetCherche != null) {
                listeObjetsConvertie.add(objetCherche);
            }
        }
        return listeObjetsConvertie;
    }
}