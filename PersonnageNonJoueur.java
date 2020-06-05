import java.util.*;

public class PersonnageNonJoueur extends Personnage{
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

	public double getHp() {
		return hp;
	}


	public void setHp(double hp) {
		this.hp = hp;
	}


	public double getMaxHp() {
		return maxHp;
	}


	public void setMaxHp(double maxHp) {
		this.maxHp = maxHp;
	}


	public int getExp() {
		return exp;
	}


	public void setExp(int exp) {
		this.exp = exp;
	}


	public int getInit() {
		return init;
	}


	public void setInit(int init) {
		this.init = init;
	}


	public int getAtk() {
		return atk;
	}


	public void setAtk(int atk) {
		this.atk = atk;
	}


	public int getEsq() {
		return esq;
	}


	public void setEsq(int esq) {
		this.esq = esq;
	}


	public int getDef() {
		return def;
	}


	public void setDef(int def) {
		this.def = def;
	}


	public int getDgt() {
		return dgt;
	}


	public void setDgt(int dgt) {
		this.dgt = dgt;
	}


	public int getPosH() {
		return posH;
	}


	public void setPosH(int posH) {
		this.posH = posH;
	}


	public int getPosV() {
		return posV;
	}


	public void setPosV(int posV) {
		this.posV = posV;
	}


	public void setObjet(Item objet) {
		this.objet = objet;
	}


	public boolean isTon_tour() {
		return ton_tour;
	}


	public void setTon_tour(boolean ton_tour) {
		this.ton_tour = ton_tour;
	}

	public void attaque_joueur(PersonnageJoueur p1) {
		System.out.println("Le monstre attaque !");
		p1.setHp(p1.getHp()-this.atk);
	}
	
	public String toString() {
		return "type :" + PersonnageNonJoueur.getType() +
				", forme :" + super.getBlessure() + ", objet :" + this.getObjet();
	}
}


