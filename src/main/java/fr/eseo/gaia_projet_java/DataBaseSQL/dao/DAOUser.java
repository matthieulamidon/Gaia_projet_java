package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    List<Exemplemon> nouveauMystimon() throws SQLException;
    //void createUser(String firstName, String lastName) throws SQLException;
}