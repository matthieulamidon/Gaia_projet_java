package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;

public class menu extends State{

    menu(Transition gaia) {
        super(gaia);
    }

    @Override
    public void init() {
        //pas possible normalement
    }

    @Override
    public void menu() {
        transition.setState(new map(transition));
    }

    @Override
    public void map() {
        transition.setState(new map(transition));
    }

    @Override
    public void inventaire() {
       //pas possible normalement
    }

    @Override
    public void combat() {
        //pas possible normalement
    }

    @Override
    public void gameOver() {
        //pas possible normalement
    }
}
