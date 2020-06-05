
import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

public class MenuCreation extends JFrame{
	String pseudo;
	String finalPseudo;
	int degres = 18;
	
	JButton[] btnPlus = {new JButton("+"),new JButton("+"),new JButton("+")};
	JButton[] btnMoins = {new JButton("-"),new JButton("-"),new JButton("-")};
	
	JButton btnConfirmer;
	boolean confirme = false;
	
	JLabel lblDegre,lblForce,lblAdr,lblRes,lblPseudo,lblPseudoError;
	int force = 0;
	int adresse = 0;
	int resistance = 0;
	
	JTextField txtPseudo;
	
	static final int ZONE_CONFIRMER = 3;
	static final int ZONE_FORCE = 0;
	static final int ZONE_ADRESSE = 1;
	static final int ZONE_RESISTANCE = 2;
	
	static final int CODE_PLUS = 0;
	static final int CODE_MOINS = 1;

	public MenuCreation() {
		super("Création personnage");
		confirme = false;
		this.initDebut();
		this.initListener();
		this.setTitle("Début du jeu");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.centrer(0.3);
		this.setVisible(true);
	}
	
	private void initDebut() {
		JPanel panStat = new JPanel();
		this.add(panStat);
		
		panStat.setLayout(new BorderLayout());
		panStat.add(buildPanelDebut(),BorderLayout.NORTH);
		panStat.add(buildPanelCarac(),BorderLayout.CENTER);
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
	
	public JPanel buildPanelDebut() {
		
		
		JPanel pan = new JPanel();
		
		lblPseudo = new JLabel("Pseudo");
		pan.add(lblPseudo);
		txtPseudo = new JTextField(9);
		pan.add(txtPseudo);
		lblPseudoError = new JLabel();
		pan.add(lblPseudoError);

		
		return pan;
	}
//	public JPanel buildPanelForce() {
//		JPanel force = new JPanel();
//		force.add(new JLabel("Force (0):"));
//
//		force.add(new JButton("+"));
//		force.add(new JButton("-"));
//
//		return force;
//	}
//	
//	public JPanel buildPanelAdresse() {
//		JPanel adresse = new JPanel();
//		adresse.add(new JLabel("Adresse (0):"));
//
//		adresse.add(new JButton("+"));
//		adresse.add(new JButton("-"));
//
//		return adresse;
//	}
//	
//	public JPanel buildPanelResistance() {
//		JPanel resistance = new JPanel();
//		resistance.add(new JLabel("Resistance (0):"));
//
//		resistance.add(new JButton("+"));
//		resistance.add(new JButton("-"));
//
//		return resistance;
//	}
	
	public JPanel buildPanelCarac() {
		JPanel stat = new JPanel();
		stat.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		
		lblDegre = new JLabel("Point(s) disponible(s) : "+degres);
		stat.add(lblDegre);
		
		c.gridx = 0;
		c.gridy= 2;
		lblForce = new JLabel("Force ("+force+"):");
		stat.add(lblForce,c);
		c.gridx = 1;
		c.gridy= 2;
		
		stat.add(btnPlus[0],c);
		c.gridx = 2;
		c.gridy = 2;
		stat.add(btnMoins[0],c);
		
		c.gridx = 0;
		c.gridy= 3;
		lblAdr = new JLabel("Adresse ("+adresse+"):");
		stat.add(lblAdr,c);
		c.gridx = 1;
		c.gridy= 3;
		stat.add(btnPlus[1],c);
		c.gridx = 2;
		c.gridy= 3;
		stat.add(btnMoins[1],c);
		
		c.gridx = 0;
		c.gridy= 4;
		lblRes = new JLabel("Resistance ("+resistance+"):");
		stat.add(lblRes,c);
		c.gridx = 1;
		c.gridy= 4;
		stat.add(btnPlus[2],c);
		c.gridx = 2;
		c.gridy= 4;
		stat.add(btnMoins[2],c);
		
		return stat;
		
	}
//	public JPanel buildPanelStat() {
//		JPanel pan = new JPanel();
//		pan.setLayout(new GridBagLayout());
//		pan.add(new JLabel("Point(s) disponible(s) : "+DEGRES));
//		
//		pan.add(buildPanelCarac());
//		
//		return pan;
//	}
	
	public JPanel buildPanelConfirmer() {
		JPanel confirmer = new JPanel();
		confirmer.add(btnConfirmer = new JButton("Confirmer"));
		
		return confirmer;
	}
	
	public void centrer(double d) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int cote = (int) (d * dim.height);
		this.setBounds((dim.width - cote) / 2, (dim.height - cote) / 2, cote, cote);
	}
	
