import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Combat extends JFrame{
	PersonnageJoueur p;
	PersonnageNonJoueur pnj;
	Map map;
	String [] Boutons = {"Nouvelle Partie", "Charger une partie", "Quitter le jeu"};
	
	public Combat(PersonnageJoueur p1, PersonnageNonJoueur pnj) {
		//Tant que le joueur est en combat la boucle tourne
		verifTour(p1, pnj);
		while (p1.enCombat) {
		if (p1.isTon_tour()) {
			p1.affichePA();
			p1.faireAction();
			if (verifMort(p1)) {
				p1.setEnCombat();
			}
			p1.setTon_tour(false);
		}
		else {
			pnj.attaque_joueur(p1);
			p1.setTon_tour(true);
		}
	}
}
	public void verifTour(PersonnageJoueur p1, PersonnageNonJoueur pnj) {
		if (p1.getInit() > pnj.getInit()) { //Si l'initiative du joueur > init du pnj alrs p1 joue avant pnj
			p1.setTon_tour(true);
		}
		else {
			pnj.setTon_tour(true);
		}
	}
	public boolean verifMort(Personnage p1) {
		if (p1.getBlessure() == "inconscient") { //le combat s'arrête si le joueur est mort
			   ImageIcon icon = new ImageIcon("mort.png");
			int choix = JOptionPane.showOptionDialog(this, "Vous êtes mort au combat",
					   "état d'urgence", JOptionPane.DEFAULT_OPTION,
					   JOptionPane.WARNING_MESSAGE, icon , Boutons ,Boutons[0]);
			p1.setEnCombat();
			if (choix == 0) {
				// redemarre le jeu avec une nouvelle partie
			}
			else if (choix == 1) {
				JFileChooser select = new JFileChooser(""); // à déterminer l'endroit où sera les parties sauvegarder
				int res = select.showDialog(this, "Charger");
				if (res == JFileChooser.APPROVE_OPTION) {
					File f	= select.getSelectedFile();
					System.out.println("Nom du fichier selectionné : " + f.getName());
				}
				else {
					System.out.println("Chargement de partie annulé");
				}
			}
			else {
				
			}
			return true;
		}
		else {
			return false;
		}
	}
}