import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonnageJoueur extends Personnage{
	private String pseudo;
	private boolean enCombat = false;
	private static final String type="joueur";
	private int pointAction = 6;
	private final String [] actions 	= 	{"attaquer (3PA)"	,"se déplacer (2PA)","utiliser un objet(variable)"	,"ramasser/deposer (2PA)"	,"finir et garder les PA restants"};
	private final int [] coutAction 	= 	{	3				,	2				,	0							,	2						,	0	};
	private final int [] faitAction 	=	{	1				,	2				,	3							,	4						,	5	};
	
	static final int ATTAQUER = 1;
	static final int DEPLACER = 2;
	static final int UTILISER = 3;
	static final int RAMASSER = 4;
	static final int DEPOSER = 5;
	static final int FINIR = 6;
	
	private int adresse,resistance,force,degre;

	
	public PersonnageJoueur() {
		super();
		MenuCreation m = new MenuCreation();
		while (!m.getConfirmation()) {
			System.out.println("");
		}
		this.pseudo = m.pseudo;
		this.adresse = m.adresse;
		this.resistance = m.resistance;
		this.force = m.force;
		this.degre = m.degres;
		
	}



	public PersonnageJoueur(String pseudo, int force, int adresse, int resistance,
				int exp, int init, int atk, int esquive, int defense, int degat,int degre)
	{
		super(exp,init,atk,esquive,defense,degat); //on appelle le constructeur de personnage avec les bons paramètres
		this.pseudo = pseudo;
		this.adresse = adresse;
		this.resistance = resistance;
		this.force = force;
		this.degre = degre;
		
	}
	
	public String getType() {
		return type;
	}

	public int getPointAction() {
		return pointAction;
	}

	public int getAdresse() {
		return adresse;
	}

	public int getResistance() {
		return resistance;
	}

	public int getForce() {
		return force;
	}

	public String getPseudo() {
		return pseudo;
	}
	
	public int getDegre() {
		return degre;
	}

	public String[] getActions() {
		return actions;
	}



	public boolean isEnCombat() {
		return enCombat;
	}



	public void setEnCombat() {
		this.enCombat = !this.enCombat;
	}



	public void setDegre(int degre) {
		this.degre = degre;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public void setAdresse(int adresse) {
		this.adresse = adresse;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public void setPointAction(int pointAction) {
		this.pointAction = pointAction;
	}

	public String affichePA() {
		return "Vos points d'action : " + pointAction;
	}
	
	public void changePA(int point) {
		this.pointAction += point;
	}
	
	public ArrayList<Integer> indActPossible(){
		ArrayList<Integer> indice = new ArrayList<>();
		int padispo = this.getPointAction();
		
		for (int i = 0;i<actions.length;i++) {			
			if (padispo>=coutAction[i]) {				
				indice.add(faitAction[i]);				
			}
		}
		return indice;
	}
	
	public ArrayList<String> actionPossible(){
		ArrayList<String> possible = new ArrayList<>();
		int padispo = this.getPointAction();
		for (int i = 0;i<actions.length;i++) {			
			if (padispo>=coutAction[i]) {				
				possible.add(actions[i]);				
			}
		}
		
		return possible;
	}
	
	public String afficheAction() {
		String s = "";
		ArrayList<String> actPossible = this.actionPossible();
		
		for (int j = 0;j<actPossible.size();j++) {
			s = s + (j+1) + " - " + actPossible.get(j) + "\n";
			}
		return s;
	}
	
	public int nbAction() {	//méthode permettant d'obtenir le nombre d'actions possible
		ArrayList<String> possible = this.actionPossible();
		return possible.size();
	}
	
	public int getChoixJoueur() {
		
		System.out.println(this.afficheAction());	//Affiche les actions et on demande le choix /!\ à pê supprimer
		System.out.print("\nVotre choix : ");		// /!\ à pê supprimer
		
		
		
		boolean choix = false;						//on initialise un choix "faux"
		Scanner sc = new Scanner(System.in);		//crée le scanner pour "l'input"
		int choixJoueur = 0;						//initialise un choix pour le reste de la boucle/méthode
		
		int nb = this.nbAction();					//stock le nombre d'action possible du joueur
		
		while (!choix) {								//boucle qui pour vérifier qu'on input bien un choix correcte
			try{	
				sc = new Scanner(System.in);
				choixJoueur = sc.nextInt();
				if (nb >= choixJoueur && choixJoueur>0) {					//on vérifie que le choix choisit correspond bien à un chiffre des choix possibles.
					choix = true;
				}	else 	{
					System.out.println("Le choix ne correspond pas à la liste d'actions possible");
					choix = false;
				}
			}
			catch (InputMismatchException e) {			//si le joueur saisi autre qu'un entier, ça renvoit une erreur et un print mais le programme continu.
				System.out.println("Vous n'avez pas saisi d'entier.");
			}
		}
		sc.close();
		return choixJoueur;
	}
	

	
	public void faireAction() { //en developpement
		int choix = getChoixJoueur();
		ArrayList<Integer> indicePossible = this.indActPossible();
		int action = faitAction[indicePossible.get(choix-1)];
		
//		switch (action) {
//			case ATTAQUER:{
//				this.attaquer();
//				break;
//			}
//			case UTILISER:{
//				int objet = choixObjet();
//				utiliserObjet(inventaire.get(objet));
//				break;
//			}
//			case DEPLACER:{
//				break;
//			}
//			case RAMASSER:{
//				objet = 
//				this.ramasser(objet);
//				break;
//			}
//			case DEPOSER:{
//				int objet = choixObjet();
//				this.deposer(objet);
//				break;
//			}
//			case FINIR:{
//				finir();
//				break;
//			}
//		}
		
	}
	
	public int choixObjet() {
		Scanner sc = new Scanner(System.in);
		boolean choix = false;
		int indice = 0;
		while (!choix) {
			try {
					indice = sc.nextInt();
					if (indice<inventaire.size()) {
						choix = true;
					}
			}
			catch (InputMismatchException e) {
				System.out.println("Saisissez une case correct (entier)");
			}
		}
		return indice;
	}
	
	public void attaquer() { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.changePA(-3);
		System.out.println("Vous attaquez !");
	}
	
	public void deplacer() {
		this.changePA(-2); //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		System.out.println(("Vous vous déplacez"));
	}
	
	public void ramasser(int indice) { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.changePA(-2);
		
		super.removeIndInventaire(indice);
		
		System.out.println("Vous avez ramassé :" + inventaire.get(indice).toString());
	}
	
	public void deposer(int indice) { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		this.changePA(-2);
		
		super.removeIndInventaire(indice);
		System.out.println("Vous avez deposé :" + inventaire.get(indice).toString());
	}
	
	public void utiliserObjet(Item i) {
		if (i instanceof Potion) {
			this.potion((Potion)i);
			System.out.println("Vous utilisez une potion !");
		}
	}
	
	public boolean finir() {
		System.out.println("Votre tour est terminé");
		return true;
	}
	
//	public void choixPotion() { //en cours de dév
//		ArrayList
//	}
	
	public void potion(Potion p) {
		System.out.println(this.getHp());
		System.out.println(this.getHp()*p.degat);
		if (p instanceof Mana) {
			this.setPointAction(this.getPointAction()+p.pa);
		} else {
			super.setHp(this.getHp() + this.getMaxHp()*p.degat);
			super.setBlessure();
		}
	}
	
	public String afficheBlessure() {
		return "Votre niveau de blessure : " + super.getBlessure();
	}
	
	public int[] tirageAlea() { //Systeme de tirage aléatoire
        int de_adresse = 0;
        int de_resistance = 0;
        int de_force = 0;
        
        int[] resultat = new int[3];

        int affichetirage = 0;
        int tirageadresse = this.adresse; //Tant que tirageadresse est <= à 3 on peut ajouter un dé
        for (de_adresse = 0; tirageadresse >= 3 ; de_adresse++) {
            tirageadresse -= 3;
            affichetirage += dee();
        }
        affichetirage += tirageadresse; // On ajoute ce qu'il reste aux Dé pour avoir le résultat du tirage
        
        resultat[0] = affichetirage;
        System.out.println("Tirage aleatoire d'adresse :"+ de_adresse + "D" + (int) tirageadresse + " Resultat : " + affichetirage);

        affichetirage = 0;
        int tirageresistance = this.resistance;
        for (de_resistance = 0; tirageresistance >= 3 ; de_resistance++) {
            tirageresistance-=3;
            affichetirage += dee();
        }
        affichetirage += tirageresistance;
        
        resultat[1] = affichetirage;
        System.out.println("Tirage aleatoire de resistance :"+ de_resistance + "D" + (int) tirageresistance + " Resultat : " + affichetirage);

        affichetirage = 0;
        int tirageforce = this.force;
        for (de_force = 0; tirageforce >= 3 ; de_force++) {
            tirageforce-=3;
            affichetirage += dee();
        }
        affichetirage += tirageforce;
        
        resultat[2] = affichetirage;
        System.out.println("Tirage aleatoire de force :"+ de_force + "D" + (int) tirageforce + " Resultat : " + affichetirage);
        return resultat;
    }
	public String afficheStats() {
		String s = "";
		s += "Experience : " + super.getExp() + "\n";
		
		s += "Force : " + this.getForce() + "\n";
		s += "Adresse : " + this.getAdresse() + "\n";
		s += "Resistance : " + this.getResistance() + "\n";
		
		s += "Initiative : " + this.getInit() + "\n";
		s += "Attaque : " + this.getAtk() + "\n";
		s += "Esquive : " + this.getEsq() + "\n";
		s += "Défense : " + this.getDef() + "\n";
		s += "Dégâts : " + this.getDgt() + "\n";
		
		return s;
	}
	public void equiper(Item i) {
		if (i instanceof Bouclier) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Vous souhaitez l'équiper en tant qu'arme(1) ou en tant que defense(2) ?");
			boolean verif = false;
			int choix = 0;
			while (!verif) {
				try {
					choix = scan.nextInt();
					if (choix > 0 && choix < 3) {
						verif = true;
					}
					else
						System.out.println("Entrez 1 pour arme ou 2 pour defense");
				}
				catch(InputMismatchException e){
					System.out.println("Veuillez entrer un entier");
				}
			}
			if(choix == 1) {
				if(!this.droite_libre) {
					
					this.addInventaire((Armes) droite);
					this.removeInventaire((Bouclier) i);
					this.droite = i;
					System.out.println(droite.toString() + "a été remplacé par "+ i.toString());
					
				}
				else {
					i = new Armes("Bouclier", Bouclier.ARMIMPACT, Bouclier.MANIABILITE);
					this.droite = i;
					this.removeInventaire((Bouclier) i);
					this.droite_libre = false;
				}
			}
			else {
				if(!this.gauche_libre) {
					
					this.addInventaire((Bouclier) gauche);
					this.removeInventaire((Bouclier) i);
					this.gauche = i;
					System.out.println(gauche.toString() + "a été remplacé par "+ i.toString());
					
				}
				else {
					i = new Vetements("Bouclier", Bouclier.SOLIDITE, Bouclier.POIDS, Bouclier.RESISTANCE);
					this.gauche = i;
					this.removeInventaire((Bouclier) i);
					this.gauche_libre = false;
				}
			}
		}
		else if (i instanceof Armes){
			if(!this.droite_libre) {
				
				this.addInventaire((Armes) droite);
				this.removeInventaire((Armes) i);
				this.droite = i;
				System.out.println(droite.toString() + "a été remplacé par "+ i.toString());
				
			}
			else {
				this.droite = i;
				this.removeInventaire(i);
				this.droite_libre = false;
			}
		}
		else if (i instanceof Vetements){
			if (!this.protection_libre) {
				
				this.addInventaire((Vetements) protection);
				this.removeInventaire((Vetements) i);

			}
			else {
				this.protection = i;
				this.removeInventaire(i);
				this.protection_libre = false;
			}
		}
	}
	
	public void desequiper(Item i) {
		if (i instanceof Bouclier) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Vous souhaitez déséquiper l'arme(1) ou la défense(2) ?");
			boolean verif = false;
			int choix = 0;
			while (!verif) {
				try {
					choix = scan.nextInt();
					if (choix > 0 && choix < 3) {
						verif = true;
					}
					else
						System.out.println("Entrez 1 pour arme ou 2 pour defense");
				}
				catch(InputMismatchException e){
					System.out.println("Veuillez entrer un entier");
				}
			}
			if(choix == 1) {
				if(this.droite_libre) {
					System.out.println("La main droite est vide");
				}
				else {
					i = new Bouclier();
					Item vide = new Main();
					this.droite = vide;
					this.addInventaire((Bouclier) i);
					this.droite_libre = true;
				}
			}
			else {
				if(this.gauche_libre)
					System.out.println("La main gauche est vide");
				else {
					i = new Bouclier();
					this.gauche = null;
					this.addInventaire((Bouclier) i);
					this.gauche_libre = true;
				}
			}
		}
		else if (i instanceof Armes){
			if(this.droite_libre) {
				System.out.println("Main droite vide");
			}
			else {
				this.droite = null;
				this.addInventaire(i);
				this.droite_libre = true;
			}
		}
		else if (i instanceof Vetements){
			if (this.protection_libre) {
				System.out.println("Vous n'êtes pas protégé");
			}
			else {
				this.protection = null;
				this.addInventaire(i);
				this.protection_libre = true;
			}
		}
	}
	
	public String toString() {
		return "pseudo: " + this.getPseudo() +", type: " + this.getType() + ", forme: " + super.getBlessure() + ", PA: " + this.getPointAction();
	}

}
