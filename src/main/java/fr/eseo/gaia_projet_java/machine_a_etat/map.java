package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;

public class map extends State{

    map(Transition gaia) {
        super(gaia);
    }

    @Override
    public void init() {
        //pas possible
    }

    @Override
    public void menu() {
        //pas possible
    }

    @Override
    public void map() {
        //deja dans l'etat map
    }

    @Override
    public void inventaire() {
        transition.setState(new inventaire(transition));
    }

    @Override
    public void combat() {
        transition.setState(new combat(transition));
    }

    @Override
    public void gameOver() {
       //pas possible
    }
}
