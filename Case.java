
public class Case {
	public Object uneCase;
	public boolean occupee;
	public PersonnageJoueur p;
	public PersonnageNonJoueur pnj;
	public Item i;
	public String barriere;
	public String obstacle = "X";
	public static final String VIDE = " ";
	
	
	public Case() {
		this.uneCase = VIDE;
		this.occupee = false;
	}
	
	public Case(PersonnageJoueur p) {
		this.uneCase = p;
		this.occupee = true;
	}
	
	public Case(PersonnageNonJoueur m) {
		this.uneCase = m;
		this.occupee = true;
	}
	
	public Case(Item i) {
		this.uneCase = i;
		this.occupee = true;
	}
	
	public Case(String s) {
		this.uneCase = s;
		this.occupee = true;
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
		else {
			return (String)this.uneCase;
		}
	}
	
}
