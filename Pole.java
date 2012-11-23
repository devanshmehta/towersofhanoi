import java.util.Stack;

public class Pole{
	
    //max num of disk on the pole
    public Pole(int numDisks){
	disks = new Stack<Disk>();
	this.numDisks = numDisks;
	currentNum = 0;
    }

    public int size(){
	return currentNum;
    }
	
    public Disk pop(){
	if(disks.empty()){
	    return null;
	}
	--currentNum;
	return disks.pop();
    }
	
    //true if the push was successfull and false otherwise
    public boolean push(Disk disk){
	if(currentNum > numDisks){
	    return false;
	}
	++currentNum;
	disks.push(disk);
	return true;
    }

    private int currentNum;
    private int numDisks;
    private Stack<Disk> disks; 
}