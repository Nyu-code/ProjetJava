public class TestMMO {

	public static void main(String[] args) {
//		MenuCreation m = new MenuCreation();
//		while (!m.getConfirmation()) {
//			System.out.println("");;
//		}
//		int[] stat = m.getStats();
//		String pseudo = m.getPseudo();
//		PersonnageJoueur p1 = new PersonnageJoueur(pseudo,stat[0],stat[1],stat[2],5,3,1,23,5,30,m.degres);
//		System.out.println(p1.affichePA());
//		System.out.println(p1.afficheBlessure());
//		p1.getChoixJoueur();
//		int[] tirage = p1.tirageAlea();
//		System.out.println(tirage[0]);
//		System.out.println(tirage[1]);
//		System.out.println(tirage[2]);
//		for (int i=0;i<19;i++) {
//			if (i%2==0)
//				p1.addInventaire(new Poison());
//			else 
//				p1.addInventaire(new Explosive());
//		}
//	    p1.afficheInventaire();
//	    p1.affichePotion();
//		Potion pot = new Soin();
//		System.out.println(pot);
		
		
		PersonnageJoueur p3 = new PersonnageJoueur("Test Potion Soin",2,6,9,10,163,479,4,2,3,5); //stats random inutile pour le test suivant
//		p3.setHp(40);
//		System.out.println(p3);
//		System.out.println(p3.getHp());
//		p3.addInventaire(new Soin());
//		System.out.println(p3.inventaire.get(0));
//		p3.potion((Soin)p3.inventaire.get(0));
//		System.out.println(p3);
//		System.out.println(p3.getHp());
			
//		
//		PersonnageJoueur p4 = new PersonnageJoueur("Test de blessure",3,2,6,9,10,163,479,4,2,5); //stats random inutile pour le test suivant
//		System.out.println(p4);
//		p4.setHp(60);
//		p4.setBlessure();
//		System.out.println(p4);
//		System.out.println("---------------");
//		p4.setHp(100);
//		p4.setBlessure();
//		System.out.println(p4);
		
		
//		MenuCreation m2 = new MenuCreation();
//		while (!m2.getConfirmation()) {
//			System.out.println("");;
//		}
//		int[] stat2 = m2.getStats();
//		String pseudo2 = m2.getPseudo();
//		
//		PersonnageJoueur p2 = new PersonnageJoueur(pseudo2,stat2[0],stat2[1],stat2[2],5,3,1,23,5,30,m.degres);
//		System.out.println(p2);
//		
//		String stats = p2.afficheStats();
//		System.out.println(stats);
//		
//		new Statistique(p2);
		
		
		Item bâton = new Baton();
		
		p3.addInventaire(bâton);
		p3.afficheInventaire();
		
		}
}