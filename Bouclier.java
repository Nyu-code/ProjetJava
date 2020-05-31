
public class Bouclier extends Item{
	static final String NOM = "Bouclier";
	static final int ARMIMPACT = 10;
	static final int MANIABILITE = 10;
	static final int SOLIDITE = 10;
	static final int POIDS = 20; //poids = encombrement vetement
	static final int RESISTANCE = 30;
	public boolean est_defense = true;
	
	public Bouclier() {
		super("chester");
	}
	public boolean getest_defense() {
		return this.est_defense;
	}
}
