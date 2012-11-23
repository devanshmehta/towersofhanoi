public class TowersOfHanoi{

	//start pole and end pole are one based
	public void start(int numDisks, int startPole, int endPole){
		startPole -= 1;
		endPole -= 1;
		createPoles(numDisks);
		createDisksOnPole(numDisks, startPole);
	}

	private void createPoles(int numDisks){
		poles = new Pole[3];
		for(int i = 0; i < poles.length; ++i){
			poles[i] = new Pole(numDisks);
		}
	}
	
	private void createDisksOnPole(int numDisks, int startPole){
		for(int i = numDisks; i > 0; --i){
			poles[startPole].push(new Disk(i));
		}
	}
	
	//move 1 - numDiks from fromPole to toPole
	public void moveDisks(int numDisks, int fromPole, int toPole){
		moveDisks(numDisk - 1, fromPole, untouchedPole(fromPole, toPole));
	}
	
	public int untouchedPole(int firstPole, int anotherPole){
	
	public static void main(String[] args){
		TowersOfHanoi t = new TowersOfHanoi();
		t.start(1, 1, 3);
	}
	
	private Pole[] poles;
}