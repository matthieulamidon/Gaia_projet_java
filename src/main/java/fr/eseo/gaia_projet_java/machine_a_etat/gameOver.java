package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.controller.Transition;

public class gameOver extends State{
    gameOver(Transition gaia) {
        super(gaia);
    }


    @Override
    public void menu() {
       transition.setState(new menu(transition));
    }

    @Override
    public void map() {
        //pas possible normalement
    }

    @Override
    public void inventaire() {
        //pas possible normalement
    }

    @Override
    public void combat() {
        //pas possible normalement
    }

}
