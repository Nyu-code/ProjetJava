

public class Vetements extends Equipement{
	public String nom;
	public static final String categorie = "chester";

	
	public Vetements() {
		this.nom = "";
		this.solidite = 0;
		this.poids = 0;
		this.resistance = 0;
	}
	public Vetements(Vetements v) {
		this.nom = v.nom;
		this.solidite = v.solidite;
		this.poids = v.poids;
		this.resistance = v.resistance;
	}
	public Vetements(String name, int sol, int poids, int res) {
		this.nom = name;
		this.solidite = sol;
		this.poids = poids;
		this.resistance = res;
	}
	public String toString() {
		return this.nom;
	}
	public String statVetements() {
		return  "Solidité du vêtement :" + this.solidite 
				+ "\n poids du vêtement :" + this.poids + "\n Résistance du vêtement : " + this.resistance;
	}
}