package view;

import java.awt.*;
import javax.swing.*;
import java.util.LinkedList;

import Autogarage.*;

// TODO: ADD pack();

public class MainFrame extends JFrame{
	
	private SimulatorController controller;		// The controller for this class.
	
	//All views
	private SimulatorView simulatorView;	// The simulator View
	private LinkedList<View> views;
	
	private JPanel contentPane;
	
	public MainFrame(SimulatorController controller) {        
        this.controller = controller;	// set the controller
        
		makeFrame();		//Build the frame.
		makeViews();		//Build the views.
		makeTabs();			//Build the tabs.
		
		pack();
        setVisible(true);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(d.width/2 - getWidth()/2, d.height/2 - getHeight()/2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * This method builds all the views that will be used in the simulation.
	 * It will also add them to a list, to loop over them later.
	 */
	private void makeViews()
	{
		views = new LinkedList<>();
		simulatorView = new SimulatorView(controller);
        views.add(simulatorView);
	}
	
	private void makeFrame()
	{
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout(6, 6));
        
	}
	
	/**
	 * Loop over every view and let it update.
	 */
	public void updateView()
	{
		for(View view : views)
		{
			view.updateView();
		}
	}
	
	/**
     * Adds the main tabs to the interface.
     */
    public void makeTabs()
    {	
  		//adding tab pages to the frame
  		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  		contentPane.add(tabbedPane, BorderLayout.NORTH);
  		
  		//adding the panel for running the simulator
  		JPanel simulatorView = new SimulatorView(controller);
  		tabbedPane.addTab("Simulator", null, simulatorView, null);
  		
  		//adding the panel for Car statistics
  		//SimulatorView panel_1 = new SimulatorPanel();
  		//tabbedPane.addTab("Car Statistics", null, panel_1, null);
  		
  		//adding the panel for the Revenue
  		JPanel panel_2 = new JPanel();
  		tabbedPane.addTab("Revenue", null, panel_2, null);
  		
  		//adding the panel for the graph showing when the car park is the busiest
  		JPanel panel_3 = new JPanel();
  		tabbedPane.addTab("Busy Periods", null, panel_3, null);
  		
  		//adding the panel for other/undefined purposes
  		JPanel panel_4 = new JPanel();
  		tabbedPane.addTab("Other", null, panel_4, null);
  	}
}
