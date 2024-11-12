package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.controller.Transition;

public class init extends State{

    public init(Transition gaia) {
        super(gaia);
    }


    @Override
    public void menu() {
        //SI LA PARTIE EST BIEN LOADED
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
