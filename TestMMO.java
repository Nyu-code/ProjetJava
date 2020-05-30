
public class TestMMO {

	public static void main(String[] args) {
//		PersonnageJoueur p1 = new PersonnageJoueur(0,3,2,6,9,10,163,479,4,2);
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
//				p1.addInventaire(new Explosion());
//		}
//	    p1.afficheInventaire();
//	    p1.affichePotion();
//		Potion pot = new Soin();
//		System.out.println(pot.getIndicePot(pot.type));
//		System.out.println(pot.getDgtPot(pot.type));
//		System.out.println(pot.getDureePot(pot.type));
//		
//		PersonnageJoueur p2 = new PersonnageJoueur(3,2,6,9,10,163,479,4,2);
//		p2.setHp(40);
//		System.out.println(p2);
//		System.out.println(p2.getHp());
//		p2.addInventaire(new Soin());
//		System.out.println(p2.inventaire.get(0));
//		p2.potion((Soin)p2.inventaire.get(0));
//		System.out.println(p2);
//		System.out.println(p2.getHp());
//			
		
//		PersonnageJoueur p1 = new PersonnageJoueur(3,2,6,9,10,163,479,4,2);
//		System.out.println(p1);
//		p1.setHp(60);
//		p1.setBlessure();
//		System.out.println(p1);
//		System.out.println("---------------");
//		p1.setHp(100);
//		p1.setBlessure();
//		System.out.println(p1);
		
		MenuCreation m = new MenuCreation();
		while (!m.getConfirmation()) {
			System.out.println("");;
		}
		int[] stat = m.getStats();
		String pseudo = m.getPseudo();
		
		PersonnageJoueur p1 = new PersonnageJoueur(pseudo,stat[0],stat[1],stat[2],5,3,1,23,5,30,m.degres);
		System.out.println(p1);
		
		String stats = p1.afficheStats();
		System.out.println(stats);
		
		new Statistique(p1);
		
		}
}