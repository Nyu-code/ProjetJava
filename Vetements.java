
public class Vetements extends Equipement{
	private String nom;
	private int solidite;
	private int poids; //poids = encombrement vetement
	private int resistance;
	private static final String categorie = "chester";
	
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
	public Vetements(String name, int sol, int poids, int res, String genre) {
		this.nom = name;
		this.solidite = sol;
		this.poids = poids;
		this.resistance = res;
	}
	public String ToString() {
		return this.nom + "\n Solidité du vêtement :" + this.solidite 
				+ "\n poids du vêtement :" + this.poids + "\n Résistance du vêtement : " + this.resistance;
	}
}
