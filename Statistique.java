import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.*;

public class Statistique extends JFrame{
	PersonnageJoueur p;
	JButton btnConfirmer;
	JLabel lblInit,lblAtk,lblEsq,lblDef,lblDgt,lblFor,lblAdr,lblRes,lblXp;
	int force,adresse,resistance,xp,init,atk,def,dgt,esq;
	JButton[] btnPlus = {new JButton("+"),new JButton("+"),new JButton("+")};
	JButton[] btnMoins = {new JButton("-"),new JButton("-"),new JButton("-")};
	int IND_CAPACITES = 0;
	int IND_CARAC = 4;
	boolean confirme = false;
	
	static final int ZONE_CONFIRMER = 3;
	static final int ZONE_FORCE = 0;
	static final int ZONE_ADRESSE = 1;
	static final int ZONE_RESISTANCE = 2;
	
	static final int CODE_PLUS = 0;
	static final int CODE_MOINS = 1;

	public Statistique(PersonnageJoueur p) {
		super("Vos statistiques");
		this.setTitle("Vos statistiques");
		force = p.getForce();
		adresse = p.getAdresse();
		resistance = p.getResistance();
		xp = p.getExp();
		this.initStat(p);
		this.initListener();
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.centrer(0.5);
		this.setVisible(true);
	}
	
	private void initStat(PersonnageJoueur p) {
		JPanel panStat = new JPanel();
		this.add(panStat);
		
		panStat.setLayout(new BorderLayout());
		panStat.setBorder(new EmptyBorder(5,5,5,5));
		panStat.add(buildPanelJoueur(p.getPseudo()),BorderLayout.NORTH);
		panStat.add(buildPanelCara(p.init, p.atk, p.esq,p.def,p.dgt),BorderLayout.WEST);
		panStat.add(buildPanelCapacite(p.getForce(), p.getAdresse(), p.getResistance(), p.getExp()),BorderLayout.EAST);
		panStat.add(buildPanelConfirmer(),BorderLayout.SOUTH);
		
	}
	
	public void initListener() {
		
		this.btnConfirmer.addActionListener(new Listener(ZONE_CONFIRMER,0));
		for (int i = 0;i<3;i++) {
			this.btnPlus[i].addActionListener(new Listener(i,CODE_PLUS));
		}
		for (int i = 0;i<3;i++) {
			this.btnMoins[i].addActionListener(new Listener(i,CODE_MOINS));
		}
	}
	
	public JPanel buildPanelJoueur(String pseudo) {
		JPanel pan = new JPanel();
		JLabel lblPseudo = new JLabel("Joueur : "+pseudo);
		pan.add(lblPseudo);
		
		return pan;
	}
	
	public JPanel buildPanelCara(int init, int atk, int esq, int def, int dgt) {
		JPanel pan = new JPanel();
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		
		lblInit = new JLabel("Initiative"+"("+init+")");
		lblAtk = new JLabel("Attaque"+"("+atk+")");
		lblEsq = new JLabel("Esquive"+"("+esq+")");
		lblDef = new JLabel("Defense"+"("+def+")");
		lblDgt = new JLabel("Degat"+"("+dgt+")");
		c.gridy = 0;
		pan.add(lblInit,c);
		c.gridy = 1;
		pan.add(lblAtk,c);
		c.gridy = 2;
		pan.add(lblEsq,c);
		c.gridy = 3;
		pan.add(lblDef,c);
		c.gridy = 4;
		pan.add(lblDgt,c);

		return pan;
	}
	
	public JPanel buildPanelCapacite(int force, int adr, int res, int xp) {
		JPanel pan = new JPanel();
		lblXp = new JLabel("EXP disponible(s) : "+xp);
		pan.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		c.gridx = 0;
		c.gridy = 0;
		pan.add(lblXp,c);
		lblFor = new JLabel("Force"+"("+force+")");
		lblAdr = new JLabel("Adresse"+"("+adr+")");
		lblRes = new JLabel("Resistance"+"("+res+")");
		
		c.gridx = 0;
		c.gridy = 1;
		JLabel lblVide = new JLabel(" ");
		pan.add(lblVide);
		
		if (xp!=0) {

			c.gridx = 0;
			c.gridy = 2;
			pan.add(lblFor,c);
			c.gridx = 1;
			c.gridy = 2;
			pan.add(btnPlus[0],c);
			c.gridx = 2;
			c.gridy = 2;
			pan.add(btnMoins[0],c);
			
			c.gridx = 0;
			c.gridy = 3;
			pan.add(lblAdr,c);
			c.gridx = 1;
			c.gridy = 3;
			pan.add(btnPlus[1],c);
			c.gridx = 2;
			c.gridy = 3;
			pan.add(btnMoins[1],c);
			
			c.gridx = 0;
			c.gridy = 4;
			pan.add(lblRes,c);
			c.gridx = 1;
			c.gridy = 4;
			pan.add(btnPlus[2],c);
			c.gridx = 2;
			c.gridy = 4;
			pan.add(btnMoins[2],c);
			
		} else {

			c.gridx = 0;
			c.gridy = 1;
			pan.add(lblFor,c);
			c.gridx = 0;
			c.gridy = 2;
			pan.add(lblAdr,c);
			c.gridx = 0;
			c.gridy = 3;
			pan.add(lblRes,c);
		}
		
		return pan;
	}
	
