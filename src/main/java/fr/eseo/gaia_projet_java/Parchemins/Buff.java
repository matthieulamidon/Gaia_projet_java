package fr.eseo.gaia_projet_java.Parchemins;


/**
 * Classe incomplète, Parchemin ayant des effets positifs
 */
public class Buff extends Parchemin {
    private String description;
    public Buff(String nom, Integer id, String effet, int efficacite, String description){
        super(nom, id, effet, efficacite);
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
}
