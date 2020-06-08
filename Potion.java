import java.io.Serializable;

public class Potion extends Item implements Serializable{
	public static final String categorieP 		= "potion";
	public String type;	//type dans le tableau tabPotion
	public double 	degat;
	public int 		duree,pa;

	
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
		return this.type;
	}
	
}