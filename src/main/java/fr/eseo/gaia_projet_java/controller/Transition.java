package fr.eseo.gaia_projet_java.controller;

import fr.eseo.gaia_projet_java.machine_a_etat.State;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;

public class Transition {

    private State currentState;



    public void setState(State state) {
        this.currentState = state;
        // et là on active l'état actuel ?
    }

    public void handleKeyPress(KeyCode key) {
        switch (key) {
            case I:
                currentState.inventaire();
                break;
            case ESCAPE:
                currentState.menu();
                break;
            case M:
                currentState.map();
                break;
        }
    }

    /*
    public void handleAction(String action){
      currentState.handleTransition(action)
    }
    */
    //methodes de deplacement pour la map
    public void MouvHaut(){
        System.out.println("Vers le haut");
    }
    public void MouvBas(){
        System.out.println("Vers le bas");
    }
    public void MouvGauche(){
        System.out.println("Vers la gauche");
    }
    public void MouvDroite(){
        System.out.println("Vers la droite");
    }



}