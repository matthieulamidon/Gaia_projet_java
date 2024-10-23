package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.users.Gaia;

public abstract class State {

    Gaia gaia;

    State(Gaia gaia) {
        this.gaia = gaia;
    }
    public abstract void init();
    public abstract void menu();
    public abstract void map();
    public abstract void inventaire();
    public abstract void combat();
    public abstract void gameOver();
}
