import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Menu extends JFrame {
	PersonnageJoueur personnage;
	JButton btnQuitter, btnSauvegarde, btnCharger,btnNouvelle;
	JButton[] btnInventaire = new JButton[personnage.inventaire.size()];
	JButton[] btnAction;
	
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
		menu.setLayout(new GridBagLayout()); //setLayout pour utiliser BorderLayout
		menu.add(buildMenuChoix(), new GridBagConstraints());
	}
	
	private void initMenu() {
		
		JPanel menu = new JPanel();
		this.add(menu);
				
	}
	
	public JPanel buildMenuChoix() {
		JPanel choix = new JPanel();
		
		choix.setLayout(new GridLayout(3,1,20,100));
		btnNouvelle = new JButton("Nouvelle partie");
		btnNouvelle.setPreferredSize(new Dimension(300,100));
		btnNouvelle.setFont(new Font("Arial", Font.PLAIN, 25));
		btnNouvelle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				// dispose pour quitter la fenêtre
				}
			}
		);
		choix.add(btnNouvelle);
		
		btnCharger = new JButton("Charger partie");
		btnCharger.setFont(new Font("Arial", Font.PLAIN, 25));
		btnCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				// dispose pour quitter la fenêtre
				}
			}
		);
		choix.add(btnCharger);
		
		btnQuitter = new JButton("Quitter");
		btnQuitter.setFont(new Font("Arial", Font.PLAIN, 25));
		btnQuitter.addActionListener(
		new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();				// dispose pour quitter la fenêtre
				}
			}
		);
		choix.add(btnQuitter);
		
		return choix;
		
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
		
		if (p.isEnCombat()==true) {
			btnAction = new JButton[p.nbAction()];
			ArrayList<String>actionPossible = p.actionPossible();
			ArrayList<Integer>indAction = p.indActPossible();
			for (int i = 0; i < actionPossible.size();i++){
				btnAction[i] = new JButton(p.getActions()[indAction.get(i)]);
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
