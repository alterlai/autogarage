package Autogarage;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class SimulatorView extends JFrame {
	private SimulatorController controller;
	private CarParkView carParkView;

    //UI stuff
    
    Container contentPane;
    JTextField simulationLengthField;

    public SimulatorView(SimulatorController controller) {        
        this.controller = controller;
    	carParkView = new CarParkView(controller);

        contentPane = getContentPane();
        contentPane.add(carParkView, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
    
    /**
     * This method adds inputs to the right of the simulation.
     */
    public void makeInputUI()
    {
    	JPanel flowInputPanel = new JPanel();
    	contentPane.add(flowInputPanel, BorderLayout.EAST);
    	flowInputPanel.setLayout(new FlowLayout());
    	
    	JPanel inputPanel = new JPanel();
    	flowInputPanel.add(inputPanel);
    	
    
    	simulationLengthField = new JTextField();
    		inputPanel.add(simulationLengthField, BorderLayout.NORTH);
    	JButton startSimulationButton = new JButton("Run");
    		startSimulationButton.addActionListener(e -> controller.startSimulation(10));
    		inputPanel.add(startSimulationButton, BorderLayout.SOUTH);
    		
    	JButton stopSimulationButton = new JButton("Stop");
    		inputPanel.add(stopSimulationButton);
    		
    	JButton resetSimulationButton = new JButton("Reset");
    		inputPanel.add(resetSimulationButton);
    		
    		pack();
    }	
    
    public void updateView() {
        carParkView.updateView();
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
    
        public void updateView() {
            // Create a new car park image if the size has changed.
            if (!size.equals(getSize())) {
                size = getSize();
                carParkImage = createImage(size.width, size.height);
            }
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
