package fr.eseo.gaia_projet_java.Map;

import java.util.ArrayList;

public class Decor {
    private ArrayList<Integer>position;
    private String image;
    private boolean tangibilite;

    public Decor(ArrayList<Integer> position, String image, boolean tangibilite) {
        this.position = position;
        this.image = image;
        this.tangibilite = tangibilite;
    }
    public ArrayList<Integer> getPosition() {
        return position;
    }
    public String getImage() {
        return image;
    }
    public boolean isTangibilite() {
        return tangibilite;
    }
    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setTangibilite(boolean tangibilite) {
        this.tangibilite = tangibilite;
    }
}
