public class Item {
	public String categorie;			//potion ou arme ou protection
	public int[] bouclier_arme={10,10};
	public int[] bouclier_defense= {10,10,10};
	public Item() {
		this.categorie = "";
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