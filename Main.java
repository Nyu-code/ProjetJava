
public class Main extends Armes{
	static final String nom = "Main";
	static final int armimpact = 10;
	static final int maniabilite = 10;
	public Main() {
		super(nom, armimpact, maniabilite);
	}

	public String toString() {
		return this.nom;
	}
}
