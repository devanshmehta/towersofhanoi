public class TowersOfHanoi{

    //start pole and end pole are one based
    //returns number of disks on each pole
    public int[] start(int numDisks, int startPole, int endPole){
	startPole -= 1;
	endPole -= 1;
	createPoles(numDisks);
	createDisksOnPole(numDisks, startPole);
	moveDisks(1, numDisks, startPole, endPole);
	int[] numDiskOnPoles = new int[3];
	for(int i = 0; i < poles.length; ++i){
	    numDiskOnPoles[i] = poles[i].size();
	}
	return numDiskOnPoles;
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

    //move the disk between start num and end num from fromPole to toPole
    //if startNum == endNum it means move that single disk
    public void moveDisks(int startNum, int endNum, int fromPole, int toPole){
	if(startNum > endNum){
	    throw new IllegalArgumentException("Start num(" + startNum + 
					       ") > End num(" + endNum + ")");
	}
	if(startNum == endNum){
	    poles[toPole].push(poles[fromPole].pop());
	    return;
	}
	int remainingPole = remainingPole(fromPole ,toPole);
	moveDisks(startNum, endNum - 1, fromPole, remainingPole);
	moveDisks(endNum, endNum, fromPole, toPole);
	moveDisks(startNum, endNum - 1, remainingPole, toPole);
    }
	
    private int remainingPole(int firstPole, int anotherPole){
	int totalSum = 0 + 1 + 2;
	return totalSum - firstPole - anotherPole;
    }
	
    public static void main(String[] args){
	TowersOfHanoi t = new TowersOfHanoi();
	for(int numDisk : t.start(1, 1, 3)){
	    System.out.println(numDisk);
	}
    }
	
    private Pole[] poles;
}