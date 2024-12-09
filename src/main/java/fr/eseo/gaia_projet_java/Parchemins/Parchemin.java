package fr.eseo.gaia_projet_java.Parchemins;

/**
 * Classe incomplète, équivalents d'objets à utiliser en combat et sur la map
 */
public abstract class Parchemin {
    protected String nom;
    protected Integer id;
    protected String effet;
    protected int efficacite;

    public Parchemin(String nom, Integer id, String effet, int efficacite) {
        this.nom = nom;
        this.id = id;
        this.effet = effet;
        this.efficacite = efficacite;
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
    public int getEfficacite() {
        return efficacite;
    }
    public void setEfficacite(int efficacite) {
        this.efficacite = efficacite;
    }
}
