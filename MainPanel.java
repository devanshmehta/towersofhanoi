import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.Timer;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.*;
import java.util.Stack;

public class MainPanel extends JPanel implements MoveListener{
    
    public MainPanel(int timerGaps){
	setLayout(new FlowLayout(FlowLayout.LEFT, 0 ,0));
	this.timerGaps = timerGaps;
    }

    public void move(final int disksize, final int startPole, final int endPole){
	previousTimer += timerGaps;
	Timer timer = new Timer(previousTimer, new ActionListener(){
		public void actionPerformed(ActionEvent e){
		    Peg peg = (Peg)poles[startPole].remove();
		    if(peg.size != disksize){
			throw new IllegalArgumentException("FCK");
		    }
		    poles[endPole].add(peg);
		    revalidate();
		    repaint();
	
		}
	    });
	timer.setRepeats(false);
	timer.start();
    }

    public void initPoles(int numPoles){
	poles = new Pole[numPoles];
	for(int i = 0; i < numPoles; ++i){
	    poles[i] = new Pole();
	    add(poles[i]);
	}
    }

    public void addPeg(int pole, int pegsize){
	poles[pole].add(new Peg(pegsize));
    }

    private int timerGaps;
    private Pole[] poles;
    private int previousTimer = 0;

    private static class Pole extends JPanel{
	
	public Pole(){
	    buttons = new Stack<Component>();
	    setLayout(new StackLayout());
	    setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}
	
	@Override
	    public Component add(Component comp){
	    buttons.push(comp);
	    return super.add(comp);
	}

	public Component remove(){
	    Component comp = buttons.pop();
	    remove(comp);
	    return comp;
	}

	private Stack<Component> buttons;
	private static int WIDTH = 200;
	private static int HEIGHT = 500;
    }

    private static class Peg extends JLabel{

	public Peg(int size){
	    super(String.valueOf(size), SwingConstants.CENTER);
	    this.size = size;
	    setOpaque(true);
	    setBackground(Color.BLUE);
	    setForeground(Color.WHITE);
	    setBorder(BorderFactory.createRaisedBevelBorder());
	    setMaximumSize(new Dimension(size * WIDTH_MUL, 
					 HEIGHT));
	}

	private int size;
	private static int WIDTH_MUL = 30;
	private static int HEIGHT = 50;
    }

    public static void main(String[] args){
	TowersOfHanoi t = new TowersOfHanoi();
	if(args.length < 3){
	    System.out.println("java MainPanel startPole endPole numDisk");
	    return;
	}
	int startPole = Integer.parseInt(args[0]);
	int endPole = Integer.parseInt(args[1]);
	int numDisk = Integer.parseInt(args[2]);
	if(erroneousInput(startPole, endPole)){
	    System.out.println("Input is incorrect");
	    return;
	}
	final MainPanel panel = new MainPanel(500);
	panel.initPoles(3);	
	for(int i = numDisk; i > 0; --i){
	    panel.addPeg(startPole - 1, i);
	}
	t.addMoveListener(panel);
	EventQueue.invokeLater(new Runnable(){
		public void run(){
		    JFrame frame = new JFrame();
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(700, 600);
		    frame.add(panel);
		    frame.setVisible(true);
		}
	    });
	t.start(numDisk,startPole, endPole);
    }

    private static boolean erroneousInput(int startDisk, int endDisk){
	return ((startDisk == endDisk) || (startDisk > 3) ||
		(endDisk > 3) || (startDisk < 1) || (endDisk < 1));
    }

}