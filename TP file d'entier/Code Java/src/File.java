import java.util.ArrayList;

public class File {
	private ArrayList<Integer> tabFile;
	private final static int MAX = 20;
	
	public File() {
		tabFile = new ArrayList<Integer>();
	}
	
	public synchronized String toString() {
		String chaineFile = "|File| = "+tabFile.size()+" :   <";
		for(int i = 0; i<tabFile.size(); i++) {
			if(i==tabFile.size()-1) { chaineFile+=Integer.toString(tabFile.get(i)); }
			else { chaineFile+=Integer.toString(tabFile.get(i))+", ";  }
		}
		chaineFile+="<";
		return chaineFile;
	}
	
	public synchronized String enfiler(int e) throws InterruptedException {
		while(tabFile.size()==MAX) wait();
		tabFile.add(e);
		notifyAll();
		return (" produit "+e+"\n");
	}
	
	public synchronized String defiler() throws InterruptedException {
		while(tabFile.size()==0) wait();
		int e = tabFile.get(0);
		tabFile.remove(0);
		notifyAll();
		return (" consomme "+e);
	}
	
	public synchronized void clear() throws InterruptedException {
		tabFile.clear();
	}

}
