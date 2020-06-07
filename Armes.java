import java.io.Serializable;

public class Armes extends Equipement implements Serializable{
	public String nom;
	public int armimpact;
	public int maniabilite;
	public static final String categorie="weapon"; //classer les armes vêtements etc

	public Armes() {
		this.nom = "";
		this.armimpact = 0;
		this.maniabilite = 0;
	}
	public Armes(Armes a) {
		this.nom = a.nom;
		this.armimpact = a.armimpact;
		this.maniabilite = a.maniabilite;
	}
	public Armes(String name,int armimpact, int maniabilite) {
		this.nom = name;
		this.armimpact = armimpact;
		this.maniabilite = maniabilite;
	}

	public String toString() {
		return this.nom;
	}
	public String statArme() {
		return "Impact Arme : "+ this.armimpact +"\n Maniabilité arme : "+
				this.maniabilite ;
	}
	
}
