package fr.eseo.gaia_projet_java.Parchemins;

public abstract class Parchemin {
    protected String nom;
    protected Integer id;
    protected String effet;
    protected int efficaciter;

    public Parchemin(String nom, Integer id, String effet, int efficacite) {
        this.nom = nom;
        this.id = id;
        this.effet = effet;
        this.efficaciter = efficacite;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getEffet() {
        return effet;
    }
    public void setEffet(String effet) {
        this.effet = effet;
    }
    public int getEfficaciter() {
        return efficaciter;
    }
    public void setEfficaciter(int efficaciter) {
        this.efficaciter = efficaciter;
    }
}
