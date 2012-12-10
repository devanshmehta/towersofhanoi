/**
 * Listener which listens to the move in the towers of hanoi game.
 *
 * @author Devansh Mehta
 */
public interface MoveListener{

    /**
     * Moves the disk with the given disk size from start pole to 
     * end pole
     *
     * @param disksize size of the disk
     * @param startPole from which the disk will move.
     * @param endPole to which the disk will move.
     */
    public void move(int disksize, int startPole, int endPole);

}