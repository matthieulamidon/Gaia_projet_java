package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.controller.Transition;

public abstract class State {

    protected Transition transition;

    State(Transition gaia) {
        this.transition = gaia;
    }

    public abstract void menu();

    public abstract void map();

    public abstract void inventaire();

    public abstract void combat();


}
