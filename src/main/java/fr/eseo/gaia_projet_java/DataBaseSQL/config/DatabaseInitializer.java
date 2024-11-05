package fr.eseo.gaia_projet_java.DataBaseSQL.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static final String url = "jdbc:mariadb://localhost:3306";
    private static final String database = "Gaia_test_1";
    private static final String username = "root";
    private static final String password = "V1v@3str3spubl1c@";

    public DatabaseInitializer() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            createDatabase(connection);
        }
    }
    public void run() throws SQLException {
        try (Connection connection = DriverManager.getConnection(url+"/"+database, username, password)) {
            createTableMystidex(connection);
            createTableEquipeInvocateur(connection);
        }
    }
    private void createDatabase(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createDatabaseQuery = "CREATE DATABASE IF NOT EXISTS "+database;
            statement.executeUpdate(createDatabaseQuery);

        }
    }
    private void createTableMystidex(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS mystidex (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            pv INT(11) NOT NULL,
            Stat JSON NOT NULL,
            types JSON NOT NULL,
            attaque JSON NOT NULL,
            evolution JSON NOT NULL,
            description VARCHAR(50) NOT NULL
        );""";
            statement.executeUpdate(createTableQuery);

            // Nouvelle version de la requête INSERT avec LEFT JOIN
            String insertDefaultScoreQuery = """
        INSERT INTO mystidex (nom, pv, Stat, types, attaque, evolution, description)
        SELECT 'kuro', 20, '[10,10,10,10,10,10]', '["tenebre"]', '{"0": "charge", "2": "toileColante", "5": "crosEmpoisonner"}', '[18,2]', 'C est le mystimon 1, celui qu on retient'
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM mystidex WHERE nom = 'kuro');
        """;

            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }

    private void createTableEquipeInvocateur(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS equipe (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            pv INT(11) NOT NULL,
            xp INT(11) NOT NULL,
            lv INT(11) NOT NULL,
            ev INT(11) NOT NULL,
            Stat JSON NOT NULL,
            types JSON NOT NULL,
            attaque JSON NOT NULL,
            objet VARCHAR(50) NOT NULL,
        );""";
            statement.executeUpdate(createTableQuery);
            String insertDefaultScoreQuery = """
        INSERT INTO equipe (nom, pv, Stat, types, attaque, evolution, description)
        SELECT 'kuro', 20, 0, 5, 25, '[10,10,10,10,10,10]', '["tenebre"]', '[ "charge", "toileColante", "crosEmpoisonner"]', '[18,2]', 'NULL'
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM mystidex WHERE nom = 'kuro');
        """;
            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }
    private void createTableEquipePnj(Connection connection,int nbPnj) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS equipedunb1 (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            pv INT(11) NOT NULL,
            xp INT(11) NOT NULL,
            lv INT(11) NOT NULL,
            ev INT(11) NOT NULL,
            Stat JSON NOT NULL,
            types JSON NOT NULL,
            attaque JSON NOT NULL,
            objet VARCHAR(50) NOT NULL,
        );""";
            statement.executeUpdate(createTableQuery);
            String insertDefaultScoreQuery = """
        INSERT INTO equipedunb1 (nom, pv, Stat, types, attaque, evolution, description)
        SELECT 'kuro', 20, 0, 5, 25, '[10,10,10,10,10,10]', '["tenebre"]', '[ "charge", "toileColante", "crosEmpoisonner"]', '[18,2]', 'NULL'
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM equipedunb1 WHERE nom = 'kuro');
        """;
            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }
    private void createTableInvocateur(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS promethee (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            listeDObjet JSON NOT NULL,
            coordonner JSON NOT NULL
        );""";
            statement.executeUpdate(createTableQuery);
            String insertDefaultScoreQuery = """
        INSERT INTO promethee (nom, listeDObjet, coordonner)
        SELECT 'promethee', '{"heal": "0", "buff": "0", "capture": "0"}', '[0,0,0]'
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM promethee WHERE nom = 'promethee');
        """;
            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }
    private void createTableAttaque(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS attaque (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            puissance INT(11) NOT NULL,
            types VARCHAR(50) NOT NULL,
            effet VARCHAR(50) NOT NULL,
            aspect VARCHAR(50) NOT NULL,
            pressision INT(11) NOT NULL
        );""";
            statement.executeUpdate(createTableQuery);
            String insertDefaultScoreQuery = """
        INSERT INTO attaque (nom, attaque, types, effet, aspect, pressision)
        SELECT 'charge', 40, 'normal', 'physique', 100
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM attaque WHERE nom = 'charge');
        """;
            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }
    private void createTableObjet(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            String createTableQuery = """
        CREATE TABLE IF NOT EXISTS objet (
            id INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
            nom VARCHAR(50) NOT NULL,
            effet VARCHAR(50) NOT NULL,
            prixAchat INT(11) NOT NULL,
            prixVente INT(11) NOT NULL,
            description VARCHAR(50) NOT NULL,
        );""";
            statement.executeUpdate(createTableQuery);
            String insertDefaultScoreQuery = """
        INSERT INTO attaque (nom, effet, prixAchat, prixVente, description)
        SELECT 'charge', 'zinedine_zidane', 250, 100, 'il est 17h20 j ai la flemme'
        FROM DUAL
        WHERE NOT EXISTS (SELECT 1 FROM objet WHERE nom = 'heal');
        """;
            statement.executeUpdate(insertDefaultScoreQuery);
        }
    }
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url+"/"+database, username, password);
    }
}
