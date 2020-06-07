
public class Case {
	public int col;
	public int ligne;
	public Object uneCase;
	public boolean occupee;
	public PersonnageJoueur p;
	public PersonnageNonJoueur pnj;
	public Mur m;
	public Item i;
	public static final String VIDE = " ";
	
	
	public Case(int ligne, int col) {
		this.ligne = ligne;
		this.col = col;
		this.uneCase = VIDE;
		this.occupee = false;
	}
	
	public Case(PersonnageJoueur p,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.uneCase = p;
		this.occupee = true;
	}
	
	public Case(PersonnageNonJoueur m,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.uneCase = m;
		this.occupee = true;
	}
	
	public Case(Item i,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.uneCase = i;
		this.occupee = true;
	}
	
	public Case(Mur m,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.uneCase = m;
		this.occupee = true;
	}
	
	public Case(Case c) {
		this.ligne = c.ligne;
		this.col = c.col;
		this.uneCase = c.uneCase;
		this.occupee = c.occupee;
	}
	
	public boolean getOccupee() {
		return this.occupee;
	}
	
	
	public String toString() {
		if (this.uneCase instanceof PersonnageJoueur) {
			return "p";
		}
		
		else if (this.uneCase instanceof Item) {
			return "i";
		}
		
		else if (this.uneCase instanceof PersonnageNonJoueur) {
			return "m";
		}
		
		else if (this.uneCase instanceof Mur) {
			return "#";
		}
		
		else {
			return (String)this.uneCase;
		}
	}
	
}
