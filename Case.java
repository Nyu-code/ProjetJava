import java.io.Serializable;

public class Case implements Serializable {
	public int col;
	public int ligne;
	public Object contenu;
	public boolean occupee;
	public PersonnageJoueur p;
	public PersonnageNonJoueur pnj;
	public Mur m;
	public Item i;
	public static final String VIDE = " ";
	
	
	public Case(int ligne, int col) {
		this.ligne = ligne;
		this.col = col;
		this.contenu = VIDE;
		this.occupee = false;
	}
	
	public Case(PersonnageJoueur p,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.contenu = p;
		this.occupee = true;
	}
	
	public Case(PersonnageNonJoueur m,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.contenu = m;
		this.occupee = true;
	}
	
	public Case(Item i,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.contenu = i;
		this.occupee = true;
	}
	
	public Case(Mur m,int ligne,int col) {
		this.ligne = ligne;
		this.col = col;
		this.contenu = m;
		this.occupee = true;
	}
	
	public Case(Case c) {
		this.ligne = c.ligne;
		this.col = c.col;
		this.contenu = c.contenu;
		this.occupee = c.occupee;
	}
	
	public boolean getOccupee() {
		return this.occupee;
	}
	
	
	public String toString() {
		if (this.contenu instanceof PersonnageJoueur) {
			return "p";
		}
		
		else if (this.contenu instanceof Item) {
			return "i";
		}
		
		else if (this.contenu instanceof PersonnageNonJoueur) {
			return "m";
		}
		
		else if (this.contenu instanceof Mur) {
			return "#";
		}
		
		else {
			return (String)this.contenu;
		}
	}
	
}