
public class Poing extends Armes{
	static final String nom = "Poing";
	static final int armimpact = 10;
	static final int maniabilite = 10;
	public Poing() {
		super(nom, armimpact, maniabilite);
	}

	public String toString() {
		return this.nom;
	}
}
