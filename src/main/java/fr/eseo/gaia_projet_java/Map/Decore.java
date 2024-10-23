package fr.eseo.gaia_projet_java.Map;

import java.util.ArrayList;

public class Decore {
    private ArrayList<Integer>position;
    private String image;
    private boolean tangibiliter;

    public Decore(ArrayList<Integer> position, String image, boolean tangibiliter) {
        this.position = position;
        this.image = image;
        this.tangibiliter = tangibiliter;
    }
    public ArrayList<Integer> getPosition() {
        return position;
    }
    public String getImage() {
        return image;
    }
    public boolean isTangibiliter() {
        return tangibiliter;
    }
    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setTangibiliter(boolean tangibiliter) {
        this.tangibiliter = tangibiliter;
    }
}
