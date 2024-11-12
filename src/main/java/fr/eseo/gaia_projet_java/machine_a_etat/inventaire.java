package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.controller.Transition;


public class inventaire extends State{



    inventaire(Transition gaia) {
        super(gaia);
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


}
