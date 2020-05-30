import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PersonnageJoueur extends Personnage{
	private static final String type="joueur";
	private static int pointAction = 12;
	private static final String [] actions 	= 	{"attaquer (3PA)"	,"se déplacer (2PA)","utiliser un objet(variable)"	,"ramasser/deposer (2PA)"	,"finir et garder les PA restants"};
	private static final int [] coutAction 	= 	{	3				,	2				,	0							,		2					,	0};
	private int adresse;
	private int resistance;
	private int force;
	
	public PersonnageJoueur() {
		super();
		this.adresse = 0;
		this.resistance = 0;
		this.force = 0;
		
	}

	public PersonnageJoueur(int blessure, int exp, int init, int atk, int esquive, int defense, int degat,
							int adresse, int resistance, int force)
	{
		super(blessure,exp,init,atk,esquive,defense,degat); //on appelle le constructeur de personnage avec les bons paramètres
		this.adresse = adresse;
		this.resistance = resistance;
		this.force = force;
		
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

	public void setAdresse(int adresse) {
		this.adresse = adresse;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public void setForce(int force) {
		this.force = force;
	}

	public String affichePA() {
		return "Vos points d'action : " + pointAction;
	}
	
	public void changePA(int point) {
		pointAction = pointAction + point;
	}
	
	public String afficheAction() {
		String s = new String("Vos choix possibles :\n");
		int padispo = this.getPointAction();		//stock le nombre de pa disponible
		ArrayList<String> possible = new ArrayList<>(); //liste d'action possible
		
		for (int i = 0;i<actions.length;i++) {		//boucle pour ajouter dans la liste "possible" les actions possibles en prenant compte du point d'action restant
			if (padispo>=coutAction[i]) {			//regarde si l'action à l'indice i est possible.
				possible.add(actions[i]);			//oui -> on l'ajoute dans la liste des actions possibles.
			}
		}
		
		for (int j = 0;j<possible.size();j++) {
			s = s + (j+1) + " - " + possible.get(j) + "\n";
			}
		return s;
	}
	
	public int nbAction() {	//méthode permettant d'obtenir le nombre d'actions possible
		int padispo = this.getPointAction();
		ArrayList<String> possible = new ArrayList<>();
		
		for (int i = 0;i<actions.length;i++) {			
			if (padispo>=coutAction[i]) {				
				possible.add(actions[i]);				
			}
		}
		
		return possible.size();
	}
	
	public int getChoixJoueur() {
		
		String listeAction = this.afficheAction();
		System.out.println(listeAction);			//Affiche les actions et on demande le choix
		System.out.print("\nVotre choix : ");
		
		boolean choix = false;						//on initialise un choix "faux"
		Scanner sc = new Scanner(System.in);		//crée le scanner pour "l'input"
		int choixJoueur = 0;						//initialise un choix pour le reste de la boucle/méthode
		
		int nb = this.nbAction();					//stock le nombre d'action possible du joueur
		
		while (!choix) {								//boucle qui pour vérifier qu'on input bien un choix correcte
			try{	
				sc = new Scanner(System.in);
				choixJoueur = sc.nextInt();
				if (nb>=choixJoueur) {					//on vérifie que le choix choisit correspond bien à un chiffre des choix possibles.
					choix = true;
				}
				else {
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
	
	public void deplacer() { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		pointAction = pointAction - 2;
	}
	
	public void attaquer() { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		pointAction = pointAction - 3;
	}
	
	public void ramadepo() { //pour plus tard !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		pointAction = pointAction - 2;
	}
	
	public String afficheBlessure() {
		return "Votre niveau de blessure : " + super.getBlessure();
	}
	
	public int[] tiragealea() { //Systeme de tirage aléatoire
        int de_adresse = 0;
        int de_resistance = 0;
        int de_force = 0;
        int[] resultats = {0,0,0};
        
/////////////////////////////////////////////////////////////////////////////////////////////////////
        int affichetirage = 0;
        int tirageadresse = this.adresse; //Tant que tirageadresse est <= à 3 on peut ajouter un dé
        for (de_adresse = 0; tirageadresse >= 3 ; de_adresse++) {
            tirageadresse -= 3;
            affichetirage += dee();
        }
        affichetirage += tirageadresse; // On ajoute ce qu'il reste aux Dé pour avoir le résultat du tirage
        resultats[0]=affichetirage;
        System.out.println("Tirage aleatoire d'adresse :"+ de_adresse + "D" + (int) tirageadresse + " Resultat : " + affichetirage);
/////////////////////////////////////////////////////////////////////////////////////////////////////
        affichetirage = 0;
        int tirageresistance = this.resistance;
        for (de_resistance = 0; tirageresistance >= 3 ; de_resistance++) {
            tirageresistance-=3;
            affichetirage += dee();
        }
        affichetirage += tirageresistance;
        resultats[1]=affichetirage;
        System.out.println("Tirage aleatoire d'adresse :"+ de_resistance + "D" + (int) tirageresistance + " Resultat : " + affichetirage);
/////////////////////////////////////////////////////////////////////////////////////////////////////
        affichetirage = 0;
        int tirageforce = this.force;
        for (de_force = 0; tirageforce >= 3 ; de_force++) {
            tirageforce-=3;
            affichetirage += dee();
        }
        affichetirage += tirageforce;
        resultats[2]=affichetirage;
        System.out.println("Tirage aleatoire d'adresse :"+ de_force + "D" + (int) tirageforce + " Resultat : " + affichetirage);
/////////////////////////////////////////////////////////////////////////////////////////////////////
        return resultats;
    }
	
	public static void main(String[] args) {
		PersonnageJoueur p1 = new PersonnageJoueur(0,0,10,20,5,10,10,20,15,30);
		//System.out.println(p1.affichePA());
		//System.out.println(p1.afficheBlessure());
		//p1.getChoixJoueur();
        //p1.tiragealea();
        p1.addInventaire("bouclier");
        p1.afficheInventaire();
        p1.removeInventaire("bouclier");
        p1.afficheInventaire();
	}
	
}