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
	if(args.length < 3){
	    System.out.println("java TowerOfHanoi startDisk endDisk numDisk");
	    return;
	}
	int startDisk = Integer.parseInt(args[0]);
	int endDisk = Integer.parseInt(args[1]);
	int numDisk = Integer.parseInt(args[2]);
	if(erroneousInput(startDisk, endDisk)){
	    System.out.println("Input is incorrect");
	    return;
	}
	
	for(int diskOnPole : t.start(numDisk, startDisk, endDisk)){
	    System.out.println(diskOnPole);
	}
    }

    private static boolean erroneousInput(int startDisk, int endDisk){
	return ((startDisk == endDisk) || (startDisk > 3) ||
		(endDisk > 3) || (startDisk < 1) || (endDisk < 1));
    }
	
    private Pole[] poles;
}