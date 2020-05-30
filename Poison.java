
public class Poison extends Potion {
	
	public static final String type = "poison";
	public static final int duree = 3;
	public static final float degat = -10/100;
	public static final String malus = "poison";
	public Poison() {
		super(type,degat,duree,0);
	}
	
}