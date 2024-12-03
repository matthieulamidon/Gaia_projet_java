package fr.eseo.gaia_projet_java.DataBaseSQL.dao;

import fr.eseo.gaia_projet_java.Attaques.AttaqueCombat;
import fr.eseo.gaia_projet_java.Invocateur.Adversaire;
import fr.eseo.gaia_projet_java.Mystimons.Exemplemon;
import fr.eseo.gaia_projet_java.Parchemins.Buff;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAOUser {
    List<Exemplemon> nouveauMystimon(int lvS) throws SQLException;
    List<Exemplemon> readLectuceDeLequipe() throws SQLException;
    List<Exemplemon> readLectuceDeEquipeAdverse(int nbAdv) throws SQLException;

    Adversaire readLectureAdversaire(int idAdv) throws SQLException;
    List<AttaqueCombat> LectuceDeEquipeAttaque() throws SQLException;
    Buff LectureParchemins(String nomObjet) throws SQLException;
    //void createUser(String firstName, String lastName) throws SQLException;
}