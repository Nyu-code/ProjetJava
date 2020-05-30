public abstract class Personnage {
	private static String [] tabBlessure = {"en forme","superficielle","blessé","gravement blessé","inconscient","mort"}; //blessure de 0 à 6
	private static String [] tabPotion = {"molotov","soin","explosion","mana"};
	private static String inventaire[] = new String[15];
	private String blessure;
	private int exp;	//experience
	private int init;	//initiative
	private int atk;	//attaque
	private int esq;	//esquive
	private int def;	//defense
	private int dgt;	//degat
	
	public Personnage() {
		for( int i = 0; i < 15; i++ )
			  inventaire[i] = "";
		this.blessure = tabBlessure[0];
		this.exp = 0;
		this.init = 0;
		this.atk = 0;
		this.esq = 0;
		this.def = 0;
		this.dgt = 0;
		 
		
	}
	
	public Personnage(int blessure, int exp, int init, int atk, int esquive, int defense, int degat) {
		super();
		for( int i = 0; i < 15; i++ )
			  inventaire[i] = "";
		this.blessure = tabBlessure[blessure];
		this.exp = exp;
		this.init = init;
		this.atk = atk;
		this.esq = esquive;
		this.def = defense;
		this.dgt = degat;
	}

	public Personnage(Personnage p) {
		for( int i = 0; i < 15; i++ )
			  inventaire[i] = "";		
		this.blessure = p.blessure;
		this.exp = p.exp;
		this.init = p.init;
		this.atk = p.atk;
		this.esq = p.esq;
		this.def = p.def;
		this.dgt = p.dgt;
	}

	public static String[] getTabBlessure() {
		return tabBlessure;
	}

	public static String[] getTabPotion() {
		return tabPotion;
	}

	public String[] getInventaire() {
		return inventaire;
	}

	public String getBlessure() {
		return blessure;
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

	public static void setTabBlessure(String[] tabBlessure) {
		Personnage.tabBlessure = tabBlessure;
	}

	public static void setTabPotion(String[] tabPotion) {
		Personnage.tabPotion = tabPotion;
	}

	public static void setInventaire(String[] inventaire) {
		Personnage.inventaire = inventaire;
	}

	public void setBlessure(String blessure) {
		this.blessure = blessure;
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
	
	public int rand(int min, int max) { //méthode pour créer l'aléatoire
		int n = min + (int)(Math.random() * ((max - min) + 1));
		return n;
	}
	
	public int dee() {	//méthode pour un dée de 6 faces
		return rand(1,6);
	}
	
	public void afficheInventaire() {
        System.out.println("Inventaire : ");
        for (int i = 0; i<inventaire.length ; i++ ) {
            System.out.print(inventaire[i]+", ");
        }
        System.out.println("");
    }

    public boolean addInventaire(String objet) {
    	int i = 0;
    	while(!inventaire[i].equals("")){
    	  i++;
    	}
    	if (i == (inventaire.length)-1) {
    		System.out.println("L'inventaire est plein !");
    		return false;
    	}
    	else {
    	inventaire[i] = objet;
    	return true;
    	}
    }
	public boolean removeInventaire(String objet) {
		for(int i = 0; i<inventaire.length;i++) {
			if(inventaire[i]==objet) {
				inventaire[i] = "";
				return true;
			}
		}
		return false;
	}
	
	

}
