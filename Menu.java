import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class Menu extends JFrame {
	PersonnageJoueur personnage;
	Map map;
	JButton btnQuitter, btnSauvegarde, btnNouvelle,btnAttribuer,btnEnvoyer;
	JLabel lblProtec,lblGauche,lblDroite,lblInventaire,lblChat,lblStatistique,lblPseudo, lblStatus;
	
	JLabel lblInit,lblEsq,lblDef,lblDgt,lblAtk;
	
	JTextArea txtMap;
	JTextField txtMessage;
	JButton[] btnInventaire = new JButton[16];
	JButton[] btnAction;
	Object[] options = {"Nouvelle partie", "Charger une partie", "Quitter"};
	static final int ZONE_INVENTAIRE = 1;
	static final int ZONE_ACTIONS = 2;
	static final int ZONE_STAT = 3;
	static final int ZONE_AUTRE = 4;
	
	//Zone Actions
	static final int CODE_ATTAQUER=1;
	static final int CODE_DEPLACER=2;
	static final int CODE_UTILISER=3;
	static final int CODE_RAMASSER=4;
	static final int CODE_DEPOSER=5;
	static final int CODE_FINIR=6;
	
	//Zone Autre
	static final int CODE_CHARGER = 1;
	static final int CODE_SAUVEGARDER = 2;
	
	//Zone Stat
	static final int CODE_ATTRIBUER = 1;
	
	//ZONE INVENTAIRE

	public Menu(PersonnageJoueur p, Map m) {
		super("EHLPTMMMORPGSVR");
		this.personnage = p;
		this.map = m;
		this.setTitle("EHLPTMMMORPGSVR");
//		this.initChoix();
		this.initComp(p,m);
		this.initListener();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.centrer();
		this.setVisible(true);
	}
	
	public void centrer() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int hauteur = (int) (0.8 * dim.height);
		int largeur = (int) (0.6 * dim.width);
		this.setBounds((dim.width - largeur) / 2, (dim.height - hauteur) / 2, (int) (dim.width/1.8), (int) (dim.height/1.2));
	}
	
	private void initComp(PersonnageJoueur p, Map m) {
		JPanel menu = new JPanel();
		this.add(menu);

		menu.add(buildMenu(p,m));
		
//		menu.add(buildActions(p));

	}
	
	public void initListener() {
		this.btnSauvegarde.addActionListener(new Listener(ZONE_AUTRE,CODE_SAUVEGARDER));
		this.btnAttribuer.addActionListener(new Listener(ZONE_STAT,CODE_ATTRIBUER));
	}
	
	public JPanel buildMenu(PersonnageJoueur p, Map m) {
		JPanel menu = new JPanel();
		menu.setLayout(new GridLayout(2,1));
		
		menu.add(buildHaut(p,m));
		menu.add(buildBas(p));
		
		return menu;
		
	}
	
	public JPanel buildHaut(PersonnageJoueur p, Map m) {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(1,2));
		
		pan.add(buildMap(m));
		pan.add(buildDroite(p));
		
		return pan;
		
	}
	
	
	public JPanel buildDroite(PersonnageJoueur p) {
		JPanel inventaire = new JPanel();
		inventaire.setLayout(new GridLayout(3,1));
		inventaire.add(buildStatut(p));
		inventaire.add(buildInventaire(p));
		inventaire.add(buildZoneChat());
		
		return inventaire;
	}
	
	public JPanel buildStatut(PersonnageJoueur p) {
		JPanel pan = new JPanel();
		//on centre au milieu honrizontalement
		
		lblStatus = new JLabel("Statut du joueur");
		lblStatus.setBorder(BorderFactory.createEtchedBorder());
		lblStatus.setAlignmentX(CENTER_ALIGNMENT);
		lblPseudo = new JLabel("Joueur : " + p.getPseudo());
		lblPseudo.setAlignmentX(CENTER_ALIGNMENT);
		lblGauche = new JLabel("Gauche : " + p.gauche.toString());
		lblGauche.setAlignmentX(CENTER_ALIGNMENT);
		lblDroite = new JLabel("Droite : " + p.droite.toString());
		lblDroite.setAlignmentX(CENTER_ALIGNMENT);
		lblProtec = new JLabel("Protection : " + p.protection.toString());
		lblProtec.setAlignmentX(CENTER_ALIGNMENT);
		
		
		//on centre verticalement
		pan.add(Box.createVerticalGlue());
		pan.add(lblStatus);
		pan.add(Box.createRigidArea(new Dimension(0, 25)));
		pan.add(lblPseudo);
		pan.add(Box.createRigidArea(new Dimension(0, 15)));
		pan.add(lblProtec);
		pan.add(Box.createRigidArea(new Dimension(0, 15)));
		pan.add(lblGauche);
		pan.add(Box.createRigidArea(new Dimension(0, 15)));
		pan.add(lblDroite);
		pan.add(Box.createVerticalGlue());
		
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.PAGE_AXIS));
		pan.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
	
	public JPanel buildInventaire (PersonnageJoueur p) {
		JPanel panInventaire = new JPanel();
		lblInventaire = new JLabel("Inventaire (" + p.getInventaire().size() + ")");
		
		panInventaire.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		//première ligne vide du gridlayout
		panInventaire.add(lblInventaire,c);
		
		c.gridy = 1;
		c.gridx = -1;
		for (int i = 0; i < p.getInventaire().size() ; i++) {
			if (i%4==0) {
				c.gridx = 0;
				c.gridy += 1;
			} else {
				c.gridx += 1;
			}
			btnInventaire[i] = new JButton(p.getInventaire().get(i).toString());
			panInventaire.add(btnInventaire[i],c);
		}
		panInventaire.setBorder(BorderFactory.createEtchedBorder());
		return panInventaire;
	}
	
	public JPanel buildChat() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridy = 0;
		lblChat = new JLabel("Chat");
		pan.add(lblChat,c);
		
		c.gridy = 1;
		JTextArea txtChat = new JTextArea(9,45);
		txtChat.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(txtChat);
		
		pan.add(scrollPane,c);
		pan.setBorder(BorderFactory.createEtchedBorder());
		
		return pan;
	}
	
	public JPanel buildMessage() {
		JPanel pan = new JPanel();

		
		txtMessage = new JTextField(38);
		pan.add(txtMessage);
		
		btnEnvoyer = new JButton("Envoyer");
		pan.add(btnEnvoyer);
		
		return pan;
	}
	
	public JPanel buildZoneChat() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy = 0;
		pan.add(buildChat(),c);
		c.gridy = 1;
		pan.add(buildMessage(),c);
		
		pan.setBorder(BorderFactory.createEtchedBorder());
		return pan;
	}
	
	public JPanel buildMap(Map m) {
		JPanel map = new JPanel();
//		for (int i = 0; i < m.getMapCase().length; i++) {
//			lblMap = new JLabel(m.laLigne(i));
//			map.add(lblMap);
//		}
		txtMap = new JTextArea(m.toString());
		Font font = new Font("Consolas", Font.PLAIN, 12);
        txtMap.setFont(font);
        txtMap.setEditable(false);
		map.add(txtMap);
		map.setBorder(BorderFactory.createEtchedBorder());
		
		return map;
	}
	
	public JPanel buildBas(PersonnageJoueur p) {
		JPanel pan = new JPanel();

		pan.setLayout(new FlowLayout());
		pan.add(buildActionsAutre(p));
		pan.add(buildStatistique(p));
		
		return pan;
	}
	
	public JPanel buildStatistique(PersonnageJoueur p) {
		JPanel stat = new JPanel();
		
		GridBagLayout gbl = new GridBagLayout();
		
		stat.setLayout(gbl);
		GridBagConstraints c = new GridBagConstraints();
		
		//pour l'espacement entre les bag
		c.insets = new Insets(10,10,10,10);

		c.gridx = 2;
		c.gridy = 0;
		lblStatistique = new JLabel("Statistique");
		stat.add(lblStatistique,c);
		
		c.gridx = 0;
		c.gridy = 1;
		lblInit = new JLabel("Initiative " + p.getInit());
		stat.add(lblInit,c);
		
		c.gridx = 2;
		c.gridy = 1;
		lblDef = new JLabel("Defense " + p.getDef());
		stat.add(lblDef,c);
		
		c.gridx = 4;
		c.gridy = 1;
		lblAtk = new JLabel("Attaque " + p.getAtk());
		stat.add(lblAtk,c);
		
		c.gridx = 1;
		c.gridy = 2;
		lblEsq = new JLabel("Esquive " + p.getEsq());
		stat.add(lblEsq,c);
		
		c.gridx = 3;
		c.gridy = 2;
		lblDgt = new JLabel("Degat " + p.getDgt());
		stat.add(lblDgt,c);
		
		c.gridx = 2;
		c.gridy = 3;
		btnAttribuer = new JButton("Attribuer");
		stat.add(btnAttribuer,c);

		
		stat.setBorder(BorderFactory.createEtchedBorder());
		
		return stat;
	}
	
	public JPanel buildActions(PersonnageJoueur p) {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,5));
		
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
		
		pan.setBorder(BorderFactory.createEtchedBorder());
		
		return pan;
	}
	
	public JPanel buildAutre() {
		JPanel pan = new JPanel();
		btnSauvegarde = new JButton("Sauvegarder");
		pan.add(btnSauvegarde);
		
		btnQuitter = new JButton("Quitter");
		pan.add(btnQuitter);
		pan.setBorder(BorderFactory.createEtchedBorder());
		
		return pan;
	}
	
	public JPanel buildActionsAutre(PersonnageJoueur p) {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(2,1));
		
		pan.add(buildActions(p));
		pan.add(buildAutre());
		pan.setBorder(BorderFactory.createEtchedBorder());
		
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
	        			switch(code) {
	        			case CODE_ATTRIBUER:
		        			{
		        				Statistique s = new Statistique(personnage);
		        				while (s.confirme) {
		        					System.out.println("");
		        				}
		        				personnage.setForce(s.force);
		        				personnage.setAdresse(s.adresse);
		        				personnage.setResistance(s.resistance);
		        				personnage.atk = s.atk;
		        				personnage.esq = s.esq;
		        				personnage.def = s.def;
		        				personnage.dgt = s.dgt;
		        				personnage.init = s.init;
		        				System.out.println("Statistique(s) modifiée(s)");
		        			}
	        			}
	        		}
	        		
	        	case ZONE_AUTRE:
	        		{
	        			switch(code) {
	        			case CODE_SAUVEGARDER:
		        			{
		        			
		        			}
	        			}
	        		break;
	        		}
        	}
        }
	}
	
}