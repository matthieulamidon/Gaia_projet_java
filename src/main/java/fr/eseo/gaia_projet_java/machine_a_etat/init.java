package fr.eseo.gaia_projet_java.machine_a_etat;

public class init extends State{

    public init(Transition gaia) {
        super(gaia);
    }

    @Override
    public void init() {
        //deja l'etat actuel
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

    @Override
    public void gameOver() {
        //clairement pas possible
    }
}
