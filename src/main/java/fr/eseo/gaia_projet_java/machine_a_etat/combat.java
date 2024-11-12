package fr.eseo.gaia_projet_java.machine_a_etat;

import fr.eseo.gaia_projet_java.controller.Transition;

public class combat extends State{

    combat(Transition gaia) { //ajouter un paramètre joueur pour verifmystimons
        super(gaia);
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

    private void VerifMystimons(){//Verifie si le joueur a toujours au moins 1 mystimon en vie
        /*
        if (joueur.getListe_mystimons().isEmpty()) { //on vérifie si elle est vide, mais il faudrait plus vérifier s'ils sont tous K.O.
            System.out.println("Plus de mystimons, game over");
            transition.setState(new gameOver(transition)); // Changer d'état ici
        }
        */
    }

}
