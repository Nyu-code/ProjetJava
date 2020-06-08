import java.io.Serializable;

public class Potion extends Item implements Serializable {
	public static final String categorieP 		= "potion";
	public String type;	//type dans le tableau tabPotion
	public double 	degat;
	public int 		duree,pa;

	public Potion() {
		this.type = "";
		this.duree = 0;
		this.pa = 0;
		this.degat = 0;
	}
	
	public Potion(String type,double degat, int duree, int pa) {
		super(categorieP);
		this.type = type;
		this.degat = degat;
		this.duree = duree;
		this.pa = pa;
	}
	
	
	public float getDureePot() {
		return this.duree;
	}
	
	public double getDgtPot() {
		return this.degat;
	}
	
	public String toString() {
		if (this instanceof Mana) {
			return this.type + " ("+this.pa+ " PA)";
		} else if (this instanceof Soin) {
			return this.type + " (+" + this.degat*100 + "% HP)";
		} else {
			return this.type + " (" + this.degat*100 + "% HP)";
		}
	}
	
}
