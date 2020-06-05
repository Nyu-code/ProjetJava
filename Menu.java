import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Menu extends JFrame {
	PersonnageJoueur personnage;
	JButton btnQuitter, btnSauvegarde, btnCharger,btnNouvelle;
	JButton[] btnInventaire = new JButton[16];
	JButton[] btnAction;
	Object[] options = {"Nouvelle partie", "Charger une partie", "Quitter"};
	
	static final int ZONE_INVENTAIRE = 1;
	static final int ZONE_ACTIONS = 2;
	static final int ZONE_STAT = 3;
	static final int ZONE_AUTRE = 4;
	
	static final int CODE_ATTAQUER=1;
	static final int CODE_DEPLACER=2;
	static final int CODE_UTILISER=3;
	static final int CODE_RAMASSER=4;
	static final int CODE_DEPOSER=5;
	static final int CODE_FINIR=6;

	public Menu(PersonnageJoueur p) {
		super("EHLPTMMMORPGSVR");
		this.setTitle("EHLPTMMMORPGSVR");
		this.initChoix();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.centrer(0.9);
		this.setVisible(true);
	}
	
	public void centrer(double d) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int cote = (int) (d * dim.height);
		this.setBounds((dim.width - cote) / 2, (dim.height - cote) / 2, cote, cote);
	}
	
	private void initChoix() {
		JPanel menu = new JPanel();
		this.add(menu);
		
		int input = JOptionPane.showOptionDialog(null,"Votre choix","Choix de la personne",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
		if (input == 0) {
			System.out.println("Création d'une nouvelle partie");
			menu.add(buildPanelJeu());
		}
		
		else if(input == 1) {
			System.out.println("Chargement d'une nouvelle partie");
		}
		
		else if (input == 2) {
			System.out.println("Vous quittez le jeu");
			
		}

	}
	public JPanel buildPanelJeu(PersonnageJoueur p) {
		JPanel jeu = new JPanel();
		return jeu;
	}
	
	public JPanel buildPanelJeu() {
		
		JPanel jeu = new JPanel();
		MenuCreation creation = new MenuCreation();
		int[] stat = creation.getStats();
		while (!creation.getConfirmation()) {
			System.out.println("");
		}
		personnage = new PersonnageJoueur(creation.getPseudo(),stat[0],stat[1],stat[2],0,0,0,0,0,0,creation.degres);
		return jeu;
				
	}
	

	
	public JPanel buildInventaire(PersonnageJoueur p) {
		JPanel pan = new JPanel();
		for (int i = 0; i < 16 ; i++) {
			btnInventaire[i] = new JButton(p.getInventaire().get(i).toString());
			pan.add(btnInventaire[i]);
		}
		return pan;
	}
	
	public JPanel buildHistorique() {
		JPanel pan = new JPanel();
		JTextArea txtHistorique = new JTextArea(300,200);
		pan.add(txtHistorique);
		
		return pan;
	}
	
	public JPanel buildActions(PersonnageJoueur p) {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,4));
		
		if (p.isEnCombat()==true) {
			btnAction = new JButton[p.nbAction()];
			ArrayList<String>actionPossible = p.actionPossible();
			ArrayList<Integer>indAction = p.indActPossible();
			for (int i = 0; i < actionPossible.size();i++){
				btnAction[i] = new JButton((i+1) + " - " + p.getActions()[indAction.get(i)]);
				pan.add(btnAction[i]);
			}
		} else {
			btnAction = new JButton[5];
			for (int i = 0;i<p.getActions().length-1;i++) { // -1 car pas besoin de finir le tour quand on est pas en combat
				btnAction[i] = new JButton((i+1) + " - " + p.getActions()[i]); //tableau car on sait la taille fixe des actions possible
				pan.add(btnAction[i]);
			}
		}
		
		return pan;
	}
	public void Sauvegarder() {
		//Demande de confirmation de sauvegarde pour éviter toute abusation et tout fail
		int choix = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir sauvegarder ?", 
				"Demande de confirmation pour sauvegarder", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (choix == 1) {
			return;
		}
		//On crée la date à laquelle il sauvegarde le fichier et on l'implente
		//dans le nom du fichier pour pouvoir se repérer lors des chargements de partie
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		System.out.println(format.format(date));
		
		//On sauvegarde le personnage et la map en les serialisant
		
		try (FileOutputStream fos = new FileOutputStream(personnage.getPseudo()+"_"+format.format(date)+".ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos)){
			oos.writeObject(personnage);
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	class Listener implements ActionListener {
		private int zone;
        private int code;

        public Listener(int z, int c) {
        	this.zone=z;
        	this.code=c;
        }
        
        public void actionPerformed(ActionEvent e) {
        	int code = this.code;
        	int zone = this.zone;
        	
        	switch(zone) {
	        	case ZONE_INVENTAIRE:
	        		{
	        		break;
	        		}
	        	case ZONE_ACTIONS: 
	        		{
	        		break;
	        		}
	        	case ZONE_STAT:
	        		{
	        			
	        		}
	        		
	        	case ZONE_AUTRE:
	        		{
	        		break;
	        		}
        	}
        }
	}
	
}