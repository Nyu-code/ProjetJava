import java.util.*;

public class Map {
	ArrayList<PersonnageNonJoueur> listePNJ = new ArrayList<PersonnageNonJoueur>();
	ArrayList<Integer> poshPNJ = new ArrayList<Integer>();
	ArrayList<Integer> posvPNJ = new ArrayList<Integer>();
	
	ArrayList<PersonnageJoueur> listePersonnage = new ArrayList<PersonnageJoueur>();
	ArrayList<Integer> poshPJ = new ArrayList<Integer>();
	ArrayList<Integer> posvPJ = new ArrayList<Integer>();
	
	ArrayList<Case> listeMur = new ArrayList<Case>();
	ArrayList<Integer> murLigne = new ArrayList<Integer>();
	ArrayList<Integer> murCol = new ArrayList<Integer>();
	
	
	int[] centre = new int[2];
	
	ArrayList<Item> itemAuSol = new ArrayList<Item>();
	
	Mur mur;
	
	Case[][] map;
	
	public Map() { //une map doit toujours avoir des parametres impairs
		
		this.map = new Case[32][16];
		int min = Math.min(this.map.length, this.map[0].length);
		int max = Math.max(this.map.length, this.map[0].length);
		int alea = Personnage.rand(min+1,(min*max-min-1));
		
		boolean verifAlea = false;
		//verifie que un chiffre aléatoire correspond bien à la taille du tableau
		while (!verifAlea) {
			if (alea%this.map[0].length+1==0 && alea%this.map[0].length==0) {
				alea = Personnage.rand(min+1,(min*max-min-1));
			} else {
				verifAlea = true;
			}
		}
		int count = 0;
		for (int i = 0; i<this.map.length;i++) {
			
			for (int j = 0; j<this.map[0].length;j++) {
				if (i == 0 || i == this.map.length-1) {
					this.map[i][j] = new Case(new Mur());
				}
				else if (j == 0 || j == this.map[0].length-1) {
					this.map[i][j] = new Case(new Mur());
				}
				
				else if (count == alea) {
					PersonnageJoueur p = new PersonnageJoueur();
					this.addPJ(p, i, j);
				}
				
				else {
					this.map[i][j] = new Case();
				}
				count++;
			}
		}
		this.placerObstacle(30);
		this.placerMonstre(25);
		
		centre[0] = (this.map.length)/2;
		centre[1] = (this.map[0].length)/2;
	}
	
	public Map(int dimx, int dimy) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			map = new Case[dimy][dimx];
			
			int min = Math.min(this.map.length, this.map[0].length);
			int max = Math.max(this.map.length, this.map[0].length);
			
			boolean verifAlea = false;
			int alea = Personnage.rand(min+1,(min*max-min-1));
			while (!verifAlea) {
				if (alea%dimx+1==0 && alea%dimx==0) {
					alea = Personnage.rand(min+1,(min*max-min-1));
				} else {
					verifAlea = true;
				}
			}
			int count = 0;
			
