package view;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This is the parent class for all the views inside the simulation.
 * @author Jeroen van der Laan.
 * @version 0.1
 *
 */
@SuppressWarnings("serial")
public abstract class View extends JPanel {
	
	/**
	 * show an error message.
	 * @param message
	 */
	public void showError(String message)
    {
    	JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
	
	/**
	 * Abstract method for all view sub-classes.
	 */
	public abstract void updateView();
}
