import java.io.Serializable;
import java.util.ArrayList;

public class Map implements Serializable {
	ArrayList<PersonnageNonJoueur> listePNJ = new ArrayList<PersonnageNonJoueur>();
	
	ArrayList<PersonnageJoueur> listePersonnage = new ArrayList<PersonnageJoueur>();

	ArrayList<Case> listeMur = new ArrayList<Case>();
	ArrayList<Integer> murLigne = new ArrayList<Integer>();
	ArrayList<Integer> murCol = new ArrayList<Integer>();
	
	
	int[] centre = new int[2];
	
	ArrayList<Item> itemAuSol = new ArrayList<Item>();
	
	Mur mur;
	
	Case[][] map;
	
	public Map() { //une map doit toujours avoir des parametres impairs
		
		this.map = new Case[55][47];
		
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
				if (i == 0 || i == this.map.length-1 || j == 0 || j == this.map[0].length-1) {
					this.map[i][j] = new Case(new Mur(),i,j);
				}
				else if (count == alea) {
					PersonnageJoueur p = new PersonnageJoueur();
					this.addPJ(p, i, j);
				}
				else {
					this.map[i][j] = new Case(i,j);
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
					if (i == 0 || i == this.map.length-1 || j == 0 || j == this.map[0].length-1) {
						this.map[i][j] = new Case(new Mur(),i,j);
					} else if (count == alea) {
						PersonnageJoueur p = new PersonnageJoueur();
						this.addPJ(p, i, j);
						
					} else {
						map[i][j] = new Case(i,j);
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
					
					if (i == 0 || i == this.map.length-1 || j == 0 || j == this.map[0].length-1) {
						this.map[i][j] = new Case(new Mur(),i,j);
					} else if (i == poshPJ && j == posvPJ) 												//condition pour placer un PJ
					{
						//ajout d'un personnagejoueur dans la liste des personnages
						this.addPJ(p, i, j);
					} else 																				//placage du vide et/ou d'un monstre

							

					map[i][j] = new Case(i,j);
					}
				}
			this.placerObstacle(40);
			this.placerMonstre(25);
			}
	}
	
	public Map(ArrayList<PersonnageNonJoueur> listeNPC, ArrayList<Integer> posHNPC,
			ArrayList<Integer> posVNPC,ArrayList<Case> mur, ArrayList<Integer> ligneMur, ArrayList<Integer> colMur, 
			PersonnageJoueur p, int dimx, int dimy) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			this.map = new Case[dimy][dimx];
			
			//création de la this.map
			for (int i = 0; i<this.map.length;i++) {
				
				for (int j = 0; j<this.map[0].length;j++) {
					
					if (i == 0 || i == this.map.length-1 || j == 0 || j == this.map[0].length-1) {
						this.map[i][j] = new Case(new Mur(),i,j);
					} 
					else if (i == p.getCaseP().ligne && j == p.getCaseP().col) 												//condition pour placer un PJ
					{
						if (j == 0 && j == this.map[0].length-1) {											//on vérifie que la position du PJ ne soit pas sur une bordure
							System.out.println("Impossible de placer le personnage" + p.getPseudo());
							
						} else {																		
							//ajout d'un personnagejoueur dans la liste des personnages
							this.addPJ(p, i, j);
						}
					} else
						{

							this.map[i][j] = new Case(i,j);
						}
				}
			}
			this.placerObstacleListe(mur, ligneMur, colMur);
			this.placerPersonnage(listeNPC, posHNPC, posVNPC, this.listePersonnage);
		}
	}
	
	
	public ArrayList<PersonnageNonJoueur> getListePNJ() {
		return listePNJ;
	}



	public ArrayList<PersonnageJoueur> getListePersonnage() {
		return listePersonnage;
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
		return p.getCaseP().ligne;
	}
	
	public int getPosVJoueur(PersonnageJoueur p) {

		return p.getCaseP().col;
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

	public void setListePersonnage(ArrayList<PersonnageJoueur> listePersonnage) {
		this.listePersonnage = listePersonnage;
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
				if (alea == count && this.map[i][j].contenu==Case.VIDE)
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
		this.addPJ(p, p.getCaseP().ligne, p.getCaseP().col);
	}
	
	public void placerPersonnage(ArrayList<PersonnageNonJoueur> listePNJ, ArrayList<Integer> posHPNJ, ArrayList<Integer> posVPNJ, 
								ArrayList<PersonnageJoueur> listePJ) {
		
		if (listePNJ.size()==0 && listePJ.size()==0){
			return;
		}
		
		if (listePNJ.size() > 0) {
			for (int i = 0; i<listePNJ.size();i++) {
				Case caseVide = new Case(posVPNJ.get(i),posVPNJ.get(i));
				if (this.map[posHPNJ.get(i)][posVPNJ.get(i)]==caseVide) {
					this.map[posHPNJ.get(i)][posVPNJ.get(i)] = new Case(listePNJ.get(i),posHPNJ.get(i),posVPNJ.get(i));
				} else {
					System.out.println("L'une des cases est indisponible");
				}
			}
		} else {
			System.out.println("Aucun monstre n'a été ajouté");
		}
		
		if (listePJ.size() > 0) {
			for (int i = 0; i<listePJ.size();i++) {
				Case caseVide = new Case(posVPNJ.get(i),posVPNJ.get(i));
				
				int ligne = listePJ.get(i).getCaseP().ligne;
				int col = listePJ.get(i).getCaseP().col;
				
				if (this.map[ligne][col]==caseVide) {
					this.map[ligne][col] = new Case(listePJ.get(i),posVPNJ.get(i),posVPNJ.get(i));
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
		this.map[ligne][col] = new Case(new Mur(),ligne,col);
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
		p.setCaseP(new Case(p,posh,posv));
		this.map[posh][posv] = p.getCaseP();
		this.listePersonnage.add(p);
	}
	
	public void addPNJ(PersonnageNonJoueur p, int posh, int posv) {
		Case c = new Case(p,posh,posv);
		p.setCaseP(c);
		this.map[posh][posv] = c;
		this.listePNJ.add(p);
	}
	
	public void remplacePJ(PersonnageJoueur p, int posh, int posv, int indice) {
		Case c = new Case(p,posh,posv);
		p.setCaseP(c);
		this.map[posh][posv] = c;
		this.listePersonnage.set(indice, p);
	}
	
	public void remplacePNJ(PersonnageNonJoueur p, int posh, int posv, int indice) {
		Case c = new Case(p,posh,posv);
		p.setCaseP(c);
		this.map[posh][posv] = c;
		this.listePNJ.set(indice, p);
	}
	
	public void placerObstacleHorizontal(int longueur) {
		int min = Math.min(this.map.length, this.map[0].length);
		int max = Math.max(this.map.length, this.map[0].length);
		int alea = Personnage.rand(min+1,(min*max-min-1));
		int count = 0;
		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length;j++) {
			
				//on place le monstre aléatoirement
				if (alea == count && this.map[i][j].contenu==Case.VIDE)
				{
					for (int k = j; k<longueur;k++) {
						if (this.map[i][k].contenu==Case.VIDE || k < this.map[0].length) {
							this.addMur(new Case(new Mur(),i,k), i, k);
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
				if (alea == count && this.map[i][j].contenu==Case.VIDE)
				{
					for (int k = i; k<longueur;k++) {
						if (this.map[k][j].contenu==Case.VIDE || k < this.map.length) {
							this.addMur(new Case(new Mur(),k,j), k, j);
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
	
	public ArrayList<Integer> affichePos(PersonnageJoueur p){
		
		ArrayList<Integer> pos = new ArrayList<Integer>(2);
		for (PersonnageJoueur personnage:listePersonnage) {
			if (personnage == p) {
				pos.add(personnage.getCaseP().ligne);
				pos.add(personnage.getCaseP().col);
			}
		}
		return pos;
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
	
	public ArrayList<Case> autour(Personnage p){
		ArrayList<Case> autourPJ = new ArrayList<>(4);

		int posh = p.getCaseP().ligne;
		int posv = p.getCaseP().col;
		
		//au dessus du personnage
		autourPJ.add(this.map[posh-1][posv]);
		//a gauche et adroite du personnage
		autourPJ.add(this.map[posh][posv-1]);
		autourPJ.add(this.map[posh][posv+1]);
		//en dessous du personnage
		autourPJ.add(this.map[posh+1][posv]);


		return autourPJ;
	}
	
	public void deplacer(PersonnageJoueur p, int c) {
		Case pos = new Case(p,p.getCaseP().ligne,p.getCaseP().col);
		ArrayList<Case> autour = this.autour(p);
		
		if (c == 11) {
			if (autour.get(0).occupee==false) {
				this.deplacement(p, this.map[p.getCaseP().ligne-1][p.getCaseP().col]);
				System.out.println("Haut");
			}
			else {
				System.out.println("Déplacement impossible");
			}
			
		} else if (c == 12) {
			if (autour.get(1).occupee==false) {
				this.deplacement(p, this.map[p.getCaseP().ligne][p.getCaseP().col-1]);
				System.out.println("Gauche");
			}
			else {
				System.out.println("Déplacement impossible");
			}
			
		} else if (c == 13) {
			if (autour.get(2).occupee==false) {
				this.deplacement(p, this.map[p.getCaseP().ligne][p.getCaseP().col+1]);
				System.out.println("Droite");
			}
			else {
				System.out.println("Déplacement impossible");
			}
			
		} else if (c == 14) {
			if (autour.get(3).occupee==false) {
				this.deplacement(p, this.map[p.getCaseP().ligne+1][p.getCaseP().col]);
				System.out.println("Bas");
			}
			else {
			System.out.println("Déplacement impossible");
			}
		}
	}
	
	public void deplacement(PersonnageJoueur p, Case c) {
		//il faut bien changer le contenu et le statut "occupee" sinon le joueur ne pourra pas revenir sur sa case de départ
		this.map[p.getCaseP().ligne][p.getCaseP().col].contenu = Case.VIDE;
		this.map[p.getCaseP().ligne][p.getCaseP().col].occupee = false;
		
		this.map[c.ligne][c.col].contenu = p;
		this.map[c.ligne][c.col].occupee = true;
		this.listePersonnage.get(0).setCaseP(new Case(p,c.ligne,c.col));
		
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