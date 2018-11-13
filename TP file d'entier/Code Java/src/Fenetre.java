import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Fenetre extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private static final int LARGEUR = 1000;
	private static final int LONGUEUR = 600;
	//[Attributs de la classe
	
	//TimeSleep Threads 
	private final int timeProd;
	private final int[] timeConsos;
	
	//Threads
	private ThreadProducteur prod;
	private ThreadConsommateur[] consos;
	
	//File
	private final File f;
	
	//JLabels
	private final JLabel labFile;
	private final JLabel labProd;
	private final JLabel labConso;
	
	//]
	
	public Fenetre(int timeProd, int[] timeConsos) {
		
		//Initialisation fenêtre
		
		this.setTitle("Operation sur File d'entier");
		this.setSize(LARGEUR,LONGUEUR);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Creation des JLabels/JButtons
		
		this.labFile = new JLabel();
		labFile.setText("< <");
		labFile.setForeground(Color.BLACK);
		labFile.setFont(new Font("Courrier New", Font.PLAIN, 20));
		
		this.labProd = new JLabel();
		labProd.setForeground(Color.BLUE);
		labProd.setFont(new Font("Courrier New", Font.PLAIN, 20));
		
		this.labConso = new JLabel();
		labConso.setForeground(Color.RED);
		labConso.setFont(new Font("Courrier New", Font.PLAIN, 20));
		
		JButton stopAndStart = new JButton("Start");
		stopAndStart.setFont(new Font("Courrier New", Font.PLAIN, 20));
		stopAndStart.addActionListener(this);
		
		
		//Organisation des JLabel
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(4,1));
		contentPane.add(labFile);
		contentPane.add(labProd);
		contentPane.add(labConso);
		contentPane.add(stopAndStart);
		
		//Initialisation Times
		
		this.timeProd = timeProd;
		this.timeConsos=timeConsos;
		
		//Creation de la file
		
		this.f = new File();
		
		
		this.setVisible(true);
		
	}
	
	//___________Creation Thread________________
	public void createThread() {
		prod = new ThreadProducteur(f, timeProd, labProd, labFile, "Producteur");
		consos = new ThreadConsommateur[timeConsos.length];
		for(int i = 0; i<timeConsos.length; i++) {
			consos[i] = new ThreadConsommateur(f,timeConsos[i], labConso, labFile, "Consommateur"+(i+1));
		}
	}
	
	//____________Start Threads_________________
	public void startThread() {
		createThread();
		prod.start();
		for(int i = 0; i<consos.length;i++) { consos[i].start(); }
	}
	
	
	//____________Interrupt Threads______________
	public void stopThread(){
		prod.interrupt();
		for(int i = 0; i<consos.length;i++) { consos[i].interrupt(); }
		labProd.setText("");
		labConso.setText("");
		repaint();
	}
	
	//__________Traitement bouton______________
	public void actionPerformed(ActionEvent e) {
		JButton jb_sAS = (JButton) e.getSource();
		String titre = jb_sAS.getText();
		
		if(titre == "Start") { startThread();  jb_sAS.setText("Stop");}
		   
		if(titre == "Stop") { stopThread();  jb_sAS.setText("Start"); }	   
	}
	//__________Méthode principale__________
	public static void main(String[] args) {
		
		int timeProd = 250;
		int[] timeConsos = {1000,2000};
		
		new Fenetre(timeProd, timeConsos);

	}

	
}
