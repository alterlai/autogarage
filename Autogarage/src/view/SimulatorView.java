package view;

import javax.swing.*;

import Autogarage.Car;
import Autogarage.Location;
import Autogarage.Simulator;
import Autogarage.SimulatorController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SimulatorView extends View {
	private SimulatorController controller;
	private CarParkView carParkView;

    //UI stuff
    JPanel contentPane;
    JLabel tickLabel;

    public SimulatorView(SimulatorController controller) {        
        this.controller = controller;
    	carParkView = new CarParkView(controller);

        contentPane = this;
        contentPane.setLayout(new BorderLayout(6, 6));
        contentPane.add(carParkView, BorderLayout.WEST);
        
        // Tick label
        tickLabel = new JLabel("Current tick: 1");
        contentPane.add(tickLabel, BorderLayout.SOUTH);
        makeInputUI();
        setVisible(true);

    }
    
    /**
     * This method adds inputs to the right of the simulation.
     */
    public void makeInputUI()
    {
    	// New JPanel for the buttons and input
    	JPanel inputPanel = new JPanel();
    	inputPanel.setLayout(new GridBagLayout());
    	GridBagConstraints c = new GridBagConstraints();
    	
    	//Label
    	JLabel lenghtLabel = new JLabel("Ammount of ticks: ");
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
    		// TODO add action listener
    	// Reset button
    	JButton resetSimulationButton = new JButton("Reset");
			c.gridy = 1;
			c.gridx = 2;
			c.gridwidth = 1;
    		inputPanel.add(resetSimulationButton, c);
    		resetSimulationButton.addActionListener(e -> controller.resetSimulation());
    
    	contentPane.add(inputPanel, BorderLayout.EAST);
    } 
    
    /**
     * Update the carparkView
     */
    public void updateView() {
        carParkView.updateView();
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
     
	private class CarParkView extends JPanel {
        
        private Dimension size;
        private Image carParkImage;    
        private SimulatorController controller;
        /**
         * Constructor for objects of class CarPark
         */
        public CarParkView(SimulatorController controller) {
            size = new Dimension(0, 0);
            this.controller = controller;
        }
    
        /**
         * Overridden. Tell the GUI manager how big we would like to be.
         */
        public Dimension getPreferredSize() {
            return new Dimension(800, 500);
        }
    
        /**
         * Overriden. The car park view component needs to be redisplayed. Copy the
         * internal image to screen.
         */
        public void paintComponent(Graphics g) {
            if (carParkImage == null) {
                return;
            }
    
            Dimension currentSize = getSize();
            if (size.equals(currentSize)) {
                g.drawImage(carParkImage, 0, 0, null);
            }
            else {
                // Rescale the previous image.
                g.drawImage(carParkImage, 0, 0, currentSize.width, currentSize.height, null);
            }
        }
        
        /**
         * Check the parking spots and update the screen with current information.
         */
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
            tickLabel.setText("Current tick: " + controller.getCurrentTick());
            Graphics graphics = carParkImage.getGraphics();
            for(int floor = 0; floor < controller.getNumberOfFloors(); floor++) {
                for(int row = 0; row < controller.getNumberOfRows(); row++) {
                    for(int place = 0; place < controller.getNumberOfPlaces(); place++) {
                        Location location = new Location(floor, row, place);
                        Car car = controller.getCarAt(location);
                        Color color = car == null ? Color.white : car.getColor();
                        drawPlace(graphics, location, color);
                    }
                }
            }
            repaint();
        }
    
        /**
         * Paint a place on this car park view in a given color.
         */
        private void drawPlace(Graphics graphics, Location location, Color color) {
            graphics.setColor(color);
            graphics.fillRect(
                    location.getFloor() * 260 + (1 + (int)Math.floor(location.getRow() * 0.5)) * 75 + (location.getRow() % 2) * 20,
                    60 + location.getPlace() * 10,
                    20 - 1,
                    10 - 1); // TODO use dynamic size or constants
        }
    }
   
}
