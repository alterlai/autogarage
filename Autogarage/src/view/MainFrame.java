package view;

import java.awt.*;
import java.net.NetworkInterface;

import javax.swing.*;
import java.util.LinkedList;
import model.Time;

import Autogarage.*;
import controller.SimulatorController;

// TODO: ADD pack();

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private SimulatorController controller;		// The controller for this class.
	
	private LinkedList<View> views;				// List of all view objects.
	
	private JPanel contentPane;					// The outer wrapper. JPanel from the frame.
	private JPanel bottomWrapper;				// Bottom wrapper for input menu
	private JLabel timeLabel;
	
	static final Color FRAME_BG_COLOR = new Color(221, 221, 221);
	
	
	public MainFrame(SimulatorController controller) {
		setTitle("Autogarage applicatie");
        this.controller = controller;	// set the controller
        
        makeViews();		// Create view objects and add to list
		makeFrame();		// Build the frame with all the content.		
	}
	
	
	/**
	 * Creating the frame with all display elements.
	 */
	private void makeFrame()
	{
		// Build the outer wrapper
        contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBackground(FRAME_BG_COLOR);
        
        // Build the title
        JLabel title = new JLabel("Welcome to the parking simulator.");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setFont(new Font("Arial", Font.PLAIN, 16));
        title.setForeground(new Color(43, 169, 237));
        contentPane.add(title, BorderLayout.NORTH);
        
        // Build the tabs.
		makeTabs();
		
		// Build the bottomWrapper (input UI + tickLabel).
		bottomWrapper = new JPanel();
		bottomWrapper.setLayout(new BorderLayout());
		bottomWrapper.setBackground(FRAME_BG_COLOR);
		contentPane.add(bottomWrapper, BorderLayout.SOUTH);
        makeInputUI();
        
        pack();												// Resize window to content.
        setLocationRelativeTo(null);						// Set the position to the center of the screen.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Shutdown when closing window.
        setVisible(true);									// Display the Frame.
	}
	
	
	/**
     * Adds the main tabs to the interface.
     */
    public void makeTabs()
    {	
  		// Adding tab pages to the frame
  		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
  		contentPane.add(tabbedPane, BorderLayout.CENTER);
  		
  		// Adding the panels for the different views
  		tabbedPane.addTab("Simulator", null, views.get(0), null);
  		tabbedPane.addTab("Statistics", null, views.get(1), null);
  		tabbedPane.addTab("Settings", null, views.get(2), null);
  		tabbedPane.addTab("Other", null, new JPanel(), null);
  	}
    
    
    /**
     * This method adds inputs to the right of the simulation.
     */
    public void makeInputUI()
    {
    	// New JPanel for the buttons and input
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new GridBagLayout());
    	inputPanel.setBackground(FRAME_BG_COLOR);
    	GridBagConstraints c = new GridBagConstraints();
    	
    	//Label
    	JLabel lenghtLabel = new JLabel("Amount of minutes: ");
    		c.gridy = 0;
    		c.gridx = 0;
    		c.gridwidth = 2;
    		c.insets = new Insets(2, 2, 2, 2);
    		inputPanel.add(lenghtLabel, c);
    	//length field
    	JTextField durationField = new JTextField(3);
    		c.gridy = 0;
    		c.gridx = 2;
    		c.gridwidth = 2;
    		c.fill = GridBagConstraints.HORIZONTAL;
    		inputPanel.add(durationField, c);
    	// Run button
    	JButton startSimulationButton = new JButton("Run");
    		startSimulationButton.addActionListener(e -> controller.startSimulation(getsimulationLengthField(durationField)));
    		c.gridy = 1;
    		c.gridx = 0;
    		c.gridwidth = 1;
    		inputPanel.add(startSimulationButton, c);
    	// Stop button
    	JButton stopSimulationButton = new JButton("Stop");
	    	c.gridy = 1;
			c.gridx = 1;
			c.gridwidth = 1;
    		inputPanel.add(stopSimulationButton, c);
    		stopSimulationButton.addActionListener(e -> controller.setRunning(false));
    	// Reset button
    	JButton resetSimulationButton = new JButton("Reset");
			c.gridy = 1;
			c.gridx = 2;
			c.gridwidth = 1;
    		inputPanel.add(resetSimulationButton, c);
    		resetSimulationButton.addActionListener(e -> controller.resetSimulation());
    
		bottomWrapper.add(inputPanel, BorderLayout.EAST);
		
        // Time
		JPanel timeWrapper = new JPanel();
		timeWrapper.setBackground(FRAME_BG_COLOR);
		timeWrapper.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		JLabel timeTitle = new JLabel ("Current time");
			c.gridx = 0;
			c.gridy = 0;
			c.gridwidth = 1;
			timeWrapper.add(timeTitle, c);
		timeLabel = new JLabel ("00:00 Monday");
			c.gridx = 0;
			c.gridy = 1;
		timeWrapper.add(timeLabel, c);
        bottomWrapper.add(timeWrapper, BorderLayout.WEST);        
    }
    
    
	/**
	 * This method builds all the views that will be used in the simulation.
	 * It will also add them to a list, to loop over them later.
	 */
	private void makeViews()
	{
		views = new LinkedList<>();
		views.add(new SimulatorView(controller));
		views.add(new StatisticsView(controller));
		views.add(new SettingsView(controller));
	}
	
	
	/**
	 * Loop over every view and let it update.
	 */
	public void updateView()
	{
		Time time = controller.getTime();
		timeLabel.setText(time.toString());
		for(View view : views)
		{
			view.updateView();
		}
	}
	
	public void reset()
	{
		for (View view : views)
		{
			view.updateView();
		}
	}
    
	
    /**
     * Get the value of the field that's requesting the length of the simulation.
     * @return length of time that the simulation will run.
     */
    public int getsimulationLengthField(JTextField durationField)
    {
    	try{
    		return Integer.parseInt(durationField.getText());
    	}
    	catch (NumberFormatException e) {
    		showError("Enter a valid number"); 
    		return 0;
    	}
    }
    
    public void showError(String message)
    {
    	JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
}
