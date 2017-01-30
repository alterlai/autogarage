package view;

import javax.swing.JPanel;

/**
 * This is the parent class for all the views inside the simulation.
 * @author Jeroen van der Laan.
 * @version 0.1
 *
 */
public abstract class View extends JPanel{
	
	public abstract void updateView();
}
