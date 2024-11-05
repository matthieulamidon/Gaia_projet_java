package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import fr.eseo.gaia_projet_java.Attaques.Attaque;
import fr.eseo.gaia_projet_java.DataBaseSQL.JsonParserUtils;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Mystimons.Mystimon;
import fr.eseo.gaia_projet_java.enumerations.Types;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            }
        }
        return listeTypesConverti;
    }
}