			for (int i = 0; i<map.length;i++) {
				for (int j = 0; j<map[0].length;j++) {
					if (i == 0 || i == map.length-1) {
						map[i][j] = new Case(new Mur());
						
					} else if (j == 0 || j == map[0].length-1) {
						map[i][j] = new Case(new Mur());
						
					} else if (count == alea) {
						PersonnageJoueur p = new PersonnageJoueur();
						this.addPJ(p, i, j);
						
					} else {
						map[i][j] = new Case();
					}
					count++;
				}
			}
			this.placerObstacle(30);
			this.placerMonstre(25);
		}
		
	}
	
	
	public Map(PersonnageJoueur p, int dimx, int dimy, int poshPJ, int posvPJ) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			map = new Case[dimy][dimx];
			

			//création de la map
			for (int i = 0; i<map.length;i++) {
				for (int j = 0; j<map[0].length;j++) {
					
					if (i == 0 || i == map.length-1)													//condition de la bordure nord
					{
						map[i][j] = new Case(new Mur());
					} else if (j == 0 || j == map[0].length-1) 											//condition de la bordure sud
					{
						map[i][j] = new Case(new Mur());
					} else if (i == poshPJ && j == posvPJ) 												//condition pour placer un PJ
					{
//						if (j == 0 && j == map[0].length-1) {											//on vérifie que la position du PJ ne soit pas sur une bordure
//							System.out.println("Impossible de placer le personnage" + p.getPseudo());
//						} else {																		
//							map[i][j] = new Case(p);
//							
//							//ajout d'un personnagejoueur dans la liste des personnages
							this.addPJ(p, i, j);
//						}
					} else 																				//placage du vide et/ou d'un monstre

							

					map[i][j] = new Case();
					}
				}
			this.placerObstacle(40);
			this.placerMonstre(25);
			}
	}
	
	public Map(ArrayList<PersonnageNonJoueur> listeNPC, ArrayList<Integer> posHNPC,
			ArrayList<Integer> posVNPC,ArrayList<Case> mur, ArrayList<Integer> ligneMur, ArrayList<Integer> colMur, 
			PersonnageJoueur p, int dimx, int dimy, int poshPJ, int posvPJ) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			this.map = new Case[dimy][dimx];
			
			//création de la this.map
			for (int i = 0; i<this.map.length;i++) {
				
				for (int j = 0; j<this.map[0].length;j++) {
					
					if (i == 0 || i == this.map.length-1)													//condition de la bordure nord
					{
						this.map[i][j] = new Case(new Mur());
						
					} else if (j == 0 || j == this.map[0].length-1) 											//condition de la bordure sud
					{
						this.map[i][j] = new Case(new Mur());
						
					} else if (i == p.posH && j == p.posV) 												//condition pour placer un PJ
					{
						if (j == 0 && j == this.map[0].length-1) {											//on vérifie que la position du PJ ne soit pas sur une bordure
							System.out.println("Impossible de placer le personnage" + p.getPseudo());
							
						} else {																		
							//ajout d'un personnagejoueur dans la liste des personnages
							this.addPJ(p, i, j);
						}
					} else
						{

							this.map[i][j] = new Case();
						}
				}
			}
			this.placerObstacleListe(mur, ligneMur, colMur);
			this.placerPersonnage(listeNPC, posHNPC, posVNPC, this.listePersonnage, this.poshPNJ, this.posvPJ);
		}
	}
	
	
	public ArrayList<PersonnageNonJoueur> getListePNJ() {
		return listePNJ;
	}

	public ArrayList<Integer> getPoshPNJ() {
		return poshPNJ;
	}

	public ArrayList<Integer> getPosvPNJ() {
		return posvPNJ;
	}

	public ArrayList<PersonnageJoueur> getListePersonnage() {
		return listePersonnage;
	}

	public ArrayList<Integer> getPoshPJ() {
		return poshPJ;
	}

	public ArrayList<Integer> getPosvPJ() {
		return posvPJ;
	}

	public ArrayList<Item> getItemAuSol() {
		return itemAuSol;
	}

	public Map getMap() {
		return this;
	}
	
	public Case[][] getMapCase(){
		return this.map;
	}
	
	public int getIndPersonnage(PersonnageJoueur p) {
		for (int i = 0; i<this.listePersonnage.size();i++) {
			if (p == this.listePersonnage.get(i)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPosHJoueur(PersonnageJoueur p) {
		int i = this.getIndPersonnage(p);
		
		if (i != -1) {
			return this.poshPJ.get(i);
		}
		
		return -1;
	}
	
	public int getPosVJoueur(PersonnageJoueur p) {
		int i = this.getIndPersonnage(p);
		
		if (i != -1) {
			return this.posvPJ.get(i);
		}
		
		return -1;
	}

	public ArrayList<Case> getListeMur() {
		return listeMur;
	}

	public ArrayList<Integer> getMurLigne() {
		return murLigne;
	}

	public ArrayList<Integer> getMurCol() {
		return murCol;
	}

	public void setListeMur(ArrayList<Case> listeMur) {
		this.listeMur = listeMur;
	}

	public void setMurLigne(ArrayList<Integer> murLigne) {
		this.murLigne = murLigne;
	}

	public void setMurCol(ArrayList<Integer> murCol) {
		this.murCol = murCol;
	}

	public void setListePNJ(ArrayList<PersonnageNonJoueur> listePNJ) {
		this.listePNJ = listePNJ;
	}

	public void setPoshPNJ(ArrayList<Integer> poshPNJ) {
		this.poshPNJ = poshPNJ;
	}

	public void setPosvPNJ(ArrayList<Integer> posvPNJ) {
		this.posvPNJ = posvPNJ;
	}

	public void setListePersonnage(ArrayList<PersonnageJoueur> listePersonnage) {
		this.listePersonnage = listePersonnage;
	}

	public void setPoshPJ(ArrayList<Integer> poshPJ) {
		this.poshPJ = poshPJ;
	}

	public void setPosvPJ(ArrayList<Integer> posvPJ) {
		this.posvPJ = posvPJ;
	}

	public void setItemAuSol(ArrayList<Item> itemAuSol) {
		this.itemAuSol = itemAuSol;
	}

	public void setMap(Case[][] map) {
		this.map = map;
	}
	
	public void placerUnMonstre() {
		int min = Math.min(this.map.length, this.map[0].length);
		int max = Math.max(this.map.length, this.map[0].length);
		int alea = Personnage.rand(min+1,(min*max-min-1));
		int count = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
			
				//on place le monstre aléatoirement
				if (alea == count && this.map[i][j].uneCase==Case.VIDE)
				{
				//ajout d'un monstre dans la liste des monstres
				PersonnageNonJoueur pnj = new PersonnageNonJoueur();
				this.addPNJ(pnj, i, j);
				}
				count++;
			}
		}
		
	}
	
	public void placerMonstre(int nombreMonstre) {
		for (int i = 0; i< nombreMonstre; i++) {
			this.placerUnMonstre();
		}
	}
	
	public void placerJoueur(PersonnageJoueur p) {
		this.addPJ(p, p.posH, p.posV);
	}
	
	public void placerPersonnage(ArrayList<PersonnageNonJoueur> listePNJ, ArrayList<Integer> posHPNJ, ArrayList<Integer> posVPNJ, 
								ArrayList<PersonnageJoueur> listePJ, ArrayList<Integer> posHPJ, ArrayList<Integer> posVPJ) {
		
		if (listePNJ.size()==0 && listePJ.size()==0){
			return;
		}
		
		if (listePNJ.size() > 0) {
			for (int i = 0; i<listePNJ.size();i++) {
				Case caseVide = new Case();
				if (this.map[posHPNJ.get(i)][posVPNJ.get(i)]==caseVide) {
					this.map[posHPNJ.get(i)][posVPNJ.get(i)] = new Case(listePNJ.get(i));
				} else {
					System.out.println("L'une des cases est indisponible");
				}
			}
		} else {
			System.out.println("Aucun monstre n'a été ajouté");
		}
		
		if (listePJ.size() > 0) {
			for (int i = 0; i<listePJ.size();i++) {
				Case caseVide = new Case();
				if (this.map[posHPJ.get(i)][posVPJ.get(i)]==caseVide) {
					this.map[posHPJ.get(i)][posVPJ.get(i)] = new Case(listePJ.get(i));
				} else {
					System.out.println("L'une des cases est indisponible");
				}
			}
		} else {
			System.out.println("Aucun personnage n'a été ajouté");
		}
	}
	 
	public int getIndPJ(PersonnageJoueur p) {
		
		if (this.estSurLaMap(p)) {
			int compteur = 0;
			for (PersonnageJoueur pj:this.listePersonnage) {
				if (pj == p) {
					return compteur;
				}
				compteur++;
			}
		}
		return -1;

	}
	
	public void addMur(Case m, int ligne, int col) {
		this.map[ligne][col] = new Case(new Mur());
		this.listeMur.add(m);
		this.murLigne.add(ligne);
		this.murCol.add(col);
	}
	
	public void remplaceMur(Case m, int ligne, int col, int indice) {
		this.map[ligne][col] = m;
		this.listeMur.set(indice, m);
		this.murLigne.set(indice, ligne);
		this.murCol.set(indice, col);
	}
	
	public void addPJ(PersonnageJoueur p, int posh, int posv) {
		this.map[posh][posv] = new Case(p);
		this.listePersonnage.add(p);
		this.poshPJ.add(posh);
		this.posvPJ.add(posv);
	}
	
	public void addPNJ(PersonnageNonJoueur p, int posh, int posv) {
		this.map[posh][posv] = new Case(p);
		this.listePNJ.add(p);
		this.poshPJ.add(posh);
		this.poshPNJ.add(posv);
	}
	
	public void remplacePJ(PersonnageJoueur p, int posh, int posv, int indice) {
		this.map[posh][posv] = new Case(p);
		this.listePersonnage.set(indice, p);
		this.poshPJ.set(indice, posh);
		this.posvPJ.set(indice, posv);
	}
	
	public void remplacePNJ(PersonnageNonJoueur p, int posh, int posv, int indice) {
		this.map[posh][posv] = new Case(p);
		this.listePNJ.set(indice, p);
		this.poshPNJ.set(indice, posh);
		this.posvPNJ.set(indice, posv);
	}
	
	public void placerObstacleHorizontal(int longueur) {
		int min = Math.min(this.map.length, this.map[0].length);
		int max = Math.max(this.map.length, this.map[0].length);
		int alea = Personnage.rand(min+1,(min*max-min-1));
		int count = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
			
				//on place le monstre aléatoirement
				if (alea == count && this.map[i][j].uneCase==Case.VIDE)
				{
					for (int k = j; k<longueur;k++) {
						if (this.map[i][k].uneCase==Case.VIDE || k < this.map[0].length) {
							this.addMur(new Case(new Mur()), i, k);
							System.out.println("Un mur");
						}
					}
					break;
				}
				count++;
			}
		}
		
	}
	
	public void placerObstacleVertical(int longueur) {
		
		int min = Math.min(this.map.length, this.map[0].length);
		int max = Math.max(this.map.length, this.map[0].length);
		int alea = Personnage.rand(min+1,(min*max-min-1));
		int count = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
			
				//on place le monstre aléatoirement
				if (alea == count && this.map[i][j].uneCase==Case.VIDE)
				{
					for (int k = i; k<longueur;k++) {
						if (this.map[k][j].uneCase==Case.VIDE || k < this.map.length) {
							this.addMur(new Case(new Mur()), k, j);
							System.out.println("Un mur");
						}
					}
					break;
				}
				count++;
			}
		}
	}
	
	public void placerObstacle(int nombre) {
		for (int i = 0; i<nombre;i++) {
			int longueur = Personnage.rand(16, 24);
			
			if (i%2==0) {
				this.placerObstacleHorizontal(longueur);
			} else {
				this.placerObstacleVertical(longueur);
			}
			
		}
	}
	
	public void placerObstacleListe(ArrayList<Case> mur, ArrayList<Integer> ligne, ArrayList<Integer> col) {
		
		
		int compteur = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
			
				//on place le monstre aléatoirement
				if (i == ligne.get(compteur) && j == col.get(compteur))
				{
					this.remplaceMur(mur.get(compteur), ligne.get(compteur), col.get(compteur), compteur);
					compteur++;
				}
			}
		}
	}
	
	public boolean estSurLaMap(PersonnageJoueur p) {
		for (PersonnageJoueur pj:this.listePersonnage) {
			if (pj.equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean estAutour(PersonnageJoueur p, Case c) {
		
		if (this.estSurLaMap(p)) {
			ArrayList<Case> autour = this.autour(p);
			
			for (Case cases:autour) {
				if (c==cases){
					return true;
				}
			}
		}
		
		return false;
		
	}
	
	public ArrayList<Case> autour(PersonnageJoueur p){
		ArrayList<Case> autourPJ = new ArrayList<>(8);

		int posh = p.posH;
		int posv = p.posV;
		
		if (this.estSurLaMap(p)) {
			//au dessus du personnage
			autourPJ.add(this.map[posh-1][posv-1]); autourPJ.add(this.map[posh-1][posv]); autourPJ.add(this.map[posh-1][posv+1]);
			//a gauche et adroite du personnage
			autourPJ.add(this.map[posh][posv-1]);autourPJ.add(this.map[posh][posv+1]);
			//en dessous du personnage
			autourPJ.add(this.map[posh+1][posv-1]); autourPJ.add(this.map[posh+1][posv]); autourPJ.add(this.map[posh+1][posv+1]);
		} else {
			System.out.println("Le personnage n'existe pas");
		}
		
		return autourPJ;
	}
	
	public void deplacer(PersonnageJoueur p) {
		
		if (this.estSurLaMap(p)) {
			if (p.getPointAction()<2) {
				return;
			}
			
			ArrayList<String> deplacement = new ArrayList<>(Arrays.asList("Z","Q","S","D"));
			//on sauvegarde les données des monstres ainsi que leur positions
			
			ArrayList<Case> autourP = this.autour(p);
			System.out.println("Où voulez-vous déplacer ? ZQSD ou WASD");
			String choixDeplacement = "";
			
			
			Scanner sc = new Scanner(System.in);
			
			
			
			boolean choix = false;
			while (!choix) {
				try {
					choixDeplacement = sc.next();
					if (deplacement.contains(choixDeplacement)) {
						choix = true;
					}
				} catch (InputMismatchException e) {
					System.out.println("Nous n'avons pas reconnu votre choix, saisissez un déplacement correct (Z, Q, S, D ou W, A, S, D)");
				}
			}
			sc.close();
			
			if ((choixDeplacement == "Z" || choixDeplacement == "W") && autourP.get(1).getOccupee() == false){
				
			}
			
			
		} else {
			System.out.println("Le personnage n'existe pas " + p.getPseudo());
		}
		

	}
	
	public void deplacer(PersonnageNonJoueur npc) {
		
	}
	
	public String laLigne(int ind) {
		String s = "";
		for (int i = 0; i < map[0].length;i++) {
			s+=map[ind][i];
		}
		
		return s;
	}

	public String toString() {
		String s = "";
		
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
				s += this.map[i][j].toString();
				if (j==map[0].length-1) {
					s +="\n";
				}
			}
		}

		return s.substring(0,s.length()-1);
	}
}
