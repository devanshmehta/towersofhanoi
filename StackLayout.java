import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Component;
import java.awt.Dimension;

public class StackLayout implements LayoutManager{

    public void removeLayoutComponent(Component comp){
    }

    public void addLayoutComponent(String name, Component comp){
    }

    public Dimension minimumLayoutSize(Container parent){
	return parent.getMinimumSize();
    }

    public Dimension preferredLayoutSize(Container parent){
	return parent.getPreferredSize();
    }

    public void layoutContainer(Container parent){
	Dimension prefdim = parent.getPreferredSize();
	int height = prefdim.height;
	for(int i = 0; i < parent.getComponents().length; ++i){
	    Component comp = parent.getComponents()[i];
	    int compWidth = comp.getMaximumSize().width;
	    int compHeight = comp.getMaximumSize().height;
	    height -= compHeight;
	    comp.setBounds((prefdim.width - compWidth) / 2, height, 
			   compWidth, compHeight);
	}
    }    
}