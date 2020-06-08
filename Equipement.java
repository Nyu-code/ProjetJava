
public class Equipement extends Item{
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
