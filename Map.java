import java.util.*;

public class Map {
	ArrayList<PersonnageNonJoueur> listePNJ = new ArrayList<PersonnageNonJoueur>();
	ArrayList<Integer> poshPNJ = new ArrayList<Integer>();
	ArrayList<Integer> posvPNJ = new ArrayList<Integer>();
	
	ArrayList<PersonnageJoueur> listePersonnage = new ArrayList<PersonnageJoueur>();
	ArrayList<Integer> poshPJ = new ArrayList<Integer>();
	ArrayList<Integer> posvPJ = new ArrayList<Integer>();
	
	int[] centre = new int[2];
	
	ArrayList<Item> itemAuSol = new ArrayList<Item>();
	
	Case[][] map;
	
	public Map() { //une map doit toujours avoir des parametres impairs
		
		this.map = new Case[32][16];
		int alea = Personnage.rand(Math.min(this.map.length, this.map[0].length), Math.max(this.map.length, this.map[0].length));
		int count = 0;
		for (int i = 0; i<this.map.length;i++) {
			
			for (int j = 0; j<this.map[0].length;j++) {
				if (i == 0 || i == this.map.length-1) {
					this.map[i][j] = new Case("#");
				}
				else if (j == 0 || j == this.map[0].length-1) {
					this.map[i][j] = new Case("#");
				}
				
				else if (count == alea) {
					this.map[i][j] = new Case("p");
					PersonnageJoueur p = new PersonnageJoueur();
					this.addPJ(p, i, j);
					this.map[i][j] = new Case(p);
				}
				
				else if (i == this.map.length/2 && j == this.map[0].length/2) {
					if (!this.map[i][j].getOccupee()) {
						this.map[i][j] = new Case("0");
					}
				}
				
				else {
					this.map[i][j] = new Case(Case.VIDE);
				}
				count++;
			}
		}
		
		centre[0] = (this.map.length)/2;
		centre[1] = (this.map[0].length)/2;
	}
	
	public Map(int dimx, int dimy) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			map = new Case[dimy][dimx];
			
			int alea = Personnage.rand(Math.min(map.length, map[0].length), Math.max(map.length, map[0].length));
			int count = 0;
			
