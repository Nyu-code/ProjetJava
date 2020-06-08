import java.io.Serializable;

public class Equipement extends Item implements Serializable {
	private String nom;
	private String categorie;
	public Equipement() {
		this.nom = "";
		this.categorie = "";
	}
	
	public String toString() {
		return this.nom +"\n La catégorie de l'item : " + this.categorie;
	}
}