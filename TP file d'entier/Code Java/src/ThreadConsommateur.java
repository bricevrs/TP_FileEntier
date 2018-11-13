import javax.swing.JLabel;

public class ThreadConsommateur extends Thread {
	private final File f;
	private final JLabel labFile;
	private final JLabel labConso;
	private final int timeSleep;
	
	public ThreadConsommateur(File  f, int timeSleep, JLabel labConso, JLabel labFile, String nom) {
		super(nom);
		this.timeSleep=timeSleep;
		this.f=f;
		this.labFile=labFile;
		this.labConso=labConso;
	}
	
	public void run() {
			try {
				while(!interrupted()) {
				labConso.setText(getName()+f.defiler());
				labFile.setText(f.toString());
				labConso.repaint(); labFile.repaint();
				sleep(timeSleep);
				}
			} catch (InterruptedException e) {}
		}
}
