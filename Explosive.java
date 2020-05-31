public class Explosive extends Potion {
	
	public static final String type = "explosive";
	public static final int duree = 3;
	public static final double degat = -0.2;
	public static final String malus = "atk";
	public Explosive() {
		super(type,degat,duree,0);
	}
	
}