	public JPanel buildPanelConfirmer() {
		JPanel pan = new JPanel();
		
		btnConfirmer = new JButton("Confirmer");
		pan.add(btnConfirmer);

		return pan;
	}
	
	public void centrer(double d) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int cote = (int) (d * dim.height);
		this.setBounds((int)(dim.width - (cote)) / 2, (dim.height - cote) / 2, cote, cote);
	}
	
	public boolean getConfirmation() {
		return confirme;
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
	        	case ZONE_CONFIRMER:
	        		{
	        			System.out.println("Je confirme");
	        			int input;
		        		if (xp!=0)
		        		{
		        			input = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de ses capacités? L'xp n'ont pas tous été distribués","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		        		} else {
		        			input = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de ses capacités ?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		        		}
		        		if (input==0) 
		        		{	
		        			confirme = true;
		        			dispose();
		        		}
		        		break;
	        		}

	        	case ZONE_FORCE: 
	        		{
	        			switch(code) {
		        			case CODE_PLUS:{
		        				if ((xp-2*force)<0) {
		        					System.out.println("EXP indisponible !");
		        				} else {
		        					System.out.println("Ajoute 1 de force");
		        					force += 1;
		        					xp -= 2*force;
		        					lblXp.setText("EXP disponible(s) : "+xp);
		        					lblFor.setText("Force("+force+")");
		        				}
		        				break;
		        			}
		        			case CODE_MOINS:
		        			{	
		        				if (force==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					xp+=2*force;
		        					force -= 1;
		        					System.out.println("Retire 1 de force");
		        					lblXp.setText("EXP disponible(s) : "+ xp);
		        					lblFor.setText("Force("+force+")");
		        				}
		        				break;
		        			}
		        			default:
		        				break;
	        			}
	        			break;
	        		
	        		}
	        	case ZONE_ADRESSE:
	        		{
	        			switch(code) {
		        			case CODE_PLUS:
		        			{
		        				if ((xp-2*adresse)<0) {
		        					System.out.println("EXP indisponible !");
		        				} else {
		        					System.out.println("Ajoute 1 d'adresse");
		        					xp-=2*adresse;
		        					adresse += 1;
		        					lblXp.setText("EXP disponible(s) : "+xp);
		        					lblAdr.setText("Adresse("+adresse+")");
		        				}
		        				break;
		        			}
		        			case CODE_MOINS:
		        			{
		        				if (adresse==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					adresse -= 1;
		        					xp+=2*adresse;
		        					lblXp.setText("EXP disponible(s) : "+xp);
		        					lblAdr.setText("Adresse("+adresse+")");
		        				}
		        				break;
		        			}
		        			default:
		        				break;
	        			}
	        			break;
	        		}
	        		
	        	case ZONE_RESISTANCE:
	        		{
	        			switch(code) {
		        			case CODE_PLUS:
		        				if ((xp-2*resistance)<0) {
		        					System.out.println("EXP indisponible !");
		        				} else {
		        					resistance += 1;
		        					xp-=2*resistance;
		        					lblXp.setText("EXP disponible(s) : "+xp);
		        					lblRes.setText("Resistance("+resistance+")");
		        				}
		        				break;
		        			case CODE_MOINS:
		        			{	
		        				if (resistance==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					xp+=2*resistance;
		        					resistance -= 1;
		        					lblXp.setText("EXP disponible(s) : "+xp);
		        					lblRes.setText("Resistance("+resistance+")");
		        				}
		        				break;
		        			}
		        			default:
		        				break;
	        			}
	        			break;
	        		}
	        	default:
	        		break;
        	}
        }
	}
	
}
