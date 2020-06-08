import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Item implements Serializable {
	public String categorie;			//potion ou arme ou protection
	public int[] bouclier_arme={10,10};
	public int[] bouclier_defense= {10,10,10};
	public int solidite;
	public int poids; //poids = encombrement vetement
	public int resistance;
	public int armimpact;
	public int maniabilite;
	//liste d'item qui existe pour le personnage non joueur
	public static ArrayList<Item> LISTE_ITEM = new ArrayList<Item>(Arrays.asList(
				new Soin(), new Explosive(), new Molotov(), new Mana(), new Poison(),
				new Poing(), new Baton(), new Marteau(), new Lance(), new Epee(), new Armes("Bouclier", Bouclier.ARMIMPACT, Bouclier.MANIABILITE),
				new TShirt(), new PlastronEnBois(), new PlastronEnFer(), new PlastronEnOr(), new PlastronDragon(), new Vetements("Bouclier", Bouclier.SOLIDITE, Bouclier.POIDS, Bouclier.RESISTANCE)
				)
			);
	
	public Item() {
		this.categorie = "vide";
	}
	
	public Item(String item) {
		this.categorie = item;
	}
	
	public boolean contentEquals(Item item) {
		return this.equals(item);
	}
	
	public String toString() {
		return this.categorie;
	}
	
}