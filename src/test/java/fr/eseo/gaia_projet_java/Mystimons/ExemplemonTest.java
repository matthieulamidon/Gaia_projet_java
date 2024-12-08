package fr.eseo.gaia_projet_java.Mystimons;

import fr.eseo.gaia_projet_java.enumerations.Types;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour les principales méthodes d'Exemplemon
 */
class ExemplemonTest {

    private Exemplemon exemplemon;

    @BeforeEach
    void setUp() {
        ArrayList<Types> types = new ArrayList<>();
        ArrayList<String> attaques = new ArrayList<>();
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("PV", 100);
        attaques.add("Charge");
        types.add(Types.feu);
        types.add(Types.eau);
        exemplemon = new Exemplemon(10, "Mysti", types, attaques, 25, 5, 26, 27, stats, 50);
    }

    @Test
    void getID() {
        assertEquals(10, exemplemon.getID(), "L'ID doit etre 10");
    }

    @Test
    void getNom() {
        assertEquals("Mysti", exemplemon.getNom(), "Le Nom doit etre Mysti");
    }

    @Test
    void crééEv() {
        for (int i = 0; i < 1000; i++) {
            int ev = exemplemon.crééEv();
            assertTrue(ev >= 0 && ev <= 509, "L'EV générée doit etre entre 0 et 509 inclus");
        }
    }

    @Test
    void crééIv() {
        for (int i = 0; i < 1000; i++) {
            int ev = exemplemon.crééEv();
            assertTrue(ev >= 0 && ev <= 31, "L'Iv générée doit etre entre 0 et 509 inclus");
        }
    }

    @Test
    void convertionTypes() {
        ArrayList<String> typesconvertis = new ArrayList<>();
        typesconvertis.add("feu");
        typesconvertis.add("eau");

        ArrayList<String> typesconvertistest = exemplemon.ConvertionTypes();
        assertEquals(typesconvertis, typesconvertistest, "La liste convertie doit etre egale à la liste de String");
    }
}