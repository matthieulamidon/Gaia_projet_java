package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;
import javafx.scene.control.Menu;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



public class inventaire extends State{



    inventaire(Transition gaia) {
        super(gaia);
    }

    @Override
    public void init() {
        //pas possible
    }

    @Override
    public void menu() {
        transition.setState(new menu(transition));
    }

    @Override
    public void map() {
        transition.setState(new map(transition));
    }

    @Override
    public void inventaire() {
        transition.setState(new map(transition));
    }

    @Override
    public void combat() {
        //pas possible
    }

    @Override
    public void gameOver() {
        //pas possible
    }
}