	public String getPseudo() {
		return txtPseudo.getText();
	}
	
	
	public boolean getConfirmation() {
		return confirme;
	}
	
	public int[] getStats() {
		int[] stat = new int[3];
		stat[0] = force;
		stat[1] = adresse;
		stat[2] = resistance;
		
		return stat;
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
	        			pseudo = getPseudo();
	        			

	        			
	        			if (Pattern.matches("", pseudo) || Pattern.matches("  ", pseudo)) {
	        				System.out.println("Le pseudo n'a pas été renseigné ou le pseudo contient des caractères spéciaux !");
	        				lblPseudoError.setText("Incorrect !");
	        				break;
	        			} else {
		        			int input;
		        			
		        			if (degres!=0)
		        			{
		        				input = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de créer "+pseudo+"? Les degres n'ont pas tous été distribués","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		        			} else {
		        				input = JOptionPane.showConfirmDialog(null,"Êtes-vous sûr de créer "+pseudo+"?","Confirmation",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		        			}
		        			System.out.println(input);
		        			if (input==0) 
		        			{
		        				confirme = true;
		        				dispose();
		        			}
	        			}
	        			
	        		break;
	        		}
	        	case ZONE_FORCE: 
	        		{
	        			switch(code) {
		        			case CODE_PLUS:{
		        				if (degres==0) {
		        					System.out.println("Nombre de degrés indisponible !");
		        				} else {
		        					System.out.println("Ajoute 1 de force");
		        					degres -= 1;
		        					force += 1;
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblForce.setText("Force ("+force+"):");
		        				}
		        				break;
		        			}
		        			case CODE_MOINS:
		        			{	
		        				if (force==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					force -= 1;
		        					degres += 1;
		        					System.out.println("Retire 1 de force");
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblForce.setText("Force ("+force+"):");
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
		        				if (degres==0) {
		        					System.out.println("Nombre de degrés indisponible !");
		        				} else {
		        					System.out.println("Ajoute 1 d'adresse");
		        					degres -= 1;
		        					adresse += 1;
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblAdr.setText("Adresse ("+adresse+"):");
		        				}
		        				break;
		        			}
		        			case CODE_MOINS:
		        			{
		        				if (adresse==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					adresse -= 1;
		        					degres += 1;
		        					System.out.println("Retire 1 d'adresse");
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblAdr.setText("Adresse ("+adresse+"):");
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
		        				if (degres==0) {
		        					System.out.println("Nombre de degrés indisponible !");
		        				} else {
		        					System.out.println("Ajoute 1 de resistance");
		        					degres -= 1;
		        					resistance += 1;
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblRes.setText("Resistance ("+resistance+"):");
		        				}
		        				break;
		        			case CODE_MOINS:
		        			{	
		        				if (resistance==0) {
		        					System.out.println("Impossible de retirer");
		        				} else {
		        					resistance -= 1;
		        					degres += 1;
		        					System.out.println("Retire 1 de resistance");
		        					lblDegre.setText("Point(s) disponible(s) : "+degres);
		        					lblRes.setText("Resistance ("+resistance+"):");
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
