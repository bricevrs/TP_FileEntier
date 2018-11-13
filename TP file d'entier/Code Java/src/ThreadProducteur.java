import java.util.Random;

import javax.swing.JLabel;

public class ThreadProducteur extends Thread {
	private final File f;
	private final int timeSleep;
	private final JLabel labProd;
	private final JLabel labFile;
	private Random generateur = new Random(java.lang.System.currentTimeMillis()) ;
	private static final int NB_MAX = 100;
	private static final int NB_MIN = 1;
	
	
	public ThreadProducteur(File  f, int timeSleep,JLabel labProd,JLabel labFile, String nom) {
		super(nom);
		this.timeSleep=timeSleep;
		this.f=f;
		this.labFile=labFile;
		this.labProd=labProd;
	}
	
	public void run() {
			try {
				while(!interrupted()) {
				int e = generateur.nextInt(NB_MAX) + NB_MIN;
				labProd.setText(getName()+f.enfiler(e));
				labFile.setText(f.toString());
				labProd.repaint(); labFile.repaint();
				sleep(timeSleep);
				}
			} catch (InterruptedException e) {}
		}
}