			for (int i = 0; i<map.length;i++) {
				for (int j = 0; j<map[0].length;j++) {
					if (i == 0 || i == map.length-1) {
						map[i][j] = new Case("#");
						
					} else if (j == 0 || j == map[0].length-1) {
						map[i][j] = new Case("#");
						
					} else if (count == alea) {
						this.map[i][j] = new Case("p");
						PersonnageJoueur p = new PersonnageJoueur();
						this.addPJ(p, i, j);
						this.map[i][j] = new Case(p);
						
					} else if (i == this.map.length/2 && j == this.map[0].length/2) 
						{ if (!this.map[i][j].getOccupee())
							{
								this.map[i][j] = new Case("0");
							}
					} else {
						map[i][j] = new Case(Case.VIDE);
					}
				}
			}
		}
		
	}
	
	
	public Map(PersonnageJoueur p, int dimx, int dimy, int poshPJ, int posvPJ) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			map = new Case[dimy][dimx];
			
			ArrayList<PersonnageNonJoueur> pnjListe = new ArrayList<PersonnageNonJoueur>();
			
			//création de la liste de pnj
			for (int i = 0; i<(int)this.map.length/3;i++) {
				pnjListe.add(new PersonnageNonJoueur());
			}
			int pnjAPlacer = pnjListe.size()-1;
			
			//création de la map
			for (int i = 0; i<map.length;i++) {
				int maxMob = 0;
				for (int j = 0; j<map[0].length;j++) {
					
					if (i == 0 || i == map.length-1)													//condition de la bordure nord
					{
						map[i][j] = new Case("#");
					} else if (j == 0 || j == map[0].length-1) 											//condition de la bordure sud
					{
						map[i][j] = new Case("#");
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
						{
						//on pose au hasard un monstre à 10% de chance 
						
						int alea = Personnage.rand(1,map[0].length);
						
						maxMob = (j > this.map[0].length-1) ? 0 : maxMob;
						
						
						if (alea == (this.map[0].length*0.3) && i%2==0 && pnjAPlacer > 0 && pnjListe.size() > 0 && maxMob < 1)
							{
//							map[i][j] = new Case(pnjListe.get(pnjAPlacer));
//							System.out.println(pnjListe.get(pnjAPlacer) + " est placé à : " + i + ", " + j);
//							
							//ajout d'un monstre dans la liste des monstres
							this.addPNJ(pnjListe.get(pnjAPlacer), i, j);
							
							pnjAPlacer--;
							maxMob++;
							
						} else
							{
								map[i][j] = new Case(Case.VIDE);
							}
						}
				}
			}
			
			this.placerPersonnage(pnjListe, this.poshPNJ, this.posvPNJ, this.listePersonnage, this.poshPJ, this.posvPJ);
		}
	}
	
	public Map(ArrayList<PersonnageNonJoueur> listeNPC, ArrayList<Integer> posHNPC, ArrayList<Integer> posVNPC, PersonnageJoueur p, int dimx, int dimy, int poshPJ, int posvPJ) {
		
		if (dimx < 5 && dimy < 5) {
			System.out.println("Les dimensions sont trop petites");
			
		} else {
			this.map = new Case[dimy][dimx];
			

			int compteurNPC = 0;
			
			//création de la this.map
			for (int i = 0; i<this.map.length;i++) {
				
				for (int j = 0; j<this.map[0].length;j++) {
					
					if (i == 0 || i == this.map.length-1)													//condition de la bordure nord
					{
						this.map[i][j] = new Case("#");
						
					} else if (j == 0 || j == this.map[0].length-1) 											//condition de la bordure sud
					{
						this.map[i][j] = new Case("#");
						
					} else if (i == posHNPC.get(compteurNPC) && j == posVNPC.get(compteurNPC) && compteurNPC < (listeNPC.size()-1))
					{
						this.map[i][j] = new Case(listeNPC.get(compteurNPC));
						
						//apres avoir redéposer le monstre à sa propre case, il faut mettre à jour sa position (s'il n'a pas bougé)
						this.remplacePNJ(listeNPC.get(compteurNPC), posHNPC.get(compteurNPC), posVNPC.get(compteurNPC), compteurNPC);
						compteurNPC++;
						
					} else if (i == p.posH && j == p.posV) 												//condition pour placer un PJ
					{
						if (j == 0 && j == this.map[0].length-1) {											//on vérifie que la position du PJ ne soit pas sur une bordure
							System.out.println("Impossible de placer le personnage" + p.getPseudo());
							
						} else {																		
							this.map[i][j] = new Case(p);
							//ajout d'un personnagejoueur dans la liste des personnages
							this.addPJ(p, i, j);
						}
					} else
						{

							this.map[i][j] = new Case(Case.VIDE);
						}
				}
			}
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
	
	public void addPJ(PersonnageJoueur p, int posh, int posv) {
		this.listePersonnage.add(p);
		this.poshPJ.add(posh);
		this.posvPJ.add(posv);
	}
	
	public void addPNJ(PersonnageNonJoueur p, int posh, int posv) {
		this.listePNJ.add(p);
		this.poshPJ.add(posh);
		this.poshPNJ.add(posv);
	}
	
	public void remplacePJ(PersonnageJoueur p, int posh, int posv, int indice) {
		this.listePersonnage.set(indice, p);
		this.poshPJ.set(indice, posh);
		this.posvPJ.set(indice, posv);
	}
	
	public void remplacePNJ(PersonnageNonJoueur p, int posh, int posv, int indice) {
		this.listePNJ.set(indice, p);
		this.poshPNJ.set(indice, posh);
		this.posvPNJ.set(indice, posv);
	}
	
	public boolean estSurLaMap(PersonnageJoueur p) {
		for (PersonnageJoueur pj:this.listePersonnage) {
			if (pj == p) {
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

		int ind = getIndPJ(p);
		int posh = this.poshPJ.get(ind);
		int posv = this.posvPJ.get(ind);
		
		if (ind >= 0) {
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

	public String toString() {
		String s = "";
		for (int i = 0; i<map[0].length;i++) {
			s += i;
		}
		s+= "\n";
		
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length;j++) {
				s += map[i][j];
				if (j==map[0].length-1) {
					s +=" "+ i +"\n";
				}
			}
		}
		return s;
	}
}
