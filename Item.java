public class Item {
	public String categorie;			//potion ou arme ou protection
	
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
