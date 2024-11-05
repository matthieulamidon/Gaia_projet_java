package fr.eseo.gaia_projet_java.machine_a_etat;

import javafx.scene.input.KeyCode;

public class Transition {
    private State currentState;

    public void setState(State state) {
        this.currentState = state;
        this.currentState.init();
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
}