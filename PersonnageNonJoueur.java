import java.io.Serializable;

public class PersonnageNonJoueur extends Personnage implements Serializable{
	public double hp;
	public double maxHp = 100;
	private static final String type="monstre";
	private static int adresse = 70;
	private static int resistance = 50;
	private static int force = 60;
	public int exp,init,atk,esq,def,dgt,posH,posV;
	private Item objet;
	protected boolean ton_tour = false;

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

	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}


	public void setObjet(Item objet) {
		this.objet = objet;
	}

	public void attaque_joueur(PersonnageJoueur p1) { 
		this.tirageAlea();
		p1.tirageAlea();
		
		if(this.getAtk() > p1.getEsq()) {

			System.out.println("Le monstre à réussit son coup");
			p1.setHp(p1.getHp()-this.atk);
		}
		else {
			System.out.println("Vous avez esquivez l'attaque du monstre !");
		}
			
	}
	public String toString() {
		return "type :" + PersonnageNonJoueur.getType() +
				", forme :" + super.getBlessure() + ", objet :" + this.getObjet();
	}
}


