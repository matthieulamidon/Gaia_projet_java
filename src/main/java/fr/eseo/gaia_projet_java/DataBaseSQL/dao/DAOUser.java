package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;

import java.sql.SQLException;
import java.util.List;

public interface DAOUser {
    List<Exemplemon> nouveauMystimon() throws SQLException;
    List<Exemplemon> readLectuceDeLequipe() throws SQLException;
    List<Exemplemon> readLectuceDeEquipeAdverse(int nbAdv) throws SQLException;


    List<AttaqueCombat> LectuceDeEquipeAttaque() throws SQLException;
    //void createUser(String firstName, String lastName) throws SQLException;
}