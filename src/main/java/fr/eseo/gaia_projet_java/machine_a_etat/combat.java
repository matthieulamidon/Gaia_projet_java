package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;

public class combat extends State{

    combat(Transition gaia) {
        super(gaia);
    }

    @Override
    public void init() {
        //non
    }

    @Override
    public void menu() {
        //pas possible normalement
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
        //deja l'etat actuel
    }

    @Override
    public void gameOver() {
        transition.setState(new gameOver(transition));
    }
}
