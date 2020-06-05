import java.util.ArrayList;

public abstract class Personnage {
	protected double hp;
	protected double maxHp = 100;
	protected static String[] malus = {"poison","atkDeb","esqDeb"};
	//initDeb provient d'une potion de molotov
	//esqDeb provient d'une potion d'explosion
	protected ArrayList<Item> inventaire = new ArrayList<Item>(16);
	protected ArrayList<String> etat = new ArrayList<String>();
	protected static final int TAILLE_ARRAYLIST = 16;
	protected String blessure;
	protected Item gauche,droite; //ce qu'il y a dans la main gauche et ce qu'il y a dans la main droite
	protected int exp,init,atk,esq,def,dgt,posH,posV;
	protected Item protection;
	protected boolean gauche_libre = true;
	protected boolean droite_libre = true;
	protected boolean protection_libre = true;
	protected boolean enCombat = false;
	
	public Personnage() {
		this.hp = maxHp;
		this.setBlessure();
		for (int i = 0; i<this.inventaire.size();i++) {
			this.inventaire.set(i,null);
		}
		this.exp = 0;
		this.init = 0;
		this.atk = 0;
		this.esq = 0;
		this.def = 0;
		this.dgt = 0;
	}
	
	public Personnage(int exp) {
		this.hp = maxHp;
		this.setBlessure();
		for (int i = 0; i<this.inventaire.size();i++) {
			this.inventaire.set(i,null);
		}
		this.exp = exp;
		this.init = 0;
		this.atk = 0;
		this.esq = 0;
		this.def = 0;
		this.dgt = 0;
	}


	public ArrayList<Item> getInventaire() {
		return this.inventaire;
	}

	public String getBlessure() {
		return this.blessure;
	}

	public int getExp() {
		return exp;
	}

	public int getInit() {
		return init;
	}

	public int getAtk() {
		return atk;
	}

	public int getEsq() {
		return esq;
	}

	public int getDef() {
		return def;
	}

	public int getDgt() {
		return dgt;
	}

	public double getHp() {
		return hp;
	}

	public double getMaxHp() {
		return maxHp;
	}

	public boolean isEnCombat() {
		return enCombat;
	}

	public void setEnCombat() {
		this.enCombat = !this.enCombat;
	}

	public void setHp(double hp) {
		if (hp>maxHp && hp<0) {
			new Exception("On ne peut pas modifier son HP");
		} else { 	
			this.hp = hp;
			this.setBlessure();
		}
	}

	public void setMaxHp(float maxHp) {
		this.maxHp = maxHp;
	}

	public void setInventaire(ArrayList<Item> inventaire) {
		this.inventaire = inventaire;
	}
	
	
	
	public void setBlessure() { //méthode qui applique automatiquement sa blessure relative au nombre d'HP du joueur
//		{"en forme","superficielle","légèrement","blessé","gravement blessé","inconscient","mort"}
		double pourcentageHP = (this.hp/maxHp)*100;
		double palier = maxHp/7;
		int nbPalier = (int) (pourcentageHP/palier);
		if (hp == 0) {
			this.blessure="mort";
		} else {
			switch (nbPalier){
				case 0 :
					this.blessure="inconscient" ;
					break;
				case 1 :
					this.blessure="gravement blessé" ;
					break;
				case 2 :
					this.blessure="gravement blessé" ;
					break;
				case 3 :
					this.blessure="blessé" ;
					break;
				case 4 :
					this.blessure="légèrement blessé" ;
					break;
				case 5 :
					this.blessure="superficielle" ;
					break;
				case 6 :
					this.blessure="en forme" ;
					break;
				default:
					this.blessure="en pleine forme";
			}
		}
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setInit(int init) {
		this.init = init;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public void setEsq(int esq) {
		this.esq = esq;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public void setDgt(int dgt) {
		this.dgt = dgt;
	}

	public void gainXP(int qty) {		
		this.setExp(this.exp+qty);
	}
	
	public static int rand(int min, int max) { //méthode pour créer l'aléatoire
		int n = min + (int)(Math.random() * ((max - min) + 1));
		return n;
	}
	
	public int dee() {	//méthode pour un dée de 6 faces
		return rand(1,6);
	}
	
	public boolean aPlusEsq(Personnage p1, Personnage p2) {
		return (p1.getEsq()>p2.getEsq());
	}
	
	public boolean aPluAtk(Personnage p1, Personnage p2) {
		return (p1.getAtk()>p2.getAtk());
	}
	
	public boolean aPlusDef(Personnage p1, Personnage p2) {
		return (p1.getDef()>p2.getDef());
	}
	
	public boolean aPluInit(Personnage p1, Personnage p2) {
		return (p1.getInit()>p2.getInit());
	}
	
	public boolean aPluDgt(Personnage p1, Personnage p2) {
		return (p1.getDgt()>p2.getDgt());
	}

	
	public boolean estDansInventaire(Item p) {
		for (	int i = 0	; i<(this.inventaire.size())	;	i++) {
			if (p.contentEquals(this.inventaire.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	
	public void afficheInventaire() {
		System.out.println("Inventaire "+"("+this.inventaire.size()+")"+": ");
		String s = "";
		if (this.inventaire.size()==0) {
			s = "vide";
		}
		else {
			for (int i = 0; i<this.inventaire.size()-1 &&this.inventaire.get(i)!=null ; i++ ) {
	            s += i+1 + ". " + this.inventaire.get(i).toString()+", ";
	        }
			s += this.inventaire.size() + ". " + this.inventaire.get(this.inventaire.size()-1).toString()+".";
		}
        System.out.println(s);
        System.out.println("");
    }

    public void addInventaire(Item objet) {
    	
    	if (this.inventaire.size()==16) {
    		System.out.println("Inventaire plein");
    	}
    	
    	else {
    		int i = 0;
            while(i<(this.inventaire.size()) && !this.inventaire.get(i).equals(null)) {
              i++;
            }
            this.inventaire.add(objet);
    	}
    }
    public void removeIndInventaire(int indice) {
    	this.inventaire.remove(indice);
    }
	
	public void removeInventaire(Item item) {
        for(int i = 0; i<this.inventaire.size();i++) {
            if(this.inventaire.get(i)==item) {
            	this.inventaire.remove(i);
                break;
            }
        }
    }
	
	public int getNbPot() { //méthode pour compter le nombre de potion dans l'inventaire
		int compteur = 0;
		for (int i  = 0; i< this.inventaire.size();i++) {
			if (this.inventaire.get(i) instanceof Potion) {
				compteur++;
			}
		}
		return compteur;
	}
	
	public void affichePotion() {
		String s = "";
		int nb = this.getNbPot();
		for (int i = 0; i < this.inventaire.size()-1;i++) {
			if (this.inventaire.get(i) instanceof Potion) {
				s += (i+1) +" "+ this.inventaire.get(i).toString() + ",";
			}
		}
		
		if (this.inventaire.get(this.inventaire.size()-1) instanceof Potion) {
			s += this.inventaire.get(this.inventaire.size()-1).toString() + ".";
		}
		System.out.println("Potion"+"("+nb+")");
		System.out.println(s);
	}
	
	public void combat(Personnage p1, Personnage p2) {
		if (p1.isEnCombat() == false) {
			p1.setEnCombat();
		}
		
		if (p2.isEnCombat() == false) {
			p1.setEnCombat();
		}
		
	}
	
	
//	
//	abstract void attaquer();
//	
//	abstract void ramasser(String obj);
//	
//	abstract void deposer(String obj);
//	
//	abstract String affichePA();
//	
//	abstract void changePA(int point);

	
	

}