package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;

public abstract class State {

    protected Transition transition;

    State(Transition gaia) {
        this.transition = gaia;
    }

    public abstract void init();

    public abstract void menu();

    public abstract void map();

    public abstract void inventaire();

    public abstract void combat();

    public abstract void gameOver();

}
