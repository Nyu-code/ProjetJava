import java.util.*;

public class PersonnageNonJoueur extends Personnage{
	private static final String type="monstre";
	private static int adresse = 70;
	private static int resistance = 50;
	private static int force = 60;
	private Item objet;
	
	public PersonnageNonJoueur() {
		super();
		super.setBlessure();
		
		//on donne a un monster un objet aléatoire
		int alea = Personnage.rand(0, Item.LISTE_ITEM.size()-1);
		
		//on lui attribue l'item trouvé aléatoirement
		this.objet = Item.LISTE_ITEM.get(alea);
	}
	
	
	public static String getType() {
		return type;
	}
	public static int getAdresse() {
		return adresse;
	}
	public static int getResistance() {
		return resistance;
	}
	public static int getForce() {
		return force;
	}

	public Item getObjet() {
		return this.objet;
	}
	
	public static void setAdresse(int adresse) {
		PersonnageNonJoueur.adresse = adresse;
	}
	public static void setResistance(int resistance) {
		PersonnageNonJoueur.resistance = resistance;
	}
	public static void setForce(int force) {
		PersonnageNonJoueur.force = force;
	}
	
	public String toString() {
		return "type :" + PersonnageNonJoueur.getType() + ", forme :" + super.getBlessure() + ", objet :" + this.getObjet();
	}
}
