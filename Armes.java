
public class Armes extends Equipement{
	private String nom;
	private int armimpact;
	private int maniabilite;
	private static final String categorie="weapon"; //classer les armes vêtements etc

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
	public Armes(String name,int armimpact, int maniabilite, String genre) {
		this.nom = name;
		this.armimpact = armimpact;
		this.maniabilite = maniabilite;
	}

	public String ToString() {
		return this.nom+"\n Impact Arme : "+armimpact +"\n Maniabilité arme : "+
				this.maniabilite ;
	}
	